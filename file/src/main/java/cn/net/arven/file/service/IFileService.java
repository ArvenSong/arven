package cn.net.arven.file.service;

import cn.net.arven.common.entity.File;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2018-12-22
 */
public interface IFileService extends IService<File> {

    String saveFile(File fileEntity);

    /**
     * 保存文件
     * @param file
     * @return
     */
    String saveMultipartFile(MultipartFile file,String tag) throws IOException;
}
