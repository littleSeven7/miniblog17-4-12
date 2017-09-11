package fish.miniblog.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 每条微博
 */
@Entity(name = "posts")
@Table(catalog = "miniblog")
public class Posts {

    @Id
    @Column(name = "id", nullable = false, precision = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post")
    @SequenceGenerator(name = "post", sequenceName = "seq_post", allocationSize = 1)
    private long id;                        // 主键

    @Column
    @NotEmpty(message = "内容不能为空")
    private String body;                    // 内容

    @ManyToOne
    @JoinColumn(name = "p_user_id")
    private Users p_user_id;                // 作者  ☆

    @DateTimeFormat(pattern = "yyyy-MM-dd hh24:mi:ss")
    @Temporal(TemporalType.DATE)    // 获取当前时间
    private Date created_t = new Date();    // 发布时间

    @DateTimeFormat(pattern = "yyyy-MM-dd hh24:mi:ss")
    private Date updated_t;                 // 更新时间

    // 双向关联
    @OneToMany(mappedBy ="c_post_id", fetch = FetchType.EAGER)
    private List<Comments> commentss;

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

    public Users getP_user_id() {
        return p_user_id;
    }

    public void setP_user_id(Users p_user_id) {
        this.p_user_id = p_user_id;
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

    public List<Comments> getCommentss() {
        return commentss;
    }

    public void setCommentss(List<Comments> commentss) {
        this.commentss = commentss;
    }

    @Override
    public String toString() {
        return "Posts{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", p_user_id=" + p_user_id +
                ", created_t=" + created_t +
                ", updated_t=" + updated_t +
                '}';
    }
}
