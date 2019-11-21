package com.pig4cloud.pig.school.service.recipe.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.school.api.entity.recipe.PurchaseFiling;
import com.pig4cloud.pig.school.mapper.PurchaseFilingMapper;

import com.pig4cloud.pig.school.service.recipe.PurchaseFilingService;
import org.springframework.stereotype.Service;

/**
 * 采购食材关联信息
 *
 * @author xiesongzhe
 * @date 2019-09-11 15:23:21
 */
@Service("purchaseFilingService")
public class PurchaseFilingServiceImpl extends ServiceImpl<PurchaseFilingMapper, PurchaseFiling> implements PurchaseFilingService {

  /**
   * 采购食材关联信息简单分页查询
   * @param purchaseFiling 采购食材关联信息
   * @return
   */
  @Override
  public IPage<PurchaseFiling> getPurchaseFilingPage(Page<PurchaseFiling> page, PurchaseFiling purchaseFiling){
      return baseMapper.getPurchaseFilingPage(page,purchaseFiling);
  }

}
