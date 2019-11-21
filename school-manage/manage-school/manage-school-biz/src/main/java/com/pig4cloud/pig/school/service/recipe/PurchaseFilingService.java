package com.pig4cloud.pig.school.service.recipe;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.school.api.entity.recipe.PurchaseFiling;


/**
 * 采购食材关联信息
 *
 * @author xiesongzhe
 * @date 2019-09-11 15:23:21
 */
public interface PurchaseFilingService extends IService<PurchaseFiling> {

  /**
   * 采购食材关联信息简单分页查询
   * @param purchaseFiling 采购食材关联信息
   * @return
   */
  IPage<PurchaseFiling> getPurchaseFilingPage(Page<PurchaseFiling> page, PurchaseFiling purchaseFiling);


}
