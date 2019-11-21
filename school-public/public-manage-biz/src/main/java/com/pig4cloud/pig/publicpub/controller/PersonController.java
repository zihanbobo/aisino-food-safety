package com.pig4cloud.pig.publicpub.controller;

import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.OSSUtil;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.portal.api.entity.Feedback;
import com.pig4cloud.pig.portal.api.entity.PublicUser;
import com.pig4cloud.pig.portal.api.feign.RemotePortalService;
import com.pig4cloud.pig.school.api.feign.RemoteSchoolService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * name 个人中心
 * auth Mr Miao
 */

@RestController
@AllArgsConstructor
@RequestMapping("/public")
public class PersonController {

  private final RemoteSchoolService remoteSchoolService;
  private final RemotePortalService remotePortalService;
  private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();  //密码类

  /**
   * 修改用户信息
   *
   * @param publicUser
   * @return
   */
  @PostMapping("/updateUserInfo")
  public R updateUserInfo(@RequestBody PublicUser publicUser) {
    PublicUser user = remotePortalService.getByPubId(publicUser.getId(), SecurityConstants.FROM_IN);
    user.setPubPic(publicUser.getPubPic());
    user.setNickName(publicUser.getNickName());
//    user.setPubName(publicUser.getPubName());
    user.setPubArea(publicUser.getPubArea());
    user.setSheng(publicUser.getSheng());
    user.setShi(publicUser.getShi());
    user.setSex(publicUser.getSex());
    return remotePortalService.updatePublicuser(user, SecurityConstants.FROM_IN);
  }

  /**
   * 绑定或解除绑定  直播，菜谱
   *
   * @param serviceCode
   * @param type
   * @param userId
   * @param operation   1 绑定 2 解除绑定
   * @return
   */
  @PostMapping("/bindSchool")
  public R bindSchool(@RequestParam(value = "serviceCode") String serviceCode,
                      @RequestParam(value = "type") String type,
                      @RequestParam(value = "userId") Integer userId,
                      @RequestParam(value = "operation") Integer operation) {
    R verify = remoteSchoolService.verify(serviceCode, type, SecurityConstants.FROM_IN);
    if (verify.getCode() == 1) {
      return new R(CommonConstants.FAIL, null, "服务码错误");
    }
    R byId = remotePortalService.getById(userId, SecurityConstants.FROM_IN);
    if (byId.getData() == null) {
      return new R(CommonConstants.FAIL, null, "未找到与该userId匹配的公共用户信息");
    }
    LinkedHashMap linkedHashMap = (LinkedHashMap) verify.getData();
    int schoolIdR = Integer.parseInt(String.valueOf(linkedHashMap.get("id")));
    // 查看该公共用户是否与学校绑定
    Boolean isBindSchool = remotePortalService.isBindSchool(schoolIdR, type, userId, SecurityConstants.FROM_IN);
    R r = null;
    if (operation == 1) {
      if (isBindSchool) {
        return new R(CommonConstants.FAIL, null, "重复绑定");
      }
       r = remotePortalService.bindSchool(schoolIdR, type, userId, SecurityConstants.FROM_IN);
    } else {
      if (!isBindSchool) {
        return new R(CommonConstants.FAIL, null, "该用户未与此学校绑定");
      }
      r = remotePortalService.unBindSchool(schoolIdR, type, userId, SecurityConstants.FROM_IN);
    }
    if (r.getCode() == 0){
      return remoteSchoolService.getById(schoolIdR,SecurityConstants.FROM_IN);
    }else{
      return new R(CommonConstants.FAIL,null,"操作失败！");
    }
  }

