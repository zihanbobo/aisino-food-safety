/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */
package com.pig4cloud.pig.school.service.account.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.school.api.entity.account.FoodAdditive;
import com.pig4cloud.pig.school.mapper.FoodAdditiveMapper;
import com.pig4cloud.pig.school.service.account.FoodAdditiveService;
import org.springframework.stereotype.Service;

/**
 * 食品添加剂使用记录表
 *
 * @author 沈凝
 * @date 2019-07-04 17:35:53
 */
@Service("foodAdditiveService")
public class FoodAdditiveServiceImpl extends ServiceImpl<FoodAdditiveMapper, FoodAdditive> implements FoodAdditiveService {

  /**
   * 食品添加剂使用记录表简单分页查询
   * @param foodAdditive 食品添加剂使用记录表
   * @return
   */
  @Override
  public IPage<FoodAdditive> getFoodAdditivePage(Page<FoodAdditive> page, FoodAdditive foodAdditive){
      return baseMapper.getFoodAdditivePage(page,foodAdditive);
  }

}
