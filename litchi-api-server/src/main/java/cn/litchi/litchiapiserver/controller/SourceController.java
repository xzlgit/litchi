package cn.litchi.litchiapiserver.controller;

import cn.litchi.litchiapiserver.entity.GroupCarouselPicEntity;
import cn.litchi.litchiapiserver.entity.Picture;
import cn.litchi.model.model.DBLzLitchiType;
import cn.litchi.model.model.DBLzOrchardPicture;
import cn.litchi.model.model.DBLzText;
import cn.litchi.model.model.DBTbContent;
import cn.litchi.model.utils.MallResult;
import cn.litchi.model.utils.MallResultStatus;
import cn.litchi.rpc.SourceServiceRpc;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/source")
public class SourceController extends BaseController {

    @Autowired
    private SourceServiceRpc sourceServiceRpc;

    @PostMapping("/file")
    public MallResult uploadFile(@PathParam("file") MultipartFile file) {
        Map<String, String> map = sourceServiceRpc.uploadFile(file);
        if (map == null || map.isEmpty()) {
            return MallResult.build(MallResultStatus.SERVER_OPERATION_FAIL, "文件上传失败");
        }
        Iterator iterator = map.keySet().iterator();
        if (iterator.hasNext()) {
            return MallResult.ok(map.get(iterator.next()));
        }
        return MallResult.build(MallResultStatus.SERVER_OPERATION_FAIL, "文件上传失败");
    }

    @GetMapping("/litchi/carouselpic")
    public MallResult getLitchiCarouselPic() {
        List<DBTbContent> list = sourceServiceRpc.getCarouselPic();
        if (CollectionUtils.isEmpty(list)) {
            return MallResult.build(MallResultStatus.SERVER_OPERATION_FAIL, "荔枝园轮播图获取失败");
        }
        List<Picture> data = new ArrayList<>();
        list.forEach(it -> {
            Picture picture = Picture.builder()
                    .pic(it.getPicture())
                    .title(it.getTitle())
                    .url(it.getUrl())
                    .build();
            data.add(picture);
        });
        return MallResult.ok(data);
    }

    @GetMapping("/litchi/orcpic")
    public MallResult getOrcPicByOrcId(@RequestParam(value = "orcId", defaultValue = "1") Long orcId) {
        List<DBLzOrchardPicture> data = sourceServiceRpc.getOrcPic(orcId);
        if (data == null) {
            return MallResult.build(MallResultStatus.SERVER_OPERATION_FAIL, "荔枝园图片获取失败");
        }
        return MallResult.ok(data);
    }

    @GetMapping("/litchi/wisdommanagement")
    public MallResult getWisdommanagement(@RequestParam(value = "typeId", defaultValue = "1") Long typeId, Integer mon) {
        DBLzLitchiType data = sourceServiceRpc.getLitchiType(typeId);
        if (data == null) {
            return MallResult.build(MallResultStatus.SERVER_OPERATION_FAIL, "智慧管理获取失败");
        }
        String management = null;
        switch (mon.intValue()) {
            case 1:
                management = data.getJan();
                break;
            case 2:
                management = data.getFeb();
                break;
            case 3:
                management = data.getMar();
                break;
            case 4:
                management = data.getApr();
                break;
            case 5:
                management = data.getMay();
                break;
            case 6:
                management = data.getJun();
                break;
            case 7:
                management = data.getJul();
                break;
            case 8:
                management = data.getAug();
                break;
            case 9:
                management = data.getSept();
                break;
            case 10:
                management = data.getOct();
                break;
            case 11:
                management = data.getNov();
                break;
            case 12:
                management = data.getDece();
                break;
            default:

        }
        return (management != null) ? MallResult.ok(management)
                : MallResult.build(MallResultStatus.SERVER_OPERATION_FAIL, "参数错误");
    }

    @GetMapping("/litchi/text")
    public MallResult getLitchiCulture(Long typeId) {
        List<DBLzText> data = sourceServiceRpc.getLitchiTextByTypeId(typeId);
        if (data == null) {
            return MallResult.build(MallResultStatus.SERVER_OPERATION_FAIL, "信息获取失败");
        }
        return MallResult.ok(data);
    }

    @GetMapping("/litchi/carouselpic/group")
    public MallResult getLitchiCarouselGroupPic() {
        List<DBLzText> data = sourceServiceRpc.getLitchiTextByTypeId(Long.valueOf(7));
        if (data == null) {
            return MallResult.build(MallResultStatus.SERVER_OPERATION_FAIL, "信息获取失败");
        }
        GroupCarouselPicEntity[][] dataArray = getArrayByList(data);
        return MallResult.ok(dataArray);
    }

    private GroupCarouselPicEntity[][] getArrayByList(List<DBLzText> listDatas) {
        int dataSize = listDatas.size();
        GroupCarouselPicEntity[][] datas = new GroupCarouselPicEntity[(int) Math.ceil(dataSize / 2.0)][2];
        Iterator<DBLzText> iterator = listDatas.iterator();
        int row = 0;
        int col = 0;
        while (iterator.hasNext()) {
            DBLzText data = iterator.next();
            datas[row][col] = new GroupCarouselPicEntity(data.getTitle(), data.getPicture());
            col = (col == 1) ? 0 : 1;
            if (col == 0) {
                row++;
            }
        }
        return datas;
    }

}
