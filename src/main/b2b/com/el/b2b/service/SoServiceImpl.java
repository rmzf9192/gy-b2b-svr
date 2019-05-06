package com.el.b2b.service;

import com.el.b2b.api.SoQuery;
import com.el.b2b.domain.*;
import com.el.b2b.mapper.*;
import com.el.b2b.sys.TableMeta;
import com.el.b2b.sys.service.TableBathService;
import com.el.cfg.security.EdpPrincipalService;
import com.el.core.domain.PagingResult;
import com.el.edi.iam.payload.EdpUser;
import com.el.edp.gen.service.EdpGenManager;
import com.el.edp.util.ResultUtil;
import com.el.edp.util.StringUtils;
import com.el.eds.dev.service.DevRedisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/5/14
 * @Description:
 */
@Slf4j
@Profile("b2b")
@Service
@RequiredArgsConstructor
public class SoServiceImpl implements SoService {

    private final SoMapper soMapper;
    private final SoDMapper soDMapper;

    private final SoDoMapper soDoMapper;//订单收货mapper

    private final TableBathService tableBathService;
    private final EdpGenManager genManager;
    private final PbItemService pbItemService;//产品信息
    private final PbItemSalepriceService pbItemSalepriceService;//产品价格信息
    private final AddrQualfyService addrQualfyService;//客户证照信息
    private final EdpPrincipalService principalService;//当前登录人

    private final CertCheckMapper certCheckMapper;
    private final CustMapper custMapper;

    //清空redis缓存
    private final DevRedisService devRedisService;


    @Override
    public PagingResult<So> page(SoQuery query) {
        int total = soMapper.findCount(query);
        return total > 0 ? PagingResult.of(soMapper.findPage(query), total) : PagingResult.of(null, 0);
    }

    @Override
    public So findById(long id) {
        return soMapper.findById(id);
    }

    @Transactional
    @Override
    public int saveOrUpdate(So so) {
        int result;
        if (StringUtils.isEmpty(so.getId())) {
            result = soMapper.insert(so);
        } else {
            result = soMapper.updateByPrimaryKey(so);
        }

        log.debug("saveOrUpdate---result:{}", result);
        return result;
    }

    @Transactional
    @Override
    public List<SoD> saveSoAndSod(So so, List<SoD> soDList,String ouCode, String custCode,boolean saveFlag){
        int result;
        //不符合修改的数据
        List<SoD> listSod=new ArrayList<>();
        //符合修改的数据
        List<SoD> listFlag=new ArrayList<>();
        if(!StringUtils.isEmpty(so.getId())){
            List<SoD> bySoId = soDMapper.findBySoId(so.getId());
            for (SoD sod : bySoId) {
                if("000".equals(sod.getSodStatus())){
                    listFlag.add(sod);
                }else{
                    if (!"A".equals(sod.getSodStatus())) {
                        listSod.add(sod);
                    }else{
                        listFlag.add(sod);
                    }
                }
            }
        }
        if (StringUtils.isEmpty(so.getId())) {
            result = soMapper.insert(so);
        } else {
            result = soMapper.updateByPrimaryKey(so);
        }

        //保存子表
        for (SoD sod : soDList) {
            sod.setSoId(so.getId());
            sod.setDocNo(so.getDocNo());//保存订单编号
            sod.setDocStatus(so.getDocStatus());//保存订单类型
            sod.setUnassignedQty(sod.getQty());//默认未分配数量 == 需求数量
            sod.setAssignedAmt(BigDecimal.ZERO); //默认为0
            sod.setCreateTime(LocalDateTime.now());//保存时间
            sod.setModifyTime(LocalDateTime.now());//更新时间
            sod.setQty(sod.getQty());
            BigDecimal price = pbItemSalepriceService.getPriceByItemCode(sod.getItemCode(), ouCode, custCode);
            sod.setPrice(price);
            sod.setAmt(price.multiply(sod.getConv()).multiply(new BigDecimal(String.valueOf(sod.getQty()))));//计算金额=主计量的单价*与当前单位的转换系数*数量

            if ("ORDERED".equals(so.getDocStatus())&& saveFlag) {
                sod.setErpStatus("000");    //未提交
            } else {
                sod.setErpStatus(sod.getSodStatus()!=null ? sod.getSodStatus(): "A");//默认A
            }
            sod.setOuCode(ouCode);
           /* //新增记录
            if(!saveFlag){
                //被删除的才保存
                for(SoD sod1:listSod){
                    if(sod.getItemCode().equals(sod1.getItemCode()) && !"S".equals(sod1.getSodStatus())) {
                        soDMapper.insert(sod);
                    }
                }
            }else{*/
           if(sod.getId()!=null){
               for(SoD sod1:listFlag){
                   if(sod.getId().equals(sod1.getId())){
                       //sod.setErpStatus(sod1.getSodStatus());
                       soDMapper.updateByPrimaryKey(sod);
                   }
               }
           }else {
               soDMapper.insert(sod);
           }
            //}
        }
       if(listSod!=null && !listSod.isEmpty()){
          return listSod;
       }
        log.debug("saveOrUpdate---result:{}", result);
        return null;
    }

