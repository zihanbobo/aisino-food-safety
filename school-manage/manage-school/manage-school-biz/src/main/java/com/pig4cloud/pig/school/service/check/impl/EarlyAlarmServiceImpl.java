package com.pig4cloud.pig.school.service.check.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.school.api.dto.check.EarlyAlarmDTO;
import com.pig4cloud.pig.school.api.entity.check.EarlyAlarm;
import com.pig4cloud.pig.school.mapper.EarlyAlarmMapper;

import com.pig4cloud.pig.school.service.check.EarlyAlarmService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 报警信息
 *
 * @author xiesongzhe
 * @date 2019-10-29 19:37:01
 */
@Service("earlyAlarmService")
public class EarlyAlarmServiceImpl extends ServiceImpl<EarlyAlarmMapper, EarlyAlarm> implements EarlyAlarmService {

  /**
   * 报警信息简单分页查询
   * @param earlyAlarmDto 报警信息
   * @return
   */
  @Override
  public IPage<EarlyAlarm> getEarlyAlarmPage(Page<EarlyAlarm> page, EarlyAlarmDTO earlyAlarmDto){
      return baseMapper.getEarlyAlarmPage(page,earlyAlarmDto);
  }
  /**
   * 检索-报警信息
   * @return
   */
  public List<Map> getAlarm(){
    return baseMapper.getAlarm();
  }
}
