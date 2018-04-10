package com.og.oms.controller;


import com.og.oms.annotation.PermisAnnotation;
import com.og.oms.enums.ResultCode;
import com.og.oms.model.DomainMain;
import com.og.oms.service.IDomainDetailService;
import com.og.oms.service.IDomainMainService;
import com.og.oms.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import javax.validation.Valid;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jeff
 * @since 2017-11-09
 */
@RestController
@RequestMapping("domainmain")
@SessionAttributes(names = "userId", types = Integer.class)
public class DomainMainController extends BaseController {

	@Autowired
    private IDomainMainService domainMainService;
	
	@Autowired
    private IDomainDetailService domainDetailService;

    /**
     * 获取域名列表
     *
     * @return
     */
    @PermisAnnotation(id = 302, name = "域名资料-列表")
    @RequestMapping(method = RequestMethod.GET)
    public JsonResult getDomainMainList(Integer purpose, Integer platform, String domain) {
    	List<DomainMain> domainMainList = this.domainMainService.getDomainMainList(purpose, platform, domain);
        return new JsonResult(domainMainList);
    }

    /**
     * 通过id获取域名信息
     *
     * @param id
     * @return
     */
    @PermisAnnotation(id = 302, name = "域名资料-查看")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public JsonResult getDomainMainById(@PathVariable Integer id) {
        return new JsonResult(this.domainMainService.getDomainMainById(id));
    }

    /**
     * 增加域名信息
     *
     * @param userId
     * @param domainMain
     * @return
     */
    @PermisAnnotation(id = 30201, name = "域名资料-增加")
    @RequestMapping(method = RequestMethod.PUT)
    public JsonResult addDomainMain(@ModelAttribute("userId")Integer userId, @Valid DomainMain domainMain, BindingResult result) {
        JsonResult ret;
        if(this.domainMainService.addDomainMain(super.getUser(userId), domainMain)) {
            ret = new JsonResult();
        } else {
            ret = new JsonResult(ResultCode.EXCEPTION);
        }
        return ret;
    }

    /**
     * 修改域名信息
     *
     * @param userId
     * @param domainMain
     * @return
     */
    @PermisAnnotation(id = 30202, name = "域名资料-修改")
    @RequestMapping(method = RequestMethod.POST)
    public JsonResult updateDomainMain(@ModelAttribute("userId")Integer userId, @Valid DomainMain domainMain, BindingResult result) {
        JsonResult ret;
        if(this.domainMainService.updateDomainMain(super.getUser(userId), domainMain)) {
            ret = new JsonResult();
        } else {
            ret = new JsonResult(ResultCode.EXCEPTION);
        }
        return ret;
    }

    /**
     * 删除域名资料
     *
     * @param userId
     * @param id
     * @return
     */
    @PermisAnnotation(id = 30203, name = "域名资料-删除")
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public JsonResult delDomainMain(@ModelAttribute("userId")Integer userId, @PathVariable Integer id) {
        JsonResult ret;
        if(this.domainMainService.delDomainMain(super.getUser(userId), id)) {
        	//删除域名资料详情
        	domainDetailService.delDomainDetailByDomainId(id);
            ret = new JsonResult();
        } else {
            ret = new JsonResult(ResultCode.EXCEPTION);
        }
        return ret;
    }
}
