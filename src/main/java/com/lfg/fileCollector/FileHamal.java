package com.lfg.fileCollector;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

/**
 * Created by lifengguang on 2017/8/16.
 */
public class FileHamal {

    private final String defaultFilePath = "/AppData/Local/Google/Chrome/User Data/Default/Bookmarks";

    @Value("filePath")
    private String filePath;

    public void run() {
        if (StringUtils.isEmpty(filePath)) {
            String userHome = System.getProperty("user.home");
            filePath = userHome+defaultFilePath;
            System.out.println(filePath);
        }

        FileListener fileListener = new FileListener(filePath);
        new Thread(fileListener).start();

    }
}
