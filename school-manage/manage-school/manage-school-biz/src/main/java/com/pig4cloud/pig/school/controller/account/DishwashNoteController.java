package com.pig4cloud.pig.school.controller.account;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.dto.UserInfo;
import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.service.PigUser;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import com.pig4cloud.pig.school.api.dto.account.DishwashNoteDTO;
import com.pig4cloud.pig.school.api.entity.account.DiningareaWash;
import com.pig4cloud.pig.school.api.entity.account.DishwashNote;
import com.pig4cloud.pig.school.service.account.DishwashNoteService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 * 餐具洗消记录
 *
 * @author 沈凝
 * @date 2019-07-04 17:22:49
 */
@RestController
@AllArgsConstructor
@RequestMapping("/dishwashnote")
public class DishwashNoteController {

  private final RemoteUserService remoteUserService;
  private final DishwashNoteService dishwashNoteService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param dishwashNoteDTO 餐具洗消记录
   * @return
   */
  @GetMapping("/page")
  public R<IPage<DishwashNote>> getDishwashNotePage(Page<DishwashNote> page, DishwashNoteDTO dishwashNoteDTO) {
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    String userType = userInfo.getSysUser().getUserType();
    if("1".equals(userType)){
    }else if("2".equals(userType)){
      dishwashNoteDTO.setSchoolId(userInfo.getSysUser().getUnionId());
    }
    Map<String, Object> map = new HashMap<>();
    return  new R<>(dishwashNoteService.getDishwashNotePage(page,dishwashNoteDTO));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<DishwashNote> getById(@PathVariable("id") Integer id){
    return new R<>(dishwashNoteService.getById(id));
  }

  /**
   * 新增记录
   * @param dishwashNote
   * @return R
   */
  @SysLog("新增餐具洗消记录")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('sl_dishwashnote_add')")
  public R save(@RequestBody DishwashNote dishwashNote){
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    Integer userId = userInfo.getSysUser().getUserId();
    Integer schoolId = userInfo.getSysUser().getUnionId();
    dishwashNote.setSchoolId(schoolId);
    return new R<>(dishwashNoteService.save(dishwashNote));
  }

  /**
   * 修改记录
   * @param dishwashNote
   * @return R
   */
  @SysLog("修改餐具洗消记录")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('sl_dishwashnote_edit')")
  public R update(@RequestBody DishwashNote dishwashNote){
    return new R<>(dishwashNoteService.updateById(dishwashNote));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除餐具洗消记录")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('sl_dishwashnote_del')")
  public R removeById(@PathVariable Integer id){
    DishwashNote dishwashNote = dishwashNoteService.getById(id);
    dishwashNote.setDelFlag("1");
    return new R<>(dishwashNoteService.updateById(dishwashNote));
  }

}
