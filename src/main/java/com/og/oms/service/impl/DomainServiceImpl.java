package com.og.oms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.og.oms.dao.DomainMapper;
import com.og.oms.enums.DomainTypeEnum;
import com.og.oms.exception.OmsException;
import com.og.oms.model.Domain;
import com.og.oms.model.Station;
import com.og.oms.model.User;
import com.og.oms.service.IDomainService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 域名服务实现类
 * </p>
 *
 * @author jeff
 * @since 2017-11-07
 */
@Service
public class DomainServiceImpl extends ServiceImpl<DomainMapper, Domain> implements IDomainService {

    @Override
    public List<Domain> getDomainList(String stationCode) {
        EntityWrapper<Domain> wrapper = new EntityWrapper<>();
        wrapper.eq("station", stationCode);
        return this.selectList(wrapper);
    }

    @Override
    public boolean addDomain(User user, Station station) {
        List<Domain> list = new ArrayList<>();

        List<String> tmpList = new ArrayList<>();
        if(station.getManagedomain()!=null) {
        	tmpList.addAll(station.getManagedomain());
        }
        if(station.getTestdomain()!=null) {
        	tmpList.addAll(station.getTestdomain());
        }
        if(station.getClientsDomain()!=null) {
        	tmpList.addAll(station.getClientsDomain());
        }
        
        tmpList.add(station.getDomain());
        // 判断域名是否重复
        EntityWrapper<Domain> wrapper = new EntityWrapper<>();
        wrapper.in("domain", tmpList);
        if(this.selectList(wrapper).size() > 0) {
            throw new OmsException("域名重复!");
        }

        // 创建主域名
        Domain domain = new Domain(user.getAccount(), station.getDomain(), station.getCode(), DomainTypeEnum.DOMAIN_MAIN);
        list.add(domain);
        // 创建后台域名对象
        if(station.getManagedomain()!=null) {
            for(String tmp : station.getManagedomain()) {
                domain = new Domain(user.getAccount(), tmp, station.getCode(), DomainTypeEnum.DOMAIN_MANAGE);
                list.add(domain);
            }
        }
        // 创建测试域名
        if(station.getTestdomain()!=null) {
        	for(String tmp : station.getTestdomain()) {
                domain = new Domain(user.getAccount(), tmp, station.getCode(), DomainTypeEnum.DOMAIN_TEST);
                list.add(domain);
            }
        }
        // 创建客户测试域名
        if(station.getClientsDomain()!=null) {
            for(String tmp : station.getClientsDomain()) {
                domain = new Domain(user.getAccount(), tmp, station.getCode(), DomainTypeEnum.DOMAIN_CLIENT);
                list.add(domain);
            }
        }

        return this.insertBatch(list);
    }

    @Override
    public boolean delDomain(User user, String stationCode) {
        EntityWrapper<Domain> wrapper = new EntityWrapper<>();
        wrapper.eq("station", stationCode);
        return this.delete(wrapper);
    }

    @Override
    public boolean updateDomain(User user, Station station) {
        delDomain(user, station.getCode());
        addDomain(user, station);
        return true;
    }
}
