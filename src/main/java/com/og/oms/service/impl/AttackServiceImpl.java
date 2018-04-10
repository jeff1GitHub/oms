package com.og.oms.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.og.oms.dao.AttackMapper;
import com.og.oms.model.Attack;
import com.og.oms.model.User;
import com.og.oms.service.IAttackService;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jeff
 * @since 2017-11-25
 */
@Service
public class AttackServiceImpl extends ServiceImpl<AttackMapper, Attack> implements IAttackService {

	@Override
	public Attack getAttack(Integer id) {
		Attack attack = this.selectById(id);
		return attack;
	}
	
	@Override
	public boolean addAttack(User user, Attack attack) {
		attack.setCreateTime(new Date());
		attack.setCreateUser(user.getAccount());
		this.insert(attack);
		//TODO 修改或者添加防御信息详情
		
		return false;
	}

	@Override
	public boolean updateAttack(User user, Attack attack) {
		attack.setUpdateTime(new Date());
		attack.setUpdateUser(user.getAccount());
		boolean ret = this.updateById(attack);

		
		return ret;
	}

	@Override
	public List<Attack> getAttackList(Integer platform, Integer level, String search, String startTime,
			String endTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		if(platform != null) {
			map.put("platform", platform);
		}
		
		if(level != null) {
			map.put("level", level);
		}
		
		if(!StringUtils.isEmpty(startTime)) {
			map.put("startTime", startTime);
		}
		
		if(!StringUtils.isEmpty(endTime)) {
			map.put("endTime", endTime);
		}
		
		if(!StringUtils.isEmpty(search)) {
			map.put("search", search);
		}
		
        return this.baseMapper.getAttackList(map);
	}


}
