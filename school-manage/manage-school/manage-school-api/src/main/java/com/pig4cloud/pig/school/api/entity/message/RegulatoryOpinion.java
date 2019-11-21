package com.pig4cloud.pig.school.api.entity.message;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 监管意见信息
 *
 * @author xiesongzhe
 * @date 2019-10-16 17:06:21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("msg_regulatory_opinion")
public class RegulatoryOpinion extends Model<RegulatoryOpinion> {
private static final long serialVersionUID = 1L;

    /**
   * 主键id
   */
    @TableId
    private Integer id;
    /**
   * 关联学校id
   */
    private Integer schoolId;
    /**
   * 监管意见内容
   */
    private String content;
    /**
   * 是否查看(1未查看2已查看)
   */
    private String status;
    /**
   * 报警类型(1证照报警)
   */
    private String type;
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
    /**
     * 关联用户表 查询发布人信息
     */
    private Integer userId;
    /**
     * 查看人view_user
     */
    private Integer viewUser;
}
