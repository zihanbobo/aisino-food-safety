package com.pig4cloud.pig.common.core.util;

import org.apache.http.entity.ContentType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class aaa {

	public static void main(String[] args) {
		/*// Endpoint以杭州为例，其它Region请按实际情况填写。
				String endpoint = "http://oss-cn-beijing.aliyuncs.com";
		// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
				String accessKeyId = "LTAIYQmA2I8gRj4W";
				String accessKeySecret = "3XPC0U3n8X1cbDVNSg0Zu3ZoXxpJm1";

		// 创建OSSClient实例。
				OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);


		// 上传文件。<yourLocalFile>由本地文件路径加文件名包括后缀组成，例如/users/local/myfile.txt。
		ossClient.putObject("shenning", "1560107760080.png", new File("E:/pic/1560107760080.png"));





		// 关闭OSSClient。
				ossClient.shutdown();*/

/*
		String s = OSSClientUtil.uploadObject2OSS(OSSClientUtil.getOSSClient(), new File("E:/pic/1562052637591.jpg"), OSSClientUtil.getBucketName());
		System.out.println(s);
*/




		File pdfFile = new File("E:/pic/1562052637591.jpg");
		FileInputStream fileInputStream = null;
		try {
			fileInputStream = new FileInputStream(pdfFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		MultipartFile multipartFile = null;
		try {
			multipartFile = new MockMultipartFile(pdfFile.getName(), pdfFile.getName(),
				ContentType.APPLICATION_OCTET_STREAM.toString(), fileInputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String upload = OSSUtil.upload(multipartFile, OSSUtil.FileDirType.ZHANGHAN_TEST);
		System.out.println(upload);

	}
}
