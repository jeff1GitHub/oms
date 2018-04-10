package com.og.oms.service.impl;

import com.og.oms.model.AttackMessage;
import com.og.oms.dao.AttackMessageMapper;
import com.og.oms.service.IAttackMessageService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jeff
 * @since 2017-11-26
 */
@Service
public class AttackMessageServiceImpl extends ServiceImpl<AttackMessageMapper, AttackMessage> implements IAttackMessageService {

	@Override
	public List<AttackMessage> getAttackMessageService(Integer attackId) {
		EntityWrapper<AttackMessage> wrapper = new EntityWrapper<>();
		wrapper.eq("attack_message_id", attackId);
		return this.selectList(wrapper);
	}
}
