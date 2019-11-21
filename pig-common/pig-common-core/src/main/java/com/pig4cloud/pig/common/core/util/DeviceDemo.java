package com.pig4cloud.pig.common.core.util;


import com.aliyuncs.IAcsClient;
import com.aliyuncs.iot.model.v20180120.*;
import com.aliyuncs.linkvisual.model.v20180120.QueryLiveStreamingRequest;
import com.aliyuncs.linkvisual.model.v20180120.QueryLiveStreamingResponse;
import org.json.simple.JSONObject;

public class DeviceDemo {

  public static void main(String[] args) {

//    String pwvCgNRENyCKT8C7mASR000100 = findRtmpByIotId("UHHk8viowyTBVbwC67yv18UbjxFHlbjx");
//    System.out.println(pwvCgNRENyCKT8C7mASR000100);

    String productKey = "a1ZQwIdRsw0";
    String deviceName1 = "oByJIEgAdawjDUYs6UQO";
    String deviceName2 = "r1ddkmHcqeNf5xkbBGJL";
    String deviceName3 = "4zIpPi12f28O8ZUbnVmG";
//    registerDevice(productKey,deviceName);
    String dev1 = queryDeviceDetail(productKey, deviceName1);
    String dev2 = queryDeviceDetail(productKey, deviceName2);
    String dev3 = queryDeviceDetail(productKey, deviceName3);
    System.out.println("dev1="+dev1);
    System.out.println("dev2="+dev2);
    System.out.println("dev3="+dev3);

    QueryDeviceResponse a1ZQwIdRsw0 = DefaultAcsClientUtil.queryDevice("a1ZQwIdRsw0");
    System.out.println(a1ZQwIdRsw0);
  }


  // 注册设备(未)
  public static String registerDevice(String productKey,String deviceName) {
    String rtmp="";
    try{
      RegisterDeviceRequest rde = new RegisterDeviceRequest();
      rde.setActionName("RegisterDevice");  //执行注册设备操作
      rde.setProductKey(productKey);
      rde.setDeviceName(deviceName);

      IAcsClient sdkClient = DefaultAcsClientUtil.getSdkClient();

      RegisterDeviceResponse qlse = sdkClient.getAcsResponse(rde);
      System.out.println(qlse);
      System.out.println(qlse.getData().getIotId());
//      System.out.println(JSONObject.toJSONString(qlse));
//      System.out.println(qlse.getRequestId());
//      if(qlse.getSuccess()){
//        rtmp=qlse.getData().getPath();
//      }
    }catch(Exception e){
      e.printStackTrace();
    }
    return "1";
  }


  // 查询设备信息详情

  public static String queryDeviceDetail(String productKey,String deviceName) {
    String rtmp="";
    try{
      QueryDeviceDetailRequest rde = new QueryDeviceDetailRequest();
      rde.setActionName("QueryDeviceDetail");  //执行注册设备操作
      rde.setProductKey(productKey);
      rde.setDeviceName(deviceName);

      IAcsClient sdkClient = DefaultAcsClientUtil.getSdkClient();

      QueryDeviceDetailResponse acsResponse = sdkClient.getAcsResponse(rde);
      System.out.println(acsResponse);
      System.out.println(acsResponse.getData().getIotId());
      if(acsResponse.getSuccess()){
        rtmp=acsResponse.getData().getIotId();
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    return rtmp;
  }





  public static String findRtmpByIotId(String iotId) {
    String rtmp="";
    try{
      QueryLiveStreamingRequest qlst=new QueryLiveStreamingRequest();
      qlst.setIotId(iotId);
      qlst.setStreamType(0);

      IAcsClient sdkClient = LinkvisualUtil.getSdkClient();

      QueryLiveStreamingResponse qlse= sdkClient.getAcsResponse(qlst);
//      QueryLiveStreamingResponse qlse= LinkvisualUtil.getSdkClient().getAcsResponse(qlst);

      //System.out.println(JSONObject.toJSONString(qlse));
      System.out.println(qlse.getRequestId());
      if(qlse.getSuccess()){
        rtmp=qlse.getData().getPath();
      }
    }catch(Exception e){
      e.printStackTrace();
    }
    return rtmp;
  }


}
