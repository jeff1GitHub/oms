package com.og.oms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.og.oms.dao.DomainMainMapper;
import com.og.oms.model.DomainMain;
import com.og.oms.model.User;
import com.og.oms.service.IDomainMainService;

import org.aspectj.weaver.ast.And;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jeff
 * @since 2017-11-09
 */
@Service
public class DomainMainServiceImpl extends ServiceImpl<DomainMainMapper, DomainMain> implements IDomainMainService {

    @Override
    public List<DomainMain> getDomainMainList(Integer purpose, Integer platform, String domain) {
        EntityWrapper<DomainMain> wrapper = new EntityWrapper<>();
        if(null != purpose) {
            wrapper.eq("purpose", purpose);
        }
        if(null != platform) {
            wrapper.eq("platform", platform);  
        }
        if(!StringUtils.isEmpty(domain)) { 	
            wrapper.like("domain", domain);
        }

        return this.selectList(wrapper);
    }

    @Override
    public DomainMain getDomainMainById(Integer id) {
        return this.selectById(id);
    }

    @Override
    public boolean addDomainMain(User user, DomainMain domainMain) {
        domainMain.setCreateTime(new Date());
        domainMain.setCreateUser(user.getAccount());
        return this.insert(domainMain);
    }

    @Override
    public boolean updateDomainMain(User user, DomainMain domainMain) {
        domainMain.setUpdateTime(new Date());
        domainMain.setUpdateUser(user.getAccount());
        return this.updateById(domainMain);
    }

    @Override
    public boolean delDomainMain(User user, Integer id) {
        return this.deleteById(id);
    }
}
