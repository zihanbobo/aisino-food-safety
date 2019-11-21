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

package com.pig4cloud.pig.school.api.feign.fallback;

import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.school.api.entity.account.AccompanyDinner;
import com.pig4cloud.pig.school.api.entity.account.Ultravioletlight;
import com.pig4cloud.pig.school.api.entity.message.Announcement;
import com.pig4cloud.pig.school.api.entity.recipe.MainFiling;
import com.pig4cloud.pig.school.api.feign.RemoteRecipeService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author lengleng
 * @date 2019/2/1
 */
@Slf4j
@Component
public class RemoteRecipeServiceFallbackImpl implements RemoteRecipeService {
	@Setter
	private Throwable cause;

	@Override
	public R<Object> getSourceByDay(Integer size, Integer current, Integer schoolId, String rdDate, /*String rdType,*/ String from) {
		log.error("feign 查询具体学校每日食谱:{}", cause);
		return null;
	}

  @Override
  public R<Object> getDailyRecipe(Integer size, Integer current, Integer schoolId, String rdDate, String rdType, String from) {
    log.error("feign 查询每日食谱:{}", cause);
    return null;
  }

  @Override
	public R<Object> getFoodTracingPerson(Integer size, Integer current, Integer schoolId, String rdDate,String rdType, String from) {
		log.error("feign 查询学校食材追溯-人员信息:{}", cause);
		return null;
	}
	@Override
	public R<Object> getSupplierInfo(Integer size, Integer current, Integer schoolId, String rdDate,/*String rdType,*/ String from) {
		log.error("feign 查询学校食材追溯-供应商信息:{}", cause);
		return null;
	}
	@Override
	public R<Object> getSampleInfo(Integer size, Integer current, Integer dailyId, Integer sourceId, String from) {
		log.error("feign 查询学校食材追溯-留样信息:{}", cause);
		return null;
	}
	@Override
	public R<Object> getIngredientsFood(Integer size, Integer current, Integer sourceId, String from) {
		log.error("feign 查询学校食材追溯-食材用料失败:{}", cause);
		return null;
	}

  @Override
  public R<Object> getPersonCook(Integer size, Integer current, Integer schoolId, String rdDate,/*String rdType,*/ String from) {
    log.error("feign 查询学校食材追溯-人员信息-大厨失败:{}", cause);
    return null;
  }
  @Override
  public R<Object> getPersonPosition(Integer size, Integer current, Integer schoolId, String rdDate,/*String rdType,*/ String from) {
    log.error("feign 查询学校食材追溯-人员信息-食安失败:{}", cause);
    return null;
  }
  @Override
  public R<Object> getPersonAccompany(Integer size, Integer current, Integer schoolId, String rdDate,/*String rdType,*/ String from) {
    log.error("feign 查询学校食材追溯-人员信息-陪餐失败:{}", cause);
    return null;
  }

  @Override
  public R<Object> getFoodIngredients(Integer size, Integer current, Integer sourceId, String from) {
    log.error("feign 食材追溯-食材信息失败:{}", cause);
    return null;
  }

  @Override
  public R<Object> getPersonnelInfo(Integer size, Integer current, Integer schoolId,  String position, String from) {
    log.error("feign app-recipes 人员信息失败:{}", cause);
	  return null;
  }

  @Override
  public R<Boolean> addSupplier(MainFiling mainFiling, String from) {
    log.error("feign 新增供应商信息失败", cause);
    return null;
  }

  @Override
  public R getSupplierById(Integer id, String from) {
    log.error("feign 查询供应商信息失败", cause);
    return null;
  }

  @Override
  public R<Object> getLiveListBySchoolId(Integer size, Integer current, Integer schoolId,String supName,String from) {
    log.error("feign 查询所有供应商信息失败", cause);
    return null;
  }

  @Override
  public R updateMainFiling(MainFiling mainFiling,String from) {
    log.error("feign 供应商信息修改失败", cause);
    return null;
  }

  @Override
  public R delMainfiling(Integer id,String from) {
    log.error("feign 删除供应商信息失败", cause);
    return null;
  }


  @Override
  public R addAccompanyDinner(AccompanyDinner accompanyDinner, String from) {
    log.error("feign 新增陪餐信息失败", cause);
    return null;
  }

  @Override
  public R<Object> getAllAccompanyDinner(Integer size, Integer current, Integer schoolId,String adTime,String from) {
    log.error("feign 查询所有陪餐信息失败", cause);
    return null;
  }

  @Override
  public R getAccompanyDinnerById(Integer id, String from) {
    log.error("feign 查询单个陪餐信息失败", cause);
    return null;
  }
  @Override
  public R updateAccompanyDinner(AccompanyDinner accompanyDinner,String from) {
    log.error("feign 陪餐信息修改失败", cause);
    return null;
  }
  @Override
  public R delAccompanyDinner(Integer id,String from) {
    log.error("feign 删除陪餐信息失败", cause);
    return null;
  }

  @Override
  public R addUltravioletLight(Ultravioletlight ultravioletlight, String from) {
    log.error("feign 新增紫外线灯失败", cause);
    return null;
  }

  @Override
  public R updateUltravioletLight(Ultravioletlight ultravioletlight, String from) {
    log.error("feign 修改紫外线灯失败", cause);
    return null;
  }

  @Override
  public R getLightPage(Integer size, Integer current, Integer schoolId,String userTime,String from) {
    log.error("feign 获取所有紫外线灯失败", cause);
	  return null;
  }

  @Override
  public R getUltravioletLightById(Integer id, String from) {
    log.error("feign 获取单个紫外线灯失败", cause);
    return null;
  }

  @Override
  public R delUltravioletLight(Integer id, String from) {
    log.error("feign 删除紫外线灯失败", cause);
    return null;
  }

}
