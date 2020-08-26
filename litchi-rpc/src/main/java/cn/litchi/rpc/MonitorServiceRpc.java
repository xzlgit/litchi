package cn.litchi.rpc;


import cn.litchi.model.model.DBLzMonitorRegulationGroup;
import cn.litchi.model.model.DBLzMonitorRegulationItem;
import cn.litchi.model.request.MonitorGroupQueryReq;
import cn.litchi.model.request.MonitorItemReq;
import org.csource.fastdfs.test.Monitor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "litchi-monitor-server")
@RequestMapping("/monitor")
public interface MonitorServiceRpc {
    @GetMapping("/list")
    List<DBLzMonitorRegulationGroup> getMonitorGroupList(@RequestParam("offset") int offset,
                                                         @RequestParam("limit") int limit);

    @PostMapping(value = "/group/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    DBLzMonitorRegulationGroup addMonitorGroup(@RequestBody DBLzMonitorRegulationGroup group);

    @PostMapping(value = "/group/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    Boolean updateMonitorGroup(@RequestBody DBLzMonitorRegulationGroup group);

    @GetMapping("/item/list")
    List<DBLzMonitorRegulationItem> getMonitorItemList(@RequestParam("offset") int offset,
                                                       @RequestParam("limit") int limit);

    @PostMapping(value = "/item/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    DBLzMonitorRegulationItem addMonitorItem(@RequestBody DBLzMonitorRegulationItem item);

    @GetMapping(value = "/group/delete")
    Boolean deleteMonitorGroup(@RequestParam("groupId") Long id);

    @GetMapping("/group/enable")
    Boolean enableMonitorGroup(@RequestParam("groupId") Long id);

    @GetMapping("/group/disable")
    Boolean disableMonitorGroup(@RequestParam("groupId") Long id);

    @GetMapping("/item/enable")
    Boolean enableMonitorItem(@RequestParam("itemId") Long id);

    @PostMapping("/item/update")
    Boolean updateMonitorItem(@RequestBody DBLzMonitorRegulationItem item);

    @GetMapping("/item/disable")
    Boolean disableMonitorItem(@RequestParam("itemId") Long id);

    @DeleteMapping("item")
    Boolean deleteMonitorItem(@RequestParam("itemId") Long id);

    @PostMapping("/item/query")
    List<DBLzMonitorRegulationItem> queryItem(@RequestBody MonitorItemReq req);

    @PostMapping("/group/query")
    List<DBLzMonitorRegulationGroup> queryGroup(@RequestBody MonitorGroupQueryReq req);

}
