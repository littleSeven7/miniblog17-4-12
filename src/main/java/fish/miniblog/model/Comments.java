package fish.miniblog.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * 评论
 */
@Entity(name = "comments")
@Table(catalog = "miniblog")
public class Comments {

    @Id
    @Column(name = "id", nullable = false, precision = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment")
    @SequenceGenerator(name = "comment", sequenceName = "seq_comment", allocationSize = 1)
    private long id;                         // 主键

    @Column
    @NotEmpty(message = "评论内容不能为空")
    private String body;                    // 评论内容

    @ManyToOne
    @JoinColumn(name = "c_post_id")
    private Posts c_post_id;                // 所属微博  ☆

    @ManyToOne
    @JoinColumn(name = "c_user_id")
    private Users c_user_id;                // 评论者  ☆

    @DateTimeFormat(pattern = "yyyy-MM-dd hh24:mi:ss")
    @Temporal(TemporalType.DATE)    // 获取当前时间
    private Date created_t = new Date();    // 评论时间

    @DateTimeFormat(pattern = "yyyy-MM-dd hh24:mi:ss")
    private Date updated_t;                 // 更新时间

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Posts getC_post_id() {
        return c_post_id;
    }

    public void setC_post_id(Posts c_post_id) {
        this.c_post_id = c_post_id;
    }

    public Users getC_user_id() {
        return c_user_id;
    }

    public void setC_user_id(Users c_user_id) {
        this.c_user_id = c_user_id;
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

    @Override
    public String toString() {
        return "Comments{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", c_post_id=" + c_post_id +
                ", c_user_id=" + c_user_id +
                ", created_t=" + created_t +
                ", updated_t=" + updated_t +
                '}';
    }
}
