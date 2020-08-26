package cn.litchi.litchiapiserver.controller;

import cn.litchi.model.model.DBLzHarm;
import cn.litchi.model.respone.model.LzHarm;
import cn.litchi.model.utils.MallResult;
import cn.litchi.model.utils.MallResultStatus;
import cn.litchi.rpc.HarmServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/harm")
public class HarmController extends BaseController {

    @Autowired
    private HarmServiceRpc harmService;

    @GetMapping("/user")
    public String user(Authentication authResult){
        return authResult.getName()+"user";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/list")
    // type == "1" 虫害 “2” 病害
    public MallResult getHarmList(String type) {
        List<DBLzHarm> list = harmService.getHarmList(type);
        List<LzHarm> simpleHarmList = new ArrayList<LzHarm>();
        if (list == null || list.size() <= 0) {
            return MallResult.build(401, "无数据");
        } else {
            list.forEach(it -> simpleHarmList.add(it.toResponeModel()));
            return MallResult.ok(simpleHarmList);
        }
    }

    @GetMapping("")
    public MallResult getHarmById(Long id) {
        DBLzHarm harm = harmService.getHarmById(id);
        return (harm == null) ? MallResult.build(MallResultStatus.SERVER_OPERATION_FAIL, "查询失败")
                : MallResult.ok(harm);
    }

    @PostMapping("/add")
    public MallResult addHarm(@RequestBody DBLzHarm harm) {
        Boolean result = harmService.addHarm(harm);
        return result ? MallResult.ok() : MallResult.build(MallResultStatus.SERVER_OPERATION_FAIL, "添加失败");
    }
}
