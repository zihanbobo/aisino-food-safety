package com.pig4cloud.pig.school.service.check;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.school.api.dto.check.FoodInspectionDTO;
import com.pig4cloud.pig.school.api.entity.check.FoodInspection;

/**
 * 食材快检管理
 *
 * @author xiesongzhe
 * @date 2019-10-30 20:51:34
 */
public interface FoodInspectionService extends IService<FoodInspection> {

  /**
   * 食材快检管理简单分页查询
   * @param foodInspectionDto 食材快检管理
   * @return
   */
  IPage<FoodInspection> getFoodInspectionPage(Page<FoodInspection> page, FoodInspectionDTO foodInspectionDto);


}
