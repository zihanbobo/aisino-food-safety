package com.pig4cloud.pig.portal.service.impl.live;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.portal.api.entity.live.Equipment;
import com.pig4cloud.pig.portal.mapper.EquipmentMapper;
import com.pig4cloud.pig.portal.service.live.EquipmentService;
import org.springframework.stereotype.Service;

/**
 * 设备表
 *
 * @author -
 * @date 2019-08-27 17:11:58
 */
@Service("equipmentService")
public class EquipmentServiceImpl extends ServiceImpl<EquipmentMapper, Equipment> implements EquipmentService {

  /**
   * 设备表简单分页查询
   * @param equipment 设备表
   * @return
   */
  @Override
  public IPage<Equipment> getEquipmentPage(Page<Equipment> page, Equipment equipment){
      return baseMapper.getEquipmentPage(page,equipment);
  }

}
