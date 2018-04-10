package com.og.oms.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.og.oms.dao.CodeMapper;
import com.og.oms.enums.*;
import com.og.oms.model.Code;
import com.og.oms.model.User;
import com.og.oms.service.ICodeService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 字典服务实现类
 * </p>
 *
 * @author jeff
 * @since 2017-10-28
 */
@Service
public class CodeServiceImpl extends ServiceImpl<CodeMapper, Code> implements ICodeService {

    @Override
    public List<Code> getCode(Integer type) {
        EntityWrapper<Code> wrapper = new EntityWrapper<>();
        wrapper.eq("type", type);
        wrapper.orderBy("index");
        return this.selectList(wrapper);
    }

    @Override
    public Code getCodeById(Integer id) {

        return this.selectById(id);
    }

    @Override
    public boolean addCode(User user, Code code) {
        code.setCreateTime(new Date());
        code.setCreateUser(user.getAccount());
        boolean flag = this.insert(code);
        if(flag) {
            initEnum();
        }
        return flag;
    }

    @Override
    public boolean updateCode(User user, Code code) {
        code.setUpdateTime(new Date());
        code.setUpdateUser(user.getAccount());
        boolean flag = this.updateById(code);
        if(flag) {
            initEnum();
        }
        return flag;
    }

    @Override
    public boolean disableCode(User user, Integer id, Integer status) {
        Code code = new Code();
        code.setId(id);
        code.setDisable(status);
        code.setUpdateUser(user.getAccount());
        code.setUpdateTime(new Date());
        boolean flag = this.updateById(code);
        if(flag) {
            initEnum();
        }
        return flag;
    }

    @Override
    public void initEnum() {

        //平台枚举
        this.initPlatformInitEnum();
        //用户等级枚举
        this.initLevelEnum();
        //故障标签枚举
        this.initFaultTypeEnum();
        //优先级
        this.initPriorityEnum();
        //供应商类型
        this.initProducerTypeEnum();
        //银行类型
        this.initBankEnum();
        //区域类型
        this.initAreaEnum();
        //Raid类型
        this.initRaidTypeEnum();
        //品牌类型
        this.initBrandEnum();
        //网设产品类型
        this.initDevicesNumberEnum();
        //网设类型
        this.initDevicesTypeEnum();
        //域名用途
        this.initDomainPurposeEnum();
        //线路品牌
        this.initNetworkBrandEnum();
        //线路类型
        this.initNetworkTypeEnum();
        //部署环境
        this.initEnvironmentEnum();
        //项目名称
        this.initProjectEnum();
        //工程名称
        this.initServiceTypeEnum();
        //部署平台
        this.initDeploymentPlatformEnum();
        //合同类型
        this.initContractTypeEnum();
        //站点类型
        this.initStationTypeEnum();
        //域名类型
        this.initDomainTypeEnum();
    }

//    /**
//     * 修改枚举值
//     *
//     * @param type
//     */
//    private void updateEnum(Integer type) {
//        switch(type) {
//            case 3:
//                this.initPlatformInitEnum();
//                break;
//            case 23:
//                this.initLevelEnum();
//                break;
//            case 18:
//                this.initFaultTypeEnum();
//                break;
//            case 17:
//                this.initPriorityEnum();
//                break;
//            case 29:
//                this.initProducerTypeEnum();
//                break;
//            case 28:
//                this.initBankEnum();
//                break;
//            case 33:
//                this.initAreaEnum();
//                break;
//            case 30:
//                this.initRaidTypeEnum();
//                break;
//            case 12:
//                this.initBrandEnum();
//                break;
//            case 34:
//                this.initDevicesNumberEnum();
//                break;
//            case 11:
//                this.initDevicesTypeEnum();
//                break;
//            case 13:
//                this.initDomainPurposeEnum();
//                break;
//            case 35:
//                this.initNetworkBrandEnum();
//                break;
//            case 10:
//                this.initNetworkTypeEnum();
//                break;
//            case 38:
//                this.initEnvironmentEnum();
//                break;
//            case 39:
//                this.initServiceTypeEnum();
//                break;
//            case 40:
//                this.initProjectEnum();
//                break;
//            case 37:
//                this.initDeploymentPlatformEnum();
//                break;
//        }
//    }

    /**
     * 初始化平台枚举
     */
    private void initPlatformInitEnum() {
        PlatformEnum.clearEnum();
        for(Code code : this.getCode(3)) {
            PlatformEnum.addEnum(code.getValue(), code.getName(), code.getDisable());
        }
    }

    /**
     * 初始化用户等级枚举
     */
    private void initLevelEnum() {
        LevelEnum.clearEnum();
        for(Code code : this.getCode(23)) {
            LevelEnum.addEnum(code.getValue(), code.getName(), code.getDisable());
        }
    }

    /**
     * 初始化故障标签枚举
     */
    private void initFaultTypeEnum() {
        FaultTypeEnum.clearEnum();
        for(Code code : this.getCode(18)) {
            FaultTypeEnum.addEnum(code.getValue(), code.getName(), code.getDisable());
        }
    }

