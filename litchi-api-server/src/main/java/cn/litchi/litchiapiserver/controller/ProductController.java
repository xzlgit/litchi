package cn.litchi.litchiapiserver.controller;

import cn.litchi.model.model.DBSysUser;
import cn.litchi.model.model.DBTbItem;
import cn.litchi.model.utils.MallResult;
import cn.litchi.model.utils.MallResultStatus;
import cn.litchi.rpc.ProductServiceRpc;
import cn.litchi.rpc.UserServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static cn.litchi.model.utils.StringUtils.splitGetLongIds;

@RestController
@RequestMapping("/api/product/vendor")
public class ProductController {

    @Autowired
    private ProductServiceRpc productRpc;

    @Autowired
    private UserServiceRpc userRpc;


    @PostMapping("/item/add")
    public MallResult addItem(@RequestBody DBTbItem item,
                              @RequestParam("desc") String desc,
                              Authentication authResult) {
        String name = authResult.getName();
        DBSysUser user = userRpc.getUserByName(name);
        item.setVendorId(user.getId());
        productRpc.addItem(item, desc);
        return MallResult.ok();
    }

    @GetMapping("/item/list")
    public MallResult getItemList(@RequestParam("offset") Integer offset,
                                  @RequestParam("limit") Integer limit,
                                  Authentication authResult) {
        // TODO check params
        String name = authResult.getName();
        DBSysUser user = userRpc.getUserByName(name);
        List<DBTbItem> itemList = productRpc.getItemList(offset, limit, user.getId());
        return MallResult.ok(itemList);
    }

    @GetMapping("/item/delete")
    public MallResult deleteItemByIds(@RequestParam("id") String id, Authentication authResult) {
        String name = authResult.getName();
        DBSysUser user = userRpc.getUserByName(name);
        List<Long> ids = productRpc.deleteItem(splitGetLongIds(id), user.getId());
        return MallResult.ok(ids);
    }

    @GetMapping("/item/basic_info")
    public MallResult getItemById(@RequestParam("id") Long id, Authentication authResult) {
        DBSysUser user = userRpc.getUserByName(authResult.getName());
        DBTbItem item = productRpc.getItem(id);
        if (item.getVendorId().equals(id)) {
            return MallResult.ok(item);
        } else {
            return MallResult.build(MallResultStatus.PARAMETER_ERROR, "操作失败，请确认数据是否正确。");
        }
    }

    @PostMapping("/item/update")
    public MallResult updateItem(@RequestBody DBTbItem item, Authentication authResult) {
        DBSysUser user = userRpc.getUserByName(authResult.getName());
        Boolean success = productRpc.updateItem(item, user.getId());
        if (success) {
            return MallResult.ok();
        } else {
            return MallResult.build(MallResultStatus.PARAMETER_ERROR, "操作失败，请确认数据是否正确。");
        }
    }

    @GetMapping("/item/enable")
    public MallResult enableItem(@RequestParam("id") Long id, Authentication authResult) {
        DBSysUser user = userRpc.getUserByName(authResult.getName());
        Boolean success = productRpc.enableItem(id, user.getId());
        if (success) {
            return MallResult.ok();
        } else {
            return MallResult.build(MallResultStatus.PARAMETER_ERROR, "操作失败，请确认数据是否正确。");
        }
    }

    @GetMapping("/item/disable")
    public MallResult disableItem(@RequestParam("id") Long id, Authentication authResult) {
        DBSysUser user = userRpc.getUserByName(authResult.getName());
        Boolean success = productRpc.disableItem(id, user.getId());
        if (success) {
            return MallResult.ok();
        } else {
            return MallResult.build(MallResultStatus.PARAMETER_ERROR, "操作失败，请确认数据是否正确。");
        }
    }

}