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
import com.pig4cloud.pig.school.api.feign.factory.RemoteStatisticsServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author lengleng
 * @date 2019/2/1
 */
@FeignClient(value = ServiceNameConstants.SCHOOL_SERVICE, fallbackFactory = RemoteStatisticsServiceFallbackFactory.class)
public interface RemoteStatisticsService {


  //监管端-趋势分析-学校预警总数Top5
  @GetMapping("/trendAnalysis/getEarlyWarningRank")
  R getEarlyWarningRank(@RequestParam(value="month")String month,
                        @RequestParam(value="regionalLevel")String regionalLevel,
                        @RequestParam(value="areaCode")String areaCode,
                        @RequestParam(value="limit")Integer limit,
                        @RequestHeader(SecurityConstants.FROM) String from);
  //监管端-趋势分析-学校报警总数Top5
  @GetMapping("/trendAnalysis/getEarlyAlarmRank")
  R getEarlyAlarmRank(@RequestParam(value="month")String month,
                        @RequestParam(value="regionalLevel")String regionalLevel,
                        @RequestParam(value="areaCode")String areaCode,
                        @RequestParam(value="limit")Integer limit,
                        @RequestHeader(SecurityConstants.FROM) String from);
  //监管端-趋势分析-预警环节数量对比(根据月查询)
  @GetMapping("/trendAnalysis/getEarlyTotalContrast")
  R getEarlyTotalContrast(@RequestParam(value="month")String month,
                        @RequestParam(value="regionalLevel")String regionalLevel,
                        @RequestParam(value="areaCode")String areaCode,
                        @RequestHeader(SecurityConstants.FROM) String from);
  //监管端-趋势分析-报警环节数量对比(根据月查询)
  @GetMapping("/trendAnalysis/getAlarmTotalContrast")
  R getAlarmTotalContrast(@RequestParam(value="month")String month,
                        @RequestParam(value="regionalLevel")String regionalLevel,
                        @RequestParam(value="areaCode")String areaCode,
                        @RequestHeader(SecurityConstants.FROM) String from);
  //监管端-趋势分析-预警数量统计&报警数量统计(根据月查询)
  @GetMapping("/trendAnalysis/getWarnAlarmTotalContrast")
  R getWarnAlarmTotalContrast(@RequestParam(value="month")String month,
                        @RequestParam(value="regionalLevel")String regionalLevel,
                        @RequestParam(value="areaCode")String areaCode,
                        @RequestHeader(SecurityConstants.FROM) String from);




  // 1-1:预警列表
  @GetMapping("/analysisData/getEarlywarningListYear")
  R getEarlywarningListYear(@RequestParam(value="year")String year,
                            @RequestParam(value="regionalLevel")String regionalLevel,
                            @RequestParam(value="areaCode")String areaCode,
                            @RequestHeader(SecurityConstants.FROM) String from);
  // 1-2:报警列表
  @GetMapping("/analysisData/getAlarmListYear")
  R getAlarmListYear(@RequestParam(value="year")String year,
                        @RequestParam(value="regionalLevel")String regionalLevel,
                        @RequestParam(value="areaCode")String areaCode,
                        @RequestHeader(SecurityConstants.FROM) String from);
  //2-1:报警数量统计柱状图
  @GetMapping("/analysisData/getAlarmsNumberByMonth")
  R getAlarmsNumberByMonth(@RequestParam(value="year")String year,
                        @RequestParam(value="regionalLevel")String regionalLevel,
                        @RequestParam(value="areaCode")String areaCode,
                        @RequestHeader(SecurityConstants.FROM) String from);
  //2-2:报警类别统计饼状图
  @GetMapping("/analysisData/getAlarmTotalByYear")
  R getAlarmTotalByYear(@RequestParam(value="year")String year,
                        @RequestParam(value="regionalLevel")String regionalLevel,
                        @RequestParam(value="areaCode")String areaCode,
                        @RequestHeader(SecurityConstants.FROM) String from);
  // 2-3:报警区域统计柱状图
  @GetMapping("/analysisData/getAlarmsAreaByRegion")
  R getAlarmsAreaByRegion(@RequestParam(value="year")String year,
                        @RequestParam(value="regionalLevel")String regionalLevel,
                        @RequestParam(value="areaCode")String areaCode,
                        @RequestParam(value="limit")Integer limit,
                        @RequestHeader(SecurityConstants.FROM) String from);
  // 2-4:报警区域横向柱状图
  @GetMapping("/analysisData/getAlarmsArea")
  R getAlarmsArea(@RequestParam(value="year")String year,
                        @RequestParam(value="regionalLevel")String regionalLevel,
                        @RequestParam(value="areaCode")String areaCode,
                        @RequestParam(value="limit")Integer limit,
                        @RequestHeader(SecurityConstants.FROM) String from);






  //学校信息上
  @GetMapping("/analysisData/getSchoolInformation")
  R getSchoolInformation(@RequestParam(value = "schoolId") String schoolId,
                         @RequestHeader(SecurityConstants.FROM) String from);

  //学校资质信息
  @GetMapping("/analysisData/getSchoolQualification")
  R getSchoolQualification(@RequestParam(value = "schoolId") String schoolId,
                         @RequestHeader(SecurityConstants.FROM) String from);
  //供应商信息
  @GetMapping("/analysisData/getSupplierInformation")
  R getSupplierInformation(@RequestParam(value = "schoolId") String schoolId,
                                   @RequestHeader(SecurityConstants.FROM) String from);

  //人员信息
  @GetMapping("/analysisData/getPersonnelInformation")
  R getPersonnelInformation(@RequestParam(value = "schoolId") String schoolId,
                           @RequestHeader(SecurityConstants.FROM) String from);
  //设备信息
  @GetMapping("/analysisData/getDeviceInformation")
  R getDeviceInformation(@RequestParam(value = "schoolId") String schoolId,
                      @RequestHeader(SecurityConstants.FROM) String from);
  //食材信息
  @GetMapping("/analysisData/getIngredientsInformation")
  R getIngredientsInformation(@RequestParam(value="page")Integer page,
                              @RequestParam(value="size")Integer size,
                              @RequestParam(value = "schoolId") String schoolId,
                              @RequestParam(value = "startingTime") String startingTime,
                              @RequestParam(value = "endTime") String endTime,
               @RequestHeader(SecurityConstants.FROM) String from);
  //台账信息
  @GetMapping("/analysisData/getAccount")
  R getAccount(@RequestParam(value = "schoolId") String schoolId,
                       @RequestHeader(SecurityConstants.FROM) String from);
  //历史报警
  @GetMapping("/analysisData/getHistoricalAlarm")
  R getHistoricalAlarm(@RequestParam(value="page")Integer page,
                       @RequestParam(value="size")Integer size,
                       @RequestParam(value = "schoolId") String schoolId,
                       @RequestHeader(SecurityConstants.FROM) String from);


//历史详情(食材)
  @GetMapping("/analysisData/getHistoryFood")
  R getHistoryFood(@RequestParam(value = "schoolId") String schoolId,
                      @RequestParam(value = "iswarning") Integer iswarning,
                      @RequestParam(value = "warningId") Integer warningId,
               @RequestHeader(SecurityConstants.FROM) String from);

  //历史详情(供应商)
  @GetMapping("/analysisData/getHistoryDetails")
  R getHistoryDetails(@RequestParam(value = "schoolId") String schoolId,
                      @RequestParam(value = "iswarning") Integer iswarning,
                      @RequestParam(value = "warningId") Integer warningId,
                      @RequestHeader(SecurityConstants.FROM) String from);

  //历史详情(人员)
  @GetMapping("/analysisData/getHistoryMan")
  R getHistoryMan(@RequestParam(value = "schoolId") String schoolId,
                      @RequestParam(value = "iswarning") Integer iswarning,
                      @RequestParam(value = "warningId") Integer warningId,
                      @RequestHeader(SecurityConstants.FROM) String from);








  //预警类型
  @GetMapping("/analysisData/getWarningType")
  R getWarningType(@RequestParam(value="year")String year,
                   @RequestParam(value="regionalLevel")String regionalLevel,
                   @RequestParam(value="areaCode")String areaCode,
                   @RequestHeader(SecurityConstants.FROM) String from);

  //全部预警
  @GetMapping("/analysisData/getWarnings")
  R getWarnings(@RequestParam(value = "dealWith") String dealWith,
                @RequestParam(value="WarningInfor")Integer WarningInfor,
                @RequestParam(value="schoolName")String schoolName,
                @RequestParam(value="startingTime")String startingTime,
                @RequestParam(value="endTime")String endTime,
                @RequestParam(value="page")Integer page,
                @RequestParam(value="size")Integer size,
                @RequestParam(value="regionalLevel")String regionalLevel,
                @RequestParam(value="areaCode")String areaCode,
                @RequestParam(value = "Type") Integer Type,
                @RequestHeader(SecurityConstants.FROM) String from);


  //报警类型
  @GetMapping("/analysisData/getAlarmType")
  R getAlarmType(@RequestParam(value="year")String year,
                   @RequestParam(value="regionalLevel")String regionalLevel,
                   @RequestParam(value="areaCode")String areaCode,
                   @RequestHeader(SecurityConstants.FROM) String from);

  //全部报警
  @GetMapping("/analysisData/getAlarms")
  R getAlarms(@RequestParam(value="AlarmInfor")Integer AlarmInfor,
                @RequestParam(value="schoolName")String schoolName,
                @RequestParam(value="startingTime")String startingTime,
                @RequestParam(value="endTime")String endTime,
                @RequestParam(value="page")Integer page,
                @RequestParam(value="size")Integer size,
                @RequestParam(value="regionalLevel")String regionalLevel,
                @RequestParam(value="areaCode")String areaCode,
                @RequestParam(value = "Type") Integer Type,
                @RequestHeader(SecurityConstants.FROM) String from);
}
