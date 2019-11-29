/*
 *  Copyright (c) 2019-2020, 冷冷 (wangiegie@gmail.com).
 *  <p>
 *  Licensed under the GNU Lesser General Public License 3.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *  <p>
 * https://www.gnu.org/licenses/lgpl.html
 *  <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.pig4cloud.pig.admin.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.entity.SysDict;
import com.pig4cloud.pig.admin.api.entity.SysRegion;
import com.pig4cloud.pig.admin.service.SysDictService;
import com.pig4cloud.pig.admin.service.SysRegionService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.annotation.Inner;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 字典表 前端控制器
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
@RestController
@AllArgsConstructor
@RequestMapping("/region")
public class RegionController {
	private final SysRegionService sysRegionService;


  /**
   * 查询省
   *
   * @return 省列表
   */
  @GetMapping("/getProvince")
  public R listRegion() {
    List<String> list = new ArrayList<String>();
    list.add("000000");
    list.add("910000");
    return new R<>(sysRegionService.list(Wrappers
      .<SysRegion>query().lambda()
      .eq(SysRegion::getPCode, "100000")
      .notIn(SysRegion::getCode,list)
    ));
  }

  /**
   * 查询市
   *
   * @return 市列表
   */
  @GetMapping("/getCity/{provice}")
  public R listCity(@PathVariable String provice) {
    return new R<>(sysRegionService.list(Wrappers
      .<SysRegion>query().lambda()
      .eq(SysRegion::getPCode, provice)
    ));
  }

  /**
   * 查询区/县
   *
   * @return 市列表
   */
  @GetMapping("/getDistrict/{district}")
  public R listDistrict(@PathVariable String district) {
    return new R<>(sysRegionService.list(Wrappers
      .<SysRegion>query().lambda()
      .eq(SysRegion::getPCode, district)
    ));
  }

  /**
   * 查询省(h5数据采集)
   *
   * @return 省列表
   */
  @Inner
  @GetMapping("/getProvinceForCol")
  public R listRegionForCol() {
    List<String> list = new ArrayList<String>();
    list.add("000000");
    list.add("910000");
    return new R<>(sysRegionService.list(Wrappers
      .<SysRegion>query().lambda()
      .eq(SysRegion::getPCode, "100000")
      .notIn(SysRegion::getCode,list)
    ));
  }

  /**
   * 查询市(h5数据采集)
   *
   * @return 市列表
   */
  @Inner
  @GetMapping("/getCityForCol")
  public R listCityForCol(@RequestParam(value = "provice")String provice) {
    return new R<>(sysRegionService.list(Wrappers
      .<SysRegion>query().lambda()
      .eq(SysRegion::getPCode, provice)
    ));
  }

  /**
   * 查询区/县(h5数据采集)
   *
   * @return 区列表
   */
  @Inner
  @GetMapping("/getDistrictForCol")
  public R listDistrictForCol(@RequestParam(value = "district")String district) {
    return new R<>(sysRegionService.list(Wrappers
      .<SysRegion>query().lambda()
      .eq(SysRegion::getPCode, district)
    ));
  }

}
