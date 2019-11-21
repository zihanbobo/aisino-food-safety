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
package com.tofirst.supervision.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tofirst.supervision.admin.entity.Regulator;
import com.tofirst.supervision.admin.mapper.RegulatorMapper;
import com.tofirst.supervision.admin.service.RegulatorService;
import org.springframework.stereotype.Service;

/**
 * 监管局信息表
 *
 * @author -
 * @date 2019-08-05 14:49:59
 */
@Service("regulatorService")
public class RegulatorServiceImpl extends ServiceImpl<RegulatorMapper, Regulator> implements RegulatorService {

  /**
   * 监管局信息表简单分页查询
   * @param regulator 监管局信息表
   * @return
   */
  @Override
  public IPage<Regulator> getRegulatorPage(Page<Regulator> page, Regulator regulator){
      return baseMapper.getRegulatorPage(page,regulator);
  }

}
