package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionTest {
    public static void main(String[] args) throws ClassNotFoundException {
        // DB연결을 위한 인터페이스 : Connection - mysql Connector/J에서 제공한다.
        // jdbc URL은 DBMS 업체에서 정한 방식으로 입력한다.
        // DBMS와 연결을 하고 Connection을 구현하고 있는 객체를 반환한다.
        Connection conn = null;
        PreparedStatement ps = null;
        try {


            // DBMS 접속
            conn =
                    DriverManager.getConnection(
                            "jdbc:mysql://127.0.0.1:3306/examplesdb",
                            "root",
                            "root");
            if(conn !=null){
                System.out.println("DBMS 연결 성공!!");
                System.out.println((conn.getClass().getName()));
            }
            // SQL을 작성하고 실행하는 코드를 작성한다.
            // DBMS 에 전송되고 DBSM 안에 실행되고.. 그 샐힝결과가 네트워크를 타고 와서 출력
            //  select * from role;
            // insert into role(role_id, name) values ('값','값');

            // conn아 지금 연결된 DBMS 에 SQL을 준비해줘 . 그런데 물음표 부분은 남겨놓아.
            // 준비하는것을 참조하도록 PreparedStatement를 반환한다.

            ps = conn.prepareStatement(" insert into role(role_id, name) values (?,?)");
            // 물음표를 채우는 것을 바인딩이라 한다. 바인딩까지 하면 SQL을 실행할 준비
            ps.setInt(1,3); // 1번째 물음표에 정수값을 설정
            ps.setString(2, "ROLE_TEST"); // 2번째 물음표에 문자열 값을 설정

            // SQL 실행 excuteUpdate 는 insert, update, delete할 때 사용한다.
           int updateCount = ps.executeUpdate();
           System.out.println("수정된 건수"+updateCount);
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }finally {
            try {
                // ps 자원 해제
                if (ps !=null)
                    ps.close();;

                // 연결 끊기
                if(conn != null)
                    conn.close();
            } catch (SQLException e) {
                System.out.println("SQLException:"+ e.getMessage());
            }
        }
    }
}
