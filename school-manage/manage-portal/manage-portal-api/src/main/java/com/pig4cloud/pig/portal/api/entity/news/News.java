package com.pig4cloud.pig.portal.api.entity.news;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 食安新闻
 *
 * @author xiesongzhe
 * @date 2019-09-06 17:15:14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("site_news")
public class News extends Model<News> {
private static final long serialVersionUID = 1L;

    /**
   * 主键id
   */
    @TableId
    private Integer id;
    /**
   * 发布区域号
   */
    private String areacode;
    /**
   * 新闻标题
   */
    private String title;
    /**
   * 咨讯类型（字典表，1-新闻，2-曝光台，3-食安科普）
   */
    private String adType;
    /**
   * 新闻类型，字典表-1-全局，2-区域（保留字段）
   */
    private String newsType;
    /**
   * 副标题
   */
    private String subTitle;
    /**
   * 导读
   */
    private String navContent;
    /**
   * 内容
   */
    private String content;
    /**
   * 作者
   */
    private String author;
    /**
   * 来源
   */
    private String source;
    /**
   * 点击数
   */
    private String clickCounts;
    /**
   * 是否置顶（1不置顶2置顶）
   */
    private String isTop;
    /**
   * 是否发布，字典表-1-发布，2-未发布
   */
    private String isPublish;
    /**
   * 关键字
   */
    private String keywords;
    /**
   * 图片链接
   */
    private String imageUrl;
    /**
   * 
   */
    private LocalDateTime publishTime;
    /**
   * 发布人id
   */
    private Integer publishId;
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
