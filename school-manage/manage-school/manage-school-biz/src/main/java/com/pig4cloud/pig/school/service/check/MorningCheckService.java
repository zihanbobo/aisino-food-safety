package com.pig4cloud.pig.school.service.check;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.school.api.dto.check.MorningCheckDTO;
import com.pig4cloud.pig.school.api.entity.check.MorningCheck;

/**
 * 人员晨检管理
 *
 * @author xiesongzhe
 * @date 2019-10-30 20:14:51
 */
public interface MorningCheckService extends IService<MorningCheck> {

  /**
   * 人员晨检管理简单分页查询
   * @param morningCheckDto 人员晨检管理
   * @return
   */
  IPage<MorningCheck> getMorningCheckPage(Page<MorningCheck> page, MorningCheckDTO morningCheckDto);


}
