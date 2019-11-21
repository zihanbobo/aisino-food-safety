/*
 *  Copyright (c) 2019-2020, 冷冷 (wangiegie@gmail.com).
 *  <p>
 *  Licensed under the GNU Lesser General Public License 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  <p>
 * https://www.gnu.org/licenses/lgpl.html
 *  <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pig4cloud.pig.school.api.vo.recipe;

import com.pig4cloud.pig.school.api.entity.recipe.MainFiling;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 沈凝
 * @date 2019/7/4
 */
@Data
public class FoodFilingVO implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 主键ID
	 */
	private Integer id;
	/**
	 * 供应商id（关联供应商备案id）
	 */
	private String supId;
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
	 * 食材图片
	 */
	private String foodBrand;
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
	 * 食材类别ID
	 */
	private Integer foodtypeId;

	/**
	 * 食材类别名称
	 */
	private String foodtypeName;
	/**
	 * 供应商列表
	 */
	private List<MainFiling> mainFilingList;
}
