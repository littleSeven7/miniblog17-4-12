package fish.miniblog.controller;

import fish.miniblog.model.Posts;
import fish.miniblog.model.Relationships;
import fish.miniblog.model.Users;
import fish.miniblog.service.UsersService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/posts")
public class PostController {

    @Resource
    private UsersService usersService;
    Users u = new Users();


    // 首页     登录以后的首页 (发布页面)
    @GetMapping("/index")
    public String post_index(HttpSession session, Model model) {
        if (session.getAttribute("session_login_user") == null) {
            return "redirect: /users/index"; // 这个会话如果是空的 就是还没有登录 重定向到首页
        } else {
            System.out.println(":成功跳转到(重定向) posts/index页面 ");
            u = (Users) session.getAttribute("session_login_user"); // 获取当前登录的用户
            info(session, model);
            return "posts/index";
        }
    }

    // 显示某个用户信息
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String show(@PathVariable int id, HttpSession session, Model model) {
        if (session.getAttribute("session_login_user") == null) {
            return "redirect: /users/index"; // 这个会话如果是空的 就是还没有登录 重定向到首页
        } else {
            System.out.println("个人  users/post/{id}");
            // 会覆盖
            u = usersService.getUsersByUsersId(id);
            Users u1 = (Users) session.getAttribute("session_login_user"); // 获取当前登录的用户
            boolean ruser_id_a = usersService.getRuser_id_a(u1.getId(), id);
            model.addAttribute("ruser_id_a", ruser_id_a);

            info(session, model);

            return "posts/post";
        }
    }

    // 个人页面里 关注 (post请求)
    @RequestMapping(value = "/relationships/{id}", method = RequestMethod.POST)
    public String relationships(@PathVariable int id, HttpSession session, Model model) {
        u = (Users) session.getAttribute("session_login_user"); // 获取当前登录的用户

        Users r_user_id_p = new Users(); // 要关注的人
        r_user_id_p.setId(id);

        Relationships relationships = new Relationships();
        relationships.setR_user_id(u); // 我
        relationships.setR_user_id_p(r_user_id_p); // 关注的对象

        usersService.relationships(relationships);

        model.addAttribute("info", "添加好友成功");

//        return "forward:/posts/" + id; // 转发
        return "forward:/posts/{id}";
    }


    // 个人页面里 取消关注 (post请求)
    @RequestMapping(value = "/relationships_f/{id}", method = RequestMethod.POST)
    public String relationships_f(@PathVariable int id, HttpSession session, Model model) {
        u = (Users) session.getAttribute("session_login_user"); // 获取当前登录的用户

        Users r_user_id_p = new Users(); // 要关注的人
        r_user_id_p.setId(id);

        usersService.relationships_f(u, r_user_id_p);

        model.addAttribute("info", "取消好友成功");


//        return "forward:/posts/" + id;
        return "forward:/posts/{id}";
    }

    // 删除
    // @DeleteMapping("/{id}")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable long id, HttpSession session, Model model) {
        usersService.delete(id);
        model.addAttribute("info", "删除成功");
        return "forward:/posts/index";
    }


    // 查询使用用户
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String users(Model model) {
        System.out.println("进入查找所有好友页面");
        List<Users> users = usersService.getUsersAll();
        model.addAttribute("session_users_all", users);
        return "posts/users";
    }

    public void info(HttpSession session, Model model) {

        // 加载所有博客消息
        List<Posts> post1 = usersService.postsList(u);
        List<Users> users1 = usersService.getRuser_id(u);
        List<Users> users2 = usersService.getRuser_id_p(u);

        // session.setAttribute("session_user", u); // session 它是登录后开始储存在session里的  会长时间在
        model.addAttribute("session_user", u);
        model.addAttribute("session_post", post1);      // model   它只在posts/index页面有效
        model.addAttribute("session_Ruser_id", users1);
        model.addAttribute("session_Ruser_id_p", users2);

    }


}
