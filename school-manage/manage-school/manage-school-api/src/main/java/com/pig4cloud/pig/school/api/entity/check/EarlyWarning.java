package com.pig4cloud.pig.school.api.entity.check;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * 预警信息
 *
 * @author xiesongzhe
 * @date 2019-10-29 19:03:16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ck_early_warning")
public class EarlyWarning extends Model<EarlyWarning> {
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
     * 学校名称（保留字段）
     */
    private String schName;
    /**
   * 预警时间
   */
    private LocalDateTime earlyTime;
    /**
   * 预警字典表
   */
    private String earlyWarning;
    /**
   * 到期时间
   */
    private LocalDate expireTime;
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
