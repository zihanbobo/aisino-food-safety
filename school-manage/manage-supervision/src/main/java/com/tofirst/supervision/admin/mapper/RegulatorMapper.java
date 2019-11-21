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
package com.tofirst.supervision.admin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tofirst.supervision.admin.entity.Regulator;
import org.apache.ibatis.annotations.Param;

/**
 * 监管局信息表
 *
 * @author -
 * @date 2019-08-05 14:49:59
 */
public interface RegulatorMapper extends BaseMapper<Regulator> {
  /**
    * 监管局信息表简单分页查询
    * @param regulator 监管局信息表
    * @return
    */
  IPage<Regulator> getRegulatorPage(Page page, @Param("regulator") Regulator regulator);


}
