package cn.net.arven.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;
import java.util.Date;


/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2018-12-13
 */
public class User extends Model<User> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)

    private Integer id;


    private String name;


    private String password;


    private String role;


    private Date modifytime;


    private Date createtime;


    public Integer getId() {
        return id;
    }

    public User setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getRole() {
        return role;
    }

    public User setRole(String role) {
        this.role = role;
        return this;
    }

    public Date getModifytime() {
        return modifytime;
    }

    public User setModifytime(Date modifytime) {
        this.modifytime = modifytime;
        return this;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public User setCreatetime(Date createtime) {
        this.createtime = createtime;
        return this;
    }

    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String PASSWORD = "password";

    public static final String ROLE = "role";

    public static final String MODIFYTIME = "modifytime";

    public static final String CREATETIME = "createtime";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "User{" +
        "id=" + id +
        ", name=" + name +
        ", password=" + password +
        ", role=" + role +
        ", modifytime=" + modifytime +
        ", createtime=" + createtime +
        "}";
    }
}
