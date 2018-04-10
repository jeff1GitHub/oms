package com.og.oms.service;

import com.baomidou.mybatisplus.service.IService;
import com.og.oms.model.Deployment;
import com.og.oms.model.User;

import java.util.List;

/**
 * <p>
 * 服务部署信息服务类接口
 * </p>
 *
 * @author jeff
 * @since 2017-11-15
 */
public interface IDeploymentService extends IService<Deployment> {
    /**
     * 获取所有部署信息
     *
     * @return
     */
    List<Deployment> getDeploymentList(Integer platform, Integer environment, Integer project, Integer service);

    /**
     * 通过id获取部署信息
     *
     * @param id
     * @return
     */
    Deployment getDeploymentById(Integer id);

    /**
     * 增加服务器部署信息
     *
     * @param user
     * @param deployment
     * @return
     */
    boolean addDeployment(User user, Deployment deployment);

    /**
     * 修改服务部署信息
     *
     * @param user
     * @param deployment
     * @return
     */
    boolean updateDeployment(User user, Deployment deployment);

    /**
     * 删除服务部署信息
     *
     * @param user
     * @param id
     * @return
     */
    boolean delDeployment(User user, Integer id);

}
