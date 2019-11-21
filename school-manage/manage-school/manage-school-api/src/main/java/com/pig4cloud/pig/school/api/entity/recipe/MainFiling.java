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

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 供应商备案表
 *
 * @author 沈凝
 * @date 2019-06-21 14:59:45
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sup_main_filing")
public class MainFiling extends Model<MainFiling> {
private static final long serialVersionUID = 1L;

    /**
   * 
   */
	@TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
   * 关联学校id
   */
    private Integer schoolId;
    /**
   * 供应商名称
   */
    private String supName;
    /**
   * 社会信用代码
   */
    private String supCreditcode;
    /**
   * 法人姓名
   */
    private String corName;
    /**
   * 联系人姓名
   */
    private String linkName;
    /**
   * 联系方式
   */
    private String contact;
    /**
   * 供应商类型(1企业供应商 2个体供应商)
   */
    private String supType;
    /**
   * 所属区划号
   */
    private String areaCode;
    /**
   * 地址
   */
    private String addr;
    /**
   * 手机
   */
    private String phone;
    /**
   * 邮编
   */
    private String zipCode;
    /**
   * 营业执照照片
   */
    private String bulicensePic;
    /**
   * 许可证照片
   */
    private String permitPic;
    /**
   * 其它证照片
   */
    private String elsePic;
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
     *营业执照开始时间
     */
    private LocalDate bulicenseStart;
    /**
     * 营业执照到期时间
     */
    private LocalDate bulicenseEnd;
    /**
     * 营业许可开始时间
     */
    private LocalDate permitStart;
    /**
     * 营业许可结束时间
     */
    private LocalDate permitEnd;
    /**
     * 供应商登记机关
     */
    private String regAuthority;
    /**
     * 营业执照发证日期
     */
    private LocalDate issuanceDate;
    /**
     * 营业执照成立日期
     */
    private LocalDate establishmentDate;
    /**
     * 营业执照经营范围
     */
    private String businessScope;
    /**
     * 许可证编号
     */
    private String licenseNumber;
    /**
     * 许可证发证机关
     */
    private String licenseAuthority;
    /**
     *许可证发证日期
     */
    private LocalDate licenseDate;
    /**
     * 负责人
     */
    private String principal;
    /**
     * 许可证主体类型(1个体工商户,2农民专业合作社,3外商投资企业,4港澳台商投资企业,5内资企业)
     */
    private String licenseType;
    /**
     * 许可范围
     */
    private String licenseScope;
    /**
     * 经营场所
     */
    private String businessPlace;
    /**
     * 营业执照类型
     */
    private String businessType;
}
