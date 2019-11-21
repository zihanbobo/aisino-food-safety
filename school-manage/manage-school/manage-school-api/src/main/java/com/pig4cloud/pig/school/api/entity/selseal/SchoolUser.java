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
package com.pig4cloud.pig.school.api.entity.selseal;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 学校用户表
 *
 * @author 崔俊前
 * @date 2019-08-05 14:48:38
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("se_school_user")
public class SchoolUser extends Model<SchoolUser> {
private static final long serialVersionUID = 1L;

    /**
   * 主键id
   */
    @TableId
    private Integer id;
    /**
   * 学校id
   */
    private Integer schoolId;
    /**
   * 是否是超级管理员1是2否
   */
    private String isAdmin;
    /**
   * 真实姓名
   */
    private String schuName;
    /**
   * 账号(登陆号）
   */
    private String schuIdentity;
    /**
   * 密码
   */
    private String schuPassword;
    /**
   * 用户区划号
   */
    private String schuArea;
    /**
   * 微信id
   */
    private String wxOpenid;
    /**
   * QQ openid
   */
    private String qqOpenid;
    /**
   * 用户头像地址
   */
    private String schuPic;
    /**
   * 手机号码
   */
    private String schuPhone;
    /**
   * 昵称
   */
    private String nickName;
    /**
   * 个性签名
   */
    private String schuSign;
    /**
   * 身份证号码
   */
    private String identityCard;
    /**
   * 备注信息
   */
    private String remarks;
    /**
   * 0-正常，9-锁定
   */
    private String lockFlag;
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
