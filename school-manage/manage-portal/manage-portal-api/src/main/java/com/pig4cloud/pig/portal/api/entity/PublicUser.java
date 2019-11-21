package com.pig4cloud.pig.portal.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 公共用户信息
 *
 * @author -
 * @date 2019-08-29 11:23:11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("se_public_user")
public class PublicUser extends Model<PublicUser> {
private static final long serialVersionUID = 1L;

    /**
   * 公众id
   */
    @TableId
    private Integer id;
    /**
   * 学校id
   */
    private Integer schoolId;
    /**
   * 真实姓名
   */
    private String pubName;
    /**
   * 账号(登陆号）
   */
    private String pubIdentity;
    /**
   * 密码
   */
    private String pubPassword;
    /**
   * 用户区划号
   */
    private String pubArea;
    /**
   * 微信id
   */
    private String wxOpenid;
    /**
   * qqid
   */
    private Integer qqOpenid;
    /**
   * 用户头像地址
   */
    private String pubPic;
    /**
   * 手机号码
   */
    private String pubPhone;
    /**
   * 昵称
   */
    private String nickName;
    /**
   * 身份证号码
   */
    private String identityCard;
    /**
   * 个性签名
   */
    private String pubSign;
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
  /**
   *  app用户token验证；
   */
  private String token;
  /**
   * 省
   */
  private String sheng;
  /**
   * 市
   */
  private String shi;
  /**
   * 性别
   */
  private String sex;
}