    @Transactional
    @Override
    public int delete(long id) {
        So so = soMapper.findById(id);
        return soMapper.updateByPrimaryKey(so);
    }

    @Transactional
    @Override
    public int deleteOrdered(long id) {
        return soMapper.deleteBySoId(id);
    }

    @Override
    public int findCount(SoQuery query) {
        return soMapper.findCount(query);
    }

    @Override
    public List<So> findPage(SoQuery query) {
        return soMapper.findPage(query);
    }

    /**
     * 证照判断：判断订单及明细是否合法
     * 合法 返回空字符串
     * 不合法 则返回错误信息
     *
     * @param addrNo
     * @param soDList
     * @return
     */
    private String checkAddrQualfy(String ouCode, String addrNo, List<SoD> soDList) {
        CustDomain cust = custMapper.findByCustCode(addrNo);
        if(cust!=null){
            List<CertCheckDomain> checkList = certCheckMapper.checkEvFlagByOu(ouCode);
            List<AddrQualfyDomain> ouQualfyList = certCheckMapper.addrQualify(String.valueOf(Integer.parseInt(ouCode)));
            List<AddrQualfyDomain> custQualfyList = addrQualfyService.findByAddrNo(addrNo);
            StringBuilder sbError = new StringBuilder();
            if("Y".equals(cust.getUnitFlag())){//公司是零售个人的资质
                if(ouQualfyList!=null && ouQualfyList.size()>0){
                    if(checkList!=null && checkList.size()>0){
                        int ev04Flag = 0,okOuFlag = 0;
                        for(CertCheckDomain check: checkList){
                            if("Y".equals(check.getEv04())){
                                ev04Flag += 1;
//                                int equalOuFlag =0,okOuFlag = 0;
                                for (AddrQualfyDomain aq : ouQualfyList) {
                                    if(check.getE8zzlxa().equals(aq.getQualifyType())){
//                                        equalOuFlag += 1;
                                        if(aq.getValidTo()!=null && aq.getValidTo().isAfter(LocalDateTime.now())){
                                            okOuFlag += 1;
                                        }
                                    }
                                }
//                                if(equalOuFlag==0){
////                                    return "请维护公司证照信息!";
//                                }else{
//                                    if(okOuFlag==0){
//                                        return "公司证照停用或无效!";
//                                    }
//                                }
                            }
                        }
                        if(ev04Flag==0){
                            return "请维护公司零售证照校验!";
                        }
                        if(okOuFlag==0){
                            return "公司证照停用或无效!";
                        }
                    }
                }else{
                    return "请维护公司证照信息。";
                }

            }else{//公司是批发销售的资质
                if(checkList!=null && checkList.size()>0){
                    if(ouQualfyList!=null && ouQualfyList.size()>0){
                        int ev03Flag = 0,okOuFlag = 0;
                        for(CertCheckDomain check: checkList){
                            if("Y".equals(check.getEv03())){
                                ev03Flag += 1;
//                                int equalOuFlag =0,okOuFlag = 0;
                                for (AddrQualfyDomain aq : ouQualfyList) {
                                    if(check.getE8zzlxa().equals(aq.getQualifyType())){
//                                        equalOuFlag += 1;
                                        if(aq.getValidTo()!=null && aq.getValidTo().isAfter(LocalDateTime.now())){
                                            okOuFlag += 1;
                                        }
                                    }
                                }
//                                if(equalOuFlag==0){
////                                    return "请维护公司证照信息!";
//                                }else{
//                                    if(okOuFlag==0){
//                                        return "公司证照停用或无效!";
//                                    }
//                                }

                            }
                        }
                        if(ev03Flag==0){
                            return "请维护公司销售证照校验!";
                        }else{
                            if(okOuFlag==0){
                                return "公司证照停用或无效!";
                            }
                            //判断客户证照信息是否有效
                            if(custQualfyList!=null && custQualfyList.size()>0){
                                int ev02Flag = 0,okFlag = 0;
                                for(CertCheckDomain check: checkList){
                                    if("Y".equals(check.getEv02())){
                                        ev02Flag += 1;
//                                        int equalFlag =0,okFlag = 0;
                                        for (AddrQualfyDomain aq : custQualfyList) {
                                            if(check.getE8zzlxa().equals(aq.getQualifyType())){
//                                                equalFlag += 1;
                                                if(aq.getValidTo()!=null && aq.getValidTo().isAfter(LocalDateTime.now())){
                                                    okFlag += 1;
                                                }
                                            }
                                        }
//                                        if(equalFlag==0){
////                                            return "请维护客户证照信息!";
//                                        }else{
//                                            if(okFlag==0){
//                                                return "客户证照停用或无效!";
//                                            }
//                                        }

                                    }
                                }
                                if(ev02Flag == 0){
                                    return "请维护客户证照校验";
                                }
                                if(okFlag==0){
                                    return "客户证照停用或无效!";
                                }
                            }else{
                                return "请维护客户证照信息!";
                            }
                        }
                    }else{
                        return "请维护公司证照信息。";
                    }

                }
            }
            //开始校验商品
            List<CertCheckDomain> ev03YList = new ArrayList<>();
            if(ouQualfyList!=null && ouQualfyList.size()>0) {
                for (CertCheckDomain check : checkList) {
                    if ("Y".equals(check.getEv03())) {
                        ev03YList.add(check);
                    }
                }
            }
            List<CertCheckDomain> ev02YList = new ArrayList<>();
            if(custQualfyList!=null && custQualfyList.size()>0) {
                for (CertCheckDomain check : checkList) {
                    if ("Y".equals(check.getEv02())) {
                        ev02YList.add(check);
                    }
                }
            }
            for (SoD sod : soDList) {
//                PbItem pbItem = pbItemService.findByItemCodeAndOuCode(sod.getItemCode(), ouCode);//获取产品信息
                PbItem pbItem = certCheckMapper.itemGrade(sod.getItemCode(), ouCode);//获取产品信息
                String gradeVal = pbItem.getGrade(), typeCode = StringUtils.trimAll(pbItem.getP1());
                if(gradeVal!=null && gradeVal.trim().length()>0){//判断风险等级
                    //判断经营范围
                    if("001".equals(gradeVal)){
                        String error = checkSaleScope001(ouQualfyList,ev03YList,
                            custQualfyList, ev02YList, typeCode);
                        if(error.length()>0){
                            sbError.append(sod.getItemCode()+":"+error);
                        }
                    }else if("002".equals(gradeVal)){
                        String error = checkSaleScope002(ouQualfyList,ev03YList,
                            custQualfyList, ev02YList, typeCode);
                        if(error.length()>0){
                            sbError.append(sod.getItemCode()+":"+error);
                        }
                    }else if("003".equals(gradeVal)){
                        String error = checkSaleScope003(ouQualfyList,ev03YList,
                            custQualfyList, ev02YList, typeCode);
                        if(error.length()>0){
                            sbError.append(sod.getItemCode()+":"+error);
                        }
                    }
                }
            }
            return sbError.length() > 0 ? sbError.toString() : "";



        /*LocalDateTime currTime = LocalDateTime.now();
        StringBuilder sbAQLV1 = new StringBuilder(), sbAQLV2 = new StringBuilder(),
            sbAQLV3 = new StringBuilder(), sbError = new StringBuilder();
        List<AddrQualfyDomain> aQList = addrQualfyService.findByAddrNo(addrNo);
        List<AddrQualfyDomain> aQNewList = new ArrayList<>();
        if (null != aQList) {
            for (AddrQualfyDomain aQ : aQList) {
                if (null == aQ.getValidFrom() || null == aQ.getValidTo()
                    || currTime.isBefore(aQ.getValidFrom()) || currTime.isAfter(aQ.getValidTo())
                ) {
                    //Do nothing
                } else {
                    sbAQLV1.append(aQ.getValidLv1()).append(";");
                    sbAQLV2.append(aQ.getValidLv2()).append(";");
                    sbAQLV3.append(aQ.getValidLv3()).append(";");
                    aQNewList.add(aQ);
                }
            }
            if (aQNewList.size() <= 0) {
                return "无有效产品经营证照，请联系管理员检查客户证照资料：证照起始结束日期需有效且均不可为空。";
            }
        } else {
            return "无产品经营证照。";
        }

        Map<String, String> aQMap = new HashMap<>();
        aQMap.put("001", sbAQLV1.toString());
        aQMap.put("002", sbAQLV2.toString());
        aQMap.put("003", sbAQLV3.toString());

        log.debug("sbAQLV1:{},sbAQLV2:{},sbAQLV3:{}", sbAQLV1, sbAQLV2, sbAQLV3);

        for (SoD sod : soDList) {
            PbItem pbItem = pbItemService.findByItemCodeAndOuCode(sod.getItemCode(), ouCode);//获取产品信息
            String gradeVal = pbItem.getGrade(), typeCode = StringUtils.trimAll(pbItem.getP1());
            log.debug("itemCode:{},gradeVal:{},typeCode:{}", sod.getItemCode(), gradeVal, typeCode);
            //判断商品性质VE84211W. E8SPXZ=03或04时（非医疗器械或服务类），不校验商品的经营范围（公司与客户的都是）
            if (("03".equals(pbItem.getSpe8spxz())) || ("04".equals(pbItem.getSpe8spxz()))) {
                continue;
            }
            if ((gradeVal.equals("001") && aQMap.get(gradeVal).contains("9999")) ||
                (StringUtils.notEmpty(typeCode) && aQMap.get(gradeVal).contains(typeCode))) {
                continue;
            } else {
                sbError.append(sod.getItemName()).append(";");
            }
        }
        return sbError.length() > 0 ? sbError.insert(0, "您选择了暂未在经营范围的产品：").toString() : "";*/
        }else{
            return "客户查找失败";
        }

    }

