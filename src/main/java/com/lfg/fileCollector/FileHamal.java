package com.lfg.fileCollector;

import org.springframework.util.StringUtils;

/**
 * Created by lifengguang on 2017/8/16.
 */
public class FileHamal {

    public FileHamal(String[] args) {
        this.args = args;
    }

    private final String defaultFilePath = "/AppData/Local/Google/Chrome/User Data/Default/Bookmarks";

    private String filePath;

    private String[] args;

    public void run() {
        if (StringUtils.isEmpty(filePath)) {
            String userHome = System.getProperty("user.home");
            filePath = userHome+defaultFilePath;
            System.out.println(filePath);
        }

        FileListener fileListener = new FileListener(filePath,args);
        new Thread(fileListener).start();

    }

}
