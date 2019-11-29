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
 * 陪餐记录表
 *
 * @author 沈凝
 * @date 2019-07-04 11:30:54
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sl_accompany_dinner")
public class AccompanyDinner extends Model<AccompanyDinner> {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@TableId
	private Integer id;
	/**
	 * 学校id
	 */
	private Integer schoolId;
	/**
	 * 陪餐人员id
	 */
	private Integer userId;
	/**
	 * 陪餐时间
	 */
	private String adTime;
	/**
	 * 餐次(字典表)
	 */
	private String parameter;
	/**
	 * 本次陪餐整体情况(字典表1好2良)
	 */
	private String adAms;
	/**
	 * 是否存在问题（字典表1不存在2存在）
	 */
	private String adIsproblem;
	/**
	 * 陪餐人
	 */
	private String adPerson;
	/**
	 * 陪餐图片
	 */
	private String adPic;
	/**
	 * 备注信息
	 */
	private String remarks;
	/**
	 * 创建人id
	 */
	private Integer createId;
	/**
	 * 更新人id
	 */
	private Integer updateId;
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
