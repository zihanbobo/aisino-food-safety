package com.pig4cloud.pig.school.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.school.api.dto.check.FoodInspectionDTO;
import com.pig4cloud.pig.school.api.entity.check.FoodInspection;
import org.apache.ibatis.annotations.Param;

/**
 * 食材快检管理
 *
 * @author xiesongzhe
 * @date 2019-10-30 20:51:34
 */
public interface FoodInspectionMapper extends BaseMapper<FoodInspection> {
  /**
    * 食材快检管理简单分页查询
    * @param foodInspectionDto 食材快检管理
    * @return
    */
  IPage<FoodInspection> getFoodInspectionPage(Page page, @Param("foodInspection") FoodInspectionDTO foodInspectionDto);


}
