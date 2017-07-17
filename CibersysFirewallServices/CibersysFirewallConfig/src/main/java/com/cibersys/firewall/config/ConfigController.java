package com.cibersys.firewall.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;


/**
 * Created by Luis Maracara on 6/13/2017.
 */

@EnableConfigServer
@SpringBootApplication
public class ConfigController {

    public static void main(String[] args) {
        SpringApplication.run(ConfigController.class, args);
    }
}
