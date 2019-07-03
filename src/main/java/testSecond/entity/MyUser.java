package testSecond.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="user")
public class MyUser implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Integer id;
    //默认传递的参数名称是 username和password
    private String username;
    private String password;
    @ManyToMany(cascade = {CascadeType.REFRESH},fetch = FetchType.EAGER)
    private List<MyRole> myRoles;

    public MyUser(){}
    public MyUser(MyUser myUser){
        this.username = username;
        this.password = password;
    }
    public void setName(String name){
        this.username=username;
    }
    public String getName(){
        return username;
    }
    public void setId(Integer id){
        this.id=id;
    }
    public Integer getId(){
        return id;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public String getPassword(){
        return this.password;
    }
    public void setMyRoles(List<MyRole> myRoles){
        this.myRoles = myRoles;
    }
    public List<MyRole> getMyRoles(){
        return myRoles;
    }

    //注解要一起放一块，要么在字段上，要么在get方法上




    //alt+enter 一键重写
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //将用户角色作为权限
        List<GrantedAuthority> auths = new ArrayList<>();
        List<MyRole> myRoles = this.getMyRoles();
//        if (null != auths)
        for (MyRole myRole : myRoles) {
            auths.add(new SimpleGrantedAuthority(myRole.getName()));
        }

        return auths;


    }

    @Override
    public String getUsername() {
        return this.username;
    }

    //下面四个校验没用到
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public static void main(String[] args) {

    }

}
