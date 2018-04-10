package com.og.oms.controller;


import com.og.oms.annotation.PermisAnnotation;
import com.og.oms.enums.ResultCode;
import com.og.oms.model.Code;
import com.og.oms.model.CodeType;
import com.og.oms.service.ICodeService;
import com.og.oms.service.ICodeTypeService;
import com.og.oms.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 数据字典前端控制器
 * </p>
 *
 * @author jeff
 * @since 2017-10-28
 */
@RestController
@SessionAttributes(names = "userId", types = Integer.class)
public class CodeController extends BaseController {

    @Autowired
    private ICodeService codeService;

    @Autowired
    private ICodeTypeService codeTypeService;

    /**
     * 获取字典类型
     *
     * @return
     */
    @PermisAnnotation(id = 601, name = "系统字典-列表")
    @RequestMapping(value = "codetype", method = RequestMethod.GET)
    public JsonResult getCodeTypeList() {
        List<CodeType> list = this.codeTypeService.selectList(null);
        return new JsonResult(list);
    }

    /**
     * 获取字典列表
     *
     * @param type
     * @return
     */
    @PermisAnnotation(id = 601, name = "系统字典-子列表")
    @RequestMapping(value = "code", method = RequestMethod.GET)
    public JsonResult getCodeList(Integer type) {
        return new JsonResult(codeService.getCode(type));
    }

    /**
     * 通过id获取字典
     *
     * @param id
     * @return
     */
    @PermisAnnotation(id = 601, name = "系统字典-查看")
    @RequestMapping(value = "code/{id}", method = RequestMethod.GET)
    public JsonResult getCodeById(@PathVariable Integer id) {
        return new JsonResult(codeService.getCodeById(id));
    }

    /**
     * 增加字典
     *
     * @param userId
     * @param code
     * @param result
     * @return
     */
    @PermisAnnotation(id = 60101, name = "系统字典-增加")
    @RequestMapping(value = "code", method = RequestMethod.PUT)
    public JsonResult addCode(@ModelAttribute("userId") Integer userId, @Valid Code code, BindingResult result) {
        JsonResult ret;
        if(this.codeService.addCode(this.getUser(userId), code)) {
            ret = new JsonResult();
        } else {
            ret = new JsonResult(ResultCode.EXCEPTION);
        }
        return ret;
    }

    /**
     * 修改字典
     *
     * @param userId
     * @param code
     * @param result
     * @return
     */
    @PermisAnnotation(id = 60102, name = "系统字典-修改")
    @RequestMapping(value = "code", method = RequestMethod.POST)
    public JsonResult updateCode(@ModelAttribute("userId") Integer userId, @Valid Code code, BindingResult result) {
        JsonResult ret;
        if(this.codeService.updateCode(this.getUser(userId), code)) {
            ret = new JsonResult();
        } else {
            ret = new JsonResult(ResultCode.EXCEPTION);
        }
        return ret;
    }

    /**
     * 禁用字典
     *
     * @param userId
     * @param id
     * @return
     */
    @PermisAnnotation(id = 60102, name = "系统字典-禁用")
    @RequestMapping(value = "code/{id}", method = RequestMethod.POST)
    public JsonResult disableCode(@ModelAttribute("userId") Integer userId, @PathVariable Integer id, Integer status) {
        JsonResult ret;
        if(this.codeService.disableCode(this.getUser(userId), id, status)) {
            ret = new JsonResult();
        } else {
            ret = new JsonResult(ResultCode.EXCEPTION);
        }
        return ret;
    }
}
