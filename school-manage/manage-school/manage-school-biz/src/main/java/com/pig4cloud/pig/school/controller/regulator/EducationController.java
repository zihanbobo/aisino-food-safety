package com.pig4cloud.pig.school.controller.regulator;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;

import com.pig4cloud.pig.school.api.entity.regulator.Education;
import com.pig4cloud.pig.school.service.regulator.EducationService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 教育局信息
 *
 * @author xiesongzhe
 * @date 2019-10-20 18:31:16
 */
@RestController
@AllArgsConstructor
@RequestMapping("/education")
public class EducationController {

  private final EducationService educationService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param education 教育局信息
   * @return
   */
  @GetMapping("/page")
  public R<IPage<Education>> getEducationPage(Page<Education> page, Education education) {
    return  new R<>(educationService.getEducationPage(page,education));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<Education> getById(@PathVariable("id") Integer id){
    return new R<>(educationService.getById(id));
  }

  /**
   * 新增记录
   * @param education
   * @return R
   */
  @SysLog("新增教育局信息")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('schoolMan_education_add')")
  public R save(@RequestBody Education education){
    return new R<>(educationService.save(education));
  }

  /**
   * 修改记录
   * @param education
   * @return R
   */
  @SysLog("修改教育局信息")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('schoolMan_education_edit')")
  public R update(@RequestBody Education education){
    return new R<>(educationService.updateById(education));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除教育局信息")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('schoolMan_education_del')")
  public R removeById(@PathVariable Integer id){
    return new R<>(educationService.removeById(id));
  }



  /**
   * 返回树形菜单集合
   *
   * @return 树形菜单
   */
  @GetMapping(value = "/tree")
  public R listEducationTrees() {
    return new R<>(educationService.listEducationTrees());
  }



}
