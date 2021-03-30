package cn.net.arven.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import java.io.Serializable;


/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2019-04-24
 */
public class FileTag extends Model<FileTag> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;

        /**
     * file
     */
     
    private String file;

        /**
     * tag
     */
     
    private String tag;


    public String getId() {
        return id;
    }

    public FileTag setId(String id) {
        this.id = id;
        return this;
    }

    public String getFile() {
        return file;
    }

    public FileTag setFile(String file) {
        this.file = file;
        return this;
    }


    public String getTag() {
        return tag;
    }

    public FileTag setTag(String tag) {
        this.tag = tag;
        return this;
    }

    public static final String ID = "id";

    public static final String FILE = "file";

    public static final String TAG = "tag";


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "File{" +
        "id=" + id +
        ", file=" + file +
        ", tag=" + tag +
        "}";
    }
}
