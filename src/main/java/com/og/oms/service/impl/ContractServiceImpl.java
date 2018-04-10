package com.og.oms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.og.oms.dao.ContractMapper;
import com.og.oms.enums.ContractTypeEnum;
import com.og.oms.model.Contract;
import com.og.oms.model.User;
import com.og.oms.service.IContractService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 合同资料服务实现类
 * </p>
 *
 * @author jeff
 * @since 2017-10-15
 */
@Service
public class ContractServiceImpl extends ServiceImpl<ContractMapper, Contract> implements IContractService {

    @Override
    public List<Contract> getContractList() {
        EntityWrapper<Contract> wrapper = new EntityWrapper<Contract>();
        return this.selectList(wrapper);
    }

    @Override
    public Contract getContractById(Integer id) {
        return this.selectById(id);
    }

    @Override
    public boolean addContract(User user, Contract contract) {
        contract.setCreateTime(new Date());
        contract.setCreateUser(user.getAccount());
        return this.insert(contract);
    }

    @Override
    public boolean updateContract(User user, Contract contract) {
        contract.setUpdateTime(new Date());
        contract.setUpdateUser(user.getAccount());
        return this.updateById(contract);
    }

    @Override
    public boolean delContract(User user, Integer id) {
        return this.deleteById(id);
    }

    @Override
    public Map<Integer, String> getContractSelect() {
        Map<Integer, String> map = new HashMap<>(ContractTypeEnum.values().length);
        for(ContractTypeEnum type : ContractTypeEnum.values()) {
            map.put(type.getCode(), type.getText());
        }
        return map;
    }

    @Override
    public void initContractTypeEnum() {
        // TODO  暂时还没有
        ContractTypeEnum.addEnum(1, "合同类型1");
        ContractTypeEnum.addEnum(2, "合同类型2");
        ContractTypeEnum.addEnum(3, "合同类型3");
        ContractTypeEnum.addEnum(4, "合同类型4");
        ContractTypeEnum.addEnum(5, "合同类型5");
        ContractTypeEnum.addEnum(6, "合同类型6");
        ContractTypeEnum.addEnum(7, "合同类型7");
        ContractTypeEnum.addEnum(8, "合同类型8");
        ContractTypeEnum.addEnum(9, "合同类型9");
    }
}
