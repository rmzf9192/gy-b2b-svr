package com.el.b2b.api;

import com.el.b2b.domain.OuDomain;
import com.el.b2b.service.OuService;
import com.el.edp.util.ResultUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 公司信息相关业务
 * Created by jerry.feng
 * on 2018/5/10.
 */
@Slf4j
@Profile("b2b")
@RestController
@RequestMapping("api/ou")
@AllArgsConstructor
public class OuApi {
    private final OuService ouService;

    /**
     * 根据条件查询公司信息
     *
     * @param ouQuery 查询条件
     * @return (返回PagingResult结果)
     */
    @GetMapping("/page")
    public ResultUtil page(OuQuery ouQuery) {
        log.debug("[OU-PAGE] page ouQuery={}", ouQuery);
        return ResultUtil.success(ouService.findPage(ouQuery));
    }

    /**
     * 全部公司信息查询
     *
     * @return 执行结果
     */
    @GetMapping("/findAll")
    public List<OuDomain> findAll() {
        log.debug("[OU-FINDALL]");
        return ouService.findAll();

    }

    /**
     * 根据ID查询
     *
     * @param id
     * @return 查询数据
     */
    @GetMapping("/findById")
    public OuDomain findById(Long id) {
        log.debug("[OU-FINDBYID] id={}", id);
        return ouService.findById(id);
    }


    /**
     * 删除测试
     */
    @PostMapping("/delete")
    public ResultUtil delete(@RequestBody @Validated Long[] ids) {
        log.debug("[OU-DELETE]");
        //模拟测试
        return ResultUtil.success(ouService.delOu(ids));
    }
}
