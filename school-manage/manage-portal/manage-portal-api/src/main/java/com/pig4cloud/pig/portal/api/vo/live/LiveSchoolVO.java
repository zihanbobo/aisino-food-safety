package com.pig4cloud.pig.portal.api.vo.live;

import com.pig4cloud.pig.portal.api.entity.live.Equipment;
import com.pig4cloud.pig.portal.api.entity.live.Live;
import lombok.Data;

import java.io.Serializable;

/**
 * 设备表
 *
 * @author -
 * @date 2019-08-27 17:11:58
 */
@Data
public class LiveSchoolVO extends Live implements Serializable {

	private static final long serialVersionUID = 1L;

    // 自定义字段

//	private School school;	//学校

	private Equipment equipment;	//设备名称


}
