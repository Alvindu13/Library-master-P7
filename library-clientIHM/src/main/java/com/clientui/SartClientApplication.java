package com.clientui;

import com.clientui.beans.BookBean;
import com.clientui.beans.UserBean;
import com.clientui.web.proxies.MicroserviceConsumerProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.util.List;

@SpringBootApplication
@EnableFeignClients("com.clientui")
@EnableOAuth2Sso
public class SartClientApplication {


    // start everything
    public static void main(String[] args) {
        SpringApplication.run(SartClientApplication.class, args);
    }


}