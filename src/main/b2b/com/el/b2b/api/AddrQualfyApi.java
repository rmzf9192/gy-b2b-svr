package com.el.b2b.api;

import com.el.b2b.domain.AddrQualfyDomain;
import com.el.b2b.service.AddrQualfyService;
import com.el.edp.util.ResultUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 客户证照信息相关业务
 * Created by jerry.feng
 * on 2018/5/14.
 */
@Slf4j
@Profile("b2b")
@RestController
@RequestMapping("api/addrQualfy")
@AllArgsConstructor
public class AddrQualfyApi {

    private final AddrQualfyService addrQualfyService;

    /**
     * 根据条件查询客户证照信息
     *
     * @param query 查询条件
     * @return (返回PagingResult结果)
     */
    @GetMapping("/page")
    public ResultUtil page(AddrQualfyQuery query) {
        log.debug("[ADDRQUALFY-PAGE] page custQuery={}", query);
        return ResultUtil.success(addrQualfyService.findPage(query));
    }

    /**
     * 全部客户证照信息查询
     *
     * @return 执行结果
     */
    @GetMapping("/findAll")
    public List<AddrQualfyDomain> findAll() {
        log.debug("[ADDRQUALFY-FINDALL]");
        return addrQualfyService.findAll();

    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return 查询数据
     */
    @GetMapping("/findById")
    public AddrQualfyDomain findById(long id) {
        log.debug("[ADDRQUALFY-FINDBYID]  id={}", id);
        return addrQualfyService.findById(id);
    }

    /**
     * 根据addrNo查询
     *
     * @param addrNo 地址号
     * @return 查询数据
     */
    @GetMapping("/findByAddrNo")
    public List<AddrQualfyDomain> findByAddrNo(String addrNo) {
        log.debug("[ADDRQUALFY-findByAddrNo]  addrNo={}", addrNo);
        return addrQualfyService.findByAddrNo(addrNo);
    }
}
