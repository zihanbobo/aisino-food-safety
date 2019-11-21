package com.pig4cloud.pig.school.service.account;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.school.api.entity.account.KitwasteTreatment;

/**
 * 厨余处理记录
 *
 * @author 沈凝
 * @date 2019-07-06 16:54:52
 */
public interface KitwasteTreatmentService extends IService<KitwasteTreatment> {

  /**
   * 厨余处理记录简单分页查询
   * @param kitwasteTreatment 厨余处理记录
   * @return
   */
  IPage<KitwasteTreatment> getKitwasteTreatmentPage(Page<KitwasteTreatment> page, KitwasteTreatment kitwasteTreatment);


}
