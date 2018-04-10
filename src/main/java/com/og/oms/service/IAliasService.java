package com.og.oms.service;

import com.baomidou.mybatisplus.service.IService;
import com.og.oms.model.Alias;
import com.og.oms.model.User;

import java.util.List;

/**
 * <p>
 * 别名服务类接口
 * </p>
 *
 * @author jeff
 * @since 2017-11-06
 */
public interface IAliasService extends IService<Alias> {
    /**
     * 获取站点下所有别名
     *
     * @param stationCode
     * @return
     */
    List<Alias> getAliasListByStationCode(String stationCode);

    /**
     * 根据id查找别名
     *
     * @param id
     * @return
     */
    Alias getAliasById(Integer id);

    /**
     * 根据别名获取别名对象
     *
     * @param alias
     * @return
     */
    Alias getAliasByAlias(String alias);

    /**
     * 增加别名
     *
     * @param user
     * @param alias
     * @return
     */
    boolean addAlias(User user, Alias alias);

    /**
     * 增加站点别名
     *
     * @param user
     * @param stationCode
     * @param aliasList
     * @return
     */
    boolean addStationAlias(User user, String stationCode, List<String> aliasList, String payAlias, String payment);

    /**
     * 修改别名
     *
     * @param user
     * @param stationCode
     * @param aliasList
     * @return
     */
    boolean updateAlias(User user, String stationCode, List<String> aliasList, String payAlias, String payment);

    /**
     * 删除别名
     *
     * @param user
     * @param alias
     * @return
     */
    boolean delAlias(User user, String alias);

    /**
     * 删除站点下所有别名
     *
     * @param user
     * @param stationCode
     * @return
     */
    boolean delAliasByStationCode(User user, String stationCode);
}
