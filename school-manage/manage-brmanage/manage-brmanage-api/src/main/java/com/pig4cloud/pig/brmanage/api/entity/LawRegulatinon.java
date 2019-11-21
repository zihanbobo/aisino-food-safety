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
package com.pig4cloud.pig.brmanage.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 法律法规表
 *
 * @author 沈凝
 * @date 2019-07-29 10:32:30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("re_law_regulatinon")
public class LawRegulatinon extends Model<LawRegulatinon> {
private static final long serialVersionUID = 1L;

    /**
   * 主键id 
   */
    @TableId
    private Integer id;
    /**
   * 法律标题
   */
    private String lawTitle;
    /**
   * 题注
   */
    private String lawChapter;
    /**
   * 法律分类（大类）字典表
   */
    private String lawClassify;
    /**
   * 发布部门
   */
    private String lawDept;
    /**
   * 发文字号
   */
    private String issuedNumber;
    /**
   * 门类（小分类）暂时字典表
   */
    private String lawClass;
    /**
   * 时效性（1有效2时效）字典表
   */
    private String lawTimeliness;
    /**
   * 发布日期
   */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lawPublish;
    /**
   * 施行日期
   */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lawApply;
    /**
   * 正文（富文本）
   */
    private String lawContent;
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
