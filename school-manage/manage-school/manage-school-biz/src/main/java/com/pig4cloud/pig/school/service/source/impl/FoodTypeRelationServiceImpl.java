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

package com.pig4cloud.pig.school.service.source.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.school.api.entity.source.FoodType;
import com.pig4cloud.pig.school.api.entity.source.FoodTypeRelation;
import com.pig4cloud.pig.school.mapper.FoodTypeRelationMapper;
import com.pig4cloud.pig.school.service.source.FoodTypeRelationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
@Service
@AllArgsConstructor
public class FoodTypeRelationServiceImpl extends ServiceImpl<FoodTypeRelationMapper, FoodTypeRelation> implements FoodTypeRelationService {
	private final FoodTypeRelationMapper foodTypeRelationMapper;

	/**
	 * 维护部门关系
	 *
	 * @param foodType 部门
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public void saveFoodTypeRelation(FoodType foodType) {
		//增加部门关系表
		FoodTypeRelation condition = new FoodTypeRelation();
		condition.setDescendant(foodType.getParentId());
		List<FoodTypeRelation> relationList = foodTypeRelationMapper
			.selectList(Wrappers.<FoodTypeRelation>query().lambda()
				.eq(FoodTypeRelation::getDescendant, foodType.getParentId()))
			.stream().map(relation -> {
				relation.setDescendant(foodType.getFoodtypeId());
				return relation;
			}).collect(Collectors.toList());
		if (CollUtil.isNotEmpty(relationList)) {
			this.saveBatch(relationList);
		}

		//自己也要维护到关系表中
		FoodTypeRelation own = new FoodTypeRelation();
		own.setDescendant(foodType.getFoodtypeId());
		own.setAncestor(foodType.getFoodtypeId());
		foodTypeRelationMapper.insert(own);
	}


	/**
	 * 通过ID删除部门关系
	 *
	 * @param id
	 */
	@Override
	public void removeFoodTypeRelationById(Integer id) {
		baseMapper.deleteFoodTypeRelationsById(id);
	}

	/**
	 * 更新部门关系
	 *
	 * @param relation
	 */
	@Override
	public void updateFoodTypeRelation(FoodTypeRelation relation) {
		baseMapper.updateFoodTypeRelations(relation);
	}

}
