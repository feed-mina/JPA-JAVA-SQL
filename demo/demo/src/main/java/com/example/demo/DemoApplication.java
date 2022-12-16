package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
	DataSource dataSource;
	// JavaX.sql 구현체를 구현하는 인터페이스가 필요하다. 히카리데이터 소스를 실제로 넣어서 생성한다.

	@Override
	public  void run(String... args) throws Exception{
			List<Connection> list = new ArrayList<>();

			int i = 0;
			while(true){
				Connection conn = dataSource.getConnection();
				// conn을 이용하여 SQL을 실행 , slow sql을 실행하게 되면..
				// close를 해서 반드시 돌려준다. SQL을 할때 데이터커넥션을 빨리 쓰고 빨리 돌려준다. SQL을 slow하지 않게 잘 작성해야 한다.
				// 그렇기 때문에 느린 SQL은 빠른 SQL로 튜닝할 필요성이 있다.
				conn.close();
				Thread.sleep(100);
			}
 		//	System.out.println(dataSource.getClass().getName());
	}
}
