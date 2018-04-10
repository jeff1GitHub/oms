package com.og.oms.service;

import com.baomidou.mybatisplus.service.IService;
import com.og.oms.model.Permis;
import com.og.oms.model.User;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author jeff
 * @since 2017-10-25
 */
public interface IPermisService extends IService<Permis> {
    List<Permis> selectPermisList();

    List<Permis> getUserPermisList(User user);
}
