package com.fabric.wms;

import com.fabric.wms.filesystemway.CommandExecutor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;

import java.nio.charset.StandardCharsets;

@SpringBootApplication
public class WaterManagementSystemApplication {

    public static void main(String[] args) {
        CommandExecutor ce = new CommandExecutor();
        ce.executeCommand();
        //SpringApplication.run(WaterManagementSystemApplication.class, args);
    }

}
