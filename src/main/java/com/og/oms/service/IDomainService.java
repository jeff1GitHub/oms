package com.og.oms.service;

import com.baomidou.mybatisplus.service.IService;
import com.og.oms.model.Domain;
import com.og.oms.model.Station;
import com.og.oms.model.User;

import java.util.List;

/**
 * <p>
 * 域名服务类接口
 * </p>
 *
 * @author jeff
 * @since 2017-11-07
 */
public interface IDomainService extends IService<Domain> {
    /**
     * 获取站点下所有域名
     *
     * @param stationCode 站点域名
     * @return 域名列表
     */
    List<Domain> getDomainList(String stationCode);

    /**
     * 增加域名
     *
     * @param user    操作用户
     * @param station 站点信息
     * @return 是否成功
     */
    boolean addDomain(User user, Station station);

    /**
     * 删除站点下所有域名
     *
     * @param user        操作用户
     * @param stationCode 站点编号
     * @return 是否成功
     */
    boolean delDomain(User user, String stationCode);

    /**
     * 修改站点域名
     *
     * @param user    操作用户
     * @param station 站点信息
     * @return
     */
    boolean updateDomain(User user, Station station);
}
