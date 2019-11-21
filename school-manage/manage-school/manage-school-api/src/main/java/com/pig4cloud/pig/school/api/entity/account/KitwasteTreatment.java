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
 * 厨余处理记录
 *
 * @author 沈凝
 * @date 2019-07-06 16:54:52
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sl_kitwaste_treatment")
public class KitwasteTreatment extends Model<KitwasteTreatment> {
private static final long serialVersionUID = 1L;

    /**
   * 主键
   */
    @TableId
    private String id;
  /**
   * 学校id
   */
  private Integer schoolId;
  /**
   * 处置单位
   */
    private String disposalUnit;
    /**
   * 回收时间
   */
    private String recoverTime;
    /**
   * 回收类别（关联回收类别码表recCategory）
   */
    private String recCategory;
    /**
   * 处置人
   */
    private String disposer;
    /**
   * 联系方式
   */
    private String contact;
    /**
   * 车牌号
   */
    private String carNumber;
    /**
   * 数量
   */
    private String ktNum;
    /**
   * 厨余处理实照
   */
    private String ktPic;
	/**
	 * 操作人
	 */
	private String ktPerson;
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
