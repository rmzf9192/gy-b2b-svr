package com.el.b2b.service;

import com.el.b2b.api.AddressQuery;
import com.el.b2b.domain.Address;
import com.el.b2b.domain.AddressCodeName;
import com.el.core.domain.PagingResult;

import java.util.List;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/5/14
 * @Description:地址簿地址信息 Service
 */
public interface AddressService {

    /**
     * 按QUERY分页查询
     *
     * @param query 查询条件
     * @return
     */
    PagingResult<Address> page(AddressQuery query);

    /**
     * 按ID查询
     *
     * @param id 查询条件
     * @return
     */
    Address findById(long id);

    /**
     * 保存
     *
     * @param address
     * @return
     */
    int saveOrUpdate(Address address);

    /**
     * 删除
     *
     * @param id
     * @return
     */
    int delete(long id);


    List<AddressCodeName> getAddressList(AddressQuery addressQuery);

}
