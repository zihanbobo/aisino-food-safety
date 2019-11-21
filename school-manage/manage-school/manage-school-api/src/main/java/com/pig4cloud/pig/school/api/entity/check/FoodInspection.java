package com.pig4cloud.pig.school.api.entity.check;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 食材快检管理
 *
 * @author xiesongzhe
 * @date 2019-10-30 20:51:34
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ck_food_inspection")
public class FoodInspection extends Model<FoodInspection> {
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
   * 检查时间
   */
    private LocalDateTime checkTime;
    /**
   * 检查单位
   */
    private String inspectionUnit;
    /**
   * 检查食材id
   */
    private Integer foodId;
    /**
     * 检查食材记录名称(检查时名称)
     */
    private String foodnameNote;
    /**
   * 是否合格
   */
    private String isQualified;
    /**
   * 上传文件路径
   */
    private String fileUrl;
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
