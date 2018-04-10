package com.og.oms.service;

import com.baomidou.mybatisplus.service.IService;
import com.og.oms.model.Devices;
import com.og.oms.model.User;

import java.util.List;

/**
 * <p>
 * 网络设备服务类接口
 * </p>
 *
 * @author jeff
 * @since 2017-10-27
 */
public interface IDevicesService extends IService<Devices> {
    List<Devices> getDevicesList();

    /**
     * 通过id获取网络设备
     *
     * @param id
     * @return
     */
    Devices getDevicesById(Integer id);

    /**
     * 增加网络设备
     *
     * @param user
     * @param devices
     * @return
     */
    boolean addDevices(User user, Devices devices);

    /**
     * 修改网络设备
     *
     * @param user
     * @param devices
     * @return
     */
    boolean updateDevices(User user, Devices devices);

    /**
     * 删除网络设备
     *
     * @param user
     * @param id
     * @return
     */
    boolean delDevices(User user, Integer id);
}
