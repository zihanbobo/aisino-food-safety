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
package com.pig4cloud.pig.school.api.entity.recipe;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 食材备案表
 *
 * @author 沈凝
 * @date 2019-07-04 00:33:20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sup_food_filing")
public class FoodFiling extends Model<FoodFiling> {
private static final long serialVersionUID = 1L;

    /**
   * 主键
   */
	@TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
   * 供应商id（关联供应商备案id）
   */
    private String supId;
    /**
   * 食材类型（关联食材id）
   */
    private Integer foodType;
    /**
   * 食材名称
   */
    private String foodName;
    /**
   * 食材图片
   */
    private String foodPic;
    /**
   * 食材品牌
   */
    private String foodBrand;
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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}
