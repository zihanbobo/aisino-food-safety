package com.pig4cloud.pig.school.api.entity.source;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 食材信息
 *
 * @author xiesongzhe
 * @date 2019-09-06 14:31:18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("sup_ingredients")
public class Ingredients extends Model<Ingredients> {
private static final long serialVersionUID = 1L;

    /**
   * 主键
   */
    @TableId
    private Integer id;
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
