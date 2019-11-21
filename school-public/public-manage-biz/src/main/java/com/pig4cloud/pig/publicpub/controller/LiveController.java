package com.pig4cloud.pig.publicpub.controller;

import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.portal.api.feign.RemoteLiveService;
import com.pig4cloud.pig.portal.api.feign.RemotePortalService;
import com.pig4cloud.pig.school.api.feign.RemoteSchoolService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 直播模块
 *
 * @author - Mr_Miao
 * @date 2019-09-20 14:12:21
 */
@RestController
@AllArgsConstructor
@RequestMapping("/public")
public class LiveController {

  private final RemoteSchoolService remoteSchoolService;
  private final RemotePortalService remotePortalService;
  private final RemoteLiveService remoteLiveService;

  /**
   * 查询学校信息
   *
   * @param id
   * @return
   */
  @PostMapping("/getSchoolEasyInfo")
  public R getSchoolEasyInfo(@RequestParam(value = "id") Integer id) {
    return remoteSchoolService.getSchoolEasyInfo(id, SecurityConstants.FROM_IN);
  }

  /**
   * 获取直播首页
   *
   * @param userId
   * @return
   */
  @PostMapping("/getLiveMain")
  public R getLiveMain(@RequestParam(value = "userId") Integer userId) {
    List<Map> schoolList = remotePortalService.checkSchoolByPubId(userId, "1", SecurityConstants.FROM_IN);
    if (schoolList != null && schoolList.size() > 0) {
      List<Integer> schoolIds = new ArrayList<>();
      for (Map map : schoolList) {
        schoolIds.add(Integer.valueOf(map.get("schoolId").toString()));
      }
      return remoteLiveService.getLiveMain(schoolIds, SecurityConstants.FROM_IN);
    } else {
      return new R<>(CommonConstants.FAIL, null, "未绑定学校！");
    }
  }

  /**
   * 获取学校直播列表
   *
   * @param schoolId
   * @return
   */
  @PostMapping("/getLiveList")
  public R getLiveListBySchoolId(@RequestParam(value = "schoolId") Integer schoolId) {
    return remoteLiveService.getLiveListBySchoolId(schoolId, SecurityConstants.FROM_IN);
  }

  /**
   * 获取直播信息
   *
   * @param liveId
   * @return
   */
  @PostMapping("/getLiveInfo")
  public R getLiveInfoByLiveId(@RequestParam(value = "liveId") Integer liveId) {
    return remoteLiveService.getLiveInfoByLiveId(liveId, SecurityConstants.FROM_IN);
  }

}
