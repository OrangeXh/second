package testSecond.doservice;

import testSecond.mapper.LogRegMapper;

//@Service
//@Component
public class DataService {
    //手动引入DataMapper的路径

    //@Autowired
    public LogRegMapper dataMapper;

    public String login(String name, String password){

        return dataMapper.login(name, password);
    }

    public int register(String name,String password){

        return dataMapper.register(name,password);
    }


}
