package com.pig4cloud.pig.school.api.entity.school;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 学校用户信息
 *
 * @author xiesongzhe
 * @date 2019-10-20 18:31:16
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
   * is_admin	是否是超级管理员1是2否
   */
    private String isAdmin;
    /**
   * schu_name	真实姓名
   */
    private String schuName;
    /**
   * schu_identity	账号(登陆号）
   */
    private String schuIdentity;
    /**
   * schu_password	密码
   */
    private String schuPassword;
    /**
   * schu_area	用户区划号
   */
    private String schuArea;
    /**
   * schu_pic	用户头像地址
   */
    private String schuPic;
    /**
   * schu_phone	手机号码
   */
    private String schuPhone;
    /**
   * nick_name	昵称
   */
    private LocalDateTime nick;
  /**
   * nick_name	时间
   */
   private LocalDateTime nickName;
    /**
   * identity_card	身份证号码
   */
    private String identityCard;
  /**
   * del_flag	是否删除 -1：已删除 0：正常
   */
   private String delflag;
  
}
