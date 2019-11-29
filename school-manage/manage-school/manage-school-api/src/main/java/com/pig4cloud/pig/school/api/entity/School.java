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
package com.pig4cloud.pig.school.api.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 学校表
 *
 * @author 崔俊前
 * @date 2019-08-05 14:48:06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("se_school")
public class School extends Model<School> {
private static final long serialVersionUID = 1L;

    /**
   * 
   */
    @TableId
    private Integer id;
    /**
   * 超级管理员id
   */
    @TableField(strategy= FieldStrategy.IGNORED)
    private Integer schoolUserid;
    /**
   * 超级管理员id
   */
    @TableField(strategy= FieldStrategy.IGNORED)
    private Integer schoolProjectid;
    /**
   * 学校名称
   */
    private String schName;
    /**
   * 学校地址
   */
    private String schAddress;
    /**
   * 学校标识码(直播)
   */
    private String liveIdentifier;
    /**
   * 学校标识码(食谱)
   */
    private String recipeIdentifier;
    /**
     * 省区划号
     */
    private String province;
    /**
     * 市区划号
     */
    private String cityCode;
    /**
     * 学校区划号
     */
    private String schArea;
    /**
     * 学校驻地城乡类型(1主城区 2城乡结合区3镇中心区4镇乡结合区5特殊区域6乡中心区7村庄)
     */
    private String schCity;
    /**
     * 学校网站地址
     */
    private String schWebsite;
    /**
   * 学校二维码地址
   */
    private String schQrcode;
    /**
   * 学校电话
   */
    private String schTel;
    /**
   * 校长姓名
   */
    private String schPrincipal;
    /**
   * 学校风采图
   */
    private String schPic;
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
	/**
	 * 学校简介
	 */
	private String introduction;
	/**
	 * 办公性质(字典表：0-公办，1-私办)
	 */
	private String officeNature;

	/**
	 * 学校类型(字典表：0-幼儿园，1-小学，2-初中，3-高中)
	 */
	private String schoolType;
	/**
	 * 学生数量
	 */
	private String stuNumber;
  /**
   * 食堂数量
   */
  private String messhallNumber;
  /**
   * 量化评级(字典表 1A2B3C4D）
   */
  private String quantitative;
  /**
   * 量化评级分数(0-100)
   */
  private String ratingScore;

}
