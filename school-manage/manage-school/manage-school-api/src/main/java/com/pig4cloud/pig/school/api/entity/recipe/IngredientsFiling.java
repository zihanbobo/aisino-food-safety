package com.pig4cloud.pig.school.api.entity.recipe;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 食材备案信息
 *
 * @author xiesongzhe
 * @date 2019-09-06 19:46:33
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sup_ingredients_filing")
public class IngredientsFiling extends Model<IngredientsFiling> {
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
   * 食材id
   */
    private String foodId;
    /**
   * 类型1食材库2自定义食材表
   */
    private String foodType;
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
