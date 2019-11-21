package com.pig4cloud.pig.school.service.check.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.school.api.dto.check.MorningCheckDTO;
import com.pig4cloud.pig.school.api.entity.check.MorningCheck;
import com.pig4cloud.pig.school.mapper.MorningCheckMapper;
import com.pig4cloud.pig.school.service.check.MorningCheckService;
import org.springframework.stereotype.Service;

/**
 * 人员晨检管理
 *
 * @author xiesongzhe
 * @date 2019-10-30 20:14:51
 */
@Service("morningCheckService")
public class MorningCheckServiceImpl extends ServiceImpl<MorningCheckMapper, MorningCheck> implements MorningCheckService {

  /**
   * 人员晨检管理简单分页查询
   * @param morningCheckDto 人员晨检管理
   * @return
   */
  @Override
  public IPage<MorningCheck> getMorningCheckPage(Page<MorningCheck> page, MorningCheckDTO morningCheckDto){
      return baseMapper.getMorningCheckPage(page,morningCheckDto);
  }

}
