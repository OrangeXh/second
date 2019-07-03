package testSecond.doservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import testSecond.entity.MyUser;
import testSecond.mapper.UserRepository;


public class AuthenService implements UserDetailsService {

    public UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //根据用户名查询数据库，查询对应的用户
        MyUser myUser = userRepository.loadByUsername(username);
        if (myUser == null) {
            throw new UsernameNotFoundException("未找到 " + username + "的信息");
        }

        //构建Security的user对象
        User user = new User(myUser.getUsername(), myUser.getPassword(), myUser.getAuthorities());
        return user;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}

