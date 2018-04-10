package com.og.oms.controller;


import com.og.oms.annotation.PermisAnnotation;
import com.og.oms.enums.LinkTypeEnum;
import com.og.oms.enums.ResultCode;
import com.og.oms.model.Linkman;
import com.og.oms.model.LinkmanInformation;
import com.og.oms.service.ILinkmanInformationService;
import com.og.oms.service.ILinkmanService;
import com.og.oms.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jeff
 * @since 2017-11-02
 */
@RestController
@RequestMapping("linkman")
@SessionAttributes(names = "userId", types = Integer.class)
public class LinkmanController extends BaseController {

    @Autowired
    private ILinkmanService linkmanService;
    
    @Autowired
    private ILinkmanInformationService linkmanInformationService;


    /**
     * 获取所有的联系人
     *
     * @param producerId
     * @return
     */
    @PermisAnnotation(id = 303, name = "供应商资料-联系人-列表")
    @RequestMapping(method = RequestMethod.GET)
    public JsonResult getLinkmanList(Integer producerId, HttpServletRequest request) {
        return new JsonResult(this.linkmanService.getLinkmanList(producerId));
    }

    /**
     * 通过id获取联系人资料
     *
     * @param id 联系人id
     * @return
     */
    @PermisAnnotation(id = 303, name = "供应商资料-联系人-查看")
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public JsonResult getLinkmanById(@PathVariable Integer id) {
    	
    	//根据供应商ID获取对应的联系人列表，以及联系人的联系方式
    	Linkman linkmanWithInfoById = this.linkmanService.getLinkmanWithInfoById(id);
    	
    	JsonResult jsonResult = new JsonResult(linkmanWithInfoById);
    	List<LinkmanInformation> linkmanInfos = linkmanWithInfoById.getLinkmanInformations();
    	
    	//将联系人的联系方式放到map中以供在页面的个文本框中显示
    	Map<Object, Object> others = jsonResult.getOthers();
    	linkmanInfos.forEach(linkmanInfo -> {

//    		switch (linkmanInfo.getType()){
//				case 1:
//					others.put(LinkTypeEnum.QQ.getName(), linkmanInfo.getMessage());
//					if(linkmanInfo.getIsDefault()==1) {
//						others.put("defaultContactType", linkmanInfo.getType());
//					}
//					break;
//			}

    		if(linkmanInfo.getType().equals(LinkTypeEnum.QQ.getCode())) {//qq
    			others.put(LinkTypeEnum.QQ.getName(), linkmanInfo.getMessage());
    			if(linkmanInfo.getIsDefault()==1) {
    				others.put("defaultContactType", linkmanInfo.getType());
    			}
    		} else if(linkmanInfo.getType().equals(LinkTypeEnum.EMAIL.getCode())) {//email
    			others.put(LinkTypeEnum.EMAIL.getName(), linkmanInfo.getMessage());
    			if(linkmanInfo.getIsDefault()==1) {
    				others.put("defaultContactType", linkmanInfo.getType());
    			}
    		} else if(linkmanInfo.getType().equals(LinkTypeEnum.TEL.getCode())) {//mob
    			others.put(LinkTypeEnum.TEL.getName(), linkmanInfo.getMessage());
    			if(linkmanInfo.getIsDefault()==1) {
    				others.put("defaultContactType", linkmanInfo.getType());
    			}
    		} else if(linkmanInfo.getType().equals(LinkTypeEnum.PHONE.getCode())) {//phone
    			others.put(LinkTypeEnum.PHONE.getName(), linkmanInfo.getMessage());
    			if(linkmanInfo.getIsDefault()==1) {
    				others.put("defaultContactType", linkmanInfo.getType());
    			}
    		} else if(linkmanInfo.getType().equals(LinkTypeEnum.WECHAT.getCode())) {//wechat
    			others.put(LinkTypeEnum.WECHAT.getName(), linkmanInfo.getMessage());
    			if(linkmanInfo.getIsDefault()==1) {
    				others.put("defaultContactType", linkmanInfo.getType());
    			}
    		} else if(linkmanInfo.getType().equals(LinkTypeEnum.TG.getCode())) {//tg
    			others.put(LinkTypeEnum.TG.getName(), linkmanInfo.getMessage());
    			if(linkmanInfo.getIsDefault()==1) {
    				others.put("defaultContactType", linkmanInfo.getType());
    			}
    		} else if(linkmanInfo.getType().equals(LinkTypeEnum.SKYPE.getCode())) {//skype
    			others.put(LinkTypeEnum.SKYPE.getName(), linkmanInfo.getMessage());
    			if(linkmanInfo.getIsDefault()==1) {
    				others.put("defaultContactType", linkmanInfo.getType());
    			}
    		} else if(linkmanInfo.getType().equals(LinkTypeEnum.WHATAPP.getCode())) {//whatsapp
    			others.put(LinkTypeEnum.WHATAPP.getName(), linkmanInfo.getMessage());
    			if(linkmanInfo.getIsDefault()==1) {
    				others.put("defaultContactType", linkmanInfo.getType());
    			}
    		}
    	});
    	
        return jsonResult;
    }

