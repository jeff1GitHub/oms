package com.og.oms.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.og.oms.annotation.PermisAnnotation;
import com.og.oms.enums.ResultCode;
import com.og.oms.enums.StatusType;
import com.og.oms.model.Group;
import com.og.oms.model.Station;
import com.og.oms.service.IStationService;
import com.og.oms.utils.JsonResult;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jeff
 * @since 2017-11-04
 */
@RestController
@RequestMapping("station")
@SessionAttributes(names = "userId", types = Integer.class)
public class StationController extends BaseController {

    @Autowired
    private IStationService stationService;
    
    /**
     * 获取站点信息列表
     *
     * @param platform
     * @param level
     * @param type
     * @param code
     * @return
     */
    @PermisAnnotation(id = 308, name = "站点资料-列表")
    @RequestMapping()
    public JsonResult getStationList(Integer platform, Integer level, Integer type, String code) {
        return new JsonResult(stationService.getStationList(platform, level, type, code));
    }

    /**
     * 通过id获取站点信息
     *
     * @param id
     * @return
     */
    @PermisAnnotation(id = 308, name = "站点资料-查看")
    @RequestMapping(value = "{id}")
    public JsonResult getStationById(@PathVariable Integer id) {
        return new JsonResult(stationService.getStationById(id));
    }

    /**
     * 增加站点信息
     *
     * @param userId
     * @param station
     * @return
     */
    @PermisAnnotation(id = 30801, name = "站点资料-增加")
    @RequestMapping(method = RequestMethod.PUT)
    public JsonResult addStation(@ModelAttribute("userId") Integer userId, Station station) {
        JsonResult ret;
        if(stationService.addStation(this.getUser(userId), station)) {
            ret = new JsonResult();
        } else {
            ret = new JsonResult(ResultCode.EXCEPTION);
        }
        return ret;
    }

    /**
     * 修改站点信息
     *
     * @param userId
     * @param station
     * @return
     */
    @PermisAnnotation(id = 30802, name = "站点资料-修改")
    @RequestMapping(method = RequestMethod.POST)
    public JsonResult updateStation(@ModelAttribute("userId") Integer userId, Station station) {
        JsonResult ret;
    	//aliasService.delAliasByStationCode(this.getUser(userId), station.getCode());
        if(stationService.updateStation(this.getUser(userId), station)) {
            ret = new JsonResult();
        } else {
            ret = new JsonResult(ResultCode.EXCEPTION);
        }
        return ret;
    }

    /**
     * 删除站点信息
     *
     * @param userId
     * @param id
     * @return
     */
    @PermisAnnotation(id = 30803, name = "站点资料-删除")
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public JsonResult delStation(@ModelAttribute("userId") Integer userId, @PathVariable Integer id) {
        JsonResult ret;
        if(stationService.delStation(this.getUser(userId), id)) {
            ret = new JsonResult();
        } else {
            ret = new JsonResult(ResultCode.EXCEPTION);
        }
        return ret;
    }

    /**
     * 设置组别权限
     *
     * @param userId
     * @param stationId
     * @param isDisable
     * @return
     */
    @PermisAnnotation(id = 30802, name = "站点资料-启用/禁用")
    @RequestMapping(value = "disable", method = RequestMethod.POST)
    public JsonResult updateGroupStatus(@ModelAttribute("userId") Integer userId, Integer stationId, Integer isDisable) {
        JsonResult ret;
        Station station = new Station();
        station.setId(stationId);
        station.setIsDisable(StatusType.getStatusType(isDisable));
        if(stationService.updateStationIsDisable(this.getUser(userId), station)) {
            ret = new JsonResult();
        } else {
            ret = new JsonResult(ResultCode.EXCEPTION);
        }
        return ret;
    }
    
}
