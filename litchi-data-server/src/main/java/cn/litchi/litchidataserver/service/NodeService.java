package cn.litchi.litchidataserver.service;

import cn.litchi.model.mapper.LzMonitorRegulationGroupDao;
import cn.litchi.model.mapper.LzNodeDao;
import cn.litchi.model.model.DBLzMonitorRegulationGroup;
import cn.litchi.model.model.DBLzNode;
import cn.litchi.model.request.NodeQueryReq;
import cn.litchi.rpc.NodeServiceRpc;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;

import static cn.litchi.model.utils.CollectionsUtilsExtend.checkListNotNull;

@RestController
public class NodeService implements NodeServiceRpc {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private LzNodeDao lzNodeDao;
    @Autowired
    private LzMonitorRegulationGroupDao groupDao;

    @Override
    public List<DBLzNode> getNodeList() {
        QueryWrapper<DBLzNode> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().orderByDesc(DBLzNode::getUpdateTime);
        List<DBLzNode> nodes = lzNodeDao.selectList(queryWrapper);
        return checkListNotNull(nodes);
    }

    @Override
    public DBLzNode addNode(@RequestBody DBLzNode node) {
        node.setCreateTime(Instant.now());
        node.setUpdateTime(Instant.now());
        node.setToken(UUID.randomUUID().toString());
        lzNodeDao.insert(node);
        return node;
    }

    @Override
    public Boolean deleteNode(@RequestParam("nodeId") Long nodeId) {
        // 取待删除节点的创建时间
        Instant nodeCreateTime = lzNodeDao.selectById(nodeId).getCreateTime();
        // 查询出创建时间小于 nodeCreateTime 的规则组
        QueryWrapper<DBLzMonitorRegulationGroup> query = new QueryWrapper<>();
        query.lambda().ge(DBLzMonitorRegulationGroup::getCreateTime, nodeCreateTime);
        List<DBLzMonitorRegulationGroup> groups = groupDao.selectList(query);
        // 遍历规则组，更新nodeList，去除被删除节点，最后更新修改过的规则组。
        groups.stream().filter(it -> it.getNodeList().contains(nodeId))
                .map(it -> {
                    it.getNodeList().remove(nodeId);
                    return it;
                }).forEach(groupDao::updateById);
        // 删除节点
        return lzNodeDao.deleteById(nodeId) == 1;
    }

    @Override
    public List<DBLzNode> queryNode(@RequestBody NodeQueryReq req) {
        logger.info(req.toString());
        int type = req.getQueryType().intValue();
        String queryKey = req.getQueryKey();
        QueryWrapper<DBLzNode> queryWrapper = new QueryWrapper<>();
        if (type == NodeQueryReq.NODE_ID) {
            queryWrapper.lambda().eq(DBLzNode::getId, Long.valueOf(queryKey));
        } else if (type == NodeQueryReq.NODE_NAME) {
            queryWrapper.lambda().like(DBLzNode::getName, queryKey);
        } else if (type == NodeQueryReq.NODE_TYPE) {
            int typeId = DBLzNode.getNodeTypeIdByName(queryKey);
            queryWrapper.lambda().eq(DBLzNode::getNodeTypeId, typeId);
        } else if (type == NodeQueryReq.NODE_TOKEN) {
            queryWrapper.lambda().eq(DBLzNode::getToken, queryKey);
        }
        return lzNodeDao.selectList(queryWrapper);
    }

    @Override
    public Boolean updateNode(@RequestBody DBLzNode node) {
        node.setUpdateTime(Instant.now());
        return lzNodeDao.updateById(node) == 1;
    }
}
