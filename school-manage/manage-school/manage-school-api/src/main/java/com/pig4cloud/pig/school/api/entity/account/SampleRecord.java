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

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 留样记录
 *
 * @author 沈凝
 * @date 2019-07-08 01:48:09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sl_sample_record")
public class SampleRecord extends Model<SampleRecord> {
private static final long serialVersionUID = 1L;

    /**
   * id
   */
    @TableId
    private Integer id;
  /**
   * 学校id
   */
    private Integer schoolId;
    /**
   * 留样编号
   */
    private String srNumber;
    /**
   * 餐次（字典表）
   */
    private String parameter;
    /**
   * 留样日期
   */
    private LocalDateTime srDate;
    /**
   * 留样重量
   */
    private String srWeight;
    /**
   * 操作人
   */
    private String srPerson;
    /**
   * 留样时长
   */
    private String srDuration;
    /**
   * 留样实照
   */
    private String srPic;
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
