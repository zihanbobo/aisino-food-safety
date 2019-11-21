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

package com.pig4cloud.pig.brmanage.api.feign;

import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.constant.ServiceNameConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.brmanage.api.feign.factory.RemoteSchoolServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

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

	// 获取学校列表
	@GetMapping("/school/list")
	R schoolList(@RequestHeader(SecurityConstants.FROM) String from);

	// 为学校设置超级管理员
	@PostMapping("/school/isadmin")
	R setIsadmin2(@RequestParam(value = "userId")Integer userId,@RequestParam(value = "schoolId")Integer schoolId, @RequestHeader(SecurityConstants.FROM) String from);

	// 为学校设置超级管理员
	@PostMapping("/school/isadmin")
	R setIsadmin(@RequestParam(value = "params") Map<String, Object> params, @RequestHeader(SecurityConstants.FROM) String from);

}
