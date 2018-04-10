package com.og.oms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.og.oms.dao.GroupUserMapper;
import com.og.oms.enums.GroupEnum;
import com.og.oms.model.GroupUser;
import com.og.oms.model.User;
import com.og.oms.service.IGroupUserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jeff
 * @since 2017-11-20
 */
@Service
public class GroupUserServiceImpl extends ServiceImpl<GroupUserMapper, GroupUser> implements IGroupUserService {

    @Override
    public List<GroupUser> getGroupUserByUser(Integer userId) {
        EntityWrapper<GroupUser> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id", userId);
        return super.selectList(wrapper);
    }

	@Override
	public boolean updateUserGroup(Integer userId, User user) {
		EntityWrapper<GroupUser> wrapper = new EntityWrapper<>();
		wrapper.eq("user_id",user.getId());
		this.delete(wrapper);
		
		List<GroupEnum> groups = user.getGroups();
		List<GroupUser>  gus = new ArrayList<>();
		for(GroupEnum groupEnum : groups) {
			GroupUser groupUser = new GroupUser();
			groupUser.setGroupId(groupEnum.getCode());
			groupUser.setUserId(user.getId());
			gus.add(groupUser);
		}
		return this.insertBatch(gus);
	}
}
