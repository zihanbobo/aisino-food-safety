package com.pig4cloud.pig.common.core.util;


import com.aliyuncs.IAcsClient;
import com.aliyuncs.linkvisual.model.v20180120.QueryLiveStreamingRequest;
import com.aliyuncs.linkvisual.model.v20180120.QueryLiveStreamingResponse;

public class VideoDemo {

  public static void main(String[] args) {

    String pwvCgNRENyCKT8C7mASR000100 = findRtmpByIotId("4zIpPi12f28O8ZUbnVmG000100");
    System.out.println(pwvCgNRENyCKT8C7mASR000100);
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
