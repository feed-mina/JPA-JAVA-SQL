package com.example.demo.repository;

import com.example.demo.domain.Role;
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
    // Role 테이블에 한건 저장, 저장을 성공하면 true, 실패하면 false를 반환한다.
    public boolean addRole(Role role){
        String sql = "INSERT INTO role(role_id, name) VALUES(?, ?)"; //물음표 두개를 바인딩한다.
        int result = jdbcTemplate.update(sql, role.getRoleId(), role.getName()); // update 메소드는 insert, update, delete SQL문을 실행할때 사용한다.
        return result == 1; // result 는 성공한 건수를 의미한다.
    }

    public boolean deleteRole(int roleId){
        String sql = "DELETE FROM role WHERE role_id = ?";
        int result = jdbcTemplate.update(sql, roleId);
        return result == 1;
    }
    public Role getRole(int roleId){
        String sql = "SELECT role_id, name FROM role WHERE role_id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            Role role = new Role();
            role.setRoleId(rs.getInt("role_id"));
            role.setName(rs.getString("name"));
            return role;
        },roleId);
    }
}
