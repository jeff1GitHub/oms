package com.og.oms.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.og.oms.dao.GroupPermisMapper;
import com.og.oms.model.GroupPermis;
import com.og.oms.service.IGroupPermisService;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jeff
 * @since 2017-10-24
 */
@Service
public class GroupPermisServiceImpl extends ServiceImpl<GroupPermisMapper, GroupPermis> implements IGroupPermisService {

	@Override
    public List<GroupPermis> getGroupPermisList(Integer groupId) {
        EntityWrapper<GroupPermis> wrapper = new EntityWrapper<>();
        if(groupId == null) {
            wrapper.eq("group_id", groupId);
        }
        return this.selectList(wrapper);
    }
	
	@Override
    public List<GroupPermis> getGroupPermisListForUpdate(Integer groupId) {
        EntityWrapper<GroupPermis> wrapper = new EntityWrapper<>();
        wrapper.eq("group_id", groupId);
        return this.selectList(wrapper);
    }

    @Override
    public boolean validation(List<Integer> list) {
        EntityWrapper<GroupPermis> wrapper = new EntityWrapper<>();
        wrapper.in("group_id", list);
        return this.selectOne(wrapper) != null;
    }

    @Override
	public void addOrUpdateGroupPermis(Integer userId, Integer groupId, Integer[] permisIds) {
		EntityWrapper<GroupPermis> wrapper = new EntityWrapper<>();
		wrapper.eq("group_id", groupId);
		this.delete(wrapper);
		
		List<GroupPermis> entityList = new ArrayList<>();
		
		for(Integer permisid : permisIds) {
			GroupPermis groupPermis = new GroupPermis();
			groupPermis.setGroupId(groupId);
			groupPermis.setPermisId(permisid);
			groupPermis.setCreateUser(userId);
			groupPermis.setCreateTime(new Date());
			groupPermis.setIsDel(0);
			entityList.add(groupPermis);
 		}
		if(entityList.size()>0) {
			this.insertBatch(entityList);
		}
	}
}
