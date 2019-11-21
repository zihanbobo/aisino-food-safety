/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */
package com.pig4cloud.pig.brmanage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.brmanage.api.entity.LawRegulatinon;
import org.apache.ibatis.annotations.Param;

/**
 * 法律法规表
 *
 * @author 沈凝
 * @date 2019-07-29 10:32:30
 */
public interface LawRegulatinonMapper extends BaseMapper<LawRegulatinon> {
  /**
    * 法律法规表简单分页查询
    * @param lawRegulatinon 法律法规表
    * @return
    */
  IPage<LawRegulatinon> getLawRegulatinonPage(Page page, @Param("lawRegulatinon") LawRegulatinon lawRegulatinon);


}
