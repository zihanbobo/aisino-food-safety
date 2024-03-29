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

package com.pig4cloud.pig.school.service.recipe.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.school.api.entity.recipe.SourceFood;
import com.pig4cloud.pig.school.mapper.SourceFoodMapper;
import com.pig4cloud.pig.school.service.recipe.SourceFoodService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 食谱表 服务实现类
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
@Service
public class SourceFoodServiceImpl extends ServiceImpl<SourceFoodMapper, SourceFood> implements SourceFoodService {

	/**
	 * 根据食谱Id删除该用户的角色关系
	 *
	 * @param sourceId 食谱ID
	 * @return boolean
	 * @author 沈凝
	 * @date 2019年7月22日 16:31:38
	 */
	@Override
	public Boolean removeFoodBySourceId(Integer sourceId) {
		return baseMapper.deleteBySourceId(sourceId);
	}


}
