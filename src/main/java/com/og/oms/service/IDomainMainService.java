package com.og.oms.service;

import com.baomidou.mybatisplus.service.IService;
import com.og.oms.model.DomainMain;
import com.og.oms.model.User;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author jeff
 * @since 2017-11-09
 */
public interface IDomainMainService extends IService<DomainMain> {
    /**
     * 获取域名列表
     * @param domain 
     * @param platform 
     * @param purpose 
     *
     * @return
     */
    List<DomainMain> getDomainMainList(Integer purpose, Integer platform, String domain);

    /**
     * 通过id获取域名信息
     *
     * @param id
     * @return
     */
    DomainMain getDomainMainById(Integer id);

    /**
     * 增加域名信息
     *
     * @param user
     * @param domainMain
     * @return
     */
    boolean addDomainMain(User user, DomainMain domainMain);

    /**
     * 修改域名信息
     *
     * @param user
     * @param domainMain
     * @return
     */
    boolean updateDomainMain(User user, DomainMain domainMain);

    /**
     * 删除服务器
     *
     * @param user
     * @param id
     * @return
     */
    boolean delDomainMain(User user, Integer id);

}
