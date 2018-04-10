package com.og.oms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.og.oms.dao.NetworkMapper;
import com.og.oms.model.Network;
import com.og.oms.model.User;
import com.og.oms.service.INetworkService;
import org.springframework.stereotype.Service;

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
public class NetworkServiceImpl extends ServiceImpl<NetworkMapper, Network> implements INetworkService {

    @Override
    public List<Network> getNetworkList() {
        EntityWrapper<Network> wrapper = new EntityWrapper<>();
        return this.selectList(wrapper);
    }

    @Override
    public Network getNetworkById(Integer id) {
        return this.selectById(id);
    }

    @Override
    public boolean addNetwork(User user, Network network) {
        network.setCreateUser(user.getAccount());
        network.setCreateTime(new Date());
        return this.insert(network);
    }

    @Override
    public boolean updateNetwork(User user, Network network) {
        network.setUpdateUser(user.getAccount());
        network.setUpdateTime(new Date());
        return this.updateById(network);
    }

    @Override
    public boolean delNetwork(User user, Integer id) {
        return this.deleteById(id);
    }
}
