package cn.litchi.litchisourceserver.service;

import cn.litchi.model.mapper.LzLitchiTypeDao;
import cn.litchi.model.mapper.LzOrcpictureDao;
import cn.litchi.model.mapper.LzTextDao;
import cn.litchi.model.mapper.TbContentDao;
import cn.litchi.model.model.DBLzLitchiType;
import cn.litchi.model.model.DBLzOrchardPicture;
import cn.litchi.model.model.DBLzText;
import cn.litchi.model.model.DBTbContent;
import cn.litchi.model.utils.FastDFSClient;
import cn.litchi.rpc.SourceServiceRpc;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.litchi.model.utils.CollectionsUtilsExtend.checkListNotNull;

@RestController
public class SourceService implements SourceServiceRpc {

    @Value("${IMAGE_SERVER_URL}")
    private String IMAGE_SERVER_URL;

    @Value("${litchi.content.category.carousepicId}")
    private Long carouselPicId;

    @Autowired
    private TbContentDao contentMapper;

    @Autowired
    private LzOrcpictureDao orcpictureMapper;

    @Autowired
    private LzLitchiTypeDao lzLitchiTypeMapper;

    @Autowired
    private LzTextDao lzTextMapper;

    @Override
    public List<DBTbContent> getCarouselPic() {
        List<DBTbContent> list = getContentListByCategoryId(carouselPicId);
        return list;

    }

    @Override
    public Map<String, String> uploadFile(@RequestPart("file") MultipartFile uploadFile) {
        try {
            FastDFSClient fastDFSClient = new FastDFSClient("classpath:client.conf");
            //取文件扩展名
            String originalFilename = uploadFile.getOriginalFilename();
            String key = uploadFile.getName();
            String extName = originalFilename.substring(originalFilename.lastIndexOf(".") + 1);
            //得到一个图片地址和文件名
            String url = fastDFSClient.uploadFile(uploadFile.getBytes(), extName);
            //补充为完整的url
            url = IMAGE_SERVER_URL + url;
            Map<String, String> map = new HashMap<>();
            //图片原来的名字为key,url为value
            map.put(key, url);
            return map;

        } catch (Exception e) {
            Map result = new HashMap<>();
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<DBLzOrchardPicture> getOrcPic(Long orcId) {
        QueryWrapper<DBLzOrchardPicture> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(DBLzOrchardPicture.ORCHARD_ID_FIELD, orcId);
        List<DBLzOrchardPicture> data = orcpictureMapper.selectList(queryWrapper);
        return checkListNotNull(data);
    }

    @Override
    public DBLzLitchiType getLitchiType(Long typeId) {
        return lzLitchiTypeMapper.selectById(typeId);
    }

    @Override
    public List<DBLzText> getLitchiTextByTypeId(Long typeId) {
        QueryWrapper<DBLzText> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(DBLzText.TEXT_TYPE_ID_FIELD, typeId);
        List<DBLzText> data = lzTextMapper.selectList(queryWrapper);
        return checkListNotNull(data);
    }

    private List<DBTbContent> getContentListByCategoryId(Long categoryId) {

        QueryWrapper<DBTbContent> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(DBTbContent.CATEGORY_ID_FIELD, categoryId);
        List<DBTbContent> datas = contentMapper.selectList(queryWrapper);
        return checkListNotNull(datas);
    }
}
