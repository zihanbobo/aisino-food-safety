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
package com.pig4cloud.pig.school.service.source.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.school.api.entity.source.HighRisk;
import com.pig4cloud.pig.school.mapper.HighRiskMapper;
import com.pig4cloud.pig.school.service.source.HighRiskService;
import org.springframework.stereotype.Service;

/**
 * 高风险食材表
 *
 * @author 沈凝
 * @date 2019-07-15 17:33:37
 */
@Service("highRiskService")
public class HighRiskServiceImpl extends ServiceImpl<HighRiskMapper, HighRisk> implements HighRiskService {

  /**
   * 高风险食材表简单分页查询
   * @param highRisk 高风险食材表
   * @return
   */
  @Override
  public IPage<HighRisk> getHighRiskPage(Page<HighRisk> page, HighRisk highRisk){
      return baseMapper.getHighRiskPage(page,highRisk);
  }

}
