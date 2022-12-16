package com.example.demo.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

// Spring JDBC를 이용해서 Database 프로그래밍
// Repository는 @Component 이고 컴포넌트는 컨테이너가 관리하는 Bean이 된다.
@Repository
public class RoleDao {
    // JDBC 탬플릿
    private final JdbcTemplate jdbcTemplate; //필드를 final로 선언하면 반드시 생성자에서 초기화 한다.

//    public RoleDao(org.springframework.jdbc.core.JdbcTemplate jdbcTemplate) {
//        JdbcTemplate = jdbcTemplate;
//    }

    // 생성자에 파라미터를 넣어주면 스프링 부트가 자동으로 주입한다. -> 생성자 주입이라고한다.
    // 생성자 주입 : 히카리CP가 만들어주는 것
    public RoleDao(DataSource dataSource){
        System.out.println("RoleDao 생성자 호출");
        System.out.println(dataSource.getClass().getName());
        jdbcTemplate = new JdbcTemplate();
    }
}
