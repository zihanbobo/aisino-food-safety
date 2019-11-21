package com.pig4cloud.pig.portal.api.entity.live;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 直播管理
 *
 * @author pig code generator
 * @date 2019-08-28 00:35:12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("vd_live")
public class Live extends Model<Live> {
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
   * 设备id
   */
    private Integer eqId;
    /**
   * 直播开始日期时间
   */
    private LocalDateTime startTime;
    /**
   * 直播结束时间
   */
    private LocalDateTime endTime;
    /**
   * 是否开启直播，1-是，2-否
   */
    private String isStart;
	/**
	 * 设备区域封面图片
	 */
	private String imageUrl;
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
