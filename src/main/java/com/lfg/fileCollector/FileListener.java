package com.lfg.fileCollector;

import com.lfg.email.MailSender;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;

/**
 * Created by lifengguang on 2017/8/16.
 */
public class FileListener implements Runnable {

    public FileListener(String filePath,String[] args) {
        this.filePath = filePath;
        this.args = args;
    }
    
    private String filePath;

    private String[] args;

    @Override
    public void run() {
        registerFile(filePath);
    }

    private void registerFile(String filePath) {
        File file = new File(filePath);
        if (!file.isFile()) {
            // do nothing
        }
        String fileSplit = "/";
        if (-1 == filePath.lastIndexOf(fileSplit)) {
            fileSplit = "\\";
        }
        String fileName = filePath.substring(filePath.lastIndexOf(fileSplit) + 1, filePath.length());
        String fileAbsPath = filePath.substring(0, filePath.lastIndexOf(fileName));
        startWatcher(fileAbsPath,fileName);

    }

    private void startWatcher(String fileAbsPath, String fileName) {
        try {
            WatchService ws = FileSystems.getDefault().newWatchService();
            Path path = Paths.get(fileAbsPath);
            path.register(ws,ENTRY_MODIFY);
            Runtime.getRuntime().addShutdownHook(new Thread(()->{
                try {
                    ws.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }));

            WatchKey key;
            while(true){
                try {
                    key = ws.take();
                    for (WatchEvent<?> envet : key.pollEvents()) {
                        if (envet.context().toString().equalsIgnoreCase(fileName)) {
                            doSendEmail(fileAbsPath,fileName);
                        }
                    }
                    boolean reset = key.reset();
                    if (!reset) {
                        System.out.println("could not reset the watch key");
                        break;
                    }

                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }

            }



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void doSendEmail(String fileAbsPath, String fileName) {
        System.out.println("========file changed========");
        MailSender mailSender = loadMailSender(fileAbsPath + File.separator + fileName, args);
        mailSender.send();
    }

    private MailSender loadMailSender(String filePath,String[] args) {
        MailSender mailSender = new MailSender(filePath);
        mailSender.setParams(args);
        return mailSender;
    }


}