    /**
     * 增加联系人
     *
     * @param userId  操作人
     * @param linkman 联系人对象
     * @return
     */
    @PermisAnnotation(id = 30304, name = "供应商资料-联系人-增加")
    @RequestMapping(method = RequestMethod.PUT)
    public JsonResult addLinkman(@ModelAttribute("userId") Integer userId, @Valid Linkman linkman, 
    		@RequestParam String qq,@RequestParam String email,@RequestParam String mob,@RequestParam String phone,
    		@RequestParam String vchat,@RequestParam String tg,@RequestParam String skype,@RequestParam String whatapp, 
    		@RequestParam Integer defaultContactType, BindingResult result) {
        JsonResult ret;
        if(this.linkmanService.addLinkman(this.getUser(userId), linkman, qq, email, mob, phone, vchat, tg, skype, whatapp, defaultContactType)) {
            ret = new JsonResult(ResultCode.SUCCESS);
        } else {
            ret = new JsonResult(ResultCode.EXCEPTION);
        }
        return ret;
    }

    /**
     * 修改联系人
     *
     * @param userId  操作人
     * @param linkman 联系人对象
     * @return
     */
    @PermisAnnotation(id = 30305, name = "供应商资料-联系人-修改")
    @RequestMapping(method = RequestMethod.POST)
    public JsonResult updateLinkman(@ModelAttribute("userId") Integer userId, @Valid Linkman linkman, 
    		@RequestParam String qq,@RequestParam String email,@RequestParam String mob,@RequestParam String phone,
    		@RequestParam String vchat,@RequestParam String tg,@RequestParam String skype,@RequestParam String whatapp, 
    		@RequestParam Integer defaultContactType, BindingResult result) {
    	
        JsonResult ret;
        if(this.linkmanService.updateLinkman(this.getUser(userId), linkman)) {
        	//如果该联系人选为默认，更新其他的为非默认
        	if(linkman.getIsDefault() == 1) {
        		linkmanService.updateLinkmanIsDefault(this.getUser(userId), linkman);
        	}
        	// 增加和修改该联系人的联系信息
        	linkmanInformationService.addLinkmanInformation(userId, linkman, qq, email, mob, phone, vchat, tg, skype, whatapp, defaultContactType);
            ret = new JsonResult(ResultCode.SUCCESS);
        } else {
            ret = new JsonResult(ResultCode.EXCEPTION);
        }
        return ret;
    }

    /**
     * 通过id删除联系人
     *
     * @param userId 操作人
     * @param id     联系人id
     * @return
     */
    @PermisAnnotation(id = 30306, name = "供应商资料-联系人-删除")
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public JsonResult delLinkman(@ModelAttribute("userId") Integer userId, @PathVariable Integer id) {
        JsonResult ret;
        if(this.linkmanService.delLinkman(this.getUser(userId), id)) {
            ret = new JsonResult();
        } else {
            ret = new JsonResult(ResultCode.EXCEPTION);
        }
        return ret;
    }
}
