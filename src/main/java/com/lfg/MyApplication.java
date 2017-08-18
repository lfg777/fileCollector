package com.lfg;

import com.lfg.fileCollector.FileHamal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by lifengguang on 2017/8/16.
 */
@SpringBootApplication
public class MyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class,args);

        FileHamal fileHamal = new FileHamal();
        fileHamal.run();
    }
}
