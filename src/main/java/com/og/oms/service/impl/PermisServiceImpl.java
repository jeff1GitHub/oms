package com.og.oms.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.og.oms.dao.PermisMapper;
import com.og.oms.model.Group;
import com.og.oms.model.GroupUser;
import com.og.oms.model.Permis;
import com.og.oms.model.User;
import com.og.oms.service.IGroupService;
import com.og.oms.service.IGroupUserService;
import com.og.oms.service.IPermisService;
import com.og.oms.service.IUserService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jeff
 * @since 2017-10-25
 */
@Service
public class PermisServiceImpl extends ServiceImpl<PermisMapper, Permis> implements IPermisService {

	@Autowired
	IUserService userService;
	
	@Autowired
	IGroupUserService groupUserService;
	
	@Autowired
	IGroupService groupService;
	
    @Override
    public List<Permis> selectPermisList() {
        return this.selectList(null);
    }

    @Override
    public List<Permis> getUserPermisList(User user) {
    	
    	// 用户权限依赖关系 user -> group_user -> group -> group_permis -> sys_permis
        
        //获取用户对应的用户组
    	List<Integer> list = new ArrayList<>();
    	
//        List<GroupUser> groupUserByUser = this.groupUserService.getGroupUserByUser(user.getId());
//        groupUserByUser.forEach(o -> {
//    		if(o.getGroupId()!=null) {
//    			list.add(o.getGroupId());
//    		}
//    	});
        
        List<Group> groups = groupService.getGroupsForUserByUserId(user.getId());
        groups.forEach(o -> {
    		if(null != o.getId() && o.getIsDel() ==0 && o.getIsDisable().getCode() == 0) {
    			list.add(o.getId());
    		}
    	});
    	
    	
    	List<Permis> list1 = this.baseMapper.getUserPermisList(list);
    	
//        List<Permis> list = this.selectList(map);

        Map<Integer, Permis> map = new TreeMap<>();
        list1.forEach(o -> map.put(o.getId(), o));

        Permis parentPermis;
        for(Permis permis : list1) {
            if(permis.getParent() == 0) {
                continue;
            }
            parentPermis = map.get(permis.getParent());
            if(parentPermis != null) {
                parentPermis.addPermis(permis);
            }
        }

        return list1.stream().filter(o -> o.getParent() == 0).collect(Collectors.toList());
    }

    /**
     * 递归获取当前权限组下的权限组
     *
     * @param permis
     * @param list
     */
    private void getGroupAll(Permis permis, List<Permis> list) {
        for(Permis childPermis : permis.getChildrenPermis()) {
            list.add(childPermis);
            getGroupAll(childPermis, list);
        }
    }
}
