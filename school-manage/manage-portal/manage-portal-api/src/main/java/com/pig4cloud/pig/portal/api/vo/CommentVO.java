package com.pig4cloud.pig.portal.api.vo;

import com.pig4cloud.pig.portal.api.entity.Comment;
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
public class CommentVO extends Comment implements Serializable {

    // 自定义字段
    private String schoolName;	//学校名称

    private String nickName;	//用户名称

	  private List<Map> childrenPor;

	  private List<Map> children;

	  private String pubPic;	//用户头像地址

	  private String isPraise;	//该用户是否点赞 1点0无

    private String pubPhone; //用户手机号

    private Integer praiseCounts;

}
