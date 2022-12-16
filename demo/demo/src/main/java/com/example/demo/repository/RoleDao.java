package com.example.demo.repository;

import org.springframework.stereotype.Repository;

// @ 이거 하나만 붙어줬는데 스프링에서 인스턴스를 메모리에 만들어서 관리한다는 의미
// 스프링부트에서 demoApplication은 패키지가 package com.example.demo로 되어있다. DemoApplication은 설정파일이고 그 의미는
// demo 이하로만 컴포넌트를 찾아서 레포지토리로 등록한다라는 의미이다.

@Repository
public class RoleDao {
    public RoleDao(){
        System.out.println("RoleDao 생성자 호출");
    }
}
