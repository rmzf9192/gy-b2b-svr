package com.el.b2b.service;

import com.el.b2b.api.TsoQuery;
import com.el.b2b.domain.TsoCodeName;
import com.el.b2b.domain.TsoDomain;
import com.el.b2b.domain.TsoObject;
import com.el.b2b.domain.TsodDomain;
import com.el.b2b.mapper.TsoMapper;
import com.el.b2b.mapper.TsodMapper;
import com.el.b2b.sys.TableMeta;
import com.el.b2b.sys.service.TableBathService;
import com.el.cfg.security.EdpPrincipalService;
import com.el.core.domain.PagingResult;
import com.el.edi.iam.payload.EdpUser;
import com.el.edp.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

/**
 * 订单模板相关业务
 * Created by jerry.feng
 * on 2018/5/14.
 */
@Slf4j
@Profile("b2b")
@Service
@RequiredArgsConstructor
public class TsoServiceImpl implements TsoService {

    private final TsoMapper tsoMapper;
    private final TsodMapper tsodMapper;
    private final TableBathService tableBathService;
    private final EdpPrincipalService principalService;//当前登录人
    private final OuService ouService;//公司
    private final UomService uomService;

    @Override
    public PagingResult<TsoDomain> findPage(TsoQuery query) {
        int total = tsoMapper.findCount(query);
        if(total > 0){
            List<TsoDomain> items = uomService.queryByItemCode(tsoMapper.findPage(query));
            if(items!=null && items.size()>0){
                for(TsoDomain t: items){
                    t.setBasePrice(t.getPrice());
                    t.setPrice(t.getPrice().multiply(t.getConv()!=null?t.getConv(): BigDecimal.ONE));
                }
            }
            return PagingResult.of(items, total);
        }else{
            return PagingResult.of(null, 0);
        }
    }

    @Override
    @Transactional
    public int saveOrUpdate(TsoObject domain) {
        int result = 0;
        int resultD = 0;
        EdpUser user = principalService.user();//获取当前登陆用户
        String ouCode = user.getEntCode();//获取当前用户的公司编码
        String custCode = user.getEmpCode();//获取当前用户的客户编码
        for (TsodDomain tsodDomain : domain.getTsodDomain()) {
            if (StringUtils.notEmpty(tsodDomain.getDocNo())) {
                TsoQuery query = new TsoQuery();
                String docNo = String.valueOf(tsodDomain.getDocNo());
                query.setDocNo(docNo);
                query.setCustCode(custCode);
                query.setOuCode(ouCode);
                int number = tsoMapper.findCount(query);//查询tso表中有无这个模板编号
                if (number > 0) {//模板已存在，无需在tso表中添加模板，直接在tsod中根据拿到的模板Id在tsod表中添加(tsoId)，添加商品
                    TsodDomain tsodDomain1 = new TsodDomain();
                    //根据模板编码查询模板id
                    Long id = tsoMapper.findByDocno(tsodDomain.getDocNo(), ouCode, custCode);
                    tsodDomain1.setTsoId(id);
                    tsodDomain1.setItemId(tsodDomain.getItemId());
                    tsodDomain1.setItemCode(tsodDomain.getItemCode());
                    tsodDomain1.setUom(tsodDomain.getUom());
                    resultD = tsodMapper.insert(tsodDomain1);
                } else {
                    //如果模板不存在，先在tso新增模板，然后再tsod插入
                    TsoDomain soDomain = new TsoDomain();
                    soDomain.setDocNo(tsodDomain.getDocNo());
                    soDomain.setOuCode(ouCode);
                    soDomain.setCustCode(custCode);
                    result = tsoMapper.insert(soDomain);
                    if (result > 0) {//在模板中新增模板成功后在tsod新增商品信息
                        Long id1 = tsoMapper.findByDocno(tsodDomain.getDocNo(), ouCode, custCode);
                        TsodDomain tsodDomain2 = new TsodDomain();
                        tsodDomain2.setTsoId(id1);//tsoId模板编号
                        tsodDomain2.setItemId(tsodDomain.getItemId());//商品itemid
                        tsodDomain2.setItemCode(tsodDomain.getItemCode());//商品itemCode
                        tsodDomain2.setUom(tsodDomain.getUom());
                        resultD = tsodMapper.insert(tsodDomain2);
                    }
                }
            }
        }
        return resultD;
    }

    @Override
    public int delTsodByIds(Long[] ids) {
        return StringUtils.isEmpty(ids) ? 0 : tableBathService.deleteBath(TableMeta.B2B_TSO_D, Arrays.asList(ids));
    }

    @Transactional
    @Override
    public int removeTsodByItemCode(TsodDomain domain) {
        Long tsoId = domain.getTsoId();//模板id
        String itemCode = domain.getItemCode();//商品itemCode
        int result = 0;
        return result = tsoMapper.removeTsodByItemCode(tsoId, itemCode);
    }

    @Override
    public List<TsoCodeName> getTsoList(TsoQuery tsoQuery) {
        return tsoMapper.getTsoList(tsoQuery);
    }
}
