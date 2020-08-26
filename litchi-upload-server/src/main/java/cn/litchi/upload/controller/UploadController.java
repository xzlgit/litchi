package cn.litchi.upload.controller;

import cn.litchi.model.mapper.LzNodeDao;
import cn.litchi.model.mapper.LzNodeDataDao;
import cn.litchi.model.mapper.LzNodeDataPictureDao;
import cn.litchi.model.mapper.SysUserDao;
import cn.litchi.model.model.DBLzNode;
import cn.litchi.model.model.DBLzNodeData;
import cn.litchi.model.model.DBLzNodeDataPicture;
import cn.litchi.model.model.DBSysUser;
import cn.litchi.model.utils.DateUtils;
import cn.litchi.model.utils.MallResult;
import cn.litchi.model.utils.RedisUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private LzNodeDataDao nodeDataDao;
    @Autowired
    private LzNodeDataPictureDao pictureDao;
    @Autowired
    private LzNodeDao nodeDao;
    @Autowired
    private RedisUtils redisUtils;
    @Value("${liveTime}")
    private int liveTime;

    @PostMapping("/data")
    public MallResult uploadNodeData(@RequestBody DBLzNodeData nodeData, ServletRequest servletRequest) {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String token = req.getHeader("token");
        QueryWrapper<DBLzNode> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(DBLzNode::getToken, token);
        DBLzNode node = nodeDao.selectOne(queryWrapper);
        if (node != null) {
            String idStr = String.valueOf(node.getId());
            if (redisUtils.isExit(idStr)) {
                nodeData.setNodeId(node.getId());
                nodeData.setTime(Instant.now());
                nodeDataDao.insert(nodeData);
                redisUtils.saveData(idStr,idStr,liveTime,TimeUnit.SECONDS);
            }
        }

        return MallResult.ok();
    }

    @PostMapping("/data/picture")
    public MallResult uploadNodePicture(@RequestBody DBLzNodeDataPicture nodeData) {
        if (true) {
            // TODO 检查token判断权限以及节点号
            nodeData.setNodeId(1L);
        }
        if (nodeData.getTime() == null) {
            nodeData.setTime(DateUtils.getNowTimeAsEpochMilli());
        }
        pictureDao.insert(nodeData);
        return MallResult.ok();
    }
}
