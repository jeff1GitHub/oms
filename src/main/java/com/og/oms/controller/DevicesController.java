package com.og.oms.controller;


import com.og.oms.annotation.PermisAnnotation;
import com.og.oms.enums.ResultCode;
import com.og.oms.model.Devices;
import com.og.oms.service.IDevicesService;
import com.og.oms.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 网络设备前端控制器
 * </p>
 *
 * @author jeff
 * @since 2017-10-27
 */
@RestController
@RequestMapping("devices")
@SessionAttributes(names = "userId", types = Integer.class)
public class DevicesController extends BaseController {
    @Autowired
    private IDevicesService devicesService;

    /**
     * 获取网设列表
     *
     * @return
     */
    @PermisAnnotation(id = 307, name = "网设资料-列表")
    @RequestMapping(method = RequestMethod.GET)
    public JsonResult getDevicesList() {
        return new JsonResult(devicesService.getDevicesList());
    }

    /**
     * 通过id获取网设信息
     *
     * @param id
     * @return
     */
    @PermisAnnotation(id = 307, name = "网设资料-查看")
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public JsonResult getDevicesById(@PathVariable Integer id) {
        Devices devices = devicesService.getDevicesById(id);
        return new JsonResult(devices);
    }

    /**
     * 增加网设
     *
     * @param userId
     * @param devices
     * @param result
     * @return
     */
    @PermisAnnotation(id = 30701, name = "网设资料-增加")
    @RequestMapping(method = RequestMethod.PUT)
    public JsonResult addDevices(@ModelAttribute("userId") Integer userId, @Valid Devices devices, BindingResult result) {
        JsonResult ret;
        if(devicesService.addDevices(this.getUser(userId), devices)) {
            ret = new JsonResult();
        } else {
            ret = new JsonResult(ResultCode.EXCEPTION);
        }
        return ret;
    }

    /**
     * 修改网设
     *
     * @param userId
     * @param devices
     * @param result
     * @return
     */
    @PermisAnnotation(id = 30702, name = "网设资料-修改")
    @RequestMapping(method = RequestMethod.POST)
    public JsonResult updateDevices(@ModelAttribute("userId") Integer userId, @Valid Devices devices, BindingResult result) {
        JsonResult ret;
        if(devicesService.updateDevices(this.getUser(userId), devices)) {
            ret = new JsonResult();
        } else {
            ret = new JsonResult(ResultCode.EXCEPTION);
        }
        return ret;
    }

    /**
     * 删除网设
     *
     * @param userId
     * @param id
     * @return
     */
    @PermisAnnotation(id = 30703, name = "系统字典-增加")
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public JsonResult delDevices(@ModelAttribute("userId") Integer userId, @PathVariable Integer id) {
        JsonResult ret;
        if(devicesService.delDevices(this.getUser(userId), id)) {
            ret = new JsonResult();
        } else {
            ret = new JsonResult(ResultCode.EXCEPTION);
        }
        return ret;
    }
}
