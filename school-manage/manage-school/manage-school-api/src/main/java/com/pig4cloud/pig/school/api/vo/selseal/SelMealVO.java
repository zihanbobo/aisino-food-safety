/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */
package com.pig4cloud.pig.school.api.vo.selseal;
import com.pig4cloud.pig.school.api.entity.selseal.ServiceMain;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 食谱资源表
 *
 * @author 沈凝
 * @date 2019-07-09 15:16:31
 */
@Data
public class SelMealVO implements Serializable {
	private static final long serialVersionUID = 1L;

    /**
   * 主键
   */
	private Integer id;
	/**
	 * 套餐名称
	 */
	private String mealName;
	/**
	 * 套餐内容
	 */
	private String mealContent;
	/**
	 * 学校风采图
	 */
	private String mealPic;
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

	/**
	 * 服务列表
	 */
	private List<ServiceMain> serviceList;

}
