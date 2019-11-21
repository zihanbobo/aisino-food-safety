package com.pig4cloud.pig.portal.api.entity.live;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 其他设备信息
 *
 * @author xiesongzhe
 * @date 2019-10-11 14:07:10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("vd_device")
public class Device extends Model<Device> {
private static final long serialVersionUID = 1L;

    /**
   * 主键id
   */
    @TableId
    private Integer id;
    /**
   * 设备名称校id
   */
    private Integer schoolId;
    /**
   * 设备名称
   */
    private String deviceName;
    /**
   * 设备品牌
   */
    private String brand;
    /**
   * 出厂日期
   */
    private String startDate;
    /**
   * 使用年限
   */
    private String useYear;
    /**
   * 餐厅名称
   */
    private String restaurantName;
    /**
   * 备注
   */
    private String remarks;
    /**
     * 是否删除  -1：已删除  0：正常
     */
    private String delFlag;
    /**
   * 设备类型（字典表）
   */
    private String deviceType;
    /**
   * 设备运转是否正常（1正常2异常）
   */
    private String runWell;
    /**
   * 检查是否合格（1合格2不合格）
   */
    private String isPassed;



  
}
