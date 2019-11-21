package com.pig4cloud.pig.portal.api.vo.live;

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
public class LiveVO extends Live implements Serializable {

	private static final long serialVersionUID = 1L;

    // 自定义字段

	private String schoolname;	//学校名称

	private String eqarea;	//设备名称

  private String equrl; //直播地址

}
