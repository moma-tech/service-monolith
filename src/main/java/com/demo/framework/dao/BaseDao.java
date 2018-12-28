package com.demo.framework.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * BaseDao
 *
 * <p>Base Dao/Mapper
 *
 * <p>Every one should extend this
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/13/18 - 4:23 PM.
 * @see com.baomidou.mybatisplus.core.mapper.BaseMapper
 */
public interface BaseDao<T> extends BaseMapper<T> {}
