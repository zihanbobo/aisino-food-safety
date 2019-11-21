package com.pig4cloud.pig.school.api.entity.project;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 管理端-项目管理
 *
 * @author 沈凝
 * @date 2019-10-17 15:30:11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("se_project_manage")
public class ProjectManage extends Model<ProjectManage> {
private static final long serialVersionUID = 1L;

    /**
   * 
   */
    @TableId
    private Integer id;
    /**
   * 项目名称
   */
    private String projectName;
    /**
   * 区划号
   */
    private String areaCode;
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
