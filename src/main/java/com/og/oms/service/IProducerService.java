package com.og.oms.service;

import com.baomidou.mybatisplus.service.IService;
import com.og.oms.model.Producer;
import com.og.oms.model.User;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 供应商服务类接口
 * </p>
 *
 * @author jeff
 * @since 2017-10-17
 */
public interface IProducerService extends IService<Producer> {

    /**
     * 获取供应商列表
     *
     * @return
     */
    List<Map<String, Object>> getProducerList();

    /**
     * 通过id获取供应商信息
     *
     * @param id
     * @return
     */
    Producer getProducerById(Integer id);

    /**
     * 添加供应商资料
     *
     * @param user
     * @param producer
     * @return
     */
    boolean addProducer(User user, Producer producer);

    /**
     * 修改供应商资料
     *
     * @param user
     * @param producer
     * @return
     */
    boolean updateProducer(User user, Producer producer);

    /**
     * 删除供应商资料
     *
     * @param user
     * @param id
     * @return
     */
    boolean delProducer(User user, Integer id);

    /**
     * 获取供应商用于下拉列表展示
     *
     * @return
     */
    Map<Integer, String> getProducerSelect();

    /**
     * 初始化供应商枚举
     */
    void initProducerEnum();
}
