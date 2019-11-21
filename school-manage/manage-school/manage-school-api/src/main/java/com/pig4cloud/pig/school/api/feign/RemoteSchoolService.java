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
import com.pig4cloud.pig.school.api.entity.School;
import com.pig4cloud.pig.school.api.entity.message.Announcement;
import com.pig4cloud.pig.school.api.entity.message.RegulatoryOpinion;
import com.pig4cloud.pig.school.api.feign.factory.RemoteSchoolServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author lengleng
 * @date 2019/2/1
 */
@FeignClient(value = ServiceNameConstants.SCHOOL_SERVICE, fallbackFactory = RemoteSchoolServiceFallbackFactory.class)
public interface RemoteSchoolService {

	// 获取单个学校详细信息
	@GetMapping("/school/{id}")
	R getById(@PathVariable("id") Integer id
		, @RequestHeader(SecurityConstants.FROM) String from);

	// 修改学校表
  @PutMapping("/school")
	R update(@RequestBody School school
    , @RequestHeader(SecurityConstants.FROM) String from);



	// 获取学校列表
	@GetMapping("/school/list")
	R schoolList(@RequestHeader(SecurityConstants.FROM) String from);

	// 为学校设置超级管理员
	@PostMapping("/school/isadmin")
	R setIsadmin2(@RequestParam(value = "userId")Integer userId,@RequestParam(value = "schoolId")Integer schoolId, @RequestHeader(SecurityConstants.FROM) String from);

	// 为学校设置超级管理员
	@PostMapping("/school/isadmin")
	R setIsadmin(@RequestParam(value = "params") Map<String, Object> params, @RequestHeader(SecurityConstants.FROM) String from);

	// 验证学校服务码
	@PostMapping("/school/verify")
	R verify(@RequestParam(value = "serviceCode") String serviceCode,@RequestParam(value = "type") String type, @RequestHeader(SecurityConstants.FROM) String from);

  // app 直播模块--获取学校信息
  @GetMapping("/school/getSchoolEasyInfo/{id}")
  R getSchoolEasyInfo(@PathVariable("id") Integer id
    , @RequestHeader(SecurityConstants.FROM) String from);

  // 学校用户管理-获取学校信息（无超级管理员的学校）
  @GetMapping("/school/getNoSchoolUserid")
  List getNoSchoolUserid(@RequestHeader(SecurityConstants.FROM) String from);

  // 监管端 通知公告模块--发送通知信息
  @PostMapping("/announcement/insertAnnouncement")
  R<Boolean> insertAnnouncement(@RequestBody Announcement announcement, @RequestHeader(SecurityConstants.FROM) String from);

  // 监管端 通知公告模块--查看通知信息
  @GetMapping("/announcement/findPage")
  R findPage(@RequestParam(value = "size")Integer size,
                      @RequestParam(value = "current")Integer current,
                      @RequestParam(value = "userId")Integer userId,
                      @RequestHeader(SecurityConstants.FROM) String from);

  // 监管端 通知公告模块--查看通知信息(根据监管人查看状态条件查询)
  @GetMapping("/announcement/findForStatus")
  R findForStatus(
              @RequestParam(value = "size")Integer size,
              @RequestParam(value = "current")Integer current,
              @RequestParam(value = "userId")Integer userId,
              @RequestParam(value = "supStatus")String supStatus,
              @RequestHeader(SecurityConstants.FROM) String from);
  // 监管端 通知公告模块--修改通知信息
  @PostMapping("/announcement/updateAnnouncement")
  R<Boolean> updateAnnouncement(@RequestBody Announcement announcement, @RequestHeader(SecurityConstants.FROM) String from);

  // 管理端 学校绑定或者解绑超级管理员
  @PostMapping("/school/schoolUserId")
  R<Boolean> schoolUserId(@RequestParam(value = "userId")Integer userId,
             @RequestParam(value = "schoolId")Integer schoolId,
             @RequestParam(value = "type")String type,
             @RequestHeader(SecurityConstants.FROM) String from);

  // 监管端 监管意见模块--下达监管意见
  @PostMapping("/regulatoryopinion/insertRegulatoryOpinion")
  R<Boolean> insertRegulatoryOpinion(@RequestBody RegulatoryOpinion regulatoryOpinion, @RequestHeader(SecurityConstants.FROM) String from);

  // 监管端 监管意见模块--查看监管意见
  @GetMapping("/regulatoryopinion/findPageForOp")
  R findPageForOp(@RequestParam(value = "size")Integer size,
                 @RequestParam(value = "current")Integer current,
                 @RequestParam(value = "userId")Integer userId,
                 @RequestHeader(SecurityConstants.FROM) String from);

}
