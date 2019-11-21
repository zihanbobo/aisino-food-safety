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

package com.pig4cloud.pig.school.api.dto.selseal;

import com.pig4cloud.pig.school.api.entity.selseal.SelMeal;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author shenning
 * @date 2019/2/1
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SelMealDTO extends SelMeal {
	/**
	 * 服务（服务id）
	 */
	private List<Integer> serviceList;

}
