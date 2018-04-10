package com.og.oms.service;

import com.baomidou.mybatisplus.service.IService;
import com.og.oms.model.Server;
import com.og.oms.model.User;

import java.util.List;

/**
 * <p>
 * 服务器信息服务类接口
 * </p>
 *
 * @author jeff
 * @since 2017-11-04
 */
public interface IServerService extends IService<Server> {
    /**
     * 获取服务器列表
     *
     * @return
     */
    List<Server> getServerList();
    
    /**
     * 获取服务器列表
     * @param endDay 
     * @param startDay 
     * @param serverCode 
     * @param linkAddr 
     *
     * @return
     */
    List<Server> getServerList(String serverCode, String startDay, String endDay, String linkAddr);

    /**
     * 通过id获取服务器信息
     *
     * @param id
     * @return
     */
    Server getServerById(Integer id);

    /**
     * 增加服务器信息
     *
     * @param user
     * @param server
     * @return
     */
    boolean addServer(User user, Server server);

    /**
     * 修改服务器信息
     *
     * @param user
     * @param server
     * @return
     */
    boolean updateServer(User user, Server server);

    /**
     * 删除服务器
     *
     * @param user
     * @param id
     * @return
     */
    boolean delServer(User user, Integer id);

	/**
	 * 服务器资料下拉显示
	 * 
	 * @return
	 */
	List<Server> getServerListForDropDown();

}
