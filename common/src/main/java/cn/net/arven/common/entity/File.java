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
 * @since 2018-12-22
 */

public class File extends Model<File> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

        /**
     * 存储路径
     */
     
    private String path;

        /**
     * 展示名称
     */
     
    private String showName;

        /**
     * 真实名称
     */
     
    private String realName;

        /**
     * 类型
     */
     
    private String type;

        /**
     * 创建人
     */
     
    private String creator;

        /**
     * 创建时间
     */
     
    private Date createTime;

        /**
     * 更新时间
     */
     
    private Date updateTime;

    public String getId() {
        return id;
    }

    public File setId(String id) {
        this.id = id;
        return this;
    }

    public String getPath() {
        return path;
    }

    public File setPath(String path) {
        this.path = path;
        return this;
    }

    public String getShowName() {
        return showName;
    }

    public File setShowName(String showName) {
        this.showName = showName;
        return this;
    }

    public String getRealName() {
        return realName;
    }

    public File setRealName(String realName) {
        this.realName = realName;
        return this;
    }

    public String getType() {
        return type;
    }

    public File setType(String type) {
        this.type = type;
        return this;
    }

    public String getCreator() {
        return creator;
    }

    public File setCreator(String creator) {
        this.creator = creator;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public File setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public File setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public static final String ID = "id";

    public static final String PATH = "path";

    public static final String SHOW_NAME = "show_name";

    public static final String REAL_NAME = "real_name";

    public static final String TYPE = "type";

    public static final String CREATOR = "creator";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "File{" +
        "id=" + id +
        ", path=" + path +
        ", showName=" + showName +
        ", realName=" + realName +
        ", type=" + type +
        ", creator=" + creator +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
