package com.og.oms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.og.oms.dao.StationMapper;
import com.og.oms.exception.OmsException;
import com.og.oms.model.*;
import com.og.oms.service.IAliasService;
import com.og.oms.service.IDomainService;
import com.og.oms.service.IStationService;
import com.og.oms.service.IVpnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 站点资料服务实现类
 * </p>
 *
 * @author jeff
 * @since 2017-11-04
 */
@Service
public class StationServiceImpl extends ServiceImpl<StationMapper, Station> implements IStationService {

    @Autowired
    private IAliasService aliasService;
    @Autowired
    private IDomainService domainService;
    @Autowired
    private IVpnService vpnService;

    @Override
    public List<Station> getStationList(Integer platform, Integer level, Integer type, String code) {
        EntityWrapper<Station> wrapper = new EntityWrapper<>();
        if(!StringUtils.isEmpty(platform)) {
            wrapper.eq("platform", platform);
        }

        if(!StringUtils.isEmpty(level)) {
            wrapper.eq("level", level);
        }

        if(!StringUtils.isEmpty(type)) {
            wrapper.eq("type", type);
        }

        if(!StringUtils.isEmpty(code)) {
            wrapper.like("code", code.toLowerCase());
        }
        return this.selectList(wrapper);
    }

    @Override
    public Station getStationById(Integer id) {
        // 获取站点基础信息
        Station station = this.getSimpleStationById(id);

        // 获取别名,支付别名,新支付别名
        List<Alias> aliasList = aliasService.getAliasListByStationCode(station.getCode());
        station.addAlias(aliasList);

        // 获取域名
        List<Domain> domainList = this.domainService.getDomainList(station.getCode());
        station.addDomain(domainList);
        // 获取vpn
        List<Vpn> vpnList = this.vpnService.getVpnByStationCode(station.getCode());
        station.addVpn(vpnList);

        return station;
    }

    @Override
    public Station getStationByCode(String code) {
        EntityWrapper<Station> wrapper = new EntityWrapper<>();
        wrapper.eq("code", code);
        return super.selectOne(wrapper);
    }

    @Override
    public Station getSimpleStationById(Integer id) {
        return this.selectById(id);
    }

    @Override
    @Transactional
    public boolean addStation(User user, Station station) {
        // 判断站点code是否重复
        EntityWrapper<Station> wrapper = new EntityWrapper<>();
        wrapper.eq("code", station.getCode());
        List<Station> list = this.selectList(wrapper);
        if(list.size() > 0) {
            throw new OmsException("站点编号重复!");
        }

        // 保存站点信息
        station.setCreateTime(new Date());
        station.setCreateUser(user.getAccount());
        // 将站点code转为小写
        station.setCode(station.getCode().toLowerCase());
        this.insert(station);

        //保存别名，支付别名，新支付别名
        this.aliasService.addStationAlias(user, station.getCode(), station.getAlias(), station.getPayalias(), station.getPayment());

        // 保存vpn
        this.vpnService.addVpn(user, station);

        // 保存域名信息
        this.domainService.addDomain(user, station);

        return true;
    }

    @Override
    @Transactional
    public boolean updateStation(User user, Station station) {
        station.setUpdateTime(new Date());
        station.setUpdateUser(user.getAccount());
        this.updateById(station);

        //保存别名，支付别名，新支付别名
        this.aliasService.updateAlias(user, station.getCode(), station.getAlias(), station.getPayalias(), station.getPayment());

        // 保存vpn
        this.vpnService.updateVpn(user, station);

        // 保存域名信息
        this.domainService.updateDomain(user, station);

        return true;
    }
    
    @Override
    @Transactional
    public boolean updateStationIsDisable(User user, Station station) {
        station.setUpdateTime(new Date());
        station.setUpdateUser(user.getAccount());
        this.updateById(station);

        return true;
    }

    @Override
    @Transactional
    public boolean delStation(User user, Integer id) {
        Station station = this.getSimpleStationById(id);
        if(station == null) {
            return true;
        }

        this.deleteById(station.getId());

        this.aliasService.delAliasByStationCode(user, station.getCode());
        this.vpnService.delVpn(user, station.getCode());
        this.domainService.delDomain(user, station.getCode());
        return true;
    }
}
