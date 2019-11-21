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

package com.pig4cloud.pig.school.service.source;


import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.school.api.entity.recipe.FoodMain;

/**
 * <p>
 * 用户角色表 服务类
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
public interface FoodMainService extends IService<FoodMain> {

	/**
	 * 根据食材Id删除该供应商的的关系
	 *
	 * @param foodId 用户ID
	 * @return boolean
	 * @author 沈凝
	 * @date 2017年12月7日 16:31:38
	 */
	Boolean removeMainByUserId(Integer foodId);
}
