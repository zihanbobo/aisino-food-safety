package com.pig4cloud.pig.portal.service.impl.live;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pig4cloud.pig.portal.api.entity.live.Device;
import com.pig4cloud.pig.portal.mapper.DeviceMapper;

import com.pig4cloud.pig.portal.service.live.DeviceService;
import org.springframework.stereotype.Service;

/**
 * 其他设备信息
 *
 * @author xiesongzhe
 * @date 2019-10-11 14:07:10
 */
@Service("deviceService")
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device> implements DeviceService {

  /**
   * 其他设备信息简单分页查询
   * @param device 其他设备信息
   * @return
   */
  @Override
  public IPage<Device> getDevicePage(Page<Device> page, Device device){
      return baseMapper.getDevicePage(page,device);
  }

}
