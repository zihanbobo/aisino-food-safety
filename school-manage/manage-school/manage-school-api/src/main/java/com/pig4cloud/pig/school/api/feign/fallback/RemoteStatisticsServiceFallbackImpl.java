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
import com.pig4cloud.pig.school.api.entity.School;
import com.pig4cloud.pig.school.api.entity.message.Announcement;
import com.pig4cloud.pig.school.api.entity.message.RegulatoryOpinion;
import com.pig4cloud.pig.school.api.feign.RemoteSchoolService;
import com.pig4cloud.pig.school.api.feign.RemoteStatisticsService;
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
public class RemoteStatisticsServiceFallbackImpl implements RemoteStatisticsService {
	@Setter
	private Throwable cause;


  @Override
  public R getEarlywarningListYear(String year, String regionalLevel, String areaCode, String from) {
    log.error("feign 查询预警列表信息失败:{}", year, cause);
    return null;
  }

  @Override
  public R getAlarmListYear(String year, String regionalLevel, String areaCode, String from) {
    log.error("feign xxx:{}", year, cause);
    return null;
  }

  @Override
  public R getAlarmsNumberByMonth(String year, String regionalLevel, String areaCode, String from) {
    log.error("feign xxx:{}", year, cause);
    return null;
  }

  @Override
  public R getAlarmTotalByYear(String year, String regionalLevel, String areaCode, String from) {
    log.error("feign 查询预警分类统计信息失败:{}", year, cause);
    return null;
  }

  @Override
  public R getAlarmsAreaByRegion(String year, String regionalLevel, String areaCode, Integer limit, String from) {
    log.error("feign 查询报警区域统计柱状图:{}", year, cause);
    return null;
  }

  @Override
  public R getAlarmsArea(String year, String regionalLevel, String areaCode, Integer limit, String from) {
    log.error("feign 查询预警分类统计信息失败:{}", year, cause);
    return null;
  }

  @Override
  public R getEarlyWarningRank(String month, String regionalLevel, String areaCode, Integer limit, String from) {
    log.error("feign 监管端-趋势分析-学校预警总数Top5:{}", month, cause);
    return null;
  }

  @Override
  public R getEarlyAlarmRank(String month, String regionalLevel, String areaCode, Integer limit, String from) {
    log.error("feign 监管端-趋势分析-学校报警总数Top5:{}", month, cause);
    return null;
  }

  @Override
  public R getEarlyTotalContrast(String month, String regionalLevel, String areaCode, String from) {
    log.error("feign 监管端-预警报警分析-预警环节数量对比(根据月查询):{}", month, cause);
    return null;
  }

  @Override
  public R getAlarmTotalContrast(String month, String regionalLevel, String areaCode, String from) {
    log.error("feign 监管端-预警报警分析-报警环节数量对比(根据月查询):{}", month, cause);
    return null;
  }

  @Override
  public R getWarnAlarmTotalContrast(String month, String regionalLevel, String areaCode, String from) {
    log.error("feign 监管端-预警报警分析-预警数量统计&报警数量统计(根据月查询):{}", month, cause);
    return null;
  }












  @Override
  public R getSchoolInformation(String schoolId, String from) {
    log.error("feign 获得学校信息失败:{}", schoolId, cause);
    return null;
  }

  @Override
  public R getSchoolQualification(String schoolId, String from) {
    log.error("feign 获得学校资质信息失败:{}", schoolId, cause);
    return null;
  }

  @Override
  public R getSupplierInformation(String schoolId, String from) {
    log.error("feign 获得供应商信息失败:{}", schoolId, cause);
    return null;
  }

  @Override
  public R getPersonnelInformation(String schoolId, String from) {
    log.error("feign 获得人员信息失败:{}", schoolId, cause);
    return null;
  }

  @Override
  public R getDeviceInformation(String schoolId, String from) {
    log.error("feign 获得设备信息失败:{}", schoolId, cause);
    return null;
  }

  @Override
  public R getIngredientsInformation(String schoolId,String startingTime,String endTime, String from) {
    log.error("feign 获得食材信息失败:{}", schoolId, cause);
    return null;
  }

  @Override
  public R getAccount(String schoolId, String from) {
    log.error("feign 获得台账信息失败:{}", schoolId, cause);
    return null;
  }

  @Override
  public R getHistoricalAlarm(String schoolId, String year,String from) {
    log.error("feign 获得历史报警失败:{}", schoolId, cause);
    return null;
  }

}
