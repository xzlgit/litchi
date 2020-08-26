package cn.litchi.rpc;


import cn.litchi.model.model.DBLzAlarmLog;
import cn.litchi.model.model.DBLzNodeData;
import cn.litchi.model.request.AlarmQueryReq;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@FeignClient(value = "litchi-data-server")
@RequestMapping("/node/data")
public interface NodeDataServiceRpc {
    @GetMapping("")
    List<DBLzNodeData> selectDatasByNodeId(@RequestParam("nodeId") Long nodeId);

    /*
     * 获取最近N天的目标节点数据
     */
    @GetMapping("/lastday")
    List<DBLzNodeData> selectLastestNDayDatasByNodeId(@RequestParam("nodeId") Long nodeId,
                                                      @RequestParam("nday") int nday);

    @GetMapping("/interval")
    List<DBLzNodeData> selectIntervalDatasByDateAndNodeId(@RequestParam("beginDate") LocalDate beginDate,
                                                          @RequestParam("endDate") LocalDate endDate,
                                                          @RequestParam("nodeId") Long nodeId);

    @GetMapping("/interval/all")
    List<DBLzNodeData> selectIntervalDatasByDate(@RequestParam("beginDate") Instant beginDate,
                                                 @RequestParam("endDate") Instant endDate);

    @GetMapping("/alarm/list")
    List<DBLzAlarmLog> getAlarmLog();

    @PostMapping("/alarm/query")
    List<DBLzAlarmLog> queryAlarmLog(@RequestBody AlarmQueryReq req);


}
