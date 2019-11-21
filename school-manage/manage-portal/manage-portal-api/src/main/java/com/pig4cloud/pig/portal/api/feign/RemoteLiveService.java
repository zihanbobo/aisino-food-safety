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

package com.pig4cloud.pig.portal.api.feign;

import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.constant.ServiceNameConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.portal.api.feign.factory.RemoteLiveServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author lengleng
 * @date 2019/2/1
 */
@FeignClient(value = ServiceNameConstants.PORTAL_SERVICE, fallbackFactory = RemoteLiveServiceFallbackFactory.class)
public interface RemoteLiveService {

	@GetMapping("/live/livePage")
	R getLiveSchoolPage(@RequestParam(value = "size") Integer size, @RequestParam(value = "current") Integer current,
                      @RequestParam(value = "schoolId") Integer schoolId,
                      @RequestHeader(SecurityConstants.FROM) String from);

  /**
   * app-live 获取直播首页
   * @param schoolIds
   * @param from
   * @return
   */
  @GetMapping("/live/getLiveMain")
  R getLiveMain(@RequestParam(value = "schoolIds") List<Integer> schoolIds, @RequestHeader(SecurityConstants.FROM) String from);

  /**
   * app-live 获取直播列表
   * @param schoolId
   * @param from
   * @return
   */
  @GetMapping("/live/getLiveList")
  R getLiveListBySchoolId(@RequestParam(value = "schoolId") Integer schoolId, @RequestHeader(SecurityConstants.FROM) String from);

  /**
   * app-live 获取直播信息
   * @param liveId
   * @param from
   * @return
   */
  @GetMapping("/live/getLiveInfo")
  R getLiveInfoByLiveId(@RequestParam(value = "liveId") Integer liveId, @RequestHeader(SecurityConstants.FROM) String from);

  /**
   * 监管端-获取学校直播信息
   * @param schoolId
   * @param from
   * @return
   */
  @GetMapping("/live/getLiveSchoolWatch")
  R getLiveSchoolWatch(@RequestParam(value = "schoolId") Integer schoolId,
                        @RequestHeader(SecurityConstants.FROM) String from);

  /**
   * 监管端-获取直播流地址(有效期10s)
   * @param eqId
   * @param from
   * @return
   */
  @GetMapping("/live/getIoTLivePathWatch")
  R getIoTLivePathWatch(@RequestParam(value = "eqId") Integer eqId,
                       @RequestHeader(SecurityConstants.FROM) String from);



}
