package com.pig4cloud.pig.school.api.entity.recipe;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 采购食材关联信息
 *
 * @author xiesongzhe
 * @date 2019-09-11 15:23:21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("pur_purchase_filing")
public class PurchaseFiling extends Model<PurchaseFiling> {
private static final long serialVersionUID = 1L;

    /**
   * 关联采购表id
   */
    private Integer purId;
    /**
   * 关联食材id
   */
    private Integer filingId;
    /**
   * 快检报告
   */
    private String checkFile;
    /**
     * 快检报告是否合格（1合格2不合格）
     */
    private String isQualified;
    /**
     * 采购计划重量
     */
    private String weight;
    /**
     * 食材单价
     */
    private String unitPrice;

    /**
     * 实际采购重量
     */
    private String realityWeight;
    /**
     * 食材渠道
     */
    private String foodChannel;  //1采购计划2采购清单

    /**
     *是否在清单里被删除（0正常1为删除）
     */
    private String isDel;  //1采购计划2采购清单

    /**
     * 保质期
     */
    private String shelfLife;
}
