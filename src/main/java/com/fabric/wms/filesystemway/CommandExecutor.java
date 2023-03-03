package com.fabric.wms.filesystemway;

import com.fabric.wms.service.WMSService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommandExecutor {

    private List<String> commands = new ArrayList<>();

    public void executeCommand() {

        Resource resource = new ClassPathResource("command.txt");
        try {
            Scanner sc = new Scanner(resource.getFile());
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                commands.add(line);
            }
            sc.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /**
         *
         * We can have a method which accepts the command and run those commands in sequece
         *
         * Like, performer.executeCommands(List<String> command) and chain all the methods
         * form the service
         */


    }
}
