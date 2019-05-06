package com.el.b2b.api;

import com.el.b2b.domain.Address;
import com.el.b2b.domain.AddressCodeName;
import com.el.b2b.service.AddressService;
import com.el.cfg.security.EdpPrincipalService;
import com.el.edi.iam.payload.EdpUser;
import com.el.edp.util.ResultUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/5/10
 * @Description:商品主文件Controller
 */
@Slf4j
@Profile("b2b")
@RestController
@RequestMapping("api/address")
@AllArgsConstructor
public class AddressApi {

    private final EdpPrincipalService principalService;//当前登录人
    private final AddressService addressService;

    /**
     * 按addressQuery分页查询
     *
     * @param addressQuery
     * @return
     */
    @GetMapping("/query")
    public ResultUtil page(AddressQuery addressQuery) {
        EdpUser user = principalService.user();//获取当前登陆用户
        String ouCode = user.getEntCode();
        addressQuery.setOuCode(ouCode);
        log.info("[AddressController-page] AddressQuery: {}:", addressQuery);
        return ResultUtil.success(addressService.page(addressQuery));
    }

    /**
     * 按ID查询
     *
     * @param id
     * @return
     */
    @GetMapping("/query/{id}")
    public Address query(@PathVariable long id) {
        log.info("[AddressController-query] id: {}:", id);
        return addressService.findById(id);
    }

    /**
     * UDC 获取地址信息
     *
     * @param addressQuery
     * @return
     */
    @GetMapping("/getAddressList")
    public List<AddressCodeName> getAddressList(AddressQuery addressQuery) {
        EdpUser user = principalService.user();//获取当前登陆用户
        String ouCode = user.getEntCode();
        addressQuery.setCustCode(user.getEmpCode());
        addressQuery.setOuCode(ouCode);
        return addressService.getAddressList(addressQuery);
    }

}
