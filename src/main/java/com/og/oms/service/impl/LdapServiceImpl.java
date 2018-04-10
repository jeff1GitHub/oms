package com.og.oms.service.impl;

import com.og.oms.service.ILdapService;
import org.apache.ibatis.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.stereotype.Service;

@Service
public class LdapServiceImpl implements ILdapService {
    private static final Logger logger = LoggerFactory.getLogger(LdapServiceImpl.class);
    @Autowired
    private LdapTemplate ldapTemplate;

    @Override
    public boolean authenticate(String account, String password) {
        boolean ret = false;
        try {
            ret = ldapTemplate.authenticate("dc=cattlee,dc=com", "uid=" + account, password);
        } catch(Exception e) {
            logger.error("ldap验证帐号出错!", e);
            throw new CacheException("ldap验证帐号出错!");
        }
        return ret;
    }
}
