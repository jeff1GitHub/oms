package com.og.oms.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.og.oms.dao.ServerRoomMapper;
import com.og.oms.enums.DeleteEnum;
import com.og.oms.enums.ServerRoomEnum;
import com.og.oms.model.ServerRoom;
import com.og.oms.model.User;
import com.og.oms.service.IServerRoomService;

/**
 * <p>
 * 机房服务实现类
 * </p>
 *
 * @author jeff
 * @since 2017-10-26
 */
@Service
public class ServerRoomServiceImpl extends ServiceImpl<ServerRoomMapper, ServerRoom> implements IServerRoomService {

    @Override
    public List<ServerRoom> getServerRoomList(boolean isAll) {
        EntityWrapper<ServerRoom> wrapper = new EntityWrapper<>();
        if(!isAll) {
            wrapper.eq("is_del", DeleteEnum.NORMAL.getCode());
        }
        return this.selectList(wrapper);
    }

    @Override
    public ServerRoom getServerRoomById(Integer roomId) {
        return this.selectById(roomId);
    }

    @Override
    public boolean addServerRoom(User user, ServerRoom serverRoom) {
        serverRoom.setIsDel(0);
        serverRoom.setCreateTime(new Date());
        serverRoom.setCreateUser(user.getAccount());
        boolean ret = this.insert(serverRoom);
        if(ret) {
            ServerRoomEnum.addEnum(serverRoom.getId(), serverRoom.getName());
        }
        return ret;
    }

    @Override
    public boolean updateServerRoom(User user, ServerRoom serverRoom) {
        serverRoom.setUpdateTime(new Date());
        serverRoom.setUpdateUser(user.getAccount());

        boolean ret = this.updateById(serverRoom);
        if(ret) {
            this.updateProducerEnum(serverRoom);
        }
        return ret;
    }

    @Override
    public boolean delServerRoom(User user, Integer roomId) {
        ServerRoom serverRoom = this.getServerRoomById(roomId);
        if(serverRoom == null) {
            return true;
        }
        serverRoom.setIsDel(DeleteEnum.DELETE.getCode());
        return this.updateById(serverRoom);
    }

    @Override
    public void initServerRoomEnum() {
    	EntityWrapper<ServerRoom> entityWrapper = new EntityWrapper<ServerRoom>();
//    	entityWrapper.eq("is_del", 0);
        for(ServerRoom serverRoom : this.selectList(entityWrapper)) {
            ServerRoomEnum.addEnum(serverRoom.getId(), serverRoom.getName());
        }
    }

    /**
     * 修改供应商枚举
     *
     * @param serverRoom
     */
    private void updateProducerEnum(ServerRoom serverRoom) {
        for(ServerRoomEnum anEnum : ServerRoomEnum.values()) {
            if(anEnum.getCode().equals(serverRoom.getId())) {
                anEnum.setName(serverRoom.getName());
                break;
            }
        }
    }
}
