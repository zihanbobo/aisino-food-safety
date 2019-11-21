package com.pig4cloud.pig.portal.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 评论信息
 *
 * @author -
 * @date 2019-08-28 14:12:21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("site_comment")
public class Comment extends Model<Comment> {
private static final long serialVersionUID = 1L;

    /**
   * 主键id
   */
    @TableId
    private Integer id;
    /**
   * 关联学校id
   */
    private Integer schoolId;
    /**
   * 关联公共用户id
   */
    private Integer userId;
    /**
   * 父级id（回复评论id）
   */
    private Integer parentId;
    /**
   * 评论/回复内容
   */
    private String content;
    /**
   * 评论/回复时间
   */
    private LocalDateTime time;
    /**
   * 点赞数
   */
    private Integer praiseCounts;
    /**
   * 评论类别，（字典表1-直播，2-录播，3-食谱）
   */
    private String commentCategory;
    /**
   * 评论类型，1-评论，2-回复
   */
    private String commentType;
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
