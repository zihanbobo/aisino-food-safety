package com.pig4cloud.pig.common.core.util;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

/**
 * 阿里sdk基本属性
 */
public class LinkvisualUtil {
    public static IAcsClient getSdkClient() throws ClientException {
        IAcsClient client = null;
        try{
          // 粮食
//            String accessKeyID = "LTAIHXQBez1MDoAB";
//            String accessKeySecret = "jRqMUF9Edp4Tm1mk6uj4oemfSej3Ux";
          // 食安
            String accessKeyID = "LTAI4FcKTQfBBdJzNuRHEnG3";
            String accessKeySecret = "xeYqm4DXnzkV3i8ol3wr0y8TGtwgu6";

            String regionId = "cn-shanghai";
            DefaultProfile.addEndpoint(regionId, regionId, "linkvisual", "linkvisual.cn-shanghai.aliyuncs.com");
            IClientProfile profile = DefaultProfile.getProfile(regionId, accessKeyID, accessKeySecret);
            //初始化client
            client = new DefaultAcsClient(profile);
        }catch(Exception e){
            System.out.println("init client failed | excrption: " + e.getMessage());
        }
        return client;
    }
}

