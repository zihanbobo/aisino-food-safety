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

package com.pig4cloud.pig.school.api.dto;

import com.pig4cloud.pig.school.api.entity.School;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;
import java.util.Map;

/**
 * @author shenning
 * @date 2019/2/1
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SchoolDTO extends School {
	/**
	 * 套餐（套餐id）
	 */
	private List<Integer> mealList;
	/**
	 * 套餐（套餐id）
	 */
	private List<Map<String,Object>> mealListAll;

	private String mealListAll2;

	private String isUseridNull;  // 是否只显示没有超级管理员的学校（1就是需要查）
}
