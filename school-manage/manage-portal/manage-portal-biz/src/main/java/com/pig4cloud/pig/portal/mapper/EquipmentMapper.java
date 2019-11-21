package com.pig4cloud.pig.portal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.portal.api.entity.live.Equipment;
import org.apache.ibatis.annotations.Param;

/**
 * 设备表
 *
 * @author -
 * @date 2019-08-27 17:11:58
 */
public interface EquipmentMapper extends BaseMapper<Equipment> {
  /**
    * 设备表简单分页查询
    * @param equipment 设备表
    * @return
    */
  IPage<Equipment> getEquipmentPage(Page page, @Param("equipment") Equipment equipment);


}
