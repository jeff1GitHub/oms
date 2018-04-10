package com.og.oms.service;

import com.baomidou.mybatisplus.service.IService;
import com.og.oms.model.GroupPermis;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author jeff
 * @since 2017-10-24
 */
public interface IGroupPermisService extends IService<GroupPermis> {
    /**
     * 通过权限组id获取权限组与权限的对应信息
     * 当权限组id为空时获取所有权限组的对应信息
     *
     * @param groupId
     * @return
     */
    List<GroupPermis> getGroupPermisList(Integer groupId);

    boolean validation(List<Integer> list);

	void addOrUpdateGroupPermis(Integer userId, Integer groupId, Integer[] permisIds);
	
	public List<GroupPermis> getGroupPermisListForUpdate(Integer groupId);
}
