package com.og.oms.service;

import com.baomidou.mybatisplus.service.IService;
import com.og.oms.model.Network;
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
public interface INetworkService extends IService<Network> {
    /**
     * 获取线路列表
     *
     * @return
     */
    List<Network> getNetworkList();

    /**
     * 通过id获取线路信息
     *
     * @param id
     * @return
     */
    Network getNetworkById(Integer id);

    /**
     * 增加线路信息
     *
     * @param user
     * @param network
     * @return
     */
    boolean addNetwork(User user, Network network);

    /**
     * 修改线路信息
     *
     * @param user
     * @param network
     * @return
     */
    boolean updateNetwork(User user, Network network);

    /**
     * 删除线路信息
     *
     * @param user
     * @param id
     * @return
     */
    boolean delNetwork(User user, Integer id);
}
