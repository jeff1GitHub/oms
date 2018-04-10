package com.og.oms.service;

import com.baomidou.mybatisplus.service.IService;
import com.og.oms.model.ServerRoom;
import com.og.oms.model.User;

import java.util.List;

/**
 * <p>
 * 机房服务类接口
 * </p>
 *
 * @author jeff
 * @since 2017-10-26
 */
public interface IServerRoomService extends IService<ServerRoom> {

    /**
     * 获取机房列表
     *
     * @param isAll 是否过滤删除 true:过滤
     * @return
     */
    List<ServerRoom> getServerRoomList(boolean isAll);

    /**
     * 通过id获取机房信息
     *
     * @param roomId
     * @return
     */
    ServerRoom getServerRoomById(Integer roomId);

    /**
     * 增加机房
     *
     * @param user
     * @param serverRoom
     * @return
     */
    boolean addServerRoom(User user, ServerRoom serverRoom);

    /**
     * 修改机房信息
     *
     * @param user
     * @param serverRoom
     * @return
     */
    boolean updateServerRoom(User user, ServerRoom serverRoom);

    /**
     * 通过id删除机房
     *
     * @param user
     * @param roomId
     * @return
     */
    boolean delServerRoom(User user, Integer roomId);

    void initServerRoomEnum();
}
