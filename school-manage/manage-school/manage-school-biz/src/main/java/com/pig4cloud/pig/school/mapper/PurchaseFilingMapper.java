package com.pig4cloud.pig.school.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.school.api.entity.recipe.PurchaseFiling;
import org.apache.ibatis.annotations.Param;

/**
 * 采购食材关联信息
 *
 * @author xiesongzhe
 * @date 2019-09-11 15:23:21
 */
public interface PurchaseFilingMapper extends BaseMapper<PurchaseFiling> {
  /**
    * 采购食材关联信息简单分页查询
    * @param purchaseFiling 采购食材关联信息
    * @return
    */
  IPage<PurchaseFiling> getPurchaseFilingPage(Page page, @Param("purchaseFiling") PurchaseFiling purchaseFiling);


}
