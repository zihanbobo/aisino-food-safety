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
import com.pig4cloud.pig.school.api.entity.account.EquipmentWash;
import com.pig4cloud.pig.school.mapper.EquipmentWashMapper;
import com.pig4cloud.pig.school.service.account.EquipmentWashService;
import org.springframework.stereotype.Service;

/**
 * 设备设施洗消记录表
 *
 * @author 沈凝
 * @date 2019-07-06 14:56:25
 */
@Service("equipmentWashService")
public class EquipmentWashServiceImpl extends ServiceImpl<EquipmentWashMapper, EquipmentWash> implements EquipmentWashService {

  /**
   * 设备设施洗消记录表简单分页查询
   * @param equipmentWash 设备设施洗消记录表
   * @return
   */
  @Override
  public IPage<EquipmentWash> getEquipmentWashPage(Page<EquipmentWash> page, EquipmentWash equipmentWash){
      return baseMapper.getEquipmentWashPage(page,equipmentWash);
  }

}
