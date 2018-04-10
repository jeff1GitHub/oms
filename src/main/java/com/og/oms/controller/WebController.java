package com.og.oms.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.og.oms.annotation.PermisAnnotation;
import com.og.oms.common.Context;
import com.og.oms.enums.BankEnum;
import com.og.oms.enums.BrandEnum;
import com.og.oms.enums.DeploymentPlatformEnum;
import com.og.oms.enums.DevicesNumberEnum;
import com.og.oms.enums.DevicesTypeEnum;
import com.og.oms.enums.DomainPurposeEnum;
import com.og.oms.enums.EnvironmentEnum;
import com.og.oms.enums.FaultTypeEnum;
import com.og.oms.enums.GroupEnum;
import com.og.oms.enums.LevelEnum;
import com.og.oms.enums.NetworkBrandEnum;
import com.og.oms.enums.NetworkTypeEnum;
import com.og.oms.enums.OperLogTypeEnum;
import com.og.oms.enums.PlatformEnum;
import com.og.oms.enums.PriorityEnum;
import com.og.oms.enums.ProducerEnum;
import com.og.oms.enums.ProducerTypeEnum;
import com.og.oms.enums.ProjectEnum;
import com.og.oms.enums.RaidTypeEnum;
import com.og.oms.enums.ServerRoomEnum;
import com.og.oms.enums.ServiceTypeEnum;
import com.og.oms.enums.StationTypeEnum;
import com.og.oms.enums.StatusType;
import com.og.oms.enums.WorkStatusEnum;
import com.og.oms.exception.OmsException;
import com.og.oms.model.Group;
import com.og.oms.model.GroupUser;
import com.og.oms.model.Permis;
import com.og.oms.model.User;
import com.og.oms.service.IContractService;
import com.og.oms.service.IGroupService;
import com.og.oms.service.IGroupUserService;
import com.og.oms.service.ILdapService;
import com.og.oms.service.ILoginLogService;
import com.og.oms.service.IPermisService;
import com.og.oms.service.IProducerService;
import com.og.oms.service.IServerService;
import com.og.oms.service.IUserService;
import com.og.oms.utils.JsonResult;

@Controller
@SessionAttributes(names = "userId", types = Integer.class)
public class WebController extends BaseController {
	@Autowired
	private Context context;

	@Autowired
	private IContractService contractService;

	@Autowired
	private IGroupService groupService;
	
	@Autowired
	private ILoginLogService loginLogService;

	@Autowired
	private IProducerService producerService;

	@Autowired
	private ILdapService ldapService;

	@Autowired
	private IUserService userService;

	@Autowired
	IPermisService permisService;

	@Autowired
	private IServerService serverService;


	@ResponseBody
	@RequestMapping(value = "login1")
	public void login1(HttpServletRequest req, String account) {
		User user = new User();
		user.setId(3);
		user.setAccount(account);
		context.addUser(user);
		// user.addGroups(context.getGroupById(1));

		loginLogService.addLogin(user, "3213213");

		req.getSession().setAttribute("userId", user.getId());
	}

	@RequestMapping(value = "login")
	public ModelAndView loginPage(ModelAndView model) {
		return model;
	}

	@ResponseBody
	@RequestMapping(value = "user-login")
	public JsonResult userLogin(String account, String password) throws IOException {
		JsonResult ret;
		if (ldapService.authenticate(account, password)) {
//		if(true) {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			HttpSession session = request.getSession();

			User user = userService.getUserByAccount(account, password, request.getRemoteAddr());
			if(user.getIsDisable()==1) {
				throw new OmsException("用户已被禁用！");
			} else {
				
				// 判断用户所在的权限组是否存在没有被禁用的，用户可以设置多个权限组，只要有一个权限组可用，即为可以登录。
				boolean isDisable = userService.checkUserGroupsIsDisable(user);
				if(!isDisable) {
					throw new OmsException("用户所在组已被禁用！");
				}
				
				// 写登录日志
				loginLogService.addLogin(user, request.getRemoteAddr());

				// 获取登录用户的权限树
				List<Permis> userPermisList = permisService.getUserPermisList(user);
				
				session.setAttribute("userId", user.getId());
				session.setAttribute("userPermis", userPermisList);
				ret = new JsonResult();
			}
		} else {
			throw new OmsException("帐号密码错误");
		}
		return ret;
	}

