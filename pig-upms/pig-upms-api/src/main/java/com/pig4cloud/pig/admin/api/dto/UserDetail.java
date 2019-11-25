package com.pig4cloud.pig.admin.api.dto;

import com.pig4cloud.pig.admin.api.entity.SysUser;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

@Data
public class UserDetail implements Serializable {
  /**
   * 用户基本信息
   */
  private Map<String,Object> sysUser;
  /**
   * 权限标识集合
   */
  private String[] permissions;
  
  /**
   * 角色集合
   */
  
  private String[] roleCodes;
}
