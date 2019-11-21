package com.pig4cloud.pig.school.api.entity.message;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 通知中心信息
 *
 * @author xiesongzhe
 * @date 2019-10-15 09:58:12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("msg_announcement")
public class Announcement extends Model<Announcement> {
private static final long serialVersionUID = 1L;

    /**
   * 主键id
   */
    @TableId
    private Integer id;
    /**
   * 学校名称(关联id)
   */
    private Integer schoolId;
    /**
   * 上传文件路径 
   */
    private String file;
    /**
   * 标题
   */
    private String title;
    /**
   * 内容
   */
    private String content;
    /**
   * 发送时间
   */
    private LocalDateTime startTime;
    /**
   * 查看时间
   */
    private LocalDateTime viewTime;
    /**
   * 状态
   */
    private String status;
    /**
   * 消息类型（1 通知中心）
   */
    private String type;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 修改时间
     */
    private String updateTime;
    /**
     * 逻辑删除
     */
    private String delFlag;
    /**
     * 监管人(关联sys_user)
     */
    private Integer userId;
    /**
     * 监管局是否查看 1 已查看 2 未查看
     */
    private String supStatus;

}
