package com.pig4cloud.pig.school.api.entity.check;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.pig4cloud.pig.school.api.entity.School;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 报警信息
 *
 * @author xiesongzhe
 * @date 2019-10-29 19:37:01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ck_early_alarm")
public class EarlyAlarm extends Model<EarlyAlarm> {
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
   * 报警时间
   */
    private LocalDateTime alarmTime;
    /**
   * 处理状态（1未处理2已处理3误报）
   */
    private String dealWith;
    /**
   * 报警字典表
   */
    private String alarm;
    /**
   * 到期时间（营业执照、许可证、健康证）
   */
    private LocalDate expireTime;
    /**
   * 学校名称
   */
    private String schName;
    /**
   * 供应商名称
   */
    private String supplierName;
    /**
   * 人员姓名
   */
    private String human;
    /**
   * 采购时间
   */
    private LocalDate purchaseTime;
    /**
   * 食材名称（采购管理）
   */
    private String foodName;
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
