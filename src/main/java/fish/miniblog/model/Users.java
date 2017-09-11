package fish.miniblog.model;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by Administrator on 2017/2/13.
 */
@Entity(name = "users")
@Table(catalog = "miniblog")
public class Users {

    @Id
    @Column(name = "id", nullable = false, precision = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user")
    @SequenceGenerator(name = "user", sequenceName = "seq_user", allocationSize = 1)
    private long id;

    @Column(length = 30)
    @Size(min = 1, max = 13, message = "*  用户名长度在1到13个字符之间")
    @NotEmpty(message = "*  用户名不能为空")
    private String name;

    @Column(length = 30)
    @Size(min = 1, max = 12, message = "*  密码长度在1到12个字符之间")
    @NotEmpty(message = "*  密码不能为空")
    private String password_d;

    @Column(nullable = false, length = 50)  // unique = true 唯一
    @Email(regexp = "^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w{2,3}){1,3})$", message = "*  错误的邮箱格式")
    @NotEmpty(message = "*  email 不能为空")
    private String email;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh24:mi:ss")
    @Temporal(TemporalType.DATE)    // 获取当前时间
    private Date created_t = new Date();    // 建立时间

    @DateTimeFormat(pattern = "yyyy-MM-dd hh24:mi:ss")
    private Date updated_t;                 // 更新时间

    @Column
    private String picture;                 // 图片

    private String remember_d;              // 记住密码

    private String admin;                   // 管理员状态 ☆

    private String activation_d;            // 激活时给邮箱发送的连接(要带的内容)

    private String activated;               // 激活状态

    @DateTimeFormat(pattern = "yyyy-MM-dd hh24:mi:ss")
    private Date activated_t;               // 激活时间

    private String reset_d;                 // 找回密码 ☆(凭什么去找回密码)

    @DateTimeFormat(pattern = "yyyy-MM-dd hh24:mi:ss")
    private Date reset_sent_at;             // 找回密码时间

    // 下面这三个不能toString 不然会报错  (会无限的找对象)
    @OneToMany(mappedBy = "p_user_id", fetch = FetchType.EAGER)
    private List<Posts> posts;

    @OneToMany(mappedBy = "r_user_id_p")
    private List<Relationships> 我关注的人的列表;

    @OneToMany(mappedBy = "r_user_id")
    private List<Relationships> 关注的我人的列表;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword_d() {
        return password_d;
    }

    public void setPassword_d(String password_d) {
        this.password_d = password_d;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getCreated_t() {
        return created_t;
    }

    public void setCreated_t(Date created_t) {
        this.created_t = created_t;
    }

    public Date getUpdated_t() {
        return updated_t;
    }

    public void setUpdated_t(Date updated_t) {
        this.updated_t = updated_t;
    }

    public String getRemember_d() {
        return remember_d;
    }

    public void setRemember_d(String remember_d) {
        this.remember_d = remember_d;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getActivation_d() {
        return activation_d;
    }

    public void setActivation_d(String activation_d) {
        this.activation_d = activation_d;
    }

    public String getActivated() {
        return activated;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setActivated(String activated) {
        this.activated = activated;
    }

    public Date getActivated_t() {
        return activated_t;
    }

    public void setActivated_t(Date activated_t) {
        this.activated_t = activated_t;
    }

    public String getReset_d() {
        return reset_d;
    }

    public void setReset_d(String reset_d) {
        this.reset_d = reset_d;
    }

    public Date getReset_sent_at() {
        return reset_sent_at;
    }

    public void setReset_sent_at(Date reset_sent_at) {
        this.reset_sent_at = reset_sent_at;
    }

    public List<Posts> getPosts() {
        return posts;
    }

    public void setPosts(List<Posts> posts) {
        this.posts = posts;
    }

    public List<Relationships> get我关注的人的列表() {
        return 我关注的人的列表;
    }

    public void set我关注的人的列表(List<Relationships> 我关注的人的列表) {
        this.我关注的人的列表 = 我关注的人的列表;
    }

    public List<Relationships> get关注的我人的列表() {
        return 关注的我人的列表;
    }

    public void set关注的我人的列表(List<Relationships> 关注的我人的列表) {
        this.关注的我人的列表 = 关注的我人的列表;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password_d='" + password_d + '\'' +
                ", email='" + email + '\'' +
                ", created_t=" + created_t +
                ", updated_t=" + updated_t +
                ", picture='" + picture + '\'' +
                ", remember_d='" + remember_d + '\'' +
                ", admin='" + admin + '\'' +
                ", activation_d='" + activation_d + '\'' +
                ", activated='" + activated + '\'' +
                ", activated_t=" + activated_t +
                ", reset_d='" + reset_d + '\'' +
                ", reset_sent_at=" + reset_sent_at +
                '}';
    }

}
