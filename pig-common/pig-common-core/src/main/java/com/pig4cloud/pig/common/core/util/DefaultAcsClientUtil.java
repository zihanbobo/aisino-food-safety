package com.pig4cloud.pig.common.core.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.iot.model.v20180120.QueryDeviceDetailRequest;
import com.aliyuncs.iot.model.v20180120.QueryDeviceDetailResponse;
import com.aliyuncs.iot.model.v20180120.QueryDeviceRequest;
import com.aliyuncs.iot.model.v20180120.QueryDeviceResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * description: IoT
 * date: 2019/11/17 15:47
 * author: Administrator
 * version: 1.0
 */
public class DefaultAcsClientUtil {

//  private static String productKey = "";

  /**
   * 初始化sdk
   * @return IAcsClient
   * @throws ClientException
   */
  public static IAcsClient getSdkClient() throws ClientException {
      IAcsClient client = null;
      try{
//        // 粮食
//        String accessKeyID = "LTAIHXQBez1MDoAB";
//        String accessKeySecret = "jRqMUF9Edp4Tm1mk6uj4oemfSej3Ux";
        // 食安
        String accessKeyID = "LTAI4FcKTQfBBdJzNuRHEnG3";
        String accessKeySecret = "xeYqm4DXnzkV3i8ol3wr0y8TGtwgu6";
        String regionId = "cn-shanghai";
        DefaultProfile.addEndpoint(regionId, regionId, "Iot", "iot.cn-shanghai.aliyuncs.com");
        IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyID, accessKeySecret);
        client = new DefaultAcsClient(profile);
      }catch(Exception e){
        System.out.println("init client failed | excrption: " + e.getMessage());
      }
      return client;
    }


  /**
   * QueryDeviceDetail(调用该接口查询指定设备的详细信息）
   * @param productKey
   * @param deviceName
   * @return
   * {
   *     "RequestId": "57b144cf-09fc-4916-a272-a62902d5b207",
   *     "Success": true,
   *     "Data": {
   *         "DeviceName": "device1",
   *         "GmtActive": "2018-08-06 10:48:41",
   *         "UtcActive": "2018-08-06T02:48:41.000Z",
   *         "ProductKey": "a1rYuVF****",
   *         "DeviceSecret": "CPwUjMUgzdvaZv56TMy6773V3v3****",
   *         "Nickname": "detectors_in_beijing",
   *         "GmtCreate": "2018-08-06 10:47:50",
   *         "UtcCreate": "2018-08-06T02:47:50.000Z",
   *         "IotId": "SR8FiTu1R9tlUR2V1bmi00105****",
   *         "Status": "ONLINE",
   *         "Region": "cn-shanghai",
   *         "NodeType": 0,
   *         "GmtOnline": "2018-08-06 13:43:12",
   *         "UtcOnline": "2018-08-06T05:43:12.000Z",
   *         "ProductName": "test",
   *         "IpAddress": "10.0.0.1",
   *         "FirmwareVersion": "V1.0"
   *     }
   * }
   */
  // 2-QueryDeviceDetail(查询指定设备信息详情）
  public static QueryDeviceDetailResponse queryDeviceDetail(String productKey,String deviceName) {
    QueryDeviceDetailResponse acsResponse = null;
    try{
      QueryDeviceDetailRequest rde = new QueryDeviceDetailRequest();
      rde.setActionName("QueryDeviceDetail");  // 执行注册设备操作
      rde.setProductKey(productKey);
      rde.setDeviceName(deviceName);
      IAcsClient sdkClient = DefaultAcsClientUtil.getSdkClient();
      acsResponse = sdkClient.getAcsResponse(rde);
    }catch(Exception e){
      e.printStackTrace();
    }
    return acsResponse;
  }

  // 4-QueryDevice(查询指定产品下的所有设备列表）
  public static QueryDeviceResponse queryDevice(String productKey) {
    QueryDeviceResponse qdResponse = null;
    try{
      QueryDeviceRequest rde = new QueryDeviceRequest();
      rde.setActionName("QueryDevice");
      rde.setProductKey(productKey);
      IAcsClient sdkClient = DefaultAcsClientUtil.getSdkClient();
      qdResponse = sdkClient.getAcsResponse(rde);
    }catch(Exception e){
      e.printStackTrace();
    }
    return qdResponse;
  }


}

