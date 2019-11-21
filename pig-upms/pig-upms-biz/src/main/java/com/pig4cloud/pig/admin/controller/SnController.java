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
package com.pig4cloud.pig.admin.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pig4cloud.pig.admin.api.entity.Sn;
import com.pig4cloud.pig.admin.service.SnService;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 代码生成
 *
 * @author 沈凝
 * @date 2019-06-05 00:58:01
 */
@RestController
@AllArgsConstructor
@RequestMapping("/sn")
public class SnController {

  private final  SnService snService;

  /**
   * 简单分页查询
   * @param page 分页对象
   * @param sn 代码生成
   * @return
   */
  @GetMapping("/page")
  public R<IPage<Sn>> getSnPage(Page<Sn> page, Sn sn) {
    return  new R<>(snService.getSnPage(page,sn));
  }


  /**
   * 通过id查询单条记录
   * @param id
   * @return R
   */
  @GetMapping("/{id}")
  public R<Sn> getById(@PathVariable("id") String id){
    return new R<>(snService.getById(id));
  }

  /**
   * 新增记录
   * @param sn
   * @return R
   */
  @SysLog("新增代码生成")
  @PostMapping
  @PreAuthorize("@pms.hasPermission('admin_sn_add')")
  public R save(@RequestBody Sn sn){
    return new R<>(snService.save(sn));
  }

  /**
   * 修改记录
   * @param sn
   * @return R
   */
  @SysLog("修改代码生成")
  @PutMapping
  @PreAuthorize("@pms.hasPermission('admin_sn_edit')")
  public R update(@RequestBody Sn sn){
    return new R<>(snService.updateById(sn));
  }

  /**
   * 通过id删除一条记录
   * @param id
   * @return R
   */
  @SysLog("删除代码生成")
  @DeleteMapping("/{id}")
  @PreAuthorize("@pms.hasPermission('admin_sn_del')")
  public R removeById(@PathVariable String id){
    return new R<>(snService.removeById(id));
  }





	@PostMapping("/uppic")
	public Map<String,Object> getFile(@RequestParam("file") MultipartFile file) {
		System.out.println("收到请求");
		Map<String,Object> map = new HashMap<String,Object>();
		if (file.isEmpty()) {
			//return "上传失败，请选择文件";
		}

		String fileName = file.getOriginalFilename();//暂时不用
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
		String filePath = "E:\\pic\\";
		String uuid= String.valueOf(new Date().getTime());
		File dest = new File(filePath + uuid+'.'+suffix );
		try {
			file.transferTo(dest);
			System.out.println("上传成功");
			map.put("name",uuid +'.'+suffix);
			map.put("url",filePath + uuid +'.'+ suffix);
			return map;
		} catch (IOException e) {
			System.out.println("上传失败");
			System.out.println(e.getMessage());
		}
		//return "上传失败！";
		return map;
	}

	@GetMapping(value = "/pic", produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] getPicture(@RequestParam("name") String name) {
		System.out.println("请求图片");
		String filePath = "C:\\Users\\96589\\Pictures\\";
		File file = new File(filePath + name);
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(file);
			byte[] bytes = new byte[inputStream.available()];
			inputStream.read(bytes, 0, inputStream.available());
			return bytes;
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return null;

	}

}
