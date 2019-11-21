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

package com.pig4cloud.pig.school.api.entity.selseal;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * <p>
 * 套餐服务表
 * </p>
 *
 * @author -
 * @since 2019/2/1
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("se_school_meal")
public class SchoolMeal extends Model<SchoolMeal> {

	private static final long serialVersionUID = 1L;

	/**
	 * 学校ID
	 */
	private Integer schoolId;
	/**
	 * 套餐ID
	 */
	private Integer mealId;
	/**
	 * 套餐开始时间
	 */
	private  LocalDate startDate;

	/**
	 * 套餐结束时间
	 */
	private  LocalDate endDate;

}
