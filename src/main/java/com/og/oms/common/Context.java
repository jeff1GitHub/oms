package com.og.oms.common;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.og.oms.enums.DomainTypeEnum;
import com.og.oms.enums.StationTypeEnum;
import com.og.oms.model.Group;
import com.og.oms.model.GroupPermis;
import com.og.oms.model.Permis;
import com.og.oms.model.User;
import com.og.oms.service.ICodeService;
import com.og.oms.service.IContractService;
import com.og.oms.service.IGroupPermisService;
import com.og.oms.service.IGroupService;
import com.og.oms.service.IPermisService;
import com.og.oms.service.IProducerService;
import com.og.oms.service.IServerRoomService;
import com.og.oms.service.IUserService;

@Component
public class Context {

    @Autowired
    private IGroupService groupService;

    @Autowired
    private IPermisService permisService;

    @Autowired
    private IGroupPermisService groupPermisService;
    ;
    @Autowired
    private IUserService userService;

    @Autowired
    private IProducerService producerService;

    @Autowired
    private IServerRoomService serverRoomService;

    @Autowired
    private IContractService contractService;

    @Autowired
    private ICodeService codeService;

    private Map<Integer, Group> groupMap;

    private ConcurrentHashMap<Integer, User> userMap = new ConcurrentHashMap<Integer, User>();

    private Map<Integer, Permis> permisMap;

    @PostConstruct
    private void init() {
        // 初始化权限
        this.initPermis();
        // 初始化权限组
        this.initGroup();
        // 构建权限和权限组的关系
        this.initGroupPermis();

        // 初始化供应商枚举
        this.producerService.initProducerEnum();
        // 初始化机房枚举
        this.serverRoomService.initServerRoomEnum();
        // 初始化合同类型枚举
        this.contractService.initContractTypeEnum();
        //TODO 初始化服务器枚举

        // 初始化权限组枚举
        this.groupService.initGroupEnum();

        // 初始化系统字典枚举
        this.codeService.initEnum();

        // 初始化测试用枚举
        this.initTestEnum();
    }

    private void initTestEnum() {
//        BrandEnum.addEnum(1, "华硕");
//        DevicesNumberEnum.addEnum(1, "p1000");
//        DevicesTypeEnum.addEnum(1, "路由器");
//        PlatformEnum.addEnum(99, "BWT99");

//        StationTypeEnum.addEnum(1,"主站点");
//        StationTypeEnum.addEnum(2,"子站点");
//
//        DomainTypeEnum.addEnum(1,"主域名");
//        DomainTypeEnum.addEnum(2,"测试域名");
//        DomainTypeEnum.addEnum(3,"客户测试域名");
//        DomainTypeEnum.addEnum(4,"后台域名");
    }

    /**
     * 初始化权限组列表并给权限增加层级关系
     */
    private void initGroup() {
        List<Group> list = groupService.getGroupList();
        Map<Integer, Group> groupMap = new HashMap<>(list.size());
        list.forEach(o -> groupMap.put(o.getId(), o));

        // 构建权限组层级
        Group parentGroup = null;
        for(Group group : groupMap.values()) {
            parentGroup = groupMap.get(group.getParentId());
            if(parentGroup != null) {
                parentGroup.addChildrenGroup(group);
            }
        }

        this.groupMap = groupMap;
    }

    /**
     * 初始化权限列表
     */
    private void initPermis() {
        List<Permis> list = this.permisService.selectPermisList();
        Map<Integer, Permis> permisMap = new HashMap<>();
        list.forEach(o -> permisMap.put(o.getId(), o));

        this.permisMap = permisMap;
    }

    /**
     * 初始化权限组权限
     */
    private void initGroupPermis() {
        List<GroupPermis> list = groupPermisService.getGroupPermisList(null);

        Group group = null;
        Permis permis = null;
        for(GroupPermis gp : list) {
            group = this.groupMap.get(gp.getGroupId());
            if(group == null) {
                continue;
            }

            permis = this.permisMap.get(gp.getPermisId());
            if(permis == null) {
                continue;
            }

            group.addPermis(permis);
        }
    }

    /**
     * 通过id获取内存中的权限组
     *
     * @param groupId
     * @return
     */
    public Group getGroupById(Integer groupId) {
        return groupMap.get(groupId);
    }

    /**
     * 通过id删除内存中的权限组
     *
     * @param groupId
     */
    public void delGroupByid(Integer groupId) {
        Group group = this.groupMap.remove(groupId);
        Group parentGroup = this.groupMap.get(group.getParentId());
        if(parentGroup != null) {
            parentGroup.delChildrenGroup(groupId);
        }
    }

    /**
     * 增加权限组
     *
     * @param group
     */
    public void addGroup(Group group) {
        this.groupMap.put(group.getId(), group);
        Group parentGroup = this.groupMap.get(group.getParentId());
        if(parentGroup != null) {
            parentGroup.addChildrenGroup(group);
        }
    }

    /**
     * 增加用户到内存
     *
     * @param user
     */
    public void addUser(User user) {
        userMap.put(user.getId(), user);
    }

    /**
     * 通过id获取用户对象
     *
     * @param userId
     * @return
     */
    public User getUserById(Integer userId) {
        User user = userMap.get(userId);
        if(user == null) {
            user = this.userService.selectById(userId);
        }
        return user;
    }
}
