package fish.miniblog.dao;

import fish.miniblog.model.Comments;
import fish.miniblog.model.Posts;
import fish.miniblog.model.Relationships;
import fish.miniblog.model.Users;
import oracle.jdbc.proxy.annotation.Post;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/4/13.
 */

@Repository
public class UsersDao extends BaseDao {

    /**
     * 登录     *
     *
     * @param email      邮箱
     * @param password_d 密码
     * @return 结果
     */
    public Users login(String email, String password_d) {
        Users u = (Users) sessionFactory.getCurrentSession().createQuery("from users where email = :email and password_d = :p").setParameter("email", email).setParameter("p", password_d).uniqueResult();
        return u;
    }

    /**
     * 注册     *
     *
     * @param u users对象
     * @return 真假值
     */
    public boolean regist(Users u) {
        long bl = (long) sessionFactory.getCurrentSession().save(u);
        return bl == 0 ? false : true;
    }


    /**
     * 用户 根据用户id 更改激活状态     *
     *
     * @param u
     */
    public void getUsersById(Users u) {
        /*u.setActivated("t");
        sessionFactory.getCurrentSession().update(u);*/
        String sql = "update users set activated='t' where id=?";
        sessionFactory.getCurrentSession().createSQLQuery(sql).setParameter(1, u.getId()).executeUpdate();
    }


    /**
     * 修改用户资料
     *
     * @param u
     */
    public void editPassword(Users u) {
        sessionFactory.getCurrentSession().update(u);
    }

    /**
     * 查询所有 用户
     */
    public List<Users> getUsersAll() {
        List<Users> userss = sessionFactory.getCurrentSession().createQuery("from users ").list();
        return userss;
    }


    /**
     * 传一个用户id 查我关注了谁
     *
     * @param u 整个用户表
     * @return 返回结果集
     */
    public List<Users> getRuser_id(Users u) {
        List<Users> userss = sessionFactory.getCurrentSession().createQuery("from relationships where r_user_id = :d").setParameter("d", u).list();
        return userss;
    }

    /**
     * 传一个用户id 查有谁关注了我
     *
     * @param u 整个用户表
     * @return 返回结果集
     */
    public List<Users> getRuser_id_p(Users u) {
        List<Users> userss = sessionFactory.getCurrentSession().createQuery("from relationships where r_user_id_p = :d").setParameter("d", u).list();
        return userss;
    }

    /**
     * 查发布的帖子
     *
     * @param id
     * @return
     */
    public List<Posts> postsList(Users id) {
        return sessionFactory.getCurrentSession().createQuery("FROM posts WHERE p_user_id = :d").setParameter("d", id).list();
    }


    /**
     * 根据id 删除帖子
     *
     * @param postid 传个id
     */
    public void deleteCommentsAndPostsByPostId(long postid) {
        // 获取 session
        Session session = getSession();

        // 删评论
        session.createSQLQuery("DELETE from comments where c_post_id = :a").setLong("a", postid).executeUpdate();

        // 删帖子
        session.delete(session.get(Post.class, postid));
    }

    public void deletecomments(long id){
        Session session = getSession();
        sessionFactory.getCurrentSession().delete(session.get(Comments.class,id));
    }

    /**
     * 插入(关注)关系
     *
     * @param r
     */
    public void relationships(Relationships r) {
        sessionFactory.getCurrentSession().save(r);
    }

    /**
     * 取消(关注)关系
     *
     * @param id
     * @param id_p
     */
    public void relationships_f(Users id, Users id_p) {
        String deleteSQL = "delete from relationships where r_user_id = :a and r_user_id_p = :b ";
        getSession().createSQLQuery(deleteSQL)
                .setParameter("a", id.getId())
                .setParameter("b", id_p.getId())
                .executeUpdate();
    }

    /**
     * 判断关系存不存在
     *
     * @param id
     * @param id_p
     * @return 真假值
     */
    public boolean getRuser_id_a(long id, long id_p) {
        boolean bl = !sessionFactory.getCurrentSession().createQuery("from relationships where r_user_id = :a and r_user_id_p=:b")
                .setLong("a", id)
                .setLong("b", id_p).list().isEmpty();
        // isEmpty 有数据是false 要！
        return bl;
    }

    /**
     * 感觉id查用户
     *
     * @param id
     * @return
     */
    public Users getUsersByUsersId(long id) {
        return sessionFactory.getCurrentSession().get(Users.class, id);
    }

    /**
     * 发帖(博客)
     *
     * @param p
     */
    public boolean posts(Posts p) {
        long bl = (long) sessionFactory.getCurrentSession().save(p);
        return bl == 0 ? false : true;
    }


    /**
     * 评论(回复博客)
     *
     * @param c
     */
    public boolean comments(Comments c) {
        long bl = (long) sessionFactory.getCurrentSession().save(c);
        return bl == 0 ? false : true;
    }
}
