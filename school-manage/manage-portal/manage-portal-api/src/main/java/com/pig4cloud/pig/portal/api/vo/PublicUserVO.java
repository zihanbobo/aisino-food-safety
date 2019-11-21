package com.pig4cloud.pig.portal.api.vo;

import com.pig4cloud.pig.portal.api.entity.PublicUser;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 设备表
 *
 * @author -
 * @date 2019-08-27 17:11:58
 */
@Data
public class PublicUserVO extends PublicUser implements Serializable {

    private String schoolNameAll;	//学校名称列表（多个学校名称，','分隔）

	private List<Map> schoolList;	// 绑定学校集合
}