    private String checkSaleScope001(List<AddrQualfyDomain> ouQualfyList, List<CertCheckDomain> ev03List,
                                     List<AddrQualfyDomain> qualfyList, List<CertCheckDomain> ev02List,
                                     String typeCode){
        String error = checkItemScope001(ev03List, ouQualfyList, typeCode, "公司");
        if(error.length()>0){
            return error;
        }else{
            return checkItemScope001(ev02List, qualfyList, typeCode, "客户");
        }
    }
    private String checkItemScope001(List<CertCheckDomain> checkYList , List<AddrQualfyDomain> qualfyList,
                                     String typeCode, String commonEror){
        if(checkYList!=null && checkYList.size()>0){
            int okFlag = 0,existFlag = 0;
            if(qualfyList!=null && qualfyList.size()>0){
                for (CertCheckDomain check : checkYList) {
                    for (AddrQualfyDomain qualify : qualfyList) {
                        if(check.getE8zzlxa().equals(qualify.getQualifyType())){
                            if(qualify.getValidLv1()!=null && qualify.getValidLv1().trim().length()>0){
                                existFlag += 1;
                                if(qualify.getValidLv1().contains(typeCode)){
                                    okFlag += 1;
                                }
                            }
                        }
                    }
                }
                if(existFlag==0){
                    return "商品超出"+commonEror+"经营范围;";
                }else{
                    if(okFlag==0){
                        return commonEror+"证照经营范围中无此产品分类;";
                    }
                }
            }
        }
        return "";
    }
    private String checkSaleScope002(List<AddrQualfyDomain> ouQualfyList, List<CertCheckDomain> ev03List,
                                     List<AddrQualfyDomain> qualfyList, List<CertCheckDomain> ev02List,
                                     String typeCode){
        String error = checkItemScope002(ev03List, ouQualfyList, typeCode, "公司");
        if(error.length()>0){
            return error;
        }else{
            return checkItemScope002(ev02List, qualfyList, typeCode, "客户");
        }
    }
    private String checkItemScope002(List<CertCheckDomain> checkYList , List<AddrQualfyDomain> qualfyList,
                                     String typeCode, String commonEror){
        if(checkYList!=null && checkYList.size()>0){
            int okFlag = 0,existFlag = 0;
            if(qualfyList!=null && qualfyList.size()>0){
                for (CertCheckDomain check : checkYList) {
                    for (AddrQualfyDomain qualify : qualfyList) {
                        if(check.getE8zzlxa().equals(qualify.getQualifyType())){
                            if(qualify.getValidLv2()!=null && qualify.getValidLv2().trim().length()>0){
                                existFlag += 1;
                                if(qualify.getValidLv2().contains(typeCode)){
                                    okFlag += 1;
                                }
                            }
                        }
                    }
                }
                if(existFlag==0){
                    return "商品超出"+commonEror+"经营范围;";
                }else{
                    if(okFlag==0){
                        return commonEror+"证照经营范围中无此产品分类;";
                    }
                }
            }
        }
        return "";
    }
    private String checkSaleScope003(List<AddrQualfyDomain> ouQualfyList, List<CertCheckDomain> ev03List,
                                     List<AddrQualfyDomain> qualfyList, List<CertCheckDomain> ev02List,
                                     String typeCode){
        String error = checkItemScope003(ev03List, ouQualfyList, typeCode, "公司");
        if(error.length()>0){
            return error;
        }else{
            return checkItemScope003(ev02List, qualfyList, typeCode, "客户");
        }
    }
    private String checkItemScope003(List<CertCheckDomain> checkYList , List<AddrQualfyDomain> qualfyList,
                                     String typeCode, String commonEror){
        if(checkYList!=null && checkYList.size()>0){
            int okFlag = 0,existFlag = 0;
            if(qualfyList!=null && qualfyList.size()>0){
                for (CertCheckDomain check : checkYList) {
                    for (AddrQualfyDomain qualify : qualfyList) {
                        if(check.getE8zzlxa().equals(qualify.getQualifyType())){
                            if(qualify.getValidLv3()!=null && qualify.getValidLv3().trim().length()>0){
                                existFlag += 1;
                                if(qualify.getValidLv3().contains(typeCode)){
                                    okFlag += 1;
                                }
                            }
                        }
                    }
                }
                if(existFlag==0){
                    return "商品超出"+commonEror+"经营范围;";
                }else{
                    if(okFlag==0){
                        return commonEror+"证照经营范围中无此产品分类;";
                    }
                }
            }
        }
        return "";
    }

