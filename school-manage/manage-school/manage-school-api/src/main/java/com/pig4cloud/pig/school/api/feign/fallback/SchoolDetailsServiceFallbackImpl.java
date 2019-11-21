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
import com.pig4cloud.pig.school.api.feign.RemoteStatisticsService;
import com.pig4cloud.pig.school.api.feign.SchoolDetailsService;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;


/**
 * @author lengleng
 * @date 2019/2/1
 */
@Slf4j
@Component
public class SchoolDetailsServiceFallbackImpl implements SchoolDetailsService {
	@Setter
	private Throwable cause;

  @Override
  public List<Map> getSuppliersum(String schoolid, String from) {
    log.error("feign 获得供应商数量信息失败:{}", schoolid, cause);
    return null;
  }

  @Override
  public Map getSchoolQualification(String schoolid, String from) {
    log.error("feign 获得学校资质信息失败:{}", schoolid, cause);
    return null;
  }

  @Override
  public List<Map> getSupplierInformation(String schoolid, String from) {
    log.error("feign 获得供应商信息失败:{}", schoolid, cause);
    return null;
  }

  @Override
  public List<Map> getAccount(String schoolid, String from) {
    log.error("feign 获得台账信息失败:{}", schoolid, cause);
    return null;
  }

  @Override
  public List<Map> getSchoolVdDevice(String schoolid, String from) {
    log.error("feign 获得设备信息失败:{}", schoolid, cause);
    return null;
  }

  @Override
  public Map getEqOperationRatio(String schoolid, String from) {
    log.error("feign 获得设备检查合格比率信息失败:{}", schoolid, cause);
    return null;
  }
}
