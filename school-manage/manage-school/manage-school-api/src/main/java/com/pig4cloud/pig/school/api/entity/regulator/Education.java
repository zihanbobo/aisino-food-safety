package com.pig4cloud.pig.school.api.entity.regulator;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 教育局信息
 *
 * @author xiesongzhe
 * @date 2019-10-20 18:31:16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("se_education")
public class Education extends Model<Education> {
private static final long serialVersionUID = 1L;

    /**
   * 主键id
   */
    @TableId
    private Integer id;
    /**
   * 父级id
   */
    private Integer parentId;
    /**
   * 超级管理员id
   */
    private String eduUserid;
    /**
   * 教育局名称
   */
    private String eduName;
    /**
   * 教育局地址
   */
    private String eduAddress;
    /**
   * 教育局区划号
   */
    private String eduArea;
    /**
   * 教育局电话
   */
    private String eduTel;
    /**
   * 备注信息
   */
    private String remarks;
    /**
   * 创建时间
   */
    private LocalDateTime createTime;
    /**
   * 修改时间
   */
    private LocalDateTime updateTime;
    /**
   * 是否删除  -1：已删除  0：正常
   */
    private String delFlag;
  
}
