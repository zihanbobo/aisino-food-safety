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
package com.pig4cloud.pig.school.api.vo.source;
import com.pig4cloud.pig.school.api.entity.recipe.FoodFiling;
import com.pig4cloud.pig.school.api.entity.recipe.MainFiling;
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
public class SourceVO implements Serializable {
	private static final long serialVersionUID = 1L;

    /**
   * 主键
   */
    private Integer id;
    /**
   * 食谱类别
   */
    private String rcId;
    /**
   * 食谱名称
   */
    private String reName;
    /**
   * 食谱名称
   */
    private String rePic;
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
	 * 食材类别名称
	 */
	private String foodtypeName;
	/**
	 * 主料列表
	 */
	private List<FoodFiling> mainMaterialList;
	/**
	 * 辅料列表
	 */
	private List<FoodFiling> minorIngredientList;

	/**
	 * 供应商列表
	 */
	private List<MainFiling> mainFilingList;
}
