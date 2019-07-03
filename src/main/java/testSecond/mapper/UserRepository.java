package testSecond.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import testSecond.entity.MyUser;

public interface UserRepository extends JpaRepository<MyUser, Long> {
    @Query(value = "select * from user where username = :username",nativeQuery = true)
    MyUser loadByUsername(@Param("username") String username);

}
