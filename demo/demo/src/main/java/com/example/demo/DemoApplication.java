package com.example.demo;

import com.example.demo.domain.Role;
import com.example.demo.repository.RoleDao;
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
public class DemoApplication implements CommandLineRunner {
	public DemoApplication(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Autowired
	RoleDao roleDao;
	 // RoleDao가 컴포넌트로 주입한다.
	@Override
	public  void run(String... args) throws Exception{
		// INSERT 문
		//	Role role = new Role();
		//	role.setRoleId(1);
		//	role.setName("ROLE_JDBC");
		//	roleDao.addRole(role);

		// DELETE 문
		//	boolean flag = roleDao.deleteRole(2);
		//	System.out.println("flag:"+flag);

		//SELECT 문
		Role role = roleDao.getRole(1);
		System.out.println(role.getRoleId()+","+role.getName());

	}
}
