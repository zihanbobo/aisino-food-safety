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
package com.pig4cloud.pig.generator.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.generator.entity.ReCategory;
import com.pig4cloud.pig.generator.mapper.ReCategoryMapper;
import com.pig4cloud.pig.generator.service.ReCategoryService;
import org.springframework.stereotype.Service;


/**
 * 食材类别名称
 *
 * @author pig code generator
 * @date 2019-06-03 16:24:23
 */
@Service("reCategoryService")
public class ReCategoryServiceImpl extends ServiceImpl<ReCategoryMapper, ReCategory> implements ReCategoryService {

  /**
   * 食材类别名称简单分页查询
   * @param reCategory 食材类别名称
   * @return
   */
  @Override
  public IPage<ReCategory> getReCategoryPage(Page<ReCategory> page, ReCategory reCategory){
      return baseMapper.getReCategoryPage(page,reCategory);
  }

}
