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

import com.pig4cloud.pig.school.api.entity.recipe.FoodFiling;
import com.pig4cloud.pig.school.api.entity.recipe.IngredientsFiling;
import com.pig4cloud.pig.school.api.entity.recipe.MainFiling;
import com.pig4cloud.pig.school.api.entity.recipe.Source;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author 沈凝
 * @date 2019/7/4
 */
@Data
public class SourceVO extends Source implements Serializable {
	private static final long serialVersionUID = 1L;


	private String schoolName;
  /**
   * 主料列表
   */
  private List<IngredientsFiling> mainMaterialList;
  /**
   * 辅料列表
   */
  private List<IngredientsFiling> minorIngredientList;

  private String mainMaterialName;  //主料名称集合

  private String minorIngredientName; //辅料名称集合


/*  private String realName;

  private String userId;*/
}
