package com.pig4cloud.pig.collectioncol.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.pig4cloud.pig.admin.api.dto.UserDTO;
import com.pig4cloud.pig.admin.api.entity.SysUser;
import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.admin.api.vo.SysUserVO;
import com.pig4cloud.pig.common.core.constant.CommonConstants;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.OSSUtil;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.school.api.entity.recipe.MainFiling;
import com.pig4cloud.pig.school.api.feign.RemoteRecipeService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;


/**
 * h5数据采集用户信息
 *
 * @author -
 * @date 2019-08-29 11:23:11
 */
@RestController
@AllArgsConstructor
@RequestMapping("/human")
public class HumanController {

  private RemoteUserService remoteUserService;

	private static final PasswordEncoder ENCODER = new BCryptPasswordEncoder();	//密码类


  /**
   * h5数据采集查询该学校的所有用户信息
   * @param size
   * @param current
   * @param schoolId
   * @return
   */
  @GetMapping("/getAllHuman")
  public R getAllSupplier(
    @RequestParam(value = "size",required = false) Integer size,
    @RequestParam(value = "current",required = false) Integer current,
    @RequestParam(value = "schoolId") Integer schoolId,
    @RequestParam(value = "realName",required = false) String realName,
    @RequestParam(value = "position",required = false) String position
    ) {
    if(size==null){
      size = 10;
    }
    if(current==null){
      current = 1;
    }
    return remoteUserService.getAllHuman(size,current,schoolId,realName,position,SecurityConstants.FROM_IN);
  }

  /**
   * 新增用户信息
   * @param sysUser
   * @return
   */
  @PostMapping("/insertHuman")
  public R insertHuman(@RequestBody(required=false) SysUser sysUser) {
    /*List<Integer> role2 = new ArrayList<Integer>();
    role2.add(8);
    role2.add(9);
    userDTO.setRole(role2);*/
    return remoteUserService.addHuman(sysUser, SecurityConstants.FROM_IN);
  }

  /**
   * h5修改用户信息
   * @param
   * @return
   */
  @PostMapping("/updateHuman")
  public R updateHuman(@RequestBody UserDTO userDTO) {
    R<Boolean> booleanR = remoteUserService.updateHuman(userDTO, SecurityConstants.FROM_IN);
    //Boolean data = booleanR.getData();
    //return new R<>(data);
    return booleanR;
  }

  /**
   * h5删除用户信息
   * @param
   * @return
   */
  @GetMapping("/delHumanById")
  public R DelHumanById(@RequestParam(value = "id") Integer id) {
    R booleanR = remoteUserService.DelHumanById(id, SecurityConstants.FROM_IN);
//    Boolean data = booleanR.getData();
//    return new R<>(data);
    return booleanR;
  }

  /**
   * h5端根据id查询当前用户信息
   * @param userId
   * @return
   */
  @GetMapping("/getHumanById")
  public R getHumanById(@RequestParam(value = "userId") Integer userId) {
    return remoteUserService.getHumanById(userId, SecurityConstants.FROM_IN);
  }

}
