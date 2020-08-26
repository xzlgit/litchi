package cn.litchi.litchiapiserver.controller;

import cn.litchi.model.model.DBLzNode;
import cn.litchi.model.model.DBSysUser;
import cn.litchi.model.request.NodeQueryReq;
import cn.litchi.model.utils.MallResult;
import cn.litchi.rpc.NodeServiceRpc;
import cn.litchi.rpc.UserServiceRpc;
import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/node")
public class NodeController extends BaseController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private NodeServiceRpc nodeService;
    @Autowired
    private UserServiceRpc userRpc;

    @GetMapping("/list")
    public MallResult getNodeList() {
        List<DBLzNode> nodes = nodeService.getNodeList();
        if (nodes.isEmpty()) {
            return MallResult.ok();
        }
        return MallResult.ok(nodes);
    }

    @DeleteMapping("")
    public MallResult deleteNode(@RequestParam("id") Long nodeId) {
        return nodeService.deleteNode(nodeId) ? MallResult.ok() : MallResult.build(401, "删除失败");
    }

    @PostMapping("")
    public MallResult addNode(@RequestBody DBLzNode node, Authentication authResult) {
        String name = authResult.getName();
        DBSysUser user = userRpc.getUserByName(name);
        node.setOrchardId(user.getOrchardId());
        nodeService.addNode(node);
        return MallResult.ok(node);
    }

    @PostMapping("/query")
    public MallResult queryNode(@RequestBody NodeQueryReq req) {
        logger.info(req.toString());
        return MallResult.ok(nodeService.queryNode(req));
    }

    @PostMapping("/update")
    public MallResult updateNode(@RequestBody DBLzNode node) {
        nodeService.updateNode(node);
        return MallResult.ok();
    }

}
