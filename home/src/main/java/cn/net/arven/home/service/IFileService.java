package cn.net.arven.home.service;

import cn.net.arven.common.entity.File;
import cn.net.arven.home.vo.FileVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 服务类
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
     * @param profile
     * @return
     */
    String saveMultipartFile(MultipartFile file, String tag, String profile) throws IOException;

    /**
     *
     * @param tag
     * @param minSize
     * @param crosswise 是否横向 null代表全部
     * @return
     */
    List<File> getFileByTag(List<String> tag, Integer minSize,Integer crosswise);

    IPage<FileVO> getAll(Long page, Long limit, String tagId, String name);

    File showOne(String id);

    /**
     * 获取文件关联的tag集合
     * @param id
     * @return
     */
    List<String> getFileTagList(String id);

    boolean updateFile(File file, List<String> tag);
}
