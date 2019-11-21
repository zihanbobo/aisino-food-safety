package com.pig4cloud.pig.common.core.util;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * OSS上传工具类-张晗-2017/10/10
 */
public class OSSUtil {
	private volatile static OSSClient instance;

	private OSSUtil() {
	}

	/**
	 * 单例
	 *
	 * @return OSS工具类实例
	 */
	private static OSSClient getOSSClient() {
		if (instance == null) {
			synchronized (OSSUtil.class) {
				if (instance == null) {
					instance = new OSSClient(OSS_END_POINT, OSS_ACCESS_KEY_ID, OSS_ACCESS_KEY_SECRET);
				}
			}
		}
		return instance;
	}

	//OSS 的地址
	private final static String OSS_END_POINT = "http://oss-cn-beijing.aliyuncs.com";
	//OSS 的key值s
	private final static String OSS_ACCESS_KEY_ID  = "LTAIYQmA2I8gRj4W";
	//OSS 的secret值
	private final static String OSS_ACCESS_KEY_SECRET = "3XPC0U3n8X1cbDVNSg0Zu3ZoXxpJm1";
	//OSS 的bucket名字
	private final static String OSS_BUCKET_NAME = "shenning";
	//设置URL过期时间为10年
	private final static Date OSS_URL_EXPIRATION = DateUtils.addDays(new Date(), 365 * 10);

	//文件路径的枚举
	public enum FileDirType {
		ZHANGHAN_TEST("test");
		private String dir;

		FileDirType(String dir) {
			this.dir = dir;
		}

		@JsonValue
		public String getDir() {
			return dir;
		}
	}

	/**
	 * 上传文件---去除URL中的？后的时间戳
	 *
	 * @param file    文件
	 * @param fileDir 上传到OSS上文件的路径
	 * @return 文件的访问地址
	 */
	public static String upload(MultipartFile file, FileDirType fileDir) {
		OSSUtil.createBucket();
		String fileName = OSSUtil.uploadFile(file, fileDir);
		String fileOssURL = OSSUtil.getImgUrl(fileName, fileDir);
		int firstChar = fileOssURL.indexOf("?");
		if (firstChar > 0) {
			fileOssURL = fileOssURL.substring(0, firstChar);
		}
		return fileOssURL;
	}


	/**
	 * 当Bucket不存在时创建Bucket
	 *
	 * @throws OSSException    异常
	 * @throws ClientException Bucket命名规则：
	 *                         1.只能包含小写字母、数字和短横线，
	 *                         2.必须以小写字母和数字开头和结尾
	 *                         3.长度在3-63之间
	 */
	private static void createBucket() {
		try {
			if (!OSSUtil.getOSSClient().doesBucketExist(OSS_BUCKET_NAME)) {//判断是否存在该Bucket，不存在时再重新创建
				OSSUtil.getOSSClient().createBucket(OSS_BUCKET_NAME);
			}
		} catch (Exception e) {
		}
	}


	/**
	 * 上传到OSS服务器  如果同名文件会覆盖服务器上的
	 *
	 * @param file    文件
	 * @param fileDir 上传到OSS上文件的路径
	 * @return 文件的访问地址
	 */
	private static String uploadFile(MultipartFile file, FileDirType fileDir) {
		String fileName = String.format(
			"%s.%s",
			UUID.randomUUID().toString(),
			FilenameUtils.getExtension(file.getOriginalFilename()));
		try (InputStream inputStream = file.getInputStream()) {
			//创建上传Object的Metadata
			ObjectMetadata objectMetadata = new ObjectMetadata();
			objectMetadata.setContentLength(inputStream.available());
			objectMetadata.setCacheControl("no-cache");
			objectMetadata.setHeader("Pragma", "no-cache");
			objectMetadata.setContentType(getContentType(FilenameUtils.getExtension("." + file.getOriginalFilename())));
			objectMetadata.setContentDisposition("inline;filename=" + fileName);
			//上传文件
			PutObjectResult putResult = OSSUtil.getOSSClient().putObject(OSS_BUCKET_NAME, fileDir.getDir() + fileName, inputStream, objectMetadata);
			return fileName;
		} catch (Exception e) {
			return "aaa";
		}
	}


