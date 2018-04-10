package com.og.oms.controller;


import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.og.oms.annotation.PermisAnnotation;
import com.og.oms.enums.ResultCode;
import com.og.oms.model.Alias;
import com.og.oms.model.Attack;
import com.og.oms.model.AttackMessage;
import com.og.oms.service.IAliasService;
import com.og.oms.service.IAttackMessageService;
import com.og.oms.service.IAttackService;
import com.og.oms.utils.JsonResult;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jeff
 * @since 2017-11-25
 */
@RestController
@RequestMapping("attack")
@SessionAttributes(names = "userId", types = Integer.class)
public class AttackController extends BaseController{
	
	@Autowired
	private IAttackService attackService;
	
	@Autowired
	private IAttackMessageService attackMessageService;
	
	@Autowired
	private IAliasService aliasService;
	
	/**
     * 分页获取攻击防御列表
     *
     * @return
     */
    @PermisAnnotation(id = 201, name = "攻击防御-列表")
    @RequestMapping(method = RequestMethod.GET)
    public JsonResult getAttackList(Integer platform, Integer level, String search, String startTime, String endTime) {
    	List<Attack> attacks = attackService.getAttackList(platform, level, search, startTime, endTime);
    	
        return new JsonResult(attacks);
    }

    /**
     * 通过id获取供应商信息
     *
     * @param id
     * @return
     */
    @PermisAnnotation(id = 201, name = "攻击防御-查看")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public JsonResult getProducerById(@PathVariable Integer id) {
    	// 获取攻击数据
    	Attack attack = attackService.getAttack(id);
    	
    	//获取对应攻击的详情
    	List<AttackMessage> atkMsgs = attackMessageService.getAttackMessageService(id);
    	attack.setAttackMessages(atkMsgs);
        return new JsonResult(attack);
    }
    
    /**
     * 添加供应商
     *
     * @param userId
     * @param attack
     * @param result
     * @return
     */
    @PermisAnnotation(id = 20101, name = "攻击防御-增加")
    @RequestMapping(method = RequestMethod.PUT)
    public JsonResult addAttack(@ModelAttribute("userId") Integer userId, @Valid Attack attack, BindingResult result) {
        JsonResult ret;
        
        // 判断别名是否存在
        if(attack.getAlias()!=null) {
        	Alias alias = this.aliasService.getAliasByAlias(attack.getAlias());
            if(alias == null) {
                return new JsonResult(ResultCode.EXCEPTION, "别名不存在!");
            }
        }
        
        // 增加攻击信息
        if(attackService.addAttack(this.getUser(userId), attack)) {
        	
    		//TODO 修改或者添加攻击信息详情
    		List<AttackMessage> atkMsgs = new ArrayList<>();
    		List<AttackMessage> attackMessages = attack.getAttackMessages();
    		if(null != attackMessages && attackMessages.size() > 0) {
    			for(AttackMessage attackMessage : attackMessages) {
        			if(!StringUtils.isEmpty(attackMessage.getAttackMessageDomain())) {
        				attackMessage.setAttackMessageId(attack.getId());
        				atkMsgs.add(attackMessage);
        			}
        		}
        		attackMessageService.insertBatch(atkMsgs);
    		}
    		
            ret = new JsonResult();
        } else {
            ret = new JsonResult(ResultCode.EXCEPTION);
        }
        return ret;
    }

    /**
     * 修改供应商
     *
     * @param userId
     * @param attack
     * @return
     */
    @PermisAnnotation(id = 20102, name = "攻击防御-修改")
    @RequestMapping(method = RequestMethod.POST)
    public JsonResult updateAttack(@ModelAttribute("userId") Integer userId, @Valid Attack attack, BindingResult result) {
        JsonResult ret;
        
        // 判断别名是否存在
        if(attack.getAlias()!=null) {
        	Alias alias = this.aliasService.getAliasByAlias(attack.getAlias());
            if(alias == null) {
                return new JsonResult(ResultCode.EXCEPTION, "别名不存在!");
            }
        }  
        
        // 更新攻击相关的详情信息
        if(attackService.updateAttack(this.getUser(userId), attack)) {
    		//TODO 修改或者添加攻击信息详情
    		List<AttackMessage> atkMsgs = new ArrayList<>();
    		List<AttackMessage> attackMessages = attack.getAttackMessages();
    		if(attackMessages != null && attackMessages.size() > 0) {
    			for(AttackMessage attackMessage : attackMessages) {
        			if(!StringUtils.isEmpty(attackMessage.getAttackMessageDomain())) {
        				attackMessage.setAttackMessageId(attack.getId());
        				atkMsgs.add(attackMessage);
        			}
        		}
        		attackMessageService.insertBatch(atkMsgs);
    		}
    		
            ret = new JsonResult();
        } else {
            ret = new JsonResult(ResultCode.EXCEPTION);
        }
        return ret;
    }
}
