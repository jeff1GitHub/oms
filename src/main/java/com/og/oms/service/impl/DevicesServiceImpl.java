package com.og.oms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.og.oms.dao.DevicesMapper;
import com.og.oms.model.Devices;
import com.og.oms.model.User;
import com.og.oms.service.IDevicesService;
import com.og.oms.utils.PasswordUtil;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 网络设备服务实现类
 * </p>
 *
 * @author jeff
 * @since 2017-10-27
 */
@Service
public class DevicesServiceImpl extends ServiceImpl<DevicesMapper, Devices> implements IDevicesService {

    @Override
    public List<Devices> getDevicesList() {
        EntityWrapper<Devices> wrapper = new EntityWrapper<>();
        return this.selectList(wrapper);
    }

    @Override
    public Devices getDevicesById(Integer id) {
    	Devices device = this.selectById(id);
    	if(!StringUtils.isEmpty(device.getPassword())){
    		device.setPassword(PasswordUtil.decrypt(device.getPassword()));
    	}
        return device;
    }

    @Override
    public boolean addDevices(User user, Devices devices) {
    	if(!StringUtils.isEmpty(devices.getPassword())){
    		devices.setPassword(PasswordUtil.encrypt(devices.getPassword()));
    	}
        return this.insert(devices);
    }

    @Override
    public boolean updateDevices(User user, Devices devices) {
    	devices.setUpdateUser(user.getAccount());
    	devices.setUpdateTime(new Date());
    	if(!StringUtils.isEmpty(devices.getPassword())){
    		devices.setPassword(PasswordUtil.encrypt(devices.getPassword()));
    	}
    	return this.updateById(devices);
    }

    @Override
    public boolean delDevices(User user, Integer id) {
        return this.deleteById(id);
    }
}
