package com.pig4cloud.pig.common.core.util;


import com.pig4cloud.pig.common.core.ocr.HttpUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * OCR营业执照识别
 */
public class OCRDemo {

  public static void main(String[] args) {
    String host = "https://dm-58.data.aliyun.com";
    String path = "/rest/160601/ocr/ocr_business_license.json";
    String method = "POST";
    String appcode = "fd99205e525b441cba86874996651657";//AppCode
    Map<String, String> headers = new HashMap<String, String>();
    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
    headers.put("Authorization", "APPCODE " + appcode);
    //根据API的要求，定义相对应的Content-Type
    headers.put("Content-Type", "application/json; charset=UTF-8");
    Map<String, String> querys = new HashMap<String, String>();
    String img = "http://shenning.oss-cn-beijing.aliyuncs.com/test40e100ba-e888-4a8c-86de-f0385cb05cee.jpg";
    String bodys = "{\"image\":\""+img+"\"}";
    System.out.println("bodys:"+bodys);
    try {
      HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
      System.out.println("返回参数："+response.toString());
      //获取response的body
      System.out.println(EntityUtils.toString(response.getEntity()));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  public static String OCRIdentification(String imgURL) {
    String s ="";
    String host = "https://dm-58.data.aliyun.com";
    String path = "/rest/160601/ocr/ocr_business_license.json";
    String method = "POST";
    String appcode = "fd99205e525b441cba86874996651657";//AppCode
    Map<String, String> headers = new HashMap<String, String>();
    //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
    headers.put("Authorization", "APPCODE " + appcode);
    //根据API的要求，定义相对应的Content-Type
    headers.put("Content-Type", "application/json; charset=UTF-8");
    Map<String, String> querys = new HashMap<String, String>();
    String bodys = "{\"image\":\""+imgURL+"\"}";
    System.out.println("bodys:"+bodys);
    System.out.println(headers);
    try {
      HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
      System.out.println("返回参数："+response.toString());
      //获取response的body
      s = EntityUtils.toString(response.getEntity());
      System.out.println(s);
      return  s;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return  s;
  }

 /* public static String stringReplace(String jsonStr) {
    //去掉" "号
    String str = jsonStr.replace("\"","").replace("\"","");
    return str ;

  }*/

}
