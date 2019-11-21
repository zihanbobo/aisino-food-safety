package com.pig4cloud.pig.school.api.entity.source;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 学校自定义食材信息
 *
 * @author xiesongzhe
 * @date 2019-09-05 20:59:49
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sup_school_custom")
public class SchoolCustom extends Model<SchoolCustom> {
private static final long serialVersionUID = 1L;

    /**
   * 主键
   */
    @TableId
    private Integer id;
    /**
   * 学校id
   */
    private Integer schoolId;
    /**
   * 食材类型（关联食材id）
   */
    private Integer foodType;
    /**
   * 食材名称
   */
    private String foodName;
    /**
   * 食材图片
   */
    private String foodPic;
    /**
   * 食材品牌
   */
    private String foodBrand;
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
