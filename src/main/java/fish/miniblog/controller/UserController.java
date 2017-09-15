package fish.miniblog.controller;

import fish.miniblog.model.Posts;
import fish.miniblog.model.Users;
import fish.miniblog.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;


@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired

    @Resource
    private UsersService usersService;


    // 首页    /(ㄒoㄒ)/~~  没登录的首页
    @RequestMapping(value = "index")
    public String index() {
        return "index";
    }

    // 进入登录页面
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Users u) {
        System.out.println("进入登录页面");
        return "/login";
    }

    //显示用户发布微博页面(进入登录页面处理跳转后的)  (post请求)
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String userLogin(HttpSession session, HttpServletRequest request) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
/*

        // 用MD5加密码原始密码，生成新的密码
        MD5 md5 = new MD5();
        password = md5.getMD5ofStr(password);
        // 用新的密码与数据表中的密码比较
*/

        Users u = usersService.login(email, password);
        if (u != null) {
            if (u.getActivated().equals("f")) { // 判断出  该用户还是未激活状态
                StringBuffer sb = new StringBuffer();
                sb.append(u.getName() + "你还没有激活");
//                sb.append("<a href=\"/?\">点击重新发送邮箱</a>");
                session.setAttribute("activated", sb);
                return "redirect: /users/index";
            } else {
                session.setAttribute("session_login_user", u); // 存储当前登录的用户
                System.out.println("登录成功");
                return "redirect: /posts/index";
            }
        }
        session.removeAttribute("session_login_user");// 指定清空 session_login_user 会话
        session.removeAttribute("activated");
        return "/login";
    }

    // 进入注册页面
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Users users) {
        System.out.println("进入注册页面");
        return "regist";
    }

    // 添加新的用户（进入到注册页面后的提交处理）
    @RequestMapping(value = "addHandle", method = RequestMethod.POST)
    public String create(@Valid Users users, Errors errors, HttpSession session, HttpServletRequest request) {
        if (errors.hasErrors()) {
            return "regist";
        }
//        users.getPassword_d();


/**
 MD5 md5 = new MD5();
 md5.getMD5ofStr(users.getPassword_d());

 */
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        users.setActivation_d(basePath);

        usersService.regist(users);
        System.out.println("添加新的用户（进入到注册页面后的提交处理）");
        if (users.getActivated().equals("f")) {  // 未激活要跳转的 页面
            session.setAttribute("activated", users.getName() + "   你还没有激活");
            return "redirect: /users/index";
        } else { // 激活跳转的页面
            return "redirect: /posts/index";
        }

    }

    // 发邮箱里的 a连接
    @RequestMapping(value = "/account_activations")
    public String account_activations(long id, String name, String email, String password_d, Date created_t, String picture, String admin) {
//http://localhost:8080/users/account_activations?id=43&name=241111113&email=1152100196@qq.com&password_d=1111&created_t=Fri%20Sep%2015%2009:31:57%20CST%202017&picture=1.jpg&admin=f
        Users u = new Users();
        u.setId(id);
        u.setName(name);
        u.setEmail(email);
        u.setPassword_d(password_d);
        u.setCreated_t(created_t);
        u.setPicture(picture);
        u.setAdmin(admin);

        usersService.getUsersById(u);

        return "redirect: /posts/index";
    }


    // 进入个人信息更改页面 post/${session_user.id}/edit
    @RequestMapping(value = "post/{id}/edit", method = RequestMethod.GET)
    public String edit(Users users) {
        System.out.println("进入个人信息更改页面");
        return "postsAnd/edit";
    }


    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    public String posts(HttpSession session, Model model, HttpServletRequest request) {
        String body = request.getParameter("content"); // 内容
        Users u = (Users) session.getAttribute("session_login_user");

        Posts p = new Posts();
        p.setBody(body);
        p.setP_user_id(u);
        boolean bl = usersService.posts(p);
        if (bl) {

            model.addAttribute("info", "发布成功！");

            return "redirect: /posts/index";
        } else {
            model.addAttribute("info", "发布失败！");
            return "redirect: /posts/index";
        }

    }


}
