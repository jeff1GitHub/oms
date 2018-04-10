package com.og.oms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.og.oms.dao.VpnMapper;
import com.og.oms.model.Station;
import com.og.oms.model.User;
import com.og.oms.model.Vpn;
import com.og.oms.service.IVpnService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jeff
 * @since 2017-11-07
 */
@Service
public class VpnServiceImpl extends ServiceImpl<VpnMapper, Vpn> implements IVpnService {

    @Override
    public List<Vpn> getVpnByStationCode(String stationCode) {
        EntityWrapper<Vpn> wrapper = new EntityWrapper<>();
        wrapper.eq("station", stationCode);
        return this.selectList(wrapper);
    }

    @Override
    public boolean addVpn(User user, Station station) {
        if(station.getVpnip() == null || station.getVpnip().size() == 0) {
            return true;
        }

        List<Vpn> list = new ArrayList<>();

        Vpn vpn;
        for(String tmp : station.getVpnip()) {
            vpn = new Vpn(user.getAccount(), tmp, station.getVpnaccount(), station.getVpnpassword(), station.getCode());
            list.add(vpn);
        }
        return this.insertBatch(list);
    }

    @Override
    public boolean updateVpn(User user, Station station) {
        this.delVpn(user, station.getCode());
        this.addVpn(user, station);
        return true;
    }

    @Override
    public boolean delVpn(User user, String stationCode) {
        EntityWrapper<Vpn> wrapper = new EntityWrapper<>();
        wrapper.eq("station", stationCode);
        return this.delete(wrapper);
    }
}
