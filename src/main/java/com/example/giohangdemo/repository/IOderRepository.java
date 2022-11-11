package com.example.giohangdemo.repository;

import com.example.giohangdemo.domain.Oder;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface IOderRepository extends JpaRepository<Oder, Integer> {


//        @Query("select  o from Oder o inner join Category c on c.id=o.id inner join User u on u.id=c.id where u.username=?1")
//    @Query(value = "select * from oder_control as o inner join category_control as cate on cate.category_id= o.category_id" +
//            "    inner join user_control as u on u.user_id = cate.user_id where u.user_name=?1", nativeQuery = true)
    List<Oder> findByCategory_User_Username(String username);


}






