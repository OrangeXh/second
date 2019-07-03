package testSecond.mapper;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;


//@Component
public interface LogRegMapper {

    //#{id}是用户输入的用户名赋给id，在数据库查询
    // 通过@Param参数的的方式传递(获取属性为Int的变量id的值，以待传递)
    //引用@Select注解进行查询，在前台输入的用户名和密码与数据库比较 返回查询到的sname属性
    //@Select("select username , password from data_entity where name=#{name} and password=#{password}")
    public String login(@Param("username") String username, @Param("password") String password);

    //spring 表达式(#{name})不能加引号
    //@Update("INSERT INTO  data_entity(name,password) VALUES(#{name},#{password})")
    // id自动增长
    //前面加了自动增长策略后，再加下面这一句就会报错，但是数据能够插入数据库
    //@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    public int register(@Param("username") String username, @Param("password") String password);

    //用户认证，根据同户名查找到用户信息

}