  /**
   * 更改绑定
   *
   * @param oldServiceCode
   * @param type
   * @param userId
   * @param newServiceCode
   * @return
   */
  @PostMapping("/updateBindSchool")
  public R updateBindSchool(@RequestParam(value = "oldServiceCode") String oldServiceCode,
                            @RequestParam(value = "type") String type,
                            @RequestParam(value = "userId") Integer userId,
                            @RequestParam(value = "newServiceCode") String newServiceCode) {
    R oldVerify = remoteSchoolService.verify(oldServiceCode, type, SecurityConstants.FROM_IN);
    R newVerify = remoteSchoolService.verify(newServiceCode, type, SecurityConstants.FROM_IN);
    if (newVerify.getCode() == 1) {
      return new R(CommonConstants.FAIL, null, "新的服务码错误");
    }
    LinkedHashMap oldHashMap = (LinkedHashMap) oldVerify.getData();
    LinkedHashMap newHashMap = (LinkedHashMap) newVerify.getData();
    int oldId = Integer.parseInt(String.valueOf(oldHashMap.get("id")));
    int newId = Integer.parseInt(String.valueOf(newHashMap.get("id")));
    // 查看该公共用户是否与学校绑定
    Boolean isBindSchool = remotePortalService.isBindSchool(newId, type, userId, SecurityConstants.FROM_IN);
    if (isBindSchool) {
      return new R(CommonConstants.FAIL, null, "重复绑定");
    }
    R r = remotePortalService.unBindSchool(oldId, type, userId, SecurityConstants.FROM_IN);
    if (r.getCode() == 0) {
      return remotePortalService.bindSchool(newId, type, userId, SecurityConstants.FROM_IN);
    } else {
      return r;
    }
  }

  /**
   * 获取已绑定的直播码信息，菜谱码信息
   *
   * @param userId
   * @return
   */
  @PostMapping("/findCodes")
  public R findCodes(@RequestParam(value = "userId") Integer userId) {
    Map map = new HashMap();
    map.put("liveCode", remotePortalService.checkSchoolByPubId(userId, "1", SecurityConstants.FROM_IN));
    map.put("menuCode", remotePortalService.checkSchoolByPubId(userId, "2", SecurityConstants.FROM_IN));
    return new R(map);
  }

  /**
   * 修改密码
   *
   * @param pubPhone
   * @param oldPassword
   * @param newPassword
   * @return
   */
  @PostMapping("/updatePassword")
  public R publicUpdatePass(@RequestParam(value = "pubPhone") String pubPhone,
                            @RequestParam(value = "oldPassword") String oldPassword,
                            @RequestParam(value = "newPassword") String newPassword) {
    PublicUser publicUser = remotePortalService.queryInfo(pubPhone, SecurityConstants.FROM_IN);
    if (!ENCODER.matches(oldPassword, publicUser.getPubPassword())) {
      return new R<>(CommonConstants.FAIL, null, "原密码错误");
    }
    // 修改为新密码
    publicUser.setPubPassword(ENCODER.encode(newPassword));  //密码加密
    return remotePortalService.updatePublicuser(publicUser, SecurityConstants.FROM_IN);
  }

  /**
   * 我发出的评论
   *
   * @param size
   * @param current
   * @param userId
   * @return R
   */
  @PostMapping("/findComments")
  public R<Object> findComments(@RequestParam(value = "size") Integer size,
                                @RequestParam(value = "current") Integer current,
                                @RequestParam(value = "userId") Integer userId) {
    return remotePortalService.personalComment(size, current, userId, SecurityConstants.FROM_IN);
  }

  /**
   * 我收到的回复
   *
   * @param size
   * @param current
   * @param userId
   * @return R
   */
  @PostMapping("/receiveComments")
  public R<Object> receiveComments(@RequestParam(value = "size") Integer size,
                                   @RequestParam(value = "current") Integer current,
                                   @RequestParam(value = "userId") Integer userId) {
    return remotePortalService.getAppReceiveComment(size, current, userId, SecurityConstants.FROM_IN);
  }

  /**
   * 删除评论
   *
   * @param commentId
   * @return
   */
  @PostMapping("/deleteComment")
  public R<Object> deleteComment(@RequestParam(value = "commentId") Integer commentId) {
    return remotePortalService.removeLogicById(commentId, SecurityConstants.FROM_IN);
  }

  /**
   * 新增意见反馈
   *
   * @param feedback
   * @return
   */
  @PostMapping("/insertFeedback")
  public R insertFeedback(@RequestBody Feedback feedback) {
    feedback.setDelFlag("0");
    feedback.setIsResponse("0");
    return remotePortalService.saveFeedback(feedback, SecurityConstants.FROM_IN);
  }

  @PostMapping("/uploadPic")
  public Map uploadPic(@RequestParam("file") MultipartFile file) {
    String upload = OSSUtil.upload(file, OSSUtil.FileDirType.ZHANGHAN_TEST);
    String fileName = file.getOriginalFilename();
    Map map = new HashMap();
    map.put("name", fileName);
    map.put("url", upload);
    return map;
  }
}
