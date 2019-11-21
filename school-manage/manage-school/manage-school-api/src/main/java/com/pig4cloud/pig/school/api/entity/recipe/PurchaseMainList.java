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

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 采购清单管理
 *
 * @author 沈凝
 * @date 2019-07-15 17:23:49
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("pur_purchase_main")
public class PurchaseMainList extends Model<PurchaseMainList> {
private static final long serialVersionUID = 1L;

    /**
   * 主键id
   */
    @TableId
    private Integer id;

    //学校id
    private Integer schoolId;
    /**
     * 清单制定人(管理学校用户id)
     */
    private Integer purPerson;
    /**
   * 采购人
   */
    private String purName;
    /**
   * 采购时间
   */
    private LocalDate purTime;
    /**
   * 采购金额（元）
   */
    private String purMoney;
    /**
   * 使用时间
   */
    private LocalDate useTime;
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
     * 上传索票索证
     */
    private String claimTicket;
    /**
     * 采购清单实照
     */
    private String listPhoto;
    /**
     * 采购状态（1未采购2待完成采购3已完成采购）
     */
    private String purStatus;
    /**
     * 供货联系人
     */
    private String supplyContact;
    /**
     * 供货人联系方式
     */
    private String supplyTel;
    /**
     * 验收人
     */
    private String acceptor;


}
