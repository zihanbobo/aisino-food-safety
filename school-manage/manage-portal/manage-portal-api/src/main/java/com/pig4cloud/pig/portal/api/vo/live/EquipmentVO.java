package com.pig4cloud.pig.portal.api.vo.live;

import com.pig4cloud.pig.portal.api.entity.live.Equipment;
import lombok.Data;

import java.io.Serializable;

/**
 * 设备表
 *
 * @author -
 * @date 2019-08-27 17:11:58
 */
@Data
public class EquipmentVO extends Equipment implements Serializable {

    // 自定义字段
    private String username;	//创建人姓名

    private String schoolname;	//学校名称

}
