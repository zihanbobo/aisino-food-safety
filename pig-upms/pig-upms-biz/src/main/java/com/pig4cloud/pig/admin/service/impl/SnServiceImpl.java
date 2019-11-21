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
package com.pig4cloud.pig.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.admin.api.entity.Sn;
import com.pig4cloud.pig.admin.mapper.SnMapper;
import com.pig4cloud.pig.admin.service.SnService;
import org.springframework.stereotype.Service;

/**
 * 代码生成
 *
 * @author 沈凝
 * @date 2019-06-05 00:58:01
 */
@Service("snService")
public class SnServiceImpl extends ServiceImpl<SnMapper, Sn> implements SnService {

  /**
   * 代码生成简单分页查询
   * @param sn 代码生成
   * @return
   */
  @Override
  public IPage<Sn> getSnPage(Page<Sn> page, Sn sn){
      return baseMapper.getSnPage(page,sn);
  }

}
