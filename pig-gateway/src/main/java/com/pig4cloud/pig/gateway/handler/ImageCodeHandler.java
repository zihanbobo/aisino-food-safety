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

package com.pig4cloud.pig.gateway.handler;

import com.google.code.kaptcha.Producer;
import com.pig4cloud.pig.common.core.constant.CommonConstants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author lengleng
 * @date 2019/2/1
 * 验证码生成逻辑处理类
 */
@Slf4j
@Component
@AllArgsConstructor
public class ImageCodeHandler implements HandlerFunction<ServerResponse> {
	private final Producer producer;
	private final RedisTemplate redisTemplate;

	@Override
	public Mono<ServerResponse> handle(ServerRequest serverRequest) {
		//生成验证码
		String text = producer.createText();// 为图片创建文本
		BufferedImage image = producer.createImage(text);// 创建带有文本的图片

		//保存验证码信息
		String randomStr = serverRequest.queryParam("randomStr").get();// 取随机数
		redisTemplate.opsForValue().set(CommonConstants.DEFAULT_CODE_KEY + randomStr, text, 60, TimeUnit.SECONDS);// 操作字符串（键、值、存活时间1分钟、过期重置null）

		// 转换流信息写出
		FastByteArrayOutputStream os = new FastByteArrayOutputStream();
		try {
			ImageIO.write(image, "jpeg", os);// 将验证码写出
		} catch (IOException e) {
			log.error("ImageIO write err", e);
			return Mono.error(e);
		}

		return ServerResponse
			.status(HttpStatus.OK)
			.contentType(MediaType.IMAGE_JPEG)
			.body(BodyInserters.fromResource(new ByteArrayResource(os.toByteArray())));
	}
}
/**
 * 调用 com.google.code.kaptcha.servlet.KaptchaServlet，生成一个图片,同一时候将生成的验证码字符串放到 HttpSession中。
 * 验证码的字体
 * 验证码字体的大小
 * 验证码字体的字体颜色
 * 验证码内容的范围(数字，字母，中文汉字！)
 * 验证码图片的大小。边框，边框粗细，边框颜色
 * 验证码的干扰线(能够自己继承com.google.code.kaptcha.NoiseProducer写一个自己定义的干扰线)
 * 验证码的样式(鱼眼样式、3D、普通模糊……当然也能够继承com.google.code.kaptcha.GimpyEngine自己定义样式)
 *
 * @param serverRequest
 * @return
 *
 *
 *  FastByteArrayOutputStream与ByteArrayOutputStream区别
 *	FastByteArrayOutputStream:内部实现由一个LinkedList<byte[]>，每一次扩容中分配一个数组的空间，并当该数据放入到List中。需要分配的数组长度为调用FastByteArrayOutputStream的write方法决定
 *	ByteArrayOutputStream:内部实现为一个数组每一次扩容需要重新分配空间并将数据复制到新数组中
 *
 *
 */

