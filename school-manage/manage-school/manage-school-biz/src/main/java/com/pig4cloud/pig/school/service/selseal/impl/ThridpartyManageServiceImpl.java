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
package com.pig4cloud.pig.school.service.selseal.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.school.api.entity.selseal.ThridpartyManage;
import com.pig4cloud.pig.school.mapper.ThridpartyManageMapper;
import com.pig4cloud.pig.school.service.selseal.ThridpartyManageService;
import org.springframework.stereotype.Service;

/**
 * 第三方服务商管理
 *
 * @author -
 * @date 2019-08-07 16:00:23
 */
@Service("thridpartyManageService")
public class ThridpartyManageServiceImpl extends ServiceImpl<ThridpartyManageMapper, ThridpartyManage> implements ThridpartyManageService {

  /**
   * 第三方服务商管理简单分页查询
   * @param thridpartyManage 第三方服务商管理
   * @return
   */
  @Override
  public IPage<ThridpartyManage> getThridpartyManagePage(Page<ThridpartyManage> page, ThridpartyManage thridpartyManage){
      return baseMapper.getThridpartyManagePage(page,thridpartyManage);
  }

}
