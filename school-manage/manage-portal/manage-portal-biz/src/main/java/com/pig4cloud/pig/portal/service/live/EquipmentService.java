package com.pig4cloud.pig.portal.service.live;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.portal.api.entity.live.Equipment;

/**
 * 设备表
 *
 * @author -
 * @date 2019-08-27 17:11:58
 */
public interface EquipmentService extends IService<Equipment> {

  /**
   * 设备表简单分页查询
   * @param equipment 设备表
   * @return
   */
  IPage<Equipment> getEquipmentPage(Page<Equipment> page, Equipment equipment);


}
