package com.pig4cloud.pig.portal.api.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 设备表
 *
 * @author -
 * @date 2019-08-27 17:11:58
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("site_comment_praise")
public class CommentPraise extends Model<CommentPraise> {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Integer id;
	/**
	 * 学校id
	 */
	private Integer commentId;
	/**
	 *
	 */
	private Integer pubId;
	/**
	 * 点赞时间
	 */
	private LocalDateTime time;


}
