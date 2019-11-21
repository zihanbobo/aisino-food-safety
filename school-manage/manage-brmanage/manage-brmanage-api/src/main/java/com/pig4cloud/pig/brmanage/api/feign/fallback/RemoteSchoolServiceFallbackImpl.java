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

package com.pig4cloud.pig.brmanage.api.feign.fallback;

import com.pig4cloud.pig.brmanage.api.feign.RemoteSchoolService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.pig4cloud.pig.common.core.util.R;

import java.util.Map;

/**
 * @author lengleng
 * @date 2019/2/1
 */
@Slf4j
@Component
public class RemoteSchoolServiceFallbackImpl implements RemoteSchoolService {
	@Setter
	private Throwable cause;

	@Override
	public R getById(Integer id, String from) {
		log.error("feign 查询学校详情信息失败:{}", id, cause);
		return null;
	}
	@Override
	public R schoolList(String from) {
		log.error("feign 查询学校列表失败:{}", cause);
		return null;
	}

	@Override
	public R setIsadmin(Map<String, Object> params, String from) {
		log.error("feign 更新学校信息失败:{}",params, cause);
		return null;
	}
	@Override
	public R setIsadmin2(Integer userId,Integer schoolId,String from) {
		log.error("feign 更新学校信息失败:{}",userId,schoolId, cause);
		return null;
	}
}
