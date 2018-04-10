package com.og.oms.controller;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.og.oms.annotation.PermisAnnotation;
import com.og.oms.enums.ResultCode;
import com.og.oms.model.User;
import com.og.oms.service.IGroupUserService;
import com.og.oms.service.IUserService;
import com.og.oms.utils.JsonResult;

@RestController
@RequestMapping(value = "user")
@SessionAttributes(names = "userId", types = Integer.class)
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IGroupUserService groupUserService;

    /**
     * 查询用户列表
     *
     * @param group
     * @param status
     * @param search
     * @param datemin
     * @param datemax
     * @return
     * @throws ParseException
     */
    @RequestMapping(method = RequestMethod.GET)
    public JsonResult getUserList(Integer group, Integer status, String search, String datemin, String datemax) throws ParseException {
        return new JsonResult(userService.getUserList(group, status, search, datemin, datemax));
    }
    
    /**
     * 通过id员工信息
     *
     * @param id
     * @return
     */
    @PermisAnnotation(id = 502, name = "员工管理-列表")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public JsonResult getUserById(@PathVariable Integer id) {
        return new JsonResult(userService.getUserById(id));
    }

    /**
     * 禁用员工
     *
     * @param userId
     * @param id
     * @return
     */
    @PermisAnnotation(id = 502, name = "员工管理-启用/禁用")
    @RequestMapping(value = "{id}", method = RequestMethod.POST)
    public JsonResult disableUser(@ModelAttribute("userId") Integer userId, @PathVariable Integer id, Integer status) {
        JsonResult ret;
        if(this.userService.disableUser(super.getUser(userId), id, status)) {
            ret = new JsonResult();
        } else {
            ret = new JsonResult(ResultCode.EXCEPTION);
        }
        return ret;
    }
    
    /**
     * 更改用户组
     *
     * @param userId
     * @param id
     * @return
     */
    @PermisAnnotation(id = 502, name = "员工管理-修改组别")
    @RequestMapping(method = RequestMethod.POST)
    public JsonResult updateUser(@ModelAttribute("userId") Integer userId, User user) {
        JsonResult ret;
        if(this.groupUserService.updateUserGroup(userId, user)) {
            ret = new JsonResult();
        } else {
            ret = new JsonResult(ResultCode.EXCEPTION);
        }
        return ret;
    }

}
