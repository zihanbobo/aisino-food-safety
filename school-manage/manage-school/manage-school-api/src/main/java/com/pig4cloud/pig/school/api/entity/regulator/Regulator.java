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
package com.pig4cloud.pig.school.api.entity.regulator;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 监管局信息表
 *
 * @author -
 * @date 2019-08-05 14:49:59
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("se_regulator")
public class Regulator extends Model<Regulator> {
private static final long serialVersionUID = 1L;

    /**
   * 
   */
    @TableId
    private Integer id;
    /**
   * 树状结构id
   */
    private Integer parentId;
    /**
   * 超级管理员id
   */
    private Integer regUserid;
    /**
   * 学校名称
   */
    private String regName;
    /**
   * 监管局地址
   */
    private String regAddress;
    /**
   * 监管局区划号
   */
    private String regArea;
    /**
   * 学校电话
   */
    private String regTel;
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
