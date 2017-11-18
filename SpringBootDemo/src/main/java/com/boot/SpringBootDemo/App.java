package com.boot.SpringBootDemo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.boot.model.User;

/**
 * Hello world!
 *
 */
@SpringBootApplication(scanBasePackages={"com.boot.Controller"})
public class App 
{
    public static void main( String[] args )
    {
    	
    	//userDataList.add(new User("1", "Amit", "Kumar", "abc@abc.com", 121001, ""))
        
        SpringApplication.run(App.class, args);
        System.out.println( "Hello World!" );
    }
}