    /**
     * 初始化优先级枚举
     */
    private void initPriorityEnum() {
        PriorityEnum.clearEnum();
        for(Code code : this.getCode(17)) {
            PriorityEnum.addEnum(code.getValue(), code.getName(), code.getDisable());
        }
    }

    /**
     * 初始化供应商角色枚举
     */
    private void initProducerTypeEnum() {
        ProducerTypeEnum.clearEnum();
        for(Code code : this.getCode(29)) {
            ProducerTypeEnum.addEnum(code.getValue(), code.getName(), code.getDisable());
        }
    }

    /**
     * 初始化银行类型枚举
     */
    private void initBankEnum() {
        BankEnum.clearEnum();
        for(Code code : this.getCode(28)) {
            BankEnum.addEnum(code.getValue(), code.getName(), code.getDisable());
        }
    }

    /**
     * 初始化机房地区枚举
     */
    private void initAreaEnum() {
        AreaEnum.clearEnum();
        for(Code code : this.getCode(33)) {
            AreaEnum.addEnum(code.getValue(), code.getName(), code.getDisable());
        }
    }

    /**
     * 磁盘增列类型枚举
     */
    private void initRaidTypeEnum() {
        RaidTypeEnum.clearEnum();
        for(Code code : this.getCode(30)) {
            RaidTypeEnum.addEnum(code.getValue(), code.getName(), code.getDisable());
        }
    }

    /**
     * 品牌枚举
     */
    private void initBrandEnum() {
        BrandEnum.clearEnum();
        for(Code code : this.getCode(12)) {
            BrandEnum.addEnum(code.getValue(), code.getName(), code.getDisable());
        }
    }

    /**
     * 产品型号
     */
    private void initDevicesNumberEnum() {
        DevicesNumberEnum.clearEnum();
        for(Code code : this.getCode(34)) {
            DevicesNumberEnum.addEnum(code.getValue(), code.getName(), code.getDisable());
        }
    }

    /**
     * 网设类型枚举
     */
    private void initDevicesTypeEnum() {
        DevicesTypeEnum.clearEnum();
        for(Code code : this.getCode(11)) {
            DevicesTypeEnum.addEnum(code.getValue(), code.getName(), code.getDisable());
        }
    }

    /**
     * 域名用途
     */
    private void initDomainPurposeEnum() {
        DomainPurposeEnum.clearEnum();
        for(Code code : this.getCode(13)) {
            DomainPurposeEnum.addEnum(code.getValue(), code.getName(), code.getDisable());
        }
    }

    /**
     * 线路品牌
     */
    private void initNetworkBrandEnum() {
        NetworkBrandEnum.clearEnum();
        for(Code code : this.getCode(35)) {
            NetworkBrandEnum.addEnum(code.getValue(), code.getName(), code.getDisable());
        }
    }

    /**
     * 线路类型
     */
    private void initNetworkTypeEnum() {
        NetworkTypeEnum.clearEnum();
        for(Code code : this.getCode(10)) {
            NetworkTypeEnum.addEnum(code.getValue(), code.getName(), code.getDisable());
        }
    }

    /**
     * 部署环境
     */
    private void initEnvironmentEnum() {
        EnvironmentEnum.clearEnum();
        for(Code code : this.getCode(38)) {
            EnvironmentEnum.addEnum(code.getValue(), code.getName(), code.getDisable());
        }
    }

    /**
     * 工程名称
     */
    private void initServiceTypeEnum() {
        ServiceTypeEnum.clearEnum();
        for(Code code : this.getCode(39)) {
            ServiceTypeEnum.addEnum(code.getValue(), code.getName(), code.getDisable());
        }
    }

    /**
     * 项目名称
     */
    private void initProjectEnum() {
        ProjectEnum.clearEnum();
        for(Code code : this.getCode(40)) {
            ProjectEnum.addEnum(code.getValue(), code.getName(), code.getDisable());
        }
    }

    /**
     * 部署信息平台
     */
    private void initDeploymentPlatformEnum() {
        DeploymentPlatformEnum.clearEnum();
        for(Code code : this.getCode(37)) {
            DeploymentPlatformEnum.addEnum(code.getValue(), code.getName(), code.getDisable());
        }
    }
    
    /**
     * 合同类型
     */
    private void initContractTypeEnum() {
    	ContractTypeEnum.clearEnum();
        for(Code code : this.getCode(8)) {
            ContractTypeEnum.addEnum(code.getValue(), code.getName(), code.getDisable());
        }
    }
    
    /**
     * 站点类型
     */
    private void initStationTypeEnum() {
    	StationTypeEnum.clearEnum();
        for(Code code : this.getCode(24)) {
        	StationTypeEnum.addEnum(code.getValue(), code.getName(), code.getDisable());
        }
    }
    
    /**
     * 域名
     */
    private void initDomainTypeEnum() {
    	DomainTypeEnum.clearEnum();
        for(Code code : this.getCode(15)) {
        	DomainTypeEnum.addEnum(code.getValue(), code.getName(), code.getDisable());
        }
    }
}
