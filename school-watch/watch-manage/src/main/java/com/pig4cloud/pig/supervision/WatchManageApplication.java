
package com.pig4cloud.pig.supervision;


import com.pig4cloud.pig.common.security.annotation.EnablePigFeignClients;
import com.pig4cloud.pig.common.security.annotation.EnablePigResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

/**
 * @author lengleng
 * @date 2018年06月21日
 * 用户统一管理系统
 */
@EnablePigResourceServer
@EnablePigFeignClients
@SpringCloudApplication
public class WatchManageApplication {
	public static void main(String[] args) {
		SpringApplication.run(WatchManageApplication.class, args);
	}

}
