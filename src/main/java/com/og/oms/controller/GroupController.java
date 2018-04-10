package com.og.oms.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
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
import com.og.oms.service.IGroupPermisService;
import com.og.oms.service.IGroupService;
import com.og.oms.utils.JsonResult;

/**
 * <p>
 * 权限组前端控制器
 * </p>
 *
 * @author jeff
 * @since 2017-10-24
 */
@RestController
@SessionAttributes(names = "userId", types = Integer.class)
public class GroupController extends BaseController {

    @Autowired
    private IGroupService groupService;

    @Autowired
    private IGroupPermisService groupPermisService;

    /**
     * 获取自己可以管理的权限组列表
     *
     * @param userId
     * @return
     */
    @PermisAnnotation(id = 501, name = "组别管理-列表")
    @RequestMapping(value = "group", method = RequestMethod.GET)
    public JsonResult getGroupList(@ModelAttribute("userId") Integer userId) {
        List<Group> list = groupService.getChildGroupListForUser(this.getUser(userId));
        return new JsonResult(list);
    }

    /**
     * 通过id获取组别信息
     *
     * @param userId
     * @param groupId
     * @return
     */
    @PermisAnnotation(id = 501, name = "组别管理-查看")
    @RequestMapping(value = "group/{groupId}", method = RequestMethod.GET)
    public JsonResult getGroupById(@ModelAttribute("userId") Integer userId, @PathVariable Integer groupId) {
    	Group group = groupService.getGroupById(groupId);
        return new JsonResult(group);
    }

    /**
     * 增加权限组
     *
     * @param userId
     * @param group
     * @return
     */
    @PermisAnnotation(id = 50101, name = "组别管理-增加")
    @RequestMapping(value = "group", method = RequestMethod.PUT)
    public JsonResult addGroup(@ModelAttribute("userId") Integer userId, @Valid Group group, BindingResult result) {
        JsonResult ret;
        if(groupService.addGroup(this.getUser(userId), group)) {
            ret = new JsonResult();
        } else {
            ret = new JsonResult(ResultCode.EXCEPTION);
        }
        return ret;
    }

    /**
     * 修改权限组
     *
     * @param userId
     * @param group
     * @return
     */
    @PermisAnnotation(id = 50102, name = "组别管理-修改")
    @RequestMapping(value = "group", method = RequestMethod.POST)
    public JsonResult updateGroup(@ModelAttribute("userId") Integer userId, @Valid Group group, BindingResult result) {
        JsonResult ret;
        // 不能把本权限组设置为所属组
        if(group.getId().equals(group.getParentId())) {
        	return new JsonResult(ResultCode.EXCEPTION, "所属组不能是本身");
        }
        if(groupService.updateGroup(this.getUser(userId), group)) {
            ret = new JsonResult();
        } else {
            ret = new JsonResult(ResultCode.EXCEPTION);
        }
        return ret;
    }

    /**
     * 删除权限组
     *
     * @param userId
     * @param groupId
     * @return
     */
    @PermisAnnotation(id = 50103, name = "组别管理-删除")
    @RequestMapping(value = "group/{groupId}", method = RequestMethod.DELETE)
    public JsonResult delGroup(@ModelAttribute("userId") Integer userId, @PathVariable Integer groupId) {
        JsonResult ret;
        if(groupService.delGroup(this.getUser(userId), groupId)) {
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
     * @param permisIds
     * @return
     */
    @PermisAnnotation(id = 50104, name = "组别管理-设置权限-查询")
    @RequestMapping(value = "group/permis", method = RequestMethod.GET)
    public JsonResult getGroupPermis(Integer groupId) {
    	if(groupId != null) {
    		return new JsonResult(groupPermisService.getGroupPermisListForUpdate(groupId));
    	} else {
    		return new JsonResult();
    	}
    	
    }
    
    /**
     * 设置组别权限
     *
     * @param userId
     * @param permisIds
     * @return
     */
    @PermisAnnotation(id = 50104, name = "组别管理-设置权限-增加/修改")
    @RequestMapping(value = "group/permis", method = RequestMethod.POST)
    public JsonResult updatePermis(@ModelAttribute("userId") Integer userId, Integer groupId, Integer[] permisIds) {
        this.groupPermisService.addOrUpdateGroupPermis(userId, groupId, permisIds);
        
        return new JsonResult();
    }
    
    /**
     * 设置组别权限
     *
     * @param userId
     * @param permisIds
     * @return
     */
    @PermisAnnotation(id = 50104, name = "组别管理-启用/禁用")
    @RequestMapping(value = "group/disable", method = RequestMethod.POST)
    public JsonResult updateGroupStatus(@ModelAttribute("userId") Integer userId, Integer groupId, Integer isDisable) {
        JsonResult ret;
        Group group = new Group();
        group.setId(groupId);
        group.setIsDisable(StatusType.getStatusType(isDisable));
        if(groupService.updateGroup(this.getUser(userId), group)) {
            ret = new JsonResult();
        } else {
            ret = new JsonResult(ResultCode.EXCEPTION);
        }
        return ret;
    }
    
    
}
