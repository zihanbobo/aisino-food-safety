package com.pig4cloud.pig.school.controller.message;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.dto.UserInfo;
import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.annotation.Inner;
import com.pig4cloud.pig.common.security.service.PigUser;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import com.pig4cloud.pig.school.api.entity.message.Announcement;
import com.pig4cloud.pig.school.api.entity.message.RegulatoryOpinion;
import com.pig4cloud.pig.school.service.message.RegulatoryOpinionService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 监管意见信息
 *
 * @author xiesongzhe
 * @date 2019-10-16 17:06:21
 */
@RestController
@AllArgsConstructor
@RequestMapping("/regulatoryopinion")
public class RegulatoryOpinionController {

  private final RemoteUserService remoteUserService;
  private final RegulatoryOpinionService regulatoryOpinionService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param regulatoryOpinion 监管意见信息
   * @return
   */
  @GetMapping("/page")
  public R<IPage<RegulatoryOpinion>> getRegulatoryOpinionPage(Page<RegulatoryOpinion> page, RegulatoryOpinion regulatoryOpinion) {
    PigUser user = SecurityUtils.getUser();
    String username = user.getUsername();	// 当前登录用户昵称
    R<UserInfo> result = remoteUserService.info(username, SecurityConstants.FROM_IN);
    UserInfo userInfo = result.getData();
    Integer userId = userInfo.getSysUser().getUserId();
    Integer schoolId = userInfo.getSysUser().getUnionId();
    regulatoryOpinion.setSchoolId(schoolId);
    return  new R<>(regulatoryOpinionService.getRegulatoryOpinionPage(page,regulatoryOpinion));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<RegulatoryOpinion> getById(@PathVariable("id") Integer id){
    return new R<>(regulatoryOpinionService.getById(id));
  }

  /**
   * 新增记录
   * @param regulatoryOpinion
   * @return R
   */
  @SysLog("新增监管意见信息")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('schoolMan_regulatoryopinion_add')")
  public R save(@RequestBody RegulatoryOpinion regulatoryOpinion){
    return new R<>(regulatoryOpinionService.save(regulatoryOpinion));
  }

  /**
   * 修改记录
   * @param regulatoryOpinion
   * @return R
   */
  @SysLog("修改监管意见信息")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('schoolMan_regulatoryopinion_edit')")
  public R update(@RequestBody RegulatoryOpinion regulatoryOpinion){
    return new R<>(regulatoryOpinionService.updateById(regulatoryOpinion));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除监管意见信息")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('schoolMan_regulatoryopinion_del')")
  public R removeById(@PathVariable Integer id){
    return new R<>(regulatoryOpinionService.removeById(id));
  }

  /**
   * 监管端下发通知接口
   */
  @Inner
  @PostMapping("/insertRegulatoryOpinion")
  public R insertRegulatoryOpinion(@RequestBody RegulatoryOpinion regulatoryOpinion) {
    return new R<>(regulatoryOpinionService.save(regulatoryOpinion));
  }

  /* *//**
   * 监管端查询通知公告接口
   */
  @GetMapping("/findPageForOp")
  public R<IPage<RegulatoryOpinion>> findPageForOp(@RequestParam(value = "size")Integer size,
                                                   @RequestParam(value = "current")Integer current,
                                                   @RequestParam(value = "userId")Integer userId) {
    Page page = new Page();
    page.setSize((long)size);
    page.setCurrent((long) current);
    RegulatoryOpinion regulatoryOpinion =new RegulatoryOpinion();
    regulatoryOpinion.setUserId(userId);
    return  new R<>(regulatoryOpinionService.getRegulatoryOpinionPage(page,regulatoryOpinion));
  }
}
