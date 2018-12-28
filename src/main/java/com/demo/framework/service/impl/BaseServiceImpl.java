package com.demo.framework.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.demo.framework.dao.BaseDao;
import com.demo.framework.service.BaseService;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * BaseServiceImpl
 *
 * <p>Base Service Impl
 *
 * @author ivan
 * @version 1.0 Created by ivan on 12/13/18 - 7:23 PM.
 * @see com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
 */
public class BaseServiceImpl<M extends BaseDao<T>, T> extends ServiceImpl<M, T>
    implements BaseService<T> {

  public <E> List<E> entityList(Wrapper<T> wrapper, Function<? super T, E> mapper) {
    return list(wrapper).stream().map(mapper).collect(Collectors.toList());
  }
}
