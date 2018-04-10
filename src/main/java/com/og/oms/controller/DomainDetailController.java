package com.og.oms.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.og.oms.annotation.PermisAnnotation;
import com.og.oms.model.DomainDetail;
import com.og.oms.service.IDomainDetailService;
import com.og.oms.utils.JsonResult;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author jeff
 * @since 2017-11-28
 */
@RestController
public class DomainDetailController {
	
	@Autowired
	private IDomainDetailService domainDetailService;
	
    /**
     * 根据域名ID获取域名资料详情
     * 
     * @param userId
     * @param domainId
     * @return
     */
    @PermisAnnotation(id = 30204, name = "域名资料-详情")
	@RequestMapping(value = "domainDetail", method = RequestMethod.GET)
	public JsonResult getDomainDetailByDomainId(@ModelAttribute("userId") String userId, Integer domainId) {
		List<DomainDetail> domainDetails = domainDetailService.getDomainDetailByDomainId(domainId);		
		return new JsonResult(domainDetails);
	}
}