	@ResponseBody
	@RequestMapping(value = "user-logout")
	public JsonResult userLogout(Integer userId) throws IOException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		session.removeAttribute("userId");
		session.removeAttribute("userPermis");
		return new JsonResult();
	}

	/**
	 * 首页
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping("/")
	public ModelAndView indexPage(Integer userId, ModelAndView model) {
		model.setViewName("index");
		model.addObject("userGroup","");
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		userId = (Integer) session.getAttribute("userId");
		User user = userService.getUserById(userId);
		model.addObject("userName",user.getAccount());
		StringBuilder groupNames = new StringBuilder("");
		List<GroupEnum> groups = user.getGroups();
		
		if(groups != null && groups.size() > 0) {
			for(GroupEnum group : groups) {
				groupNames.append(group.getName() + ",");
			}
			model.addObject("userGroup", groupNames.toString().substring(0, groupNames.length() - 1));;
		}
		return model;
	}

	/**
	 * 欢迎页面
	 *
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "welcome")
	public ModelAndView welcomePage(ModelAndView model) {
		return model;
	}

	/**
	 * 避免超时
	 *
	 * @param time
	 * @return
	 */
	@RequestMapping(value = "keepAlive", method = RequestMethod.GET)
	@ResponseBody
	public JsonResult keepAlive(String time) {
		return new JsonResult(time);
	}

	// ====================================================================================================
	// ===============================线路管理=============================================================
	// ====================================================================================================

	/**
	 * 客服工单列表页面
	 *
	 * @param model
	 * @return
	 */
	@PermisAnnotation(id = 101, name = "客服任务")
	@RequestMapping(value = "work-server-list")
	public ModelAndView workServerListpage(ModelAndView model) {
		model.addObject("level", LevelEnum.getSelect());
		model.addObject("platform", PlatformEnum.getSelect());
		model.addObject("faultType", FaultTypeEnum.getSelect());
		model.addObject("priority", PriorityEnum.getSelect());
		model.addObject("workStatusSelect", WorkStatusEnum.getSelect());
		model.addObject("workStatus", 0);
		model.addObject("role", 1);
		model.setViewName("work-list");
		return model;
	}
	
	/**
	 * 运维工单列表页面
	 *
	 * @param model
	 * @return
	 */
	@PermisAnnotation(id = 102, name = "运维任务")
	@RequestMapping(value = "work-operations-list")
	public ModelAndView workOperationsListpage(ModelAndView model) {
		model.addObject("level", LevelEnum.getSelect());
		model.addObject("platform", PlatformEnum.getSelect());
		model.addObject("faultType", FaultTypeEnum.getSelect());
		model.addObject("priority", PriorityEnum.getSelect());
		model.addObject("workStatusSelect", WorkStatusEnum.getSelect());
		model.addObject("workStatus", 0);
		model.addObject("role", 2);
		model.setViewName("work-list");
		return model;
	}
	
	/**
	 * 客服工单列表页面
	 *
	 * @param model
	 * @return
	 */
	@PermisAnnotation(id = 101, name = "客服任务")
	@RequestMapping(value = "work-server-msg")
	public ModelAndView workServerListPageforMsg(ModelAndView model) {
		model.addObject("level", LevelEnum.getSelect());
		model.addObject("platform", PlatformEnum.getSelect());
		model.addObject("faultType", FaultTypeEnum.getSelect());
		model.addObject("priority", PriorityEnum.getSelect());
		model.addObject("workStatusSelect", WorkStatusEnum.getSelect());
		model.addObject("workStatus", 3);
		model.addObject("role", 1);
		model.setViewName("work-list");
		return model;
	}
	
	/**
	 * 运维工单列表页面
	 *
	 * @param model
	 * @return
	 */
	@PermisAnnotation(id = 102, name = "运维任务")
	@RequestMapping(value = "work-operations-msg")
	public ModelAndView workOperationsListpageforMsg(ModelAndView model) {
		model.addObject("level", LevelEnum.getSelect());
		model.addObject("platform", PlatformEnum.getSelect());
		model.addObject("faultType", FaultTypeEnum.getSelect());
		model.addObject("priority", PriorityEnum.getSelect());
		model.addObject("workStatusSelect", WorkStatusEnum.getSelect());
		model.addObject("workStatus", 1);
		model.addObject("role", 2);
		model.setViewName("work-list");
		return model;
	}

	/**
	 * 客服工单内容页面
	 *
	 * @param model
	 * @return
	 */
	@PermisAnnotation(id = 101, name = "客服任务")
	@RequestMapping(value = "work-server-info")
	public ModelAndView workServerInfopage(ModelAndView model) {
		model.addObject("faultType", FaultTypeEnum.getSelect());
		model.addObject("priority", PriorityEnum.getSelect());
		model.setViewName("work-info");
		return model;
	}

	/**
	 * 运维工单内容页面
	 *
	 * @param model
	 * @return
	 */
	@PermisAnnotation(id = 102, name = "运维任务")
	@RequestMapping(value = "work-operations-info")
	public ModelAndView workOperationsInfopage(ModelAndView model) {
		model.setViewName("work-info");
		model.addObject("faultType", FaultTypeEnum.getSelect());
		model.addObject("priority", PriorityEnum.getSelect());
		return model;
	}
	// ====================================================================================================
	// ===============================运维部署=============================================================
	// ====================================================================================================

	/**
	 * 攻击防御列表页面
	 *
	 * @param model
	 * @return
	 */
	@PermisAnnotation(id = 201, name = "攻击防御列表")
	@RequestMapping(value = "attack-list")
	public ModelAndView attackListPage(ModelAndView model) {
		model.setViewName("attack-list");
		model.addObject("platform", PlatformEnum.getSelect());
		model.addObject("level", LevelEnum.getSelect());
		return model;
	}

	/**
	 * 服务器信息页面
	 *
	 * @param model
	 * @return
	 */
	@PermisAnnotation(id = 201, name = "攻击防御编辑")
	@RequestMapping(value = "attack-info")
	public ModelAndView attackInfoPage(ModelAndView model) {
		List<User> opUsers = this.userService.getUsersByGroups();
		model.addObject("platform", PlatformEnum.getSelect());
		model.addObject("level", LevelEnum.getSelect());
		model.addObject("opUsers", opUsers);
		return model;
	}

	// ====================================================================================================
	// ===============================信息整合=============================================================
	// ====================================================================================================

	/**
	 * 服务器列表页面
	 *
	 * @param model
	 * @return
	 */
	@PermisAnnotation(id = 301, name = "服务器资料")
	@RequestMapping(value = "server-list")
	public ModelAndView serverListPage(ModelAndView model) {
		// 获取服务器信息，存入下拉列表
		model.addObject("serverList", serverService.getServerListForDropDown());
		// TODO 如需枚举需要处理
		model.setViewName("server-list");
		return model;
	}

	/**
	 * 服务器信息页面
	 *
	 * @param model
	 * @return
	 */
	@PermisAnnotation(id = 301, name = "服务器资料")
	@RequestMapping(value = "server-info")
	public ModelAndView serverInfoPage(ModelAndView model) {
		model.addObject("platform", PlatformEnum.getSelect());
		model.addObject("raidType", RaidTypeEnum.getSelect());
		model.addObject("serverroom", ServerRoomEnum.getSelect());
		model.addObject("raid", RaidTypeEnum.getSelect());
		return model;
	}

	/**
	 * 服务部署资料页面
	 *
	 * @param model
	 * @return
	 */
	@PermisAnnotation(id = 309, name = "服务器部署资料增加")
	@RequestMapping(value = "server-deployment-info")
	public ModelAndView serverDeploymentInfoPage(ModelAndView model) {
		model.addObject("project", ProjectEnum.getSelect());
		model.addObject("platform", DeploymentPlatformEnum.getSelect());
		model.addObject("environment", EnvironmentEnum.getSelect());
		model.addObject("service", ServiceTypeEnum.getSelect());
		return model;
	}

	/**
	 * 域名列表页面
	 *
	 * @param model
	 * @return
	 */
	@PermisAnnotation(id = 302, name = "域名资料")
	@RequestMapping(value = "domain-list")
	public ModelAndView domainListPage(ModelAndView model) {
		model.addObject("purpose", DomainPurposeEnum.getSelect());
		model.addObject("platform", PlatformEnum.getSelect());
		return model;
	}

	/**
	 * 域名信息页面
	 *
	 * @param model
	 * @return
	 */
	@PermisAnnotation(id = 302, name = "域名资料")
	@RequestMapping(value = "domain-info")
	public ModelAndView domainInfoPage(ModelAndView model) {
		model.addObject("purpose", DomainPurposeEnum.getSelect());
		model.addObject("platform", PlatformEnum.getSelect());
		model.addObject("producer", ProducerEnum.getSelect());
		return model;
	}
	
	/**
	 * 域名信息页面
	 *
	 * @param model
	 * @return
	 */
	@PermisAnnotation(id = 302, name = "域名资料详情")
	@RequestMapping(value = "domain-detail")
	public ModelAndView domainDetailPage(ModelAndView model) {
		return model;
	}

	/**
	 * 供应商列表页面
	 *
	 * @param model
	 * @return
	 */
	@PermisAnnotation(id = 303, name = "供应商资料")
	@RequestMapping(value = "producer-list")
	public ModelAndView producerListPage(ModelAndView model) {
		return model;
	}

	/**
	 * 供应商信息页面
	 *
	 * @param model
	 * @return
	 */
	@PermisAnnotation(id = 303, name = "供应商资料")
	@RequestMapping(value = "producer-info")
	public ModelAndView producerInfoPage(ModelAndView model) {
		model.addObject("role", ProducerTypeEnum.getSelect());
		model.addObject("bankType", BankEnum.getSelect());
		return model;
	}

	/**
	 * 联系人列表页面
	 *
	 * @param model
	 * @return
	 */
	@PermisAnnotation(id = 303, name = "供应商资料")
	@RequestMapping(value = "linkman-list")
	public ModelAndView linkmanListPage(ModelAndView model) {
		return model;
	}

	/**
	 * 联系人信息页面
	 *
	 * @param model
	 * @return
	 */
	@PermisAnnotation(id = 303, name = "供应商资料")
	@RequestMapping(value = "linkman-info")
	public ModelAndView linkmanInfoPage(ModelAndView model) {
		model.addObject("producer", ProducerEnum.getSelect());
		return model;
	}

	/**
	 * 合同列表页面
	 *
	 * @param model
	 * @return
	 */
	@PermisAnnotation(id = 304, name = "合同资料")
	@RequestMapping(value = "contract-list")
	public ModelAndView contractListPage(ModelAndView model) {
		return model;
	}

	/**
	 * 合同详细页面
	 *
	 * @param model
	 * @return
	 */
	@PermisAnnotation(id = 304, name = "合同资料")
	@RequestMapping(value = "contract-info")
	public ModelAndView contractInfoPage(ModelAndView model) {
		model.addObject("contractSelect", contractService.getContractSelect());
		model.addObject("producerSelect", producerService.getProducerSelect());
		return model;
	}

	/**
	 * 机房列表页面
	 *
	 * @param model
	 * @return
	 */
	@PermisAnnotation(id = 305, name = "机房资料")
	@RequestMapping(value = "serverroom-list")
	public ModelAndView serverroomListPage(ModelAndView model) {
		return model;
	}

	/**
	 * 机房信息页面
	 *
	 * @param model
	 * @return
	 */
	@PermisAnnotation(id = 305, name = "机房资料")
	@RequestMapping(value = "serverroom-info")
	public ModelAndView serverroomInfoPage(ModelAndView model) {

		model.addObject("producer", producerService.getProducerSelect());
		return model;
	}

	/**
	 * 线路列表页面
	 *
	 * @param model
	 * @return
	 */
	@PermisAnnotation(id = 306, name = "线路资料")
	@RequestMapping(value = "network-list")
	public ModelAndView networkListPage(ModelAndView model) {
		return model;
	}

	/**
	 * 线路列表页面
	 *
	 * @param model
	 * @return
	 */
	@PermisAnnotation(id = 306, name = "线路资料")
	@RequestMapping(value = "network-info")
	public ModelAndView networkInfoPage(ModelAndView model) {
		model.addObject("platform", PlatformEnum.getSelect());
		model.addObject("brand", NetworkBrandEnum.getSelect());
		model.addObject("type", NetworkTypeEnum.getSelect());
		model.addObject("producer", ProducerEnum.getSelect());
		model.addObject("serverroom", ServerRoomEnum.getSelect());
		return model;
	}

	/**
	 * 网设列表页面
	 *
	 * @param model
	 * @return
	 */
	@PermisAnnotation(id = 307, name = "网设资料")
	@RequestMapping(value = "devices-list")
	public ModelAndView devicesListPage(ModelAndView model) {
		return model;
	}

	/**
	 * 网设信息页面
	 *
	 * @param model
	 * @return
	 */
	@PermisAnnotation(id = 307, name = "网设资料")
	@RequestMapping(value = "devices-info")
	public ModelAndView devicesInfoPage(ModelAndView model) {
		model.addObject("producer", producerService.getProducerSelect());
		model.addObject("brand", BrandEnum.values());
		model.addObject("platform", PlatformEnum.values());
		model.addObject("serverroom", ServerRoomEnum.values());
		model.addObject("code", DevicesNumberEnum.values());
		model.addObject("type", DevicesTypeEnum.values());
		return model;
	}

	/**
	 * 站点列表页面
	 *
	 * @param model
	 * @return
	 */
	@PermisAnnotation(id = 308, name = "站点资料")
	@RequestMapping(value = "station-list")
	public ModelAndView stationListPage(ModelAndView model) {
		model.addObject("platform", PlatformEnum.getSelect());
		model.addObject("level", LevelEnum.getSelect());
		model.addObject("type", StationTypeEnum.getSelect());
		return model;
	}

	/**
	 * 站点信息页面
	 *
	 * @param model
	 * @return
	 */
	@PermisAnnotation(id = 308, name = "站点资料")
	@RequestMapping(value = "station-info")
	public ModelAndView stationInfoPage(ModelAndView model) {
		model.addObject("platform", PlatformEnum.getSelect());
		model.addObject("level", LevelEnum.getSelect());
		model.addObject("type", StationTypeEnum.getSelect());
		return model;
	}

	/**
	 * 服务部署资料页面
	 *
	 * @param model
	 * @return
	 */
	@PermisAnnotation(id = 309, name = "服务部署资料")
	@RequestMapping(value = "deployment-list")
	public ModelAndView servicePagePage(ModelAndView model) {
		model.addObject("platform", DeploymentPlatformEnum.getSelect());
		model.addObject("environment", EnvironmentEnum.getSelect());
		model.addObject("project", ProjectEnum.getSelect());
		model.addObject("service", ServiceTypeEnum.getSelect());
		return model;
	}

	/**
	 * 服务部署资料页面
	 *
	 * @param model
	 * @return
	 */
	@PermisAnnotation(id = 309, name = "服务部署资料")
	@RequestMapping(value = "deployment-info")
	public ModelAndView serviceInfoPage(ModelAndView model) {
		model.addObject("project", ProjectEnum.getSelect());
		model.addObject("platform", DeploymentPlatformEnum.getSelect());
		model.addObject("environment", EnvironmentEnum.getSelect());
		model.addObject("service", ServiceTypeEnum.getSelect());
		return model;
	}
	// ====================================================================================================
	// ===============================报表中心=============================================================
	// ====================================================================================================

	/**
	 * 机房问题报表页面
	 *
	 * @param model
	 * @return
	 */
	@PermisAnnotation(id = 401, name = "机房问题报表")
	@RequestMapping(value = "report-serverroom-list")
	public ModelAndView reportServerroomPage(ModelAndView model) {
		model.addObject("platform", PlatformEnum.getSelect());
		return model;
	}

	/**
	 * 网址问题排行榜
	 *
	 * @param model
	 * @return
	 */
	@PermisAnnotation(id = 402, name = "网址问题排行榜")
	@RequestMapping(value = "report-domain-list")
	public ModelAndView reportDomainPage(ModelAndView model) {
		model.addObject("platform", PlatformEnum.getSelect());
		return model;
	}

	/**
	 * 平台问题报表
	 *
	 * @param model
	 * @return
	 */
	@PermisAnnotation(id = 403, name = "平台问题报表")
	@RequestMapping(value = "report-platform-list")
	public ModelAndView reportPlatformPage(ModelAndView model) {
		model.addObject("platform", PlatformEnum.getSelect());
		//model.setViewName("report-platform-list2");
		return model;
	}

	/**
	 * 站点问题排行
	 *
	 * @param model
	 * @return
	 */
	@PermisAnnotation(id = 404, name = "站点问题报表")
	@RequestMapping(value = "report-station-list")
	public ModelAndView reportStationPage(ModelAndView model) {
		model.addObject("platform", PlatformEnum.getSelect());
		return model;
	}

	/**
	 * 别名问题排行
	 *
	 * @param model
	 * @return
	 */
	@PermisAnnotation(id = 405, name = "别名问题报表")
	@RequestMapping(value = "report-alias-list")
	public ModelAndView reportAliasPage(ModelAndView model) {
		model.addObject("platform", PlatformEnum.getSelect());
		return model;
	}

	// ====================================================================================================
	// ===============================权限控制=============================================================
	// ====================================================================================================

	/**
	 * 权限组列表页面
	 *
	 * @param model
	 * @return
	 */
	@PermisAnnotation(id = 501, name = "组别管理")
	@RequestMapping(value = "group-list")
	public ModelAndView groupListPage(ModelAndView model) {
		return model;
	}

	/**
	 * 权限组信息页面
	 *
	 * @param model
	 * @return
	 */
	@PermisAnnotation(id = 501, name = "组别管理")
	@RequestMapping(value = "group-info")
	public ModelAndView groupInfoPage(@ModelAttribute("userId") Integer userId, ModelAndView model) {
		Collection<Group> list = groupService.getChildGroupListForUser(this.getUser(userId));
		Map<Integer, String> groupSelect = new TreeMap<>();
		list.forEach(o -> groupSelect.put(o.getId(), o.getName()));
		model.addObject("parentGroup", groupSelect);
		return model;
	}

	/**
	 * 权限组权限页面
	 *
	 * @param userId
	 * @param model
	 * @return
	 */
	@PermisAnnotation(id = 501, name = "组别权限管理")
	@RequestMapping(value = "group-permis")
	public ModelAndView groupPermisPage(@ModelAttribute("userId") Integer userId, Integer id, ModelAndView model) {
		model.addObject("groupId", id);
		model.addObject("permis", permisService.getUserPermisList(super.getUser(userId)));
		return model;
	}

	/**
	 * 用户列表页面
	 *
	 * @param model
	 * @return
	 */
	@PermisAnnotation(id = 502, name = "员工管理")
	@RequestMapping(value = "user-list")
	public ModelAndView userListPage(ModelAndView model) {
		model.addObject("status", StatusType.getSelect());
		model.addObject("group", GroupEnum.getSelect());
		return model;
	}

	/**
	 * 用户详细页面
	 *
	 * @param model
	 * @return
	 */
	@PermisAnnotation(id = 502, name = "员工管理")
	@RequestMapping(value = "user-info")
	public ModelAndView userInfoPage(@ModelAttribute("userId") Integer userId, ModelAndView model) {
		// model.addObject("groupTypes", GroupEnum.getSelect());
		Collection<Group> list = groupService.getChildGroupListForUser(this.getUser(userId));
		Map<Integer, String> groupSelect = new TreeMap<>();
		list.forEach(o -> groupSelect.put(o.getId(), o.getName()));
		model.addObject("groupTypes", groupSelect);
		return model;
	}

	/**
	 * 日志列表页面
	 *
	 * @param model
	 * @return
	 */
	@PermisAnnotation(id = 503, name = "操作日志")
	@RequestMapping(value = "log-list")
	public ModelAndView logListPage(ModelAndView model) {
		model.addObject("logType",OperLogTypeEnum.getSelect());
		return model;
	}

	/**
	 * 登录日志列表
	 *
	 * @param model
	 * @return
	 */
	@PermisAnnotation(id = 504, name = "登录日志")
	@RequestMapping(value = "login-list")
	public ModelAndView loginListPage(ModelAndView model) {
		return model;
	}

	// ====================================================================================================
	// ===============================系统设置=============================================================
	// ====================================================================================================

	/**
	 * 系统字典列表页面
	 *
	 * @param model
	 * @return
	 */
	@PermisAnnotation(id = 601, name = "系统字典")
	@RequestMapping(value = "system-code-list")
	public ModelAndView codeListPage(ModelAndView model) {
		return model;
	}

	/**
	 * 系统字典信息页面
	 *
	 * @param model
	 * @return
	 */
	@PermisAnnotation(id = 601, name = "系统字典")
	@RequestMapping(value = "system-code-info")
	public ModelAndView systemCodeInfoPage(ModelAndView model) {
		return model;
	}
}
