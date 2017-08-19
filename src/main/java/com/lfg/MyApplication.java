package com.lfg;

import com.lfg.fileCollector.FileHamal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

/**
 * Created by lifengguang on 2017/8/16.
 */
@SpringBootApplication
public class MyApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class,args);

        System.out.println("******"+ Arrays.toString(args));
        FileHamal fileHamal = new FileHamal();
        fileHamal.run();
    }
}
