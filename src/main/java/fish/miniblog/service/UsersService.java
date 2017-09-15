package fish.miniblog.service;

import fish.miniblog.dao.UsersDao;
import fish.miniblog.model.Comments;
import fish.miniblog.model.Posts;
import fish.miniblog.model.Relationships;
import fish.miniblog.model.Users;
import fish.miniblog.util.MailUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/4/13.
 */
@Service
@Transactional
public class UsersService {

    @Resource
    private UsersDao usersDao;
    @Resource
    private MailUtil mailUtil;

//    HttpServletRequest request;

    /**
     * 登录
     *
     * @param email      邮箱
     * @param password_d 密码
     * @return 结果
     */
    public Users login(String email, String password_d) {
        return usersDao.login(email, password_d);
    }


    /**
     * 注册
     *
     * @param u users对象
     * @return 真假值
     */

    public boolean regist(Users u) {
        u.setPicture("1.jpg");  // 默认图片   (因为是new是对象   它所有对象都是null 不给它值它是null值)
        u.setAdmin("f");        // 管理员
        u.setActivated("f");    // 是否邮箱激活(给它一个 f=false 值)

//        u.setActivation_d("a"); //激活时给邮箱发送的连接(要带的内容)
//        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

        // 组装方式  邮件的内容
        StringBuffer sb = new StringBuffer();
//        sb.append("<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><title>Title</title></head><body>");
        sb.append("<h1>南方微博</h1><p>您好，" + u.getName() + ",</p><p>欢迎使用南方微博，点击下面链接激活您的账号</p>");
        sb.append("<a href=\"" + u.getActivation_d() + "/users/account_activations?id="
                + u.getId() + "&name=" + u.getName() + "&email=" + u.getEmail() + "&password_d=" + u.getPassword_d() + "&created_t="
                + u.getCreated_t() + "&picture=" + u.getPicture() + "&admin=" + u.getAdmin() + "\"");
        sb.append(">Activate</a>");
//        sb.append("</body></html>");
        System.out.println("sb打印==/n" + sb);

        mailUtil.send(u.getEmail(), sb.toString());


        return usersDao.regist(u);
    }

    /**
     * 用户 根据用户id 更改激活状态
     *
     * @param u
     */
    public void getUsersById(Users u) {
        usersDao.getUsersById(u);
    }

    /**
     * 修改用户资料
     *
     * @param u
     */
    public void editPassword(Users u) {
        usersDao.editPassword(u);
    }

    /**
     * 查询所有 用户
     */
    public List<Users> getUsersAll() {
        return usersDao.getUsersAll();
    }

    /**
     * 传一个用户id 查我关注了谁
     *
     * @param u 整个用户表
     * @return 返回结果集
     */
    public List<Users> getRuser_id(Users u) {
        return usersDao.getRuser_id(u);
    }

    /**
     * 传一个用户id 查有谁关注了我
     *
     * @param u 整个用户表
     * @return 返回结果集
     */
    public List<Users> getRuser_id_p(Users u) {
        return usersDao.getRuser_id_p(u);
    }

    /**
     * 查发布的帖子
     *
     * @param id
     * @return
     */
    public List<Posts> postsList(Users id) {
        return usersDao.postsList(id);
    }

    /**
     * 根据id 删除帖子
     *
     * @param postid 传个id
     */
    public void delete(long postid) {
        usersDao.deleteCommentsAndPostsByPostId(postid);
    }

    /**
     * 插入(关注)关系
     *
     * @param r
     */
    public void relationships(Relationships r) {
        usersDao.relationships(r);
    }


    /**
     * 取消(关注)关系
     *
     * @param id
     * @param id_p
     */
    public void relationships_f(Users id, Users id_p) {
        usersDao.relationships_f(id, id_p);
    }

    /**
     * 判断关系存不存在
     *
     * @param id
     * @param id_p
     * @return 真假值
     */
    public boolean getRuser_id_a(long id, long id_p) {
        return usersDao.getRuser_id_a(id, id_p);
    }

    /**
     * 感觉id查用户
     *
     * @param id
     * @return
     */
    public Users getUsersByUsersId(long id) {
        return usersDao.getUsersByUsersId(id);
    }

    /**
     * 发帖(博客)
     *
     * @param p
     */
    public boolean posts(Posts p) {
        return usersDao.posts(p);
    }


    /**
     * 评论(回复博客)
     *
     * @param c
     */
    public boolean comments(Comments c) {
        return usersDao.comments(c);
    }


}
