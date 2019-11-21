package com.pig4cloud.pig.school.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.school.api.dto.check.EarlyWarningDTO;
import com.pig4cloud.pig.school.api.entity.check.EarlyWarning;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 预警信息
 *
 * @author xiesongzhe
 * @date 2019-10-29 19:03:16
 */
public interface EarlyWarningMapper extends BaseMapper<EarlyWarning> {
  /**
    * 预警信息简单分页查询
    * @param earlyWarningDto 预警信息
    * @return
    */
  IPage<EarlyWarning> getEarlyWarningPage(Page page, @Param("earlyWarning") EarlyWarningDTO earlyWarningDto);

  /**
   * 检索-营业执照预警
   * @return
   */
  List<Map> getBulicenseWarn();
  /**
   * 检索-许可证预警
   * @return
   */
  List<Map> getPermitWarn();
  /**
   * 检索-健康证预警
   * @return
   */
  List<Map> getHealthWarn();
  /**
   * 检索-食材预警（采购时未上传索证索票）
   * @return
   */
  List<Map> getPurchaseWarn();

}
