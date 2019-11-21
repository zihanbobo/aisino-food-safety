package com.pig4cloud.pig.school.api.entity.recipe;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 每日食谱资源关联信息
 *
 * @author xie
 * @date 2019-09-17 20:23:24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("re_daily_source")
public class DailySource extends Model<DailySource> {
private static final long serialVersionUID = 1L;

    /**
   * 每日食谱id
   */
    private Integer dailyId;
    /**
   * 食谱id
   */
    private Integer sourceId;
    /**
   * 关联学校id
   */
    private Integer userId;
    /**
   * 是否已留样（0未留样1已留样）
   */
    private String srType;
    /**
   * 留样时间
   */
    private LocalDateTime srStartdate;
    /**
   * 销毁时间
   */
    private LocalDateTime srEnddate;
    /**
   * 留样重量（g）
   */
    private Integer srWeight;
    /**
   * 留样人
   */
    private String srSampleholder;
    /**
   * 销毁人
   */
    private String srDestroys;
    /**
   * 快检报告地址
   */
    private String srCheckurl;
    /**
   * 留样图片
   */
    private String srPic;
    /**
     * 是否合格（1合格 2不合规）
     */
    private String isQualified;
  
}
