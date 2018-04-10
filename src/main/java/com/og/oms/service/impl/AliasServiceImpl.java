package com.og.oms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.og.oms.dao.AliasMapper;
import com.og.oms.enums.AliasTypeEnum;
import com.og.oms.exception.OmsException;
import com.og.oms.model.Alias;
import com.og.oms.model.User;
import com.og.oms.service.IAliasService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jeff
 * @since 2017-11-06
 */
@Service
public class AliasServiceImpl extends ServiceImpl<AliasMapper, Alias> implements IAliasService {

    @Override
    public List<Alias> getAliasListByStationCode(String stationCode) {
        EntityWrapper<Alias> wrapper = new EntityWrapper<>();
        wrapper.eq("station",stationCode);
        return this.selectList(wrapper);
    }

    @Override
    public Alias getAliasById(Integer id) {
        return this.getAliasById(id);
    }

    @Override
    public Alias getAliasByAlias(String alias) {
        EntityWrapper<Alias> wrapper = new EntityWrapper<>();
        wrapper.eq("alias", alias);
        return this.selectOne(wrapper);
    }

    @Override
    public boolean addAlias(User user, Alias alias) {
        return this.insert(alias);
    }

    @Override
    public boolean addStationAlias(User user, String stationCode, List<String> aliasList, String payAlias, String payment) {
        // 验证别名是否重复
        List<String> testList = new ArrayList<>();
        if(aliasList != null){
            testList.addAll(aliasList);
        }
        if(!StringUtils.isEmpty(payAlias)) {
        	testList.add(payAlias);
        }
        if(!StringUtils.isEmpty(payment)) {
        	testList.add(payment);
        }
        EntityWrapper<Alias> wrapper = new EntityWrapper<>();
        wrapper.in("alias", testList);
        if(this.selectList(wrapper).size() > 0) {
            throw new OmsException("别名已存在!");
        }

        /*保存别名*/
        List<Alias> list = new ArrayList<>();
        // 支付别名
        list.add(new Alias(user.getAccount(), payAlias.toLowerCase(), stationCode, AliasTypeEnum.ALIAS_TYPE_PAY));
        // 新支付别名
        list.add(new Alias(user.getAccount(), payment.toLowerCase(), stationCode, AliasTypeEnum.ALIAS_TYPE_PAYMENT));
        // 别名
        if(aliasList != null){
            for(String tmp : aliasList) {
                list.add(new Alias(user.getAccount(), tmp.toLowerCase(), stationCode, AliasTypeEnum.ALIAS_TYPE_NORMAL));
            }
        }

        // 批量保存
        return this.insertBatch(list);
    }

    @Override
    public boolean updateAlias(User user, String stationCode, List<String> aliasList, String payAlias, String payment) {
        // 删除已有的别名
        EntityWrapper<Alias> wrapper = new EntityWrapper<>();
        this.delAliasByStationCode(user, stationCode);

        // 验证别名是否重复
        List<String> testList = new ArrayList<>();
        testList.addAll(aliasList);
        if(!StringUtils.isEmpty(payAlias)) {
        	testList.add(payAlias);
        }
        if(!StringUtils.isEmpty(payment)) {
        	testList.add(payment);
        }
        
        wrapper = new EntityWrapper<>();
        wrapper.in("alias", testList);
        List<Alias> selectList = this.selectList(wrapper);
        if(selectList.size() > 0) {
            throw new OmsException("别名已存在!");
        }

        //增加别名
        return this.addStationAlias(user, stationCode, aliasList, payAlias, payment);
    }

    @Override
    public boolean delAlias(User user, String alias) {
        EntityWrapper<Alias> wrapper = new EntityWrapper<>();
        wrapper.eq("alias", alias);
        return this.delete(wrapper);
    }

    @Override
    public boolean delAliasByStationCode(User user, String stationCode) {
        EntityWrapper<Alias> wrapper = new EntityWrapper<>();
        wrapper.eq("station", stationCode);
        return this.delete(wrapper);
    }
}
