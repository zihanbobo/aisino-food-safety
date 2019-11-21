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

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.json.JSON;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.pig4cloud.pig.admin.api.entity.SysDept;
import com.pig4cloud.pig.admin.service.SysDeptService;
import com.pig4cloud.pig.common.core.util.OSSUtil;
import com.pig4cloud.pig.common.core.util.R;
import com.pig4cloud.pig.common.log.annotation.SysLog;
import com.pig4cloud.pig.common.security.annotation.Inner;
import com.pig4cloud.pig.portal.api.entity.live.Equipment;
import lombok.AllArgsConstructor;
import org.apache.http.entity.ContentType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 图片上传 前端控制器
 * </p>
 *
 * @author lengleng
 * @since 2019/2/1
 */
@RestController
@AllArgsConstructor
@RequestMapping("/common")
public class CommonController {


	@PostMapping("/uppic")
	public Map<String,Object> getFile(@RequestParam("file") MultipartFile file) {
		System.out.println("收到请求");
		String upload = OSSUtil.upload(file, OSSUtil.FileDirType.ZHANGHAN_TEST);
		String fileName = file.getOriginalFilename();
		Map map = new HashMap();
		map.put("name",fileName);
		map.put("url",upload);
		return map;
  }

	@PostMapping("/createQrcode")
	public Map<String,Object> getQrcode(@RequestParam(value = "url", required = false) String url) throws IOException {
		System.out.println("收到请求，url为"+url);
		BufferedImage generate = QrCodeUtil.generate(url, 300, 300);
		InputStream inputStream = OSSUtil.bufferedImageToInputStream(generate);
		MockMultipartFile mockMultipartFile = new MockMultipartFile(url, url, "", inputStream);

		String upload = OSSUtil.upload(mockMultipartFile, OSSUtil.FileDirType.ZHANGHAN_TEST);
		String fileName = mockMultipartFile.getOriginalFilename();
		Map map = new HashMap();
		map.put("name",fileName);
		map.put("url",upload);
		return map;
	}





}
