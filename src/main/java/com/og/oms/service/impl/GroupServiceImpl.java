package com.og.oms.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.og.oms.common.Context;
import com.og.oms.dao.GroupMapper;
import com.og.oms.enums.GroupEnum;
import com.og.oms.enums.StatusType;
import com.og.oms.exception.OmsException;
import com.og.oms.model.Group;
import com.og.oms.model.GroupUser;
import com.og.oms.model.User;
import com.og.oms.service.IGroupService;
import com.og.oms.service.IGroupUserService;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jeff
 * @since 2017-10-24
 */
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements IGroupService {

    @Autowired
    private Context context;
    
    @Autowired
    private IGroupUserService groupUserService;

    @Override
    public List<Group> getGroupList() {
        return this.selectList(null);
    }

    @Override
    public List<Group> getChildGroupListForUser(User user) {
    	//获取当前用户所属的所有权限组
    	List<GroupUser> groupList = groupUserService.getGroupUserByUser(user.getId());
    	
    	//如果已经设置，根据权限组ID获取父子孙权限组的所有信息
    	if(groupList != null && groupList.size() > 0) {
    		List<Integer> list = new ArrayList<>();
        	for(GroupUser g : groupList) {
        		list.add(g.getGroupId());
        	}
        	
        	List<Group> groups = this.baseMapper.getChildGroupListForUser(list);
            return groups;
    	}
    	return null;
    }

    @Override
    public Group getGroupById(Integer groupId) {
    	EntityWrapper<Group> wrapper = new EntityWrapper<>();
    	wrapper.eq("f_id", groupId);
        return this.selectOne(wrapper);
    }

    @Override
    public Group getGroupById(User user, Integer groupId) {
        return null;
    }

    @Override
    public boolean addGroup(User user, Group group) {
        group.setCreateTime(new Date());
        group.setCreateUser(user.getAccount());

        boolean ret = this.insert(group);
        if(ret) {
            context.addGroup(group);
        }
        return ret;
    }

    @Override
    public boolean setGroupPermis(User user, Integer groupId, Integer[] permisIds) {
        return false;
    }

    @Override
    public boolean updateGroup(User user, Group group) {
        group.setUpdateTime(new Date());
        group.setUpdateUser(user.getAccount());

        boolean ret = this.updateById(group);
        if(ret) {
            context.addGroup(group);
        }
        return ret;
    }

    @Override
    public boolean delGroup(User user, Integer groupId) {
        Group group = context.getGroupById(groupId);
        if(group == null) {
            return true;
        }

        boolean ret = this.deleteById(groupId);
        if(ret) {
            context.delGroupByid(groupId);
        }
        return ret;
    }

    @Override
    public boolean disableGroup(User user, Integer groupId, boolean isDisable) {
        Group group = context.getGroupById(groupId);
        if(group == null) {
            throw new OmsException("权限组不存在!");
        }
        if(isDisable) {
            group.setIsDisable(StatusType.YES);
        } else {
            group.setIsDisable(StatusType.NO);
        }
        return this.updateById(group);
    }

    @Override
    public boolean initGroupEnum() {
    	List<Group> groupList = this.getGroupList();
        for(Group group : groupList) {
            GroupEnum.addEnum(group.getId(), group.getName(), group.getIsDisable().getCode());
        }
        return false;
    }

    /**
     * 递归获取当前权限组下的权限组
     *
     * @param group
     * @param list
     */
    private void getGroupAll(Group group, List<Group> list) {
        for(Group childGroup : group.getChildrenGroup().values()) {
            list.add(childGroup);
            getGroupAll(childGroup, list);
        }
    }

	@Override
	public List<Group> getGroupsForUserByUserId(Integer userId) {
		return this.baseMapper.getGroupsForUserByUserId(userId);
	}
}
