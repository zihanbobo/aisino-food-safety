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

package com.pig4cloud.pig.school.api.feign;

import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.constant.ServiceNameConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.school.api.entity.account.AccompanyDinner;
import com.pig4cloud.pig.school.api.entity.account.Ultravioletlight;
import com.pig4cloud.pig.school.api.entity.message.Announcement;
import com.pig4cloud.pig.school.api.entity.recipe.MainFiling;
import com.pig4cloud.pig.school.api.feign.factory.RemoteRecipeServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author lengleng
 * @date 2019/2/1
 */
@FeignClient(value = ServiceNameConstants.SCHOOL_SERVICE, fallbackFactory = RemoteRecipeServiceFallbackFactory.class)
public interface RemoteRecipeService {

	// 获取单个学校详细信息
	@GetMapping("/daily/getSourceByDay")
  R<Object> getSourceByDay(@RequestParam(value = "size")Integer size,
                                  @RequestParam(value = "current")Integer current,
                                  @RequestParam(value = "schoolId")Integer schoolId,
                                  @RequestParam(value = "rdDate") String rdDate,
                                  /*@RequestParam(value = "rdType") String rdType,*/
                                  @RequestHeader(SecurityConstants.FROM) String from);

  // 公众app 获取每日食谱
  @GetMapping("/daily/getDailyRecipe")
  R<Object> getDailyRecipe(@RequestParam(value = "size")Integer size,
                           @RequestParam(value = "current")Integer current,
                           @RequestParam(value = "schoolId")Integer schoolId,
                           @RequestParam(value = "rdDate") String rdDate,
                           @RequestParam(value = "rdType",required = false) String rdType,
                           @RequestHeader(SecurityConstants.FROM) String from);

  // 门户接口-食材追溯-人员信息
	@GetMapping("/daily/getFoodTracingPerson")
  R<Object> getFoodTracingPerson(@RequestParam(value = "size")Integer size,
                                  @RequestParam(value = "current")Integer current,
                                  @RequestParam(value = "schoolId")Integer schoolId,
                                  @RequestParam(value = "rdDate") String rdDate,
                                  @RequestParam(value = "rdType") String rdType,
                                  @RequestHeader(SecurityConstants.FROM) String from);

  // 门户接口-食材追溯-供应商信息
	@GetMapping("/daily/getSupplierInfo")
  R<Object> getSupplierInfo(@RequestParam(value = "size")Integer size,
                                  @RequestParam(value = "current")Integer current,
                                  @RequestParam(value = "schoolId")Integer schoolId,
                                  @RequestParam(value = "rdDate") String rdDate,
                                /*  @RequestParam(value = "rdType") String rdType,*/
                                  @RequestHeader(SecurityConstants.FROM) String from);
  // 门户接口-食材追溯-留样信息
	@GetMapping("/daily/getSampleInfo")
  R<Object> getSampleInfo(@RequestParam(value = "size")Integer size,
                                  @RequestParam(value = "current")Integer current,
                                  @RequestParam(value = "dailyId")Integer dailyId,
                                  @RequestParam(value = "sourceId") Integer sourceId,
                                  @RequestHeader(SecurityConstants.FROM) String from);
  // 门户接口-食材追溯-食材用料
	@GetMapping("/daily/getIngredientsFood")
  R<Object> getIngredientsFood(@RequestParam(value = "size")Integer size,
                                  @RequestParam(value = "current")Integer current,
                                  @RequestParam(value = "sourceId") Integer sourceId,
                                  @RequestHeader(SecurityConstants.FROM) String from);

  // 门户接口-食材追溯-人员信息-大厨
  @GetMapping("/daily/getPersonCook")
  R<Object> getPersonCook(@RequestParam(value = "size")Integer size,
                                 @RequestParam(value = "current")Integer current,
                                 @RequestParam(value = "schoolId")Integer schoolId,
                                 @RequestParam(value = "rdDate") String rdDate,
                                 /*@RequestParam(value = "rdType") String rdType,*/
                                 @RequestHeader(SecurityConstants.FROM) String from);

  // 门户接口-食材追溯-人员信息-食品安全管理员
  @GetMapping("/daily/getPersonPosition")
  R<Object> getPersonPosition(@RequestParam(value = "size")Integer size,
                                 @RequestParam(value = "current")Integer current,
                                 @RequestParam(value = "schoolId")Integer schoolId,
                                 @RequestParam(value = "rdDate") String rdDate,
                                /* @RequestParam(value = "rdType") String rdType,*/
                                 @RequestHeader(SecurityConstants.FROM) String from);

  // 门户接口-食材追溯-人员信息-陪餐人员
  @GetMapping("/daily/getPersonAccompany")
  R<Object> getPersonAccompany(@RequestParam(value = "size")Integer size,
                                 @RequestParam(value = "current")Integer current,
                                 @RequestParam(value = "schoolId")Integer schoolId,
                                 @RequestParam(value = "rdDate") String rdDate,
                                 /*@RequestParam(value = "rdType") String rdType,*/
                                 @RequestHeader(SecurityConstants.FROM) String from);

