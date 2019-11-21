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
package com.pig4cloud.pig.school.api.entity.source;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 食材类别表
 *
 * @author 沈凝
 * @date 2019-07-08 15:50:37
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sup_food_type")
public class FoodType extends Model<FoodType> {
private static final long serialVersionUID = 1L;

    /**
   * 
   */
    @TableId
    private Integer foodtypeId;
    /**
   * 部门名称
   */
    private String name;
    /**
   * 排序
   */
    private Integer sort;
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
    /**
   * 
   */
    private Integer parentId;
  
}
