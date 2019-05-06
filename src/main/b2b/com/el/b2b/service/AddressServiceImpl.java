package com.el.b2b.service;

import com.el.b2b.api.AddressQuery;
import com.el.b2b.domain.Address;
import com.el.b2b.domain.AddressCodeName;
import com.el.b2b.mapper.AddressMapper;
import com.el.core.domain.PagingResult;
import com.el.edp.util.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/5/10
 * @Description:
 */
@Slf4j
@Profile("b2b")
@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressMapper addressMapper;

    @Override
    public PagingResult<Address> page(AddressQuery query) {
        int total = addressMapper.findCount(query);
        return total > 0 ? PagingResult.of(addressMapper.findPage(query), total) : PagingResult.of(null, 0);
    }

    @Override
    public Address findById(long id) {
        return addressMapper.findById(id);
    }

    @Transactional
    @Override
    public int saveOrUpdate(Address address) {
        int result = 0;
        if (StringUtils.isEmpty(address.getId())) {
            result = addressMapper.insert(address);
        } else {
            result = addressMapper.updateByPrimaryKey(address);
        }
        return result;
    }

    @Transactional
    @Override
    public int delete(long id) {
        Address address = addressMapper.findById(id);
        return addressMapper.updateByPrimaryKey(address);
    }

    @Override
    public List<AddressCodeName> getAddressList(AddressQuery query) {
        return addressMapper.getAddressList(query);
    }


}
