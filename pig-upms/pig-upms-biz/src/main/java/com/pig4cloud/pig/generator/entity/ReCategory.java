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
package com.pig4cloud.pig.generator.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 食材类别名称
 *
 * @author pig code generator
 * @date 2019-06-03 16:24:23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("re_category")
public class ReCategory extends Model<ReCategory> {
private static final long serialVersionUID = 1L;

    /**
   * 主键
   */
    @TableId
    private String id;
    /**
   * 创建者
   */
    private String createBy;
    /**
   * 创建时间
   */
    private LocalDateTime createDate;
    /**
   * 更新者
   */
    private String updateBy;
    /**
   * 更新时间
   */
    private LocalDateTime updateDate;
    /**
   * 备注信息
   */
    private String remarks;
    /**
   * 逻辑删除标记（0：显示；1：隐藏）
   */
    private String delFlag;
    /**
   * 食谱类别名称
   */
    private String reName;
  
}