   // @Transactional
    @Override
    public ResultUtil saveForm(SoObject soObject) {
        boolean saveFlag = false;
        EdpUser user = principalService.user();//获取当前登陆用户
        String ouCode = user.getEntCode();//获取当前用户的公司id
        String custCode = user.getEmpCode();//获取当前用户的客户编码


        So so = soObject.getSo();//获取订单表头
        so.setOuCode(ouCode);
        so.setTransferFlag("0");//默认0 未传送
        so.setCustCode(user.getEmpCode());
        so.setModifyTime(LocalDateTime.now());//更新时间

        ///***************************商品经营范围判断**开始***************************/
        List<SoD> soDList = soObject.getSodList();
        //List<SoD> soDList =soDMapper.findBySoId(so.getId());
        /*获取公司的证照信息*/
//        String ouCodeCheckResult = checkAddrQualfy(ouCode, soDList, ouCode);
//        if (StringUtils.notEmpty(ouCodeCheckResult)) {
//            return ResultUtil.fail("订单公司经营范围验证不通过，原因: " + ouCodeCheckResult);
//        }
        String custCodeCheckResult = checkAddrQualfy(ouCode, custCode, soDList);
        if (StringUtils.notEmpty(custCodeCheckResult)) {
            return ResultUtil.fail(custCodeCheckResult);
        }
        ///***************************商品经营范围判断**结束***************************/
        //被删除的数据
        //List<SoD> listSod=new ArrayList<>();
        //存储是否有没有被删除的数据
        List<SoD> listFlag=new ArrayList<>();
        if (StringUtils.isEmpty(so.getDocNo())) {
            /*新增记录，发放申请单号*/
            so.setDocNo(genManager.nextSeq("B2B_SONO", so));
            so.setDocTime(LocalDateTime.now());//下单时间
            saveFlag = true;
            this.saveSoAndSod(so, soDList, ouCode, custCode, saveFlag);
        } else {
            /*如果编辑记录，则对明细数据进行全删全插*/
            //判断状态是否允许被编辑
          /*  List<SoD> bySoId = soDMapper.findBySoId(so.getId());

            for(SoD sod:bySoId){
                if(!"S".equals(sod.getSodStatus())){
                    soDMapper.deleteBySoId(sod.getId());
                    listSod.add(sod);
                }else{
                    listFlag.add(sod);
                }
            }*/
            List<SoD> bySoId = soDMapper.findBySoId(so.getId());
            String strCode="";
            for(val sod:bySoId){
                strCode=strCode+sod.getOuCode()+","+sod.getDocNo()+","+sod.getItemCode()+":";
            }
            strCode= strCode.substring(0, strCode.lastIndexOf(":"));

            ResultUtil sync = this.sync(strCode);
            if(sync.isSuccess()){
                so.setModifyUserId(user.getId());
                listFlag = this.saveSoAndSod(so, soDList,ouCode, custCode, saveFlag);
            }else{
                return sync;
            }
        }

       /* //保存表头
        this.saveOrUpdate(so);
        //保存子表
        for (SoD sod : soDList) {

            sod.setSoId(so.getId());
            sod.setDocNo(so.getDocNo());//保存订单编号
            sod.setDocStatus(so.getDocStatus());//保存订单类型
            sod.setUnassignedQty(sod.getQty());//默认未分配数量 == 需求数量
            sod.setAssignedAmt(BigDecimal.ZERO); //默认为0
            sod.setCreateTime(LocalDateTime.now());//保存时间
            sod.setModifyTime(LocalDateTime.now());//更新时间

            BigDecimal price = pbItemSalepriceService.getPriceByItemCode(sod.getItemCode(), ouCode, custCode);
            sod.setPrice(price);
            sod.setAmt(price.multiply(sod.getConv()).multiply(new BigDecimal(String.valueOf(sod.getQty()))));//计算金额=主计量的单价*与当前单位的转换系数*数量

            if ("ORDERED".equals(so.getDocStatus())&& saveFlag) {
                sod.setErpStatus("000");    //未提交
            } else {
                sod.setErpStatus("A");//默认A
            }
            sod.setOuCode(ouCode);
            //新增记录
            if(!saveFlag){
                //被删除的才保存
                for(SoD sod1:listSod){
                    if(sod.getItemCode().equals(sod1.getItemCode()) && !"S".equals(sod1.getSodStatus())) {
                        soDMapper.insert(sod);
                    }
                }
            }else{
                soDMapper.insert(sod);
            }
        }*/


        if ("SUBMITTED".equals(so.getDocStatus())) {
            String svrURL = "http://localhost:9080/gy-sync";
            String API_SO_SEND = "/api/erp/sync/so/";
            String reqURL = svrURL + API_SO_SEND + so.getId();
            new AsynExecutorBlh() {
                @Override
                public void execute() {
                    try {
                        Thread.sleep(1000);
                        RestTemplate restTemplate = new RestTemplate();
                        restTemplate.postForObject(reqURL, null, Object.class);
                        so.setTransferFlag("1");//已传送
                        so.setModifyTime(LocalDateTime.now());//更新时间
                        soMapper.updateByPrimaryKey(so);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.run();
        }
        if(listFlag!=null && !listFlag.isEmpty()){
            String itemCodes="";
            for(SoD sod:listFlag){
                if("S".equals(sod.getSodStatus())){
                    itemCodes=itemCodes+sod.getItemCode()+"  ";
                }
            }
            return ResultUtil.fail("产品编号："+itemCodes+"不允许修改");
        }
        if (saveFlag) {
            return ResultUtil.success("订单号为：" + so.getDocNo());
        } else {
            return ResultUtil.success("保存成功");
        }

    }
   //循环遍历ids,并判断数据的状态是否满足
    private List<SoD> isStatus(Long[] ids){
        List<SoD> soList=new ArrayList<>();
        for(val id:ids){
            SoD byId = soDMapper.findById(id);
            if(!("A".equals(byId.getErpStatus())||("000").equals(byId.getSodStatus()))){
                return  soList;
            }
            soList.add(byId);
        }
        return soList;
    }
    public ResultUtil sync(String strCode){
        String svrURL = "http://localhost:9080/gy-sync";
        String API_SO_SEND = "/api/b2b/sync/syncStatus/";
        //请求同步
        String reqURL = svrURL + API_SO_SEND+strCode;
        // 发送请求
        RestTemplate template = new RestTemplate();
        ResponseEntity responseEntity = template.postForEntity(reqURL, null, Object.class);
        if(HttpStatus.OK == responseEntity.getStatusCode()){
            return ResultUtil.success("同步成功");
        }else{
            return ResultUtil.fail("同步失败");
        }
    }
  //  @Transactional
    @Override
    public ResultUtil delByIds(Long[] ids,boolean flag) {
       // val soList=new ArrayList<>();
        List<SoD> status = isStatus(ids);

        if(ids.length!=status.size()){
             return ResultUtil.fail("所选数据不符合要求");
         }else{
            for(val sod:status){
                if(!("A".equals(sod.getErpStatus())||("000").equals(sod.getSodStatus()))){
                    return ResultUtil.fail("所选数据不符合要求");
                }
            }
        }
        //被删除的数据在B2B中是A,则调用同步项目
        String svrURL = "http://localhost:9080/gy-sync";
        String API_SO_SEND = "/api/b2b/sync/syncStatus/";
        String API_SO_DELETE="/api/b2b/sync/delete/";
        String API_SO_ISSYNC="/api/b2b/sync/isSyncErp/";
        //请求同步
        String reqURL = svrURL + API_SO_SEND;
       // List<SoD> secondList = isStatus(ids);
        //将删除的数据拼接为字符串，传递到同步项目中
        String strCode="";
        for(val sod:status){
            strCode=strCode+sod.getOuCode()+","+sod.getDocNo()+","+sod.getItemCode()+":";
        }
        strCode= strCode.substring(0, strCode.lastIndexOf(":"));

        //判断ERP存在对应数据
        String isSyncErp=svrURL +API_SO_ISSYNC+strCode;
        // 发送请求
        RestTemplate template = new RestTemplate();
        //1.首先根据ids判断ERP中是否存在对应的数据，如果存在则同步，否则直接删除数据
        //网上订单在提交时，已经将数据同步到erp中
       // ResponseEntity<Object> isSycnErpResponse = template.postForEntity(isSyncErp, null, Object.class);
        //if(HttpStatus.OK == isSycnErpResponse.getStatusCode()){
            //2.ERP存在对应的数据，则同步数据，并判断数据的状态
        ResponseEntity responseEntity = template.postForEntity(reqURL+strCode, null, Object.class);

        if(HttpStatus.OK == responseEntity.getStatusCode()){
            long soId=0;
               // devRedisService.flushAll();
                List<SoD> listSod=new ArrayList<>();
                String reqStr="";
                for(Long id :ids){
                    SoD sod = soDMapper.findById(id);
                    if("A".equals(sod.getErpStatus())||("000").equals(sod.getSodStatus())){
                        listSod.add(sod);
                    }
                    soId = sod.getSoId();
                }

                //if(!flag){
                    if(status.size()==listSod.size()){
                        for(val sod:listSod){
                            reqStr=reqStr+sod.getOuCode()+","+sod.getDocNo()+","+sod.getItemCode()+":";
                        }
                        reqStr= reqStr.substring(0, reqStr.lastIndexOf(":"));
                        String reqDeleteUrl=svrURL +API_SO_DELETE+reqStr;
                        ResponseEntity<Object> objectResponseEntity = template.postForEntity(reqDeleteUrl, null, Object.class);

                        if(HttpStatus.OK == objectResponseEntity.getStatusCode()){
                            deleteIds(ids);
                            List<SoD> sodList = soDMapper.findBySoId(soId);
                            if(sodList.size()==0){
                                soMapper.deleteByPrimaryKey(soId);
                            }
                        }else{
                            return ResultUtil.fail("JDE数据删除失败");
                        }
                    }else{
                        return ResultUtil.fail("同步后状态不允许删除");
                    }
               /* }else{
                    if(status.size()==listSod.size()){
                        return ResultUtil.success("数据可以修改");
                    }else {
                        return ResultUtil.fail("同步后状态数据不可以修改");
                    }
                }*/

            }else{
                return ResultUtil.fail("数据同步失败");
            }
        return ResultUtil.success("操作成功");
    }

    @Transactional
    @Override
    public int deleteIds(Long[] ids) {
       return StringUtils.isEmpty(ids) ? 0 : tableBathService.deleteBath(TableMeta.B2B_SO_D, Arrays.asList(ids));
    }

    @Override
    public PagingResult<SoExport> exportDomain(SoQuery query) {
        EdpUser user = principalService.user();//获取当前登陆用户
        query.setOuCode(user.getEntCode());
        query.setCustCode(user.getEmpCode());
        /*************↓↓↓此处特殊处理↓↓↓************/  //DONE 对应UDC中 C,S 两状态
        if ("DONE".equals(query.getErpStatus())) {
            query.setErpStatus("C' OR SOD.ERP_STATUS = 'S");
        }
        int total = soMapper.exportDomainCount(query);
        return total > 0 ? PagingResult.of(soMapper.exportDomain(query), total) : PagingResult.of(null, 0);
    }
    @Override
   public  PagingResult<SoDelivered> deliveredDomain(SoQuery query){
        EdpUser user = principalService.user();//获取当前登陆用户
        query.setOuCode(user.getEntCode());
        query.setCustCode(user.getEmpCode());
        /*************↓↓↓此处特殊处理↓↓↓************/  //DONE 对应UDC中 C,S 两状态
       /* if ("DONE".equals(query.getErpStatus())) {
            query.setErpStatus("C' OR SOD.ERP_STATUS = 'S");
        }*/
        //List<SoDelivered> lists = soMapper.deliveredDomain(query);

        int total=soMapper.deliveredDomainCount(query);
        return total > 0 ? PagingResult.of(soMapper.deliveredDomain(query), total) : PagingResult.of(null, 0);
    }



    /*************↓↓↓订单收货↓↓↓************/
    @Override
    public List<SoDo> findSoDoPage(SoQuery query) {
        return soDoMapper.findPage(query);
    }

    @Override
    public int findSoDoCount(SoQuery query) {
        return soDoMapper.findCount(query);
    }

    @Override
    public List<SoDo> findSoDoById(String id, String shipStatus) {
        return soDoMapper.findSoDoById(id, shipStatus);
    }

    @Transactional
    @Override
    public int updateShipStatus(String[] ids) {
        return soDoMapper.updateShipStatus(Arrays.asList(ids));
    }

    @Override
    public int findUnReceivedSoCount(SoQuery query) {
        return soDoMapper.findUnReceivedSoCount(query);
    }
}

