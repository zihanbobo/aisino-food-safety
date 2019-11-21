package com.pig4cloud.pig.school.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.school.api.dto.check.EarlyAlarmDTO;
import com.pig4cloud.pig.school.api.entity.check.EarlyAlarm;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 报警信息
 *
 * @author xiesongzhe
 * @date 2019-10-29 19:37:01
 */
public interface EarlyAlarmMapper extends BaseMapper<EarlyAlarm> {
  /**
    * 报警信息简单分页查询
    * @param earlyAlarmDto 报警信息
    * @return
    */
  IPage<EarlyAlarm> getEarlyAlarmPage(Page page, @Param("earlyAlarm") EarlyAlarmDTO earlyAlarmDto);

  /**
   * 检索-报警信息
   * @return
   */
  List<Map> getAlarm();
}
