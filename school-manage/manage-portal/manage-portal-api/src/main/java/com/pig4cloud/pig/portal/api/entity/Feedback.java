package com.pig4cloud.pig.portal.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 意见反馈信息
 *
 * @author xiesongzhe
 * @date 2019-09-03 15:07:52
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("site_feedback")
public class Feedback extends Model<Feedback> {
private static final long serialVersionUID = 1L;

    /**
   * 主键id
   */
    @TableId
    private Integer id;
    /**
   * 关联学校id
   */
    private Integer schoolId;
    /**
   * 关联用户id
   */
    private Integer userId;
    /**
   * 意见标题
   */
    private String opTitle;
    /**
   * 意见内容
   */
    private String opContent;
    /**
   * 时间
   */
    private LocalDateTime opTime;
    /**
   * 姓名
   */
    private String opName;
    /**
   * 邮箱
   */
    private String opEmail;
    /**
   * 电话
   */
    private String opTel;
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
   * 回复时间
   */
    private LocalDateTime responseTime;
    /**
   * 回复内容
   */
    private String responseContent;
    /**
   * 是否回复(0-未回复，1-已回复)
   */
    private String isResponse;
  
}
