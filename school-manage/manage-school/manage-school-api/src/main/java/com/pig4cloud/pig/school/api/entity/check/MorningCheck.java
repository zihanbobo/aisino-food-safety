package com.pig4cloud.pig.school.api.entity.check;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 人员晨检管理
 *
 * @author xiesongzhe
 * @date 2019-10-30 20:14:51
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ck_morning_check")
public class MorningCheck extends Model<MorningCheck> {
private static final long serialVersionUID = 1L;

    /**
   * 
   */
    @TableId
    private Integer id;
    /**
   * 学校id
   */
    private Integer schoolId;
    /**
   * 被检查人id
   */
    private Integer userId;
    /**
   * 被检查人名称
   */
    private String realName;
    /**
   * 检查时间
   */
    private LocalDateTime checkTime;
    /**
   * 检查人
   */
    private String rummager;
    /**
   * 是否合格（1合格2不合格）
   */
    private String isQualified;
    /**
   * 不合格原因
   */
    private String reason;
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
