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

package com.pig4cloud.pig.school.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pig4cloud.pig.school.api.entity.selseal.SchoolMeal;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户角色表 Mapper 接口
 * </p>
 *
 * @author -
 * @since 2019/2/1
 */
public interface SchoolMealMapper extends BaseMapper<SchoolMeal> {
	/**
	 * 根据学校Id删除该套餐的关联关系
	 *
	 * @param schoolId 学校ID
	 * @return boolean
	 * @author -
	 * @date 2017年12月7日 16:31:38
	 */
	Boolean deleteBySchoolId(@Param("schoolId") Integer schoolId);
}
