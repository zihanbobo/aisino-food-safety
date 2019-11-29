package com.pig4cloud.pig.school.api.entity.account;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 紫外线灯使用信息
 *
 * @author xiesongzhe
 * @date 2019-10-29 15:35:57
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sl_ultravioletlight")
public class Ultravioletlight extends Model<Ultravioletlight> {
  private static final long serialVersionUID = 1L;

  /**
   * 主键id
   */
  @TableId
  private Integer id;
  /**
   * 使用时间
   */
  private String useTime;
  /**
   * 消毒开始时间
   */
  private LocalDateTime disinfectionStart;
  /**
   * 消毒结束时间
   */
  private LocalDateTime disinfectionEnd;
  /**
   * 本次消毒时间（分钟）
   */
  private String disinfectionTime;
  /**
   * 灯管累计使用时间
   */
  private String lampUsageTime;
  /**
   * 操作人
   */
  private String username;
  /**
   * 审核人
   */
  private String reviewer;
  /**
   * 审核人是否确认
   */
  private String isConfirm;
  /**
   * 备注信息
   */
  private String remarks;
  /**
   * 创建人id
   */
  private Integer createId;
  /**
   * 创建时间
   */
  private LocalDateTime createTime;
  /**
   * 更新人id
   */
  private Integer updateId;
  /**
   * 修改时间
   */
  private LocalDateTime updateTime;
  /**
   * 是否删除  -1：已删除  0：正常
   */
  private String delFlag;
  /**
   * 关联学校id
   */
  private Integer schoolId;
}
