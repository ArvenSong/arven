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
 * @since 2018-12-15
 */
public class Note extends Model<Note> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)

    private String id;

        /**
     * 内容
     */
     
    private String content;

        /**
     * 金额
     */
     
    private Double amount;

        /**
     * 余额
     */
     
    private Double remain;

        /**
     * 文件id
     */
     
    private String file;

        /**
     * 创建人id
     */
     
    private String creator;


    private Date createTime;


    private Date updateTime;


    public String getId() {
        return id;
    }

    public Note setId(String id) {
        this.id = id;
        return this;
    }

    public String getContent() {
        return content;
    }

    public Note setContent(String content) {
        this.content = content;
        return this;
    }

    public Double getAmount() {
        return amount;
    }

    public Note setAmount(Double amount) {
        this.amount = amount;
        return this;
    }

    public Double getRemain() {
        return remain;
    }

    public Note setRemain(Double remain) {
        this.remain = remain;
        return this;
    }

    public String getFile() {
        return file;
    }

    public Note setFile(String file) {
        this.file = file;
        return this;
    }

    public String getCreator() {
        return creator;
    }

    public Note setCreator(String creator) {
        this.creator = creator;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public Note setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public Note setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
        return this;
    }

    public static final String ID = "id";

    public static final String CONTENT = "content";

    public static final String AMOUNT = "amount";

    public static final String REMAIN = "remain";

    public static final String FILE = "file";

    public static final String CREATOR = "creator";

    public static final String CREATE_TIME = "create_time";

    public static final String UPDATE_TIME = "update_time";

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Note{" +
        "id=" + id +
        ", content=" + content +
        ", amount=" + amount +
        ", remain=" + remain +
        ", file=" + file +
        ", creator=" + creator +
        ", createTime=" + createTime +
        ", updateTime=" + updateTime +
        "}";
    }
}