  // 公众端-食材追溯-食材信息
  @GetMapping("/daily/getFoodIngredients")
  R<Object> getFoodIngredients(@RequestParam(value = "size")Integer size,
                               @RequestParam(value = "current")Integer current,
                               @RequestParam(value = "sourceId") Integer sourceId,
                               @RequestHeader(SecurityConstants.FROM) String from);

  /**
   * app-recipes 人员信息
   * @param size
   * @param current
   * @param schoolId
   * @param position
   * @param from
   * @return
   */
  @GetMapping("/daily/getPersonnelInfo")
  R<Object> getPersonnelInfo(@RequestParam(value = "size")Integer size,
                         @RequestParam(value = "current")Integer current,
                         @RequestParam(value = "schoolId")Integer schoolId,
                         @RequestParam(value = "position")String position,
                         @RequestHeader(SecurityConstants.FROM) String from);


  // h5数据采集 新增供应商信息
  @PostMapping("/mainfiling/addSupplier")
  R<Boolean> addSupplier(@RequestBody MainFiling mainFiling, @RequestHeader(SecurityConstants.FROM) String from);

  // h5数据采集获取单个供应商详细信息
  @GetMapping("/mainfiling/getSupplierById")
  R getSupplierById(@RequestParam(value = "id")Integer id,
                           @RequestHeader(SecurityConstants.FROM) String from);

  // h5数据采集根据学校id查询所有供应商列表
  @GetMapping("/mainfiling/getLiveListBySchoolId")
  R<Object> getLiveListBySchoolId(@RequestParam(value = "size")Integer size,
                                 @RequestParam(value = "current")Integer current,
                                 @RequestParam(value = "schoolId")Integer schoolId,
                                 @RequestParam(value = "supName",required = false)String supName, @RequestHeader(SecurityConstants.FROM) String from);

  // h5数据采集更新供应商信息
  @PostMapping("/mainfiling/updateMainfiling")
  R updateMainFiling(@RequestBody MainFiling mainFiling, @RequestHeader(SecurityConstants.FROM) String from);

  // h5数据采集删除供应商信息
  @GetMapping("/mainfiling/delMainfiling")
  R delMainfiling(@RequestParam(value = "id")Integer id, @RequestHeader(SecurityConstants.FROM) String from);

  // h5数据采集 新增陪餐信息信息
  @PostMapping("/accompanydinner/addAccompanyDinner")
  R addAccompanyDinner(@RequestBody AccompanyDinner accompanyDinner, @RequestHeader(SecurityConstants.FROM) String from);

  //h5数据采集 查询所有陪餐信息
  @GetMapping("/accompanydinner/getAllAccompanyDinner")
  R<Object> getAllAccompanyDinner(@RequestParam(value = "size")Integer size,
                                  @RequestParam(value = "current")Integer current,
                                  @RequestParam(value = "schoolId")Integer schoolId,
                                  @RequestParam(value = "adTime",required = false)String adTime,
                                  @RequestHeader(SecurityConstants.FROM) String from);

  // h5数据采集获取单个陪餐信息
  @GetMapping("/accompanydinner/getAccompanyDinnerById")
  R getAccompanyDinnerById(@RequestParam(value = "id")Integer id,
                           @RequestHeader(SecurityConstants.FROM) String from);

  // h5数据采集更新陪餐信息
  @PostMapping("/accompanydinner/updateAccompanyDinner")
  R updateAccompanyDinner(@RequestBody AccompanyDinner accompanyDinner, @RequestHeader(SecurityConstants.FROM) String from);

  // h5数据采集删除陪餐信息
  @GetMapping("/accompanydinner/delAccompanyDinner")
  R delAccompanyDinner(@RequestParam(value = "id")Integer id, @RequestHeader(SecurityConstants.FROM) String from);

  // h5数据采集 新增紫外线灯设备信息
  @PostMapping("/ultravioletLight/addUltravioletLight")
  R addUltravioletLight(@RequestBody Ultravioletlight ultravioletlight, @RequestHeader(SecurityConstants.FROM) String from);

  // h5数据采集 修改紫外线灯设备信息
  @PostMapping("/ultravioletLight/updateUltravioletLight")
  R updateUltravioletLight(@RequestBody Ultravioletlight ultravioletlight, @RequestHeader(SecurityConstants.FROM) String from);

  // h5数据采集获取所有紫外线灯设备信息
  @GetMapping("/ultravioletLight/getLightPage")
  R getLightPage(@RequestParam(value = "size")Integer size,
                 @RequestParam(value = "current")Integer current,
                 @RequestParam(value = "schoolId")Integer schoolId,
                 @RequestParam(value = "useTime",required = false)String useTime,
                 @RequestHeader(SecurityConstants.FROM) String from);

  // h5数据采集获取单个紫外线灯设备信息
  @GetMapping("/ultravioletLight/getUltravioletLightById")
  R getUltravioletLightById(@RequestParam(value = "id")Integer id,
                           @RequestHeader(SecurityConstants.FROM) String from);

  // h5数据采集 删除紫外线灯设备信息
  @GetMapping("/ultravioletLight/delUltravioletLight")
  R delUltravioletLight(@RequestParam(value = "id")Integer id, @RequestHeader(SecurityConstants.FROM) String from);

}
