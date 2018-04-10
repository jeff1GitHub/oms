package com.og.oms.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.og.oms.dao.UserMapper;
import com.og.oms.enums.GroupEnum;
import com.og.oms.model.Group;
import com.og.oms.model.GroupUser;
import com.og.oms.model.User;
import com.og.oms.service.IGroupService;
import com.og.oms.service.IGroupUserService;
import com.og.oms.service.IUserService;
import com.og.oms.utils.PasswordUtil;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jeff
 * @since 2017-10-17
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
	
	@Autowired
	private IGroupUserService groupUserService;
	
	@Autowired
	private IGroupService groupService;

    @Override
    public List<User> getUserList(Integer group, Integer status, String search, String datemin, String datemax) throws ParseException {

        Map<String, Object> map = new HashMap<>(5);
        if(group != null) {
        	map.put("group", group);
        }
        if(status != null) {
        	map.put("status", status);
        }
        if(!StringUtils.isEmpty(search)) {
        	map.put("search", search);
        }
        if(!StringUtils.isEmpty(datemin)) {
        	map.put("datemin", datemin);
        }
        if(!StringUtils.isEmpty(datemax)) {
        	map.put("datemax", datemax);
        }
        
        // 获取用户列表
        List<User> list = this.baseMapper.getUserListByParam(map);
        
        
//        for(User user : list) {
//        	Wrapper<GroupUser> wrapper = new EntityWrapper<>();
//        	wrapper.eq("user_id", user.getId());
//        	//获取每一个用户对应的权限组
//			List<GroupUser> gus = groupUserService.selectList(wrapper);
//			List<GroupEnum> ges = new ArrayList<>();
//          	String groupInfos = "";
//          	
//          	//将权限组转换为页面显示的格式
//			for(GroupUser gu : gus) {
//          		GroupEnum groupEnum = GroupEnum.getGroupEnum(gu.getGroupId());
//          		if(groupEnum!=null) {
//          			ges.add(groupEnum);
//            		groupInfos  += groupEnum.getName() + " ";
//          		}
//        	}
//        	user.setGroupInfos(groupInfos);
//        }
//        String ids;
//        StringBuilder sb = new StringBuilder();
//        for(User user : list) {
//            ids = user.getGroups1().replace("{", "").replace("}", "").replace("\"", "");
//            for(String tmp : ids.split(",")) {
//                for(GroupEnum gEnum : GroupEnum.values()) {
//                    if(gEnum.getCode() == Integer.valueOf(tmp)) {
//                        sb.append(gEnum.getName()).append(",");
//                    }
//                }
//            }
//            user.setGroups1(sb.toString());
//            sb.delete(0, sb.length());
//        }

        return list;
    }

    @Override
    public User getUserById(Integer id) {

    	//根据id获得对应的组别
    	User user = this.selectById(id);
    	
    	//根据用户id获取对应的组别
    	List<GroupUser> gus = groupUserService.getGroupUserByUser(id);
    	List<GroupEnum> groups = new ArrayList<>();
    	
    	if(gus != null && gus.size()>0) {
    		for(GroupUser gu : gus) {
    			GroupEnum groupEnum = GroupEnum.getGroupEnum(gu.getGroupId());
        		if(groupEnum != null) {
        			groups.add(groupEnum);
        		}
    		}
    	}
    	user.setGroups(groups);
    
        return user;
    }

    @Override
    public User getUserByAccount(String account, String password, String ip) {
    	if(!StringUtils.isEmpty(password)){
    		password = PasswordUtil.encrypt(password);
    	}
        EntityWrapper<User> wrapper = new EntityWrapper<>();
        wrapper.eq("account", account);
        User user = this.selectOne(wrapper);
        if(user == null) {
            user = new User();
            user.setAccount(account);
            user.setPassword(password);
            user.setIsDisable(0);
            user.setCreateUser("system");
            user.setCreateTime(new Date());
            user.setIp("0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip);
            this.insert(user);
        } else {
        	user.setIp("0:0:0:0:0:0:0:1".equals(ip) ? "127.0.0.1" : ip);
            user.setLastLoginTime(new Date());
            this.update(user, wrapper);
        }
        return user;
    }
    
	@Override
	public List<User> getUsersByGroups() {
		return this.baseMapper.getUsersByGroups();
	}

	@Override
	public User selectUserById(Integer id) {
		return this.baseMapper.selectUserById(id);
	}

    @Override
    public boolean addUser(User user) {
        user.setCreateTime(new Date());
        user.setCreateUser("system");
        return this.insert(user);
    }

    @Override
    public boolean updateUser(User user, User target) {
        target.setUpdateUser(user.getAccount());
        target.setUpdateTime(new Date());
        return this.updateById(target);
    }

    @Override
    public boolean disableUser(User user, Integer userId, Integer status) {
        User targetUser = this.selectById(userId);
        targetUser.setUpdateUser(user.getAccount());
        targetUser.setUpdateTime(new Date());
        targetUser.setIsDisable(status);
        return super.updateById(targetUser);
    }

	@Override
	public boolean checkUserGroupsIsDisable(User user) {
		//判断用户所在的权限组是否被禁用
		List<Group> groups = groupService.getGroupsForUserByUserId(user.getId());
		// 用户在首次设置权限组之前，存在没有组的情况，所以要先判断用户是否已经设置权限组
		
		if(null != groups && groups.size() > 0) {
			// 已经设置权限组，判断用户所在的多个权限组是否都已经被禁用，只要有一个未被禁用都可以登陆。
			for(Group gu : groups) {
				if(gu.getIsDel() == 0 && gu.getIsDisable().getCode() == 0) {
					return true;
				}
			}
			// 没有一个权限组启用
			return false;
		} else {
			// 没有设置权限组，通过
			return true;
		}
	}
}
