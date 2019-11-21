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
import com.pig4cloud.pig.school.api.feign.factory.RemoteStatisticsServiceFallbackFactory;
import com.pig4cloud.pig.school.api.feign.factory.SchoolDetailsServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @author lengleng
 * @date 2019/2/1
 */
@FeignClient(value = ServiceNameConstants.SCHOOL_SERVICE, fallbackFactory = SchoolDetailsServiceFallbackFactory.class)
public interface SchoolDetailsService {

  // 监管端-学校详情-供应商数量
	@GetMapping("/schooldeta/getSuppliersum")
  List<Map> getSuppliersum(@RequestParam(value = "schoolid") String schoolid,
                           @RequestHeader(SecurityConstants.FROM) String from);

  //学校资质信息
  @GetMapping("/schooldeta/getSchoolQualification")
  Map getSchoolQualification(@RequestParam(value = "schoolid") String schoolid,
                           @RequestHeader(SecurityConstants.FROM) String from);
  //根据学校id获得供应商信息
  @GetMapping("/schooldeta/getSupplierInformation")
  List<Map> getSupplierInformation(@RequestParam(value = "schoolid") String schoolid,
                           @RequestHeader(SecurityConstants.FROM) String from);

  //根据学校id获得台账信息
  @GetMapping("/schooldeta/getAccount")
  List<Map> getAccount(@RequestParam(value = "schoolId") String schoolid,
                                   @RequestHeader(SecurityConstants.FROM) String from);

  //根据学校id获得设备信息
  @GetMapping("/schooldeta/getSchoolVdDevice")
  List<Map> getSchoolVdDevice(@RequestParam(value = "schoolId") String schoolid,
                       @RequestHeader(SecurityConstants.FROM) String from);
  //设备检查合格比率
  @GetMapping("/schooldeta/getEqOperationRatio")
  Map getEqOperationRatio(@RequestParam(value = "schoolId") String schoolid,
                              @RequestHeader(SecurityConstants.FROM) String from);

}

