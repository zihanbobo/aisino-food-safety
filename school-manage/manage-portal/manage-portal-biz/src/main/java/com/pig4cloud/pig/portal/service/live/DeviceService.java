package com.pig4cloud.pig.portal.service.live;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pig4cloud.pig.portal.api.entity.live.Device;


/**
 * 其他设备信息
 *
 * @author xiesongzhe
 * @date 2019-10-11 14:07:10
 */
public interface DeviceService extends IService<Device> {

  /**
   * 其他设备信息简单分页查询
   * @param device 其他设备信息
   * @return
   */
  IPage<Device> getDevicePage(Page<Device> page, Device device);


}
