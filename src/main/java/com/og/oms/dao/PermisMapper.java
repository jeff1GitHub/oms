package com.og.oms.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.og.oms.model.Permis;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author jeff
 * @since 2017-10-25
 */
public interface PermisMapper extends BaseMapper<Permis> {
    List<Permis> getUserPermisList(List<Integer> list);
}