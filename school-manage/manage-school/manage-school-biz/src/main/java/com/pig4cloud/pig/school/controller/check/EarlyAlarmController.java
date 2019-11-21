package com.pig4cloud.pig.school.controller.check;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.dto.UserInfo;
import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;

import com.pig4cloud.pig.common.security.service.PigUser;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import com.pig4cloud.pig.school.api.dto.check.EarlyAlarmDTO;
import com.pig4cloud.pig.school.api.entity.check.EarlyAlarm;
import com.pig4cloud.pig.school.api.entity.check.EarlyWarning;
import com.pig4cloud.pig.school.service.check.EarlyAlarmService;
import com.pig4cloud.pig.school.service.check.EarlyWarningService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * 报警信息
 *
 * @author xiesongzhe
 * @date 2019-10-29 19:37:01
 */
@RestController
@AllArgsConstructor
@RequestMapping("/earlyalarm")
public class EarlyAlarmController {

  private final EarlyAlarmService earlyAlarmService;
  private final RemoteUserService remoteUserService;
  /**
   * 简单分页查询
   * @param page 分页对象
   * @param earlyAlarmDto 报警信息
   * @return
   */
  @GetMapping("/page")
  public R<IPage<EarlyAlarm>> getEarlyAlarmPage(Page<EarlyAlarm> page, EarlyAlarmDTO earlyAlarmDto) {
    // 权限控制
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    if("1".equals(userType)){
    }else if("2".equals(userType)){
      earlyAlarmDto.setSchoolId(userInfo.getSysUser().getUnionId());
    }
    return  new R<>(earlyAlarmService.getEarlyAlarmPage(page,earlyAlarmDto));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<EarlyAlarm> getById(@PathVariable("id") Integer id){
    return new R<>(earlyAlarmService.getById(id));
  }

  /**
   * 新增记录
   * @param earlyAlarm
   * @return R
   */
  @SysLog("新增报警信息")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('schoolMan_earlyalarm_add')")
  public R save(@RequestBody EarlyAlarm earlyAlarm){
    return new R<>(earlyAlarmService.save(earlyAlarm));
  }

  /**
   * 修改记录
   * @param earlyAlarm
   * @return R
   */
  @SysLog("修改报警信息")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('schoolMan_earlyalarm_edit')")
  public R update(@RequestBody EarlyAlarm earlyAlarm){
    return new R<>(earlyAlarmService.updateById(earlyAlarm));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除报警信息")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('schoolMan_earlyalarm_del')")
  public R removeById(@PathVariable Integer id){
    return new R<>(earlyAlarmService.removeById(id));
  }



  @PostMapping("/handleAlarm")
  public R handleAlarm() {
    // 报警信息
    List<Map> alarms = earlyAlarmService.getAlarm();
    List<EarlyAlarm> alarmList = alarms
      .stream().map(alarm -> {
        EarlyAlarm earlyAlarm = new EarlyAlarm();
        earlyAlarm.setAlarmTime(LocalDateTime.now());// 报警时间
        if(!StringUtils.isEmpty(String.valueOf(alarm.get("alarm")))&&!"null".equals(String.valueOf(alarm.get("alarm")))) {
          earlyAlarm.setAlarm(String.valueOf(alarm.get("alarm")));
        }
        // 过期时间
        if(!StringUtils.isEmpty(String.valueOf(alarm.get("expire_time")))&&!"null".equals(String.valueOf(alarm.get("expire_time")))){
          earlyAlarm.setExpireTime(LocalDate.parse(String.valueOf(alarm.get("expire_time")),DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
        // 学校名称
        earlyAlarm.setSchName(String.valueOf(alarm.get("sch_name")));
        earlyAlarm.setSchoolId(Integer.parseInt(String.valueOf(alarm.get("school_id"))));
        // 供应商名称
        if(!StringUtils.isEmpty(String.valueOf(alarm.get("supplier_name")))&&!"null".equals(String.valueOf(alarm.get("supplier_name")))){
          earlyAlarm.setSupplierName(String.valueOf(alarm.get("supplier_name")));
        }
        // 人员名称
        if(!StringUtils.isEmpty(String.valueOf(alarm.get("human")))&&!"null".equals(String.valueOf(alarm.get("human")))){
          earlyAlarm.setHuman(String.valueOf(alarm.get("human")));
        }
        // 完成采购时间
        if(!StringUtils.isEmpty(String.valueOf(alarm.get("purchase_time")))&&!"null".equals(String.valueOf(alarm.get("purchase_time")))){
          earlyAlarm.setPurchaseTime(LocalDate.parse(String.valueOf(alarm.get("purchase_time")),DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        }
        // 食材名称
        if(!StringUtils.isEmpty(String.valueOf(alarm.get("food_name")))&&!"null".equals(String.valueOf(alarm.get("food_name")))){
          earlyAlarm.setFoodName(String.valueOf(alarm.get("food_name")));
        }
        return earlyAlarm;
      }).collect(Collectors.toList());

    return new R<>(earlyAlarmService.saveBatch(alarmList));
  }

  @PostMapping("/delAlarm")
  public R delWarn() {
    return new R<>(earlyAlarmService.remove(Wrappers.<EarlyAlarm>update()));
  }

}
