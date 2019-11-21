package com.pig4cloud.pig.portal.api.entity.live;

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
@TableName("vd_equipment")
public class Equipment extends Model<Equipment> {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Integer id;
	/**
	 * 学校id
	 */
	private Integer schoolId;
	/**
	 * 设备id(关联)
	 */
	private String eqId;
	/**
	 * 设备名称
	 */
	private String eqName;
	/**
	 * 设备编号
	 */
	private String eqCode;
	/**
	 * 设备型号
	 */
	private String eqModal;
	/**
	 * 设备品牌
	 */
	private String eqBrand;
	/**
	 * 设备ip地址
	 */
	private String eqIp;
	/**
	 * 设备端口
	 */
	private String eqPort;
	/**
	 * 设备码流
	 */
	private String eqBit;
	/**
	 * 设备负责区域
	 */
	private String eqArea;
	/**
	 * 餐厅名称
	 */
	private String restaurantName;
	/**
	 * 设备状态
	 */
	private String eqStatus;
	/**
	 * 设备帐号
	 */
	private String eqAdmin;
	/**
	 * 设备密码
	 */
	private String eqPassword;
	/**
	 * 设备路径
	 */
	private String eqUrl;
	/**
	 * 创建人id
	 */
	private Integer createById;
	/**
	 * 创建人时间
	 */
	private LocalDateTime createTime;
	/**
	 * 更新人id
	 */
	private Integer updateById;
	/**
	 * 更新时间
	 */
	private LocalDateTime updateTime;
	/**
	 * 是否删除  -1：已删除  0：正常
	 */
	private String delFlag;


}
