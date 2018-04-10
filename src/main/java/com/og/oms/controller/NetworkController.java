package com.og.oms.controller;


import com.og.oms.annotation.PermisAnnotation;
import com.og.oms.enums.ResultCode;
import com.og.oms.model.Network;
import com.og.oms.service.INetworkService;
import com.og.oms.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jeff
 * @since 2017-11-09
 */
@RestController
@RequestMapping("network")
@SessionAttributes(names = "userId", types = Integer.class)
public class NetworkController extends BaseController {

    @Autowired
    private INetworkService networkService;

    /**
     * 获取线路列表
     *
     * @return
     */
    @PermisAnnotation(id = 306, name = "线路资料-列表")
    @RequestMapping(method = RequestMethod.GET)
    public JsonResult getNetworkList() {
        return new JsonResult(this.networkService.getNetworkList());
    }

    /**
     * 通过id获取线路信息
     *
     * @param id
     * @return
     */
    @PermisAnnotation(id = 306, name = "线路资料-查看")
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public JsonResult getNetworkById(@PathVariable Integer id) {
        return new JsonResult(this.networkService.getNetworkById(id));
    }

    /**
     * 增加线路信息
     *
     * @param userId
     * @param network
     * @return
     */
    @PermisAnnotation(id = 30601, name = "线路资料-增加")
    @RequestMapping(method = RequestMethod.PUT)
    public JsonResult addNetwork(@ModelAttribute("userId") Integer userId, @Valid Network network, BindingResult result) {
        JsonResult ret;
        if(this.networkService.addNetwork(this.getUser(userId), network)) {
            ret = new JsonResult();
        } else {
            ret = new JsonResult(ResultCode.EXCEPTION);
        }
        return ret;
    }

    /**
     * 修改线路信息
     *
     * @param userId
     * @param network
     * @return
     */
    @PermisAnnotation(id = 30602, name = "线路资料-修改")
    @RequestMapping(method = RequestMethod.POST)
    public JsonResult updateNetwork(@ModelAttribute("userId") Integer userId, @Valid Network network, BindingResult result) {
        JsonResult ret;
        if(this.networkService.updateNetwork(this.getUser(userId), network)) {
            ret = new JsonResult();
        } else {
            ret = new JsonResult(ResultCode.EXCEPTION);
        }
        return ret;
    }

    /**
     * 删除线路信息
     *
     * @param userId
     * @param id
     * @return
     */
    @PermisAnnotation(id = 30603, name = "线路资料-删除")
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public JsonResult delNetwork(@ModelAttribute("userId") Integer userId, @PathVariable Integer id) {
        JsonResult ret;
        if(this.networkService.delNetwork(this.getUser(userId), id)) {
            ret = new JsonResult();
        } else {
            ret = new JsonResult(ResultCode.EXCEPTION);
        }
        return ret;
    }
}
