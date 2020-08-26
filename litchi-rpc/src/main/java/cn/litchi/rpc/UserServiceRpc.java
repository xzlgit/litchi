package cn.litchi.rpc;

import cn.litchi.model.model.DBLzNode;
import cn.litchi.model.model.DBSysUser;
import cn.litchi.model.request.NodeQueryReq;
import cn.litchi.model.request.UserQueryReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "litchi-customer-server")
@RequestMapping("/customer")
public interface UserServiceRpc {

    @GetMapping("/basic_info")
    DBSysUser getUserByName(@RequestParam("name") String name);

    @PostMapping("/add")
    DBSysUser addUser(@RequestBody DBSysUser user, @RequestParam("role") String role);

    @GetMapping
    List<DBSysUser> getUserAndAdminList(@RequestParam(value = "offset", required = false) int offset,
                                        @RequestParam(value = "limit", required = false) int limit);

    @PostMapping(value = "/query")
    List<DBSysUser> queryUser(@RequestBody UserQueryReq req);

    @PostMapping("/update")
    DBSysUser updateUser(@RequestBody DBSysUser user);

}
