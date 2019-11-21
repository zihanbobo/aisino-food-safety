package com.pig4cloud.pig.school.service.check.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.school.api.dto.check.EarlyWarningDTO;
import com.pig4cloud.pig.school.api.entity.check.EarlyWarning;
import com.pig4cloud.pig.school.mapper.EarlyWarningMapper;
import com.pig4cloud.pig.school.service.check.EarlyWarningService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 预警信息
 *
 * @author xiesongzhe
 * @date 2019-10-29 19:03:16
 */
@Service("earlyWarningService")
public class EarlyWarningServiceImpl extends ServiceImpl<EarlyWarningMapper, EarlyWarning> implements EarlyWarningService {

  /**
   * 预警信息简单分页查询
   * @param earlyWarningDto 预警信息
   * @return
   */
  @Override
  public IPage<EarlyWarning> getEarlyWarningPage(Page<EarlyWarning> page, EarlyWarningDTO earlyWarningDto){
      return baseMapper.getEarlyWarningPage(page,earlyWarningDto);
  }

  /**
   * 检索-营业执照预警
   * @return
   */
  public List<Map> getBulicenseWarn(){
    return baseMapper.getBulicenseWarn();
  }
  /**
   * 检索-许可证预警
   * @return
   */
  public List<Map> getPermitWarn(){
    return baseMapper.getPermitWarn();
  }
  /**
   * 检索-健康证预警
   * @return
   */
  public List<Map> getHealthWarn(){
    return baseMapper.getHealthWarn();
  }
  /**
   * 检索-食材预警（采购时未上传索证索票）
   * @return
   */
  public List<Map> getPurchaseWarn(){
    return baseMapper.getPurchaseWarn();
  }




}
