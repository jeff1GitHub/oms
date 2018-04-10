package com.og.oms.service;

import com.baomidou.mybatisplus.service.IService;
import com.og.oms.model.Station;
import com.og.oms.model.User;
import com.og.oms.model.Vpn;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author jeff
 * @since 2017-11-07
 */
public interface IVpnService extends IService<Vpn> {
    /**
     * 获取站点下所有vpn信息
     *
     * @param stationCode
     * @return
     */
    List<Vpn> getVpnByStationCode(String stationCode);

    /**
     * 增加站点vpn信息
     *
     * @param user    操作用户
     * @param station 站点信息
     * @return 是否成功
     */
    boolean addVpn(User user, Station station);

    /**
     * 修改站点vpn信息
     *
     * @param user    操作用户
     * @param station 站点信息
     * @return 是否成功
     */
    boolean updateVpn(User user, Station station);

    /**
     * 删除站点vpn信息
     *
     * @param user        操作用户
     * @param stationCode 站点编号
     * @return 是否成功
     */
    boolean delVpn(User user, String stationCode);
}
