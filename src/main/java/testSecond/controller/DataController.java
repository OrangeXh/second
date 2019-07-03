package testSecond.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import testSecond.doservice.DataService;
import testSecond.entity.MyUser;
import testSecond.mapper.UserRepository;
import javax.servlet.http.HttpServletRequest;
//@RestController 注解下面的方法都以json格式输出；但是用了模板或者Freemarker(jtl)的就只能用@Controller注解，返回值是ModelAndView
@Controller
public class DataController {

    //@Autowired
    public DataService dataService;
    public UserRepository authenService;
    private MyUser dataOwner = new MyUser();

    //路径输入value 的值， 调用相应的方法
    //这是因为没有在对应的Controller类中添加对GET请求的处理方法。虽然并没有使用get请求，但是在进入首页加载表单的时候，默认就是个get请求
    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    //有的时候要手动alt+enter下jar包。。。不然类名不能识别
    //不用session封装信息，用model

    /*@PostMapping("/dologin")
    public String dologin(HttpServletRequest request, Model model) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println("你输入的用户名是:" + username);
        System.out.println("你输入的密码是:" + password);

        String allInfo = dataService.login(username, password);
        //向modelmap中添加一個属性
        model.addAttribute("username",username);
        String str = "";
        //没找到这个用户（==null）
        if (allInfo == null) {
            return this.failPage();
        } else {
            return this.successPage();
        }

    }*/


    @PostMapping("/register")
    public String doregister(HttpServletRequest request,Model model){
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        String password2 = request.getParameter("password2");
        String str="";
        //把请求中的数据封装到allName;name的属性添加到model
        String allName = dataService.login(name,password);
        model.addAttribute("name",name);
        if(password.equals(password2)){
           //前面mapper类里面 查询到和数据库里面的不匹配，所以返回查询到的name属性就是null；否则就是name
            if(allName == null){
                dataOwner.setName(name);
                dataOwner.setPassword(password);
                dataService.register(name,password);
                str="index";
            }else{
                str = "index";
            }
        }else{
            str = "index";
        }
        return str;
    }


    @PostMapping("/successForAll")
    public String successPage() {
        return "successForAll";
    }

    @PostMapping("/failPage")
    public String failPage() {
        return "failPage";
    }

}
