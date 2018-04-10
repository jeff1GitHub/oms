package com.og.oms.controller;


import com.og.oms.annotation.PermisAnnotation;
import com.og.oms.enums.ResultCode;
import com.og.oms.model.ServerRoom;
import com.og.oms.service.IServerRoomService;
import com.og.oms.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * <p>
 * 机房前端控制器
 * </p>
 *
 * @author jeff
 * @since 201-26
 */
@RestController
@RequestMapping("serverroom")
@SessionAttributes(names = "userId", types = Integer.class)
public class ServerRoomController extends BaseController {

    @Autowired
    private IServerRoomService serverRoomService;

    /**
     * 获取机房列表
     *
     * @return
     */
    @PermisAnnotation(id = 305, name = "机房资料-列表")
    @RequestMapping(method = RequestMethod.GET)
    public JsonResult getServerRoomList() {
        return new JsonResult(serverRoomService.getServerRoomList(false));
    }

    /**
     * 通过id获取机房信息
     *
     * @param serverRoomId
     * @return
     */
    @PermisAnnotation(id = 305, name = "机房资料-查看")
    @RequestMapping(value = "{serverRoomId}", method = RequestMethod.GET)
    public JsonResult getServerRoomById(@PathVariable Integer serverRoomId) {
        return new JsonResult(serverRoomService.getServerRoomById(serverRoomId));
    }

    /**
     * 增加机房信息
     *
     * @param userId
     * @param room
     * @param result
     * @return
     */
    @PermisAnnotation(id = 30501, name = "机房资料-增加")
    @RequestMapping(method = RequestMethod.PUT)
    public JsonResult addServerRoom(@ModelAttribute("userId") Integer userId, @Valid ServerRoom room, BindingResult result) {
        JsonResult ret;
        if(serverRoomService.addServerRoom(this.getUser(userId), room)) {
            ret = new JsonResult();
        } else {
            ret = new JsonResult(ResultCode.EXCEPTION);
        }
        return ret;
    }

    /**
     * 修改机房信息
     *
     * @param userId
     * @param room
     * @param result
     * @return
     */
    @PermisAnnotation(id = 30502, name = "机房资料-修改")
    @RequestMapping(method = RequestMethod.POST)
    public JsonResult updateServerRoom(@ModelAttribute("userId") Integer userId, @Valid ServerRoom room, BindingResult result) {
        JsonResult ret;
        if(serverRoomService.updateServerRoom(this.getUser(userId), room)) {
            ret = new JsonResult();
        } else {
            ret = new JsonResult(ResultCode.EXCEPTION);
        }
        return ret;
    }

    /**
     * 通过id删除机房信息
     *
     * @param userId
     * @param id
     * @return
     */
    @PermisAnnotation(id = 30503, name = "机房资料-删除")
    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public JsonResult delServerRoomById(@ModelAttribute("userId") Integer userId, @PathVariable Integer id) {
        JsonResult ret;
        if(serverRoomService.delServerRoom(this.getUser(userId), id)) {
            ret = new JsonResult();
        } else {
            ret = new JsonResult(ResultCode.EXCEPTION);
        }
        return ret;
    }
}
