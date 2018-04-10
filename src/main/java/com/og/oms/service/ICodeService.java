package com.og.oms.service;

import com.baomidou.mybatisplus.service.IService;
import com.og.oms.model.Code;
import com.og.oms.model.User;

import java.util.List;

/**
 * <p>
 * 数据字典服务类接口
 * </p>
 *
 * @author jeff
 * @since 2017-10-28
 */
public interface ICodeService extends IService<Code> {
    /**
     * 通过类型获取字典表
     *
     * @param type
     * @return
     */
    List<Code> getCode(Integer type);

    /**
     * 通过id获取字典
     *
     * @param id
     * @return
     */
    Code getCodeById(Integer id);

    /**
     * 增加字典
     *
     * @param user
     * @param code
     * @return
     */
    boolean addCode(User user, Code code);

    /**
     * 修改字典
     *
     * @param user
     * @param code
     * @return
     */
    boolean updateCode(User user, Code code);

    /**
     * 禁用字典
     *
     * @param user
     * @param id
     * @return
     */
    boolean disableCode(User user, Integer id, Integer status);

    /**
     * 初始化枚举对象
     */
    void initEnum();
}
