package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

// @Component 붙어 있는 객체는 스프링 컨테이너가 관리하는 객체가 된다.
// Bean 은 스프링이 관리하는 객체이다.
@SpringBootApplication
// DempApplication 은 스프링부트 설정파일이기도 하고 컴포넌트이기도 한다.
// Autowired 이라고 해서 자동으로 주입할 수 있따.
public class DemoApplication implements CommandLineRunner {
	// main 메소드는 Spring이 관리 안한다. run으로 실행한 이후에 스프링으로 관리한다.
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	// Spring이 Object로 참조 할 수 있는 모든 Bean을 List 형태로 주입한다.
	// Object는 모든 객체의 최상위 객체이기 때문에 모든 Bean을 출력한다.
	@Autowired
	List<Object> beans;

	@Override
	public  void run(String... args) throws Exception{
		for(Object obj: beans){
			System.out.println(obj.getClass().getName());
		}

	}
}
