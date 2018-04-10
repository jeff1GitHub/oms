package com.og.oms.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.og.oms.annotation.PermisAnnotation;
import com.og.oms.enums.ResultCode;
import com.og.oms.model.Producer;
import com.og.oms.service.IProducerService;
import com.og.oms.utils.JsonResult;

/**
 * <p>
 * 供应商前端控制器
 * </p>
 *
 * @author jeff
 * @since 2017-10-17
 */
@RestController
@RequestMapping("producer")
@SessionAttributes(names = "userId", types = Integer.class)
public class ProducerController extends BaseController {
    /**
     * 供应商资料服务类
     */
    @Autowired
    private IProducerService producerService;

    /**
     * 分页获取供应商列表
     *
     * @return
     */
    @PermisAnnotation(id = 303, name = "供应商资料-列表")
    @RequestMapping(method = RequestMethod.GET)
    public JsonResult getProducerList() {
    	List<Map<String, Object>> producerList = producerService.getProducerList();
        return new JsonResult(producerList);
    }

    /**
     * 通过id获取供应商信息
     *
     * @param id
     * @return
     */
    @PermisAnnotation(id = 303, name = "供应商资料-查看")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public JsonResult getProducerById(@PathVariable Integer id) {
        return new JsonResult(producerService.getProducerById(id));
    }

    /**
     * 添加供应商
     *
     * @param userId
     * @param producer
     * @param result
     * @return
     */
    @PermisAnnotation(id = 30301, name = "供应商资料-增加")
    @RequestMapping(method = RequestMethod.PUT)
    public JsonResult addProducer(@ModelAttribute("userId") Integer userId, @Valid Producer producer, BindingResult result) {
        JsonResult ret;
        if(producerService.addProducer(this.getUser(userId), producer)) {
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
     * @param producer
     * @return
     */
    @PermisAnnotation(id = 30302, name = "供应商资料-修改")
    @RequestMapping(method = RequestMethod.POST)
    public JsonResult updateProducer(@ModelAttribute("userId") Integer userId, @Valid Producer producer) {
        JsonResult ret;
        if(producerService.updateProducer(this.getUser(userId), producer)) {
            ret = new JsonResult();
        } else {
            ret = new JsonResult(ResultCode.EXCEPTION);
        }
        return ret;
    }

    /**
     * 删除供应商
     *
     * @param userId
     * @param id
     * @return
     */
    @PermisAnnotation(id = 30303, name = "供应商资料-删除")
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public JsonResult delProducer(@ModelAttribute("userId") Integer userId, @PathVariable Integer id) {
        JsonResult ret;
        if(producerService.delProducer(this.getUser(userId), id)) {
            ret = new JsonResult();
        } else {
            ret = new JsonResult(ResultCode.EXCEPTION);
        }
        return ret;
    }
}
