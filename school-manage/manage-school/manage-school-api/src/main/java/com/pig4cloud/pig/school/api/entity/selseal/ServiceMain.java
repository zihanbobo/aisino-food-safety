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
package com.pig4cloud.pig.school.api.entity.selseal;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 服务表
 *
 * @author 沈凝
 * @date 2019-08-05 10:32:15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("se_service_main")
public class ServiceMain extends Model<ServiceMain> {
private static final long serialVersionUID = 1L;

    /**
   * 
   */
    @TableId
    private Integer id;
    /**
   * 服务名称
   */
    private String serviceName;
    /**
   * 服务详情
   */
    private String serviceContent;
    /**
   * 服务价格
   */
    private BigDecimal servicePrice;
    /**
   * 服务介绍图
   */
    private String servicePic;
    /**
   * 服务电话
   */
    private String serviceTel;
    /**
   * 服务提供厂商
   */
    private String serviceFirm;
    /**
   * 备注信息
   */
    private String remarks;
    /**
   * 创建时间
   */
    private LocalDateTime createTime;
    /**
   * 修改时间
   */
    private LocalDateTime updateTime;
    /**
   * 是否删除  -1：已删除  0：正常
   */
    private String delFlag;
  
}