	/**
	 * 获得文件路径
	 *
	 * @param fileUrl 文件的URL
	 * @param fileDir 文件在OSS上的路径
	 * @return 文件的路径
	 */
	public static String getImgUrl(String fileUrl, FileDirType fileDir) {
		if (StringUtils.isEmpty(fileUrl)) {
			throw new RuntimeException("文件地址为空");
		}
		String[] split = fileUrl.split("/");

		//获取oss图片URL失败
		URL url = OSSUtil.getOSSClient().generatePresignedUrl(OSS_BUCKET_NAME, fileDir.getDir() + split[split.length - 1], OSS_URL_EXPIRATION);
		if (url == null) {
		}
		return url.toString();
	}

	/**
	 * 判断OSS服务文件上传时文件的contentType
	 *
	 * @param FilenameExtension 文件后缀
	 * @return 后缀
	 */
	private static String getContentType(String FilenameExtension) {
		if (FilenameExtension.equalsIgnoreCase("bmp")) {
			return "image/bmp";
		}
		if (FilenameExtension.equalsIgnoreCase("gif")) {
			return "image/gif";
		}
		if (FilenameExtension.equalsIgnoreCase("jpeg") ||
			FilenameExtension.equalsIgnoreCase("jpg") ||
			FilenameExtension.equalsIgnoreCase("png")) {
			return "image/jpeg";
		}
		if (FilenameExtension.equalsIgnoreCase("html")) {
			return "text/html";
		}
		if (FilenameExtension.equalsIgnoreCase("txt")) {
			return "text/plain";
		}
		if (FilenameExtension.equalsIgnoreCase("vsd")) {
			return "application/vnd.visio";
		}
		if (FilenameExtension.equalsIgnoreCase("pptx") ||
			FilenameExtension.equalsIgnoreCase("ppt")) {
			return "application/vnd.ms-powerpoint";
		}
		if (FilenameExtension.equalsIgnoreCase("docx") ||
			FilenameExtension.equalsIgnoreCase("doc")) {
			return "application/msword";
		}
		if (FilenameExtension.equalsIgnoreCase("xml")) {
			return "text/xml";
		}
		return "image/jpeg";
	}



	/**
	 * 将BufferedImage转换为InputStream
	 * @param image
	 * @return
	 */
	public static InputStream bufferedImageToInputStream(BufferedImage image){
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		try {
			ImageIO.write(image, "png", os);
			InputStream input = new ByteArrayInputStream(os.toByteArray());
			return input;
		} catch (IOException e) {
		}
		return null;
	}








	/**
	 * 获取路径地址
	 * @param fileName
	 * @return
	 */
	public static String getPathUrl(String fileName){
		return fileName.substring(fileName.indexOf(OSS_END_POINT)+OSS_END_POINT.length()+1);
	}

	/**
	 * 获取路径地址
	 * @param objectName
	 * @return
	 */
	public static String getObjectName(String fileUrl){
		String[] split = fileUrl.split("/");
		return split[split.length - 1];
	}



	/**
	 * 文件删除
	 * @param keys
	 */
	public static void delete(List<String> keys) {
		List<String> newKeys = keys.stream().map((item) -> {return OSSUtil.getPathUrl(item);}).collect(Collectors.toList());
		try{
			DeleteObjectsResult deleteObjectsResult = OSSUtil.getOSSClient().deleteObjects(new DeleteObjectsRequest(OSS_BUCKET_NAME).withKeys(newKeys));
			List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
			System.out.println(deletedObjects);
		}catch (OSSException oe) {
			System.out.println("Error Message: " + oe.getErrorCode());
			System.out.println("Error Code:    " + oe.getErrorCode());
			System.out.println("Request ID:    " + oe.getRequestId());
			System.out.println("Host ID:       " + oe.getHostId());
		} catch (ClientException ce) {
			System.out.println("Error Message: " + ce.getMessage());
		}

	}
}
