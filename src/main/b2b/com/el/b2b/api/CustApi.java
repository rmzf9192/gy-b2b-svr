package com.el.b2b.api;

import com.el.b2b.domain.CustDomain;
import com.el.b2b.service.CustService;
import com.el.edp.util.ResultUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by jerry.feng
 * on 2018/5/11.
 */
@Slf4j
@Profile("b2b")
@RestController
@RequestMapping("api/cust")
@AllArgsConstructor
public class CustApi {
    private final CustService custService;

    /**
     * 根据条件查询客户信息
     *
     * @param custQuery 查询条件
     * @return (返回PagingResult结果)
     */
    @GetMapping("/page")
    public ResultUtil page(CustQuery custQuery) {
        log.debug("[CUST-PAGE] page custQuery={}", custQuery);
        return ResultUtil.success(custService.findPage(custQuery));
    }

    /**
     * 全部客户信息查询
     *
     * @return 执行结果
     */
    @GetMapping("/findAll")
    public List<CustDomain> findAll() {
        log.debug("[CUST-FINDALL]");
        return custService.findAll();
    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return 查询数据
     */
    @GetMapping("/findById")
    public CustDomain findById(long id) {
        log.debug("[OU-FINDBYID]  id={}", id);
        return custService.findById(id);
    }

}
