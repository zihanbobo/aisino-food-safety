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
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 就餐区域洗消记录表
 *
 * @author 沈凝
 * @date 2019-07-06 16:44:56
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sl_diningarea_wash")
public class DiningareaWash extends Model<DiningareaWash> {
private static final long serialVersionUID = 1L;

    /**
   * 主键id
   */
    @TableId
    private Integer id;
    /**
     *
     */
    private Integer schoolId;
    /**
   * 
   */
    private String dwName;
    /**
   * 消毒日期
   */
    private String dwDate;
    /**
   * 消毒开始时间
   */
    private String dwStart;
    /**
   * 消毒结束时间
   */
    private String dwEnd;
    /**
   * 消毒时间
   */
    private String dwTimelength;
    /**
   * 操作人
   */
    private String dwPerson;
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
