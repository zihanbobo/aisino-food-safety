package com.pig4cloud.pig.school.controller.account;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.dto.UserInfo;
import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.service.PigUser;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import com.pig4cloud.pig.portal.api.entity.CommentPraise;
import com.pig4cloud.pig.school.api.dto.account.SampleRecordDTO;
import com.pig4cloud.pig.school.api.entity.account.SampleRecord;
import com.pig4cloud.pig.school.api.entity.recipe.DailySource;
import com.pig4cloud.pig.school.api.entity.recipe.SourceFood;
import com.pig4cloud.pig.school.service.account.SampleRecordService;
import com.pig4cloud.pig.school.service.recipe.DailySourceService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 留样记录
 *
 * @author 沈凝
 * @date 2019-07-08 01:48:09
 */
@RestController
@AllArgsConstructor
@RequestMapping("/samplerecord")
public class SampleRecordController {

  private final RemoteUserService remoteUserService;
  private final SampleRecordService sampleRecordService;
  private final DailySourceService dailySourceService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param sampleRecordDto 留样记录
   * @return
   */
  @GetMapping("/page")
  public R<IPage<List<Map>>> getSampleRecordPage(Page page, SampleRecordDTO sampleRecordDto) {
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    if("1".equals(userType)){
    }else if("2".equals(userType)){
      sampleRecordDto.setSchoolId(userInfo.getSysUser().getUnionId());
    }
    Map<String, Object> map = new HashMap<>();
    map.put("schoolId",userInfo.getSysUser().getUnionId());
    map.put("rdDate",sampleRecordDto.getRdDate());
    map.put("isSample",sampleRecordDto.getIsSample());
    return  new R<>(sampleRecordService.getSampleSchoolPage(page,map));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<SampleRecord> getById(@PathVariable("id") Integer id){
    return new R<>(sampleRecordService.getById(id));
  }

  /**
   * 新增记录
   * @param sampleRecord
   * @return R
   */
  @SysLog("新增留样记录")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('sl_samplerecord_add')")
  public R save(@RequestBody SampleRecord sampleRecord){
    return new R<>(sampleRecordService.save(sampleRecord));
  }

  /**
   * 修改记录
   * @param dailySource
   * @return R
   */
  @SysLog("修改留样记录")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('sl_samplerecord_edit')")
  public R update(@RequestBody DailySource dailySource){
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    dailySource.setUpdateId(userInfo.getSysUser().getUserId());
//    return new R<>(dailySourceService.updateById(dailySource));

    // lambda+mybatisplus更新指定对象
    return new R<>(dailySourceService.update(dailySource,Wrappers.<DailySource>update().lambda()
      .eq(DailySource::getDailyId, dailySource.getDailyId())
      .eq(DailySource::getSourceId, dailySource.getSourceId())));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除留样记录")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('sl_samplerecord_del')")
  public R removeById(@PathVariable Integer id){
    return new R<>(sampleRecordService.removeById(id));
  }


  /**
   *
   *查询指定日期留样详情
   * @return
   */
//  @Inner
  @PostMapping("/getRecipeSampleDay")
  public R getRecipeSampleDay(@RequestParam(value="time") String time) {
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    Integer schoolId = userInfo.getSysUser().getUnionId();  //获取当前登录学校的id

    Map<String,Object> map = new HashMap<String,Object>();
    Map<String,Object> mapR = new HashMap<String,Object>();
    map.put("schoolId",schoolId);
    map.put("rdDate",time);
    return new R<>(sampleRecordService.getRecipeSampleDay(map));
  }


}
