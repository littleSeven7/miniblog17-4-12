package fish.miniblog.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Administrator on 2017/4/13.
 */
@Entity(name = "relationships")
@Table(catalog = "miniblog")
public class Relationships {

    @Id
    @Column(name = "id", nullable = false, precision = 0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "relationships")
    @SequenceGenerator(name = "relationships", sequenceName = "seq_relationships", allocationSize = 1)
    private long id;                         // 主键

    @ManyToOne
    @JoinColumn(name = "r_user_id")
    private Users r_user_id;                // 我  ☆

    @ManyToOne
    @JoinColumn(name = "r_user_id_p")
    private Users r_user_id_p;              // 关注的人  ☆

    @DateTimeFormat(pattern = "yyyy-MM-dd hh24:mi:ss")
    @Temporal(TemporalType.DATE)    // 获取当前时间
    private Date created_t = new Date();    // 评论时间

    @DateTimeFormat(pattern = "yyyy-MM-dd hh24:mi:ss")
    private Date updated_t;                 // 更新时间

    @Override
    public String toString() {
        return "Relationships{" +
                "id=" + id +
                ", r_user_id=" + r_user_id +
                ", r_user_id_p=" + r_user_id_p +
                ", created_t=" + created_t +
                ", updated_t=" + updated_t +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Users getR_user_id() {
        return r_user_id;
    }

    public void setR_user_id(Users r_user_id) {
        this.r_user_id = r_user_id;
    }

    public Users getR_user_id_p() {
        return r_user_id_p;
    }

    public void setR_user_id_p(Users r_user_id_p) {
        this.r_user_id_p = r_user_id_p;
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
}
