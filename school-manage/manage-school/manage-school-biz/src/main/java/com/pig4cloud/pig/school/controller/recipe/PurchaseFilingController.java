package com.pig4cloud.pig.school.controller.recipe;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.dto.UserInfo;
import com.pig4cloud.pig.admin.api.feign.RemoteUserService;
import com.pig4cloud.pig.common.core.constant.SecurityConstants;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.service.PigUser;
import com.pig4cloud.pig.common.security.util.SecurityUtils;
import com.pig4cloud.pig.school.api.entity.recipe.PurchaseFiling;
import com.pig4cloud.pig.school.service.recipe.PurchaseFilingService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * 采购食材关联信息
 *
 * @author xiesongzhe
 * @date 2019-09-11 15:23:21
 */
@RestController
@AllArgsConstructor
@RequestMapping("/purchasefiling")
public class PurchaseFilingController {

  private final PurchaseFilingService purchaseFilingService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param purchaseFiling 采购食材关联信息
   * @return
   */
  @GetMapping("/page")
  public R<IPage<PurchaseFiling>> getPurchaseFilingPage(Page<PurchaseFiling> page, PurchaseFiling purchaseFiling) {
    return  new R<>(purchaseFilingService.getPurchaseFilingPage(page,purchaseFiling));
  }


  /**
   * 通过id查询单条记录
   * @param purId
   * @return R
   */
  @GetMapping("/{purId}")
  public R<PurchaseFiling> getById(@PathVariable("purId") Integer purId){
    return new R<>(purchaseFilingService.getById(purId));
  }

  /**
   * 新增记录
   * @param purchaseFiling
   * @return R
   */
  @SysLog("新增采购食材关联信息")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('school_purchasefiling_add')")
  public R save(@RequestBody PurchaseFiling purchaseFiling){
    return new R<>(purchaseFilingService.save(purchaseFiling));
  }

  /**
   * 修改记录
   * @param purchaseFiling
   * @return R
   */
  @SysLog("修改采购食材关联信息")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('school_purchasefiling_edit')")
  public R update(@RequestBody PurchaseFiling purchaseFiling){
    return new R<>(purchaseFilingService.updateById(purchaseFiling));
  }

  /**
   * 通过id删除一条记录
   * @param purId
   * @return R
   */
  @SysLog("删除采购食材关联信息")
  @DeleteMapping("/{purId}")
  @PreAuthorize("@pms.hasPermission('school_purchasefiling_del')")
  public R removeById(@PathVariable Integer purId){
    return new R<>(purchaseFilingService.removeById(purId));
  }

}
