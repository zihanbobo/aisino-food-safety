package com.pig4cloud.pig.portal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.pig4cloud.pig.portal.api.entity.live.Device;
import org.apache.ibatis.annotations.Param;

/**
 * 其他设备信息
 *
 * @author xiesongzhe
 * @date 2019-10-11 14:07:10
 */
public interface DeviceMapper extends BaseMapper<Device> {
  /**
    * 其他设备信息简单分页查询
    * @param device 其他设备信息
    * @return
    */
  IPage<Device> getDevicePage(Page page, @Param("device") Device device);


}
