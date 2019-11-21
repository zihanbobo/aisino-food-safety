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
package com.pig4cloud.pig.school.api.entity.account;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 食品添加剂使用记录表
 *
 * @author 沈凝
 * @date 2019-07-04 17:35:53
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sl_food_additive")
public class FoodAdditive extends Model<FoodAdditive> {
private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId(value = "id", type = IdType.AUTO)
    private Integer id;
  /**
   * 学校id
   */
  private Integer schoolId;
  /**
   * 食品添加剂名称
   */
    private String faName;
    /**
   * 用途
   */
    private String faUse;
    /**
   * 使用量
   */
    private String faUsage;
    /**
   * 使用时间
   */
    private String faTime;
    /**
   * 卫生许可证号
   */
    private String faHcn;
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
