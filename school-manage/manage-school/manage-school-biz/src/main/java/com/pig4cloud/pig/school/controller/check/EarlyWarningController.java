package com.pig4cloud.pig.school.controller.check;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.dto.UserInfo;
import com.pig4cloud.pig.admin.api.entity.SysUserRole;
import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.service.PigUser;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import com.pig4cloud.pig.school.api.dto.check.EarlyWarningDTO;
import com.pig4cloud.pig.school.api.entity.check.EarlyWarning;
import com.pig4cloud.pig.school.api.entity.recipe.SourceFood;
import com.pig4cloud.pig.school.service.check.EarlyWarningService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 预警信息
 *
 * @author xiesongzhe
 * @date 2019-10-29 19:03:16
 */
@RestController
@AllArgsConstructor
@RequestMapping("/earlywarning")
public class EarlyWarningController {

  private final RemoteUserService remoteUserService;
  private final EarlyWarningService earlyWarningService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param earlyWarningDto 预警信息
   * @return
   */
  @GetMapping("/page")
  public R<IPage<EarlyWarning>> getEarlyWarningPage(Page<EarlyWarning> page, EarlyWarningDTO earlyWarningDto) {
    // 权限控制
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    if("1".equals(userType)){
    }else if("2".equals(userType)){
      earlyWarningDto.setSchoolId(userInfo.getSysUser().getUnionId());
    }
    return  new R<>(earlyWarningService.getEarlyWarningPage(page,earlyWarningDto));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<EarlyWarning> getById(@PathVariable("id") Integer id){
    return new R<>(earlyWarningService.getById(id));
  }

  /**
   * 新增记录
   * @param earlyWarning
   * @return R
   */
  @SysLog("新增预警信息")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('schoolMan_earlywarning_add')")
  public R save(@RequestBody EarlyWarning earlyWarning){
    return new R<>(earlyWarningService.save(earlyWarning));
  }

  /**
   * 修改记录
   * @param earlyWarning
   * @return R
   */
  @SysLog("修改预警信息")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('schoolMan_earlywarning_edit')")
  public R update(@RequestBody EarlyWarning earlyWarning){
    return new R<>(earlyWarningService.updateById(earlyWarning));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除预警信息")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('schoolMan_earlywarning_del')")
  public R removeById(@PathVariable Integer id){
    return new R<>(earlyWarningService.removeById(id));
  }

  @PostMapping("/handleWarn")
  public R handleWarn() {
    List<EarlyWarning> EarlyWarningList = new ArrayList<EarlyWarning>();

    // 营业执照预警
    List<Map> bulicenseWarns = earlyWarningService.getBulicenseWarn();
    List<EarlyWarning> bulicenseWarnList = bulicenseWarns
      .stream().map(bulicenseWarn -> {
        EarlyWarning earlyWarning = new EarlyWarning();
        earlyWarning.setEarlyTime(LocalDateTime.now());
        earlyWarning.setEarlyWarning(String.valueOf(bulicenseWarn.get("early_warning")));
        // 过期时间
        earlyWarning.setExpireTime(LocalDate.parse(String.valueOf(bulicenseWarn.get("expire_time")),DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        // 学校名称
        earlyWarning.setSchName(String.valueOf(bulicenseWarn.get("sch_name")));
        earlyWarning.setSchoolId(Integer.parseInt(String.valueOf(bulicenseWarn.get("school_id"))));
        // 供应商名称
        earlyWarning.setSupplierName(String.valueOf(bulicenseWarn.get("supplier_name")));
        return earlyWarning;
      }).collect(Collectors.toList());

    // 供应商许可预警
    List<Map> permitWarns = earlyWarningService.getPermitWarn();
    List<EarlyWarning> permitWarnList = permitWarns
      .stream().map(permitWarn -> {
        EarlyWarning earlyWarning = new EarlyWarning();
        earlyWarning.setEarlyTime(LocalDateTime.now());
        earlyWarning.setEarlyWarning(String.valueOf(permitWarn.get("early_warning")));
        earlyWarning.setExpireTime(LocalDate.parse(String.valueOf(permitWarn.get("expire_time")),DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        earlyWarning.setSchName(String.valueOf(permitWarn.get("sch_name")));
        earlyWarning.setSchoolId(Integer.parseInt(String.valueOf(permitWarn.get("school_id"))));
        earlyWarning.setSupplierName(String.valueOf(permitWarn.get("supplier_name")));
        return earlyWarning;
      }).collect(Collectors.toList());

    // 人员健康证预警
    List<Map> healthWarns = earlyWarningService.getHealthWarn();
    List<EarlyWarning> healthWarnList = healthWarns
      .stream().map(healthWarn -> {
        EarlyWarning earlyWarning = new EarlyWarning();
        earlyWarning.setEarlyTime(LocalDateTime.now());
        earlyWarning.setEarlyWarning(String.valueOf(healthWarn.get("early_warning")));
        // 健康证过期时间
        earlyWarning.setExpireTime(LocalDate.parse(String.valueOf(healthWarn.get("expire_time")),DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        // 学校名称
        earlyWarning.setSchName(String.valueOf(healthWarn.get("sch_name")));
        earlyWarning.setSchoolId(Integer.parseInt(String.valueOf(healthWarn.get("school_id"))));
        // 姓名
        earlyWarning.setHuman(String.valueOf(healthWarn.get("human")));
//        earlyWarning.setSupplierName(String.valueOf(healthWarn.get("supplier_name")));
        return earlyWarning;
      }).collect(Collectors.toList());

    // 食材预警(某次采购清单未上传索证索票)
    List<Map> purchaseWarns = earlyWarningService.getPurchaseWarn();
    List<EarlyWarning> purchaseWarnsList = purchaseWarns
      .stream().map(purchaseWarn -> {
        EarlyWarning earlyWarning = new EarlyWarning();
        earlyWarning.setEarlyTime(LocalDateTime.now());
        earlyWarning.setEarlyWarning(String.valueOf(purchaseWarn.get("early_warning")));
        // 过期时间
//        earlyWarning.setExpireTime(LocalDate.parse(String.valueOf(purchaseWarn.get("expire_time")),DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        // 学校id
        earlyWarning.setSchName(String.valueOf(purchaseWarn.get("sch_name")));
        earlyWarning.setSchoolId(Integer.parseInt(String.valueOf(purchaseWarn.get("school_id"))));
        // 姓名
//        earlyWarning.setHuman(String.valueOf(purchaseWarn.get("human")));
        // 供应商名称
//        earlyWarning.setSupplierName(String.valueOf(healthWarn.get("supplier_name")));
        // 采购时间
        earlyWarning.setPurchaseTime(LocalDate.parse(String.valueOf(purchaseWarn.get("purchase_time")),DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        return earlyWarning;
      }).collect(Collectors.toList());



    // 将主料辅料合并后插入
    EarlyWarningList.addAll(bulicenseWarnList);
    EarlyWarningList.addAll(permitWarnList);
    EarlyWarningList.addAll(healthWarnList);
    EarlyWarningList.addAll(purchaseWarnsList);

    return new R<>(earlyWarningService.saveBatch(EarlyWarningList));
  }

  @PostMapping("/delWarn")
  public R delWarn() {
    return new R<>(earlyWarningService.remove(Wrappers.<EarlyWarning>update()));
  }

}
