package com.pig4cloud.pig.school.api.entity.check;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("ck_early_seff")
public class Welocomedomo extends Model<Welocomedomo> {
  //员工姓名
  private String pubName;
  //职位
  private String position;
  //晨检情况
  private String morningCheck;
  //健康证截止日期
  private String healthEndtime;
  //检查人
  private String rummager;
  //晨检是否合格
  private String isQualified;
  //晨检总数
  private String zong;
  //晨检合格数
  private String hegeshu;
  //处理结果
  private String reason;
}
