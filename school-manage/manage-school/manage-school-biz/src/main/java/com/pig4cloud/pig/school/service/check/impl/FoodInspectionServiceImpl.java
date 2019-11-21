package com.pig4cloud.pig.school.service.check.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.school.api.dto.check.FoodInspectionDTO;
import com.pig4cloud.pig.school.api.entity.check.FoodInspection;
import com.pig4cloud.pig.school.mapper.FoodInspectionMapper;
import com.pig4cloud.pig.school.service.check.FoodInspectionService;
import org.springframework.stereotype.Service;

/**
 * 食材快检管理
 *
 * @author xiesongzhe
 * @date 2019-10-30 20:51:34
 */
@Service("foodInspectionService")
public class FoodInspectionServiceImpl extends ServiceImpl<FoodInspectionMapper, FoodInspection> implements FoodInspectionService {

  /**
   * 食材快检管理简单分页查询
   * @param foodInspectionDto 食材快检管理
   * @return
   */
  @Override
  public IPage<FoodInspection> getFoodInspectionPage(Page<FoodInspection> page, FoodInspectionDTO foodInspectionDto){
      return baseMapper.getFoodInspectionPage(page,foodInspectionDto);
  }

}
