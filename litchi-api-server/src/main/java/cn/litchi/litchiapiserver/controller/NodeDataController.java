package cn.litchi.litchiapiserver.controller;

import cn.litchi.litchiapiserver.entity.NodeIdAndDateList;
import cn.litchi.model.model.DBLzNodeData;
import cn.litchi.model.request.AlarmQueryReq;
import cn.litchi.model.request.NodeQueryReq;
import cn.litchi.model.respone.model.LzNodeData;
import cn.litchi.model.utils.MallResult;
import cn.litchi.rpc.NodeDataServiceRpc;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api/nodeData")
public class NodeDataController extends BaseController {

    @Autowired
    private NodeDataServiceRpc nodeDataService;

    public List<LocalDate> nodeIdAndDateListToListDate(NodeIdAndDateList nodeIdAndDateList) {
        List<LocalDate> dateList = new ArrayList<LocalDate>();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<String> dateStringList = new ArrayList<String>();
        for (String year : nodeIdAndDateList.getYears()) {
            for (String date : nodeIdAndDateList.getDates()) {
                dateStringList.add(year + "-" + date);
            }

        }
        for (String str : dateStringList) {
            LocalDate date = LocalDate.parse(str, fmt);
            dateList.add(date);
        }
        return dateList;
    }

    @GetMapping(value = "/alarm")
    public MallResult getAlarmList() {
        return MallResult.ok(nodeDataService.getAlarmLog());
    }

    @GetMapping(value = "/interval")
    public MallResult getIntervalNodeData(@RequestParam("beginDate") Instant beginDate,
                                          @RequestParam("endDate") Instant endDate) {
        List<DBLzNodeData> data = nodeDataService.selectIntervalDatasByDate(beginDate, endDate);
        return MallResult.ok(data);
    }

    @PostMapping("/alarm/query")
    public MallResult queryAlarm(@RequestBody AlarmQueryReq req) {
        return MallResult.ok(nodeDataService.queryAlarmLog(req));
    }


    /*
     * 温度
     */
    @PostMapping(value = "/temp/interval")
    public MallResult getIntervalNodeDateTempByNodeIdtest(@RequestBody NodeIdAndDateList nodeIdAndDateList) {
        List<LzNodeData> dataList = new ArrayList<LzNodeData>();
        List<LocalDate> dateList = nodeIdAndDateListToListDate(nodeIdAndDateList);
        Iterator<LocalDate> iterator = dateList.iterator();
        LocalDate beginDate = null;
        LocalDate endDate = null;
        int lineNum = -1;
        while (iterator.hasNext()) {
            lineNum++;
            beginDate = iterator.next();
            System.out.println(beginDate);
            if (iterator.hasNext()) {
                endDate = iterator.next();
                System.out.println(endDate);
            }
            List<DBLzNodeData> list = nodeDataService.selectIntervalDatasByDateAndNodeId(beginDate, endDate,
                    nodeIdAndDateList.getNodeId());
            if (CollectionUtils.isEmpty(list)) {
                LzNodeData myNodeData = new LzNodeData(null, null, lineNum);
                dataList.add(myNodeData);
                continue;
            }
            for (int index = 0; index < list.size(); index++) {
                DBLzNodeData data = list.get(index);
                LzNodeData myNodeData = new LzNodeData(data.getTime(), data.getTemp(), lineNum);
                dataList.add(myNodeData);
            }
        }
        return MallResult.ok(dataList);
    }

    @GetMapping(value = "/temp/threeday")
    public MallResult getLastThreeDayNodeDataTempByNodeId(Long nodeId) {
        List<DBLzNodeData> dataList = nodeDataService.selectLastestNDayDatasByNodeId(nodeId, -3);
        if (CollectionUtils.isEmpty(dataList)) {
            return MallResult.ok(new String[][]{});
        }
        String[][] datas = getTempDataArrayByResult(dataList);
        return MallResult.ok(datas);
    }

    @GetMapping(value = "/temp/aweek")
    public MallResult getLastWeekNodeDataTempByNodeId(Long nodeId) {
        List<DBLzNodeData> dataList = nodeDataService.selectLastestNDayDatasByNodeId(nodeId, -7);
        if (CollectionUtils.isEmpty(dataList)) {
            return MallResult.ok(new String[][]{});
        }
        String[][] datas = getTempDataArrayByResult(dataList);
        return MallResult.ok(datas);
    }

    @GetMapping(value = "/temp/amouth")
    public MallResult getLastMouthNodeDataTempByNodeId(Long nodeId) {
        List<DBLzNodeData> dataList = nodeDataService.selectLastestNDayDatasByNodeId(nodeId, -30);
        if (CollectionUtils.isEmpty(dataList)) {
            return MallResult.ok(new String[][]{});
        }
        String[][] datas = getTempDataArrayByResult(dataList);
        return MallResult.ok(datas);
    }

    @GetMapping(value = "/temp/all")
    public MallResult getNodeDataTempByNodeId(Long nodeId) {
        List<DBLzNodeData> dataList = nodeDataService.selectDatasByNodeId(nodeId);
        if (CollectionUtils.isEmpty(dataList)) {
            return MallResult.ok(new String[][]{});
        }
        String[][] datas = getTempDataArrayByResult(dataList);
        return MallResult.ok(datas);
    }

    /*
     * 湿度
     */

    @PostMapping(value = "/humi/interval")
    public MallResult getIntervalNodeDateHumiByNodeIdtest(@RequestBody NodeIdAndDateList nodeIdAndDateList) {
        List<LzNodeData> dataList = new ArrayList<LzNodeData>();
        List<LocalDate> dateList = nodeIdAndDateListToListDate(nodeIdAndDateList);
        Iterator<LocalDate> iterator = dateList.iterator();
        LocalDate beginDate = null;
        LocalDate endDate = null;
        int lineNum = -1;
        while (iterator.hasNext()) {
            lineNum++;
            beginDate = iterator.next();
            System.out.println(beginDate);
            if (iterator.hasNext()) {
                endDate = iterator.next();
                System.out.println(endDate);
            }
            List<DBLzNodeData> list = nodeDataService.selectIntervalDatasByDateAndNodeId(beginDate, endDate,
                    nodeIdAndDateList.getNodeId());
            if (list == null || list.isEmpty()) {
                LzNodeData myNodeData = new LzNodeData(null, null, lineNum);
                dataList.add(myNodeData);
                continue;
            }
            for (int index = 0; index < list.size(); index++) {
                DBLzNodeData data = list.get(index);
                LzNodeData myNodeData = new LzNodeData(data.getTime(), data.getHumi(), lineNum);
                dataList.add(myNodeData);
            }
        }
        return MallResult.ok(dataList);
    }

    @GetMapping(value = "/humi/threeday")
    public MallResult getLastThreeDayNodeDataHumiByNodeId(Long nodeId) {
        List<DBLzNodeData> dataList = nodeDataService.selectLastestNDayDatasByNodeId(nodeId, -3);
        if (CollectionUtils.isEmpty(dataList)) {
            return MallResult.ok(new String[][]{});
        }
        String[][] datas = getHumiDataArrayByResult(dataList);
        return MallResult.ok(datas);
    }

    @GetMapping(value = "/humi/aweek")
    public MallResult getLastWeekNodeDataHumiByNodeId(Long nodeId) {
        List<DBLzNodeData> dataList = nodeDataService.selectLastestNDayDatasByNodeId(nodeId, -7);
        if (CollectionUtils.isEmpty(dataList)) {
            return MallResult.ok(new String[][]{});
        }
        String[][] datas = getHumiDataArrayByResult(dataList);
        return MallResult.ok(datas);
    }

    @GetMapping(value = "/humi/amouth")
    public MallResult getLastMouthNodeDataHumiByNodeId(Long nodeId) {
        List<DBLzNodeData> dataList = nodeDataService.selectLastestNDayDatasByNodeId(nodeId, -30);
        if (CollectionUtils.isEmpty(dataList)) {
            return MallResult.ok(new String[][]{});
        }
        String[][] datas = getHumiDataArrayByResult(dataList);
        return MallResult.ok(datas);
    }

    @GetMapping(value = "/humi/all")
    public MallResult getNodeDataHumiByNodeId(Long nodeId) {
        List<DBLzNodeData> dataList = nodeDataService.selectDatasByNodeId(nodeId);
        if (CollectionUtils.isEmpty(dataList)) {
            return MallResult.ok(new String[][]{});
        }
        String[][] datas = getHumiDataArrayByResult(dataList);
        return MallResult.ok(datas);
    }


    /*
     * Co2
     */
    @PostMapping(value = "/co2/interval")
    public MallResult getIntervalNodeDateCo2ByNodeIdtest(@RequestBody NodeIdAndDateList nodeIdAndDateList) {
        List<LzNodeData> dataList = new ArrayList<LzNodeData>();
        List<LocalDate> dateList = nodeIdAndDateListToListDate(nodeIdAndDateList);
        Iterator<LocalDate> iterator = dateList.iterator();
        LocalDate beginDate = null;
        LocalDate endDate = null;
        int lineNum = -1;
        while (iterator.hasNext()) {
            lineNum++;
            beginDate = iterator.next();
            System.out.println(beginDate);
            if (iterator.hasNext()) {
                endDate = iterator.next();
                System.out.println(endDate);
            }
            List<DBLzNodeData> list = nodeDataService.selectIntervalDatasByDateAndNodeId(beginDate, endDate,
                    nodeIdAndDateList.getNodeId());
            if (list == null || list.isEmpty()) {
                LzNodeData myNodeData = new LzNodeData(null, null, lineNum);
                dataList.add(myNodeData);
                continue;
            }
            for (int index = 0; index < list.size(); index++) {
                DBLzNodeData data = list.get(index);
                LzNodeData myNodeData = new LzNodeData(data.getTime(), data.getCo2(), lineNum);
                dataList.add(myNodeData);
            }
        }
        return MallResult.ok(dataList);
    }

    @GetMapping(value = "/co2/threeday")
    public MallResult getLastThreeDayNodeDataCo2ByNodeId(Long nodeId) {
        List<DBLzNodeData> dataList = nodeDataService.selectLastestNDayDatasByNodeId(nodeId, -3);
        if (CollectionUtils.isEmpty(dataList)) {
            return MallResult.ok(new String[][]{});
        }
        String[][] datas = getCo2DataArrayByResult(dataList);
        return MallResult.ok(datas);
    }

    @GetMapping(value = "/co2/aweek")
    public MallResult getLastWeekNodeDataCo2ByNodeId(Long nodeId) {
        List<DBLzNodeData> dataList = nodeDataService.selectLastestNDayDatasByNodeId(nodeId, -7);
        if (CollectionUtils.isEmpty(dataList)) {
            return MallResult.ok(new String[][]{});
        }
        String[][] datas = getCo2DataArrayByResult(dataList);
        return MallResult.ok(datas);
    }

    @GetMapping(value = "/co2/amouth")
    public MallResult getLastMouthNodeDataCo2ByNodeId(Long nodeId) {
        List<DBLzNodeData> dataList = nodeDataService.selectLastestNDayDatasByNodeId(nodeId, -30);
        if (CollectionUtils.isEmpty(dataList)) {
            return MallResult.ok(new String[][]{});
        }
        String[][] datas = getCo2DataArrayByResult(dataList);
        return MallResult.ok(datas);
    }

    @GetMapping(value = "/co2/all")
    public MallResult getNodeDataCo2ByNodeId(Long nodeId) {
        List<DBLzNodeData> dataList = nodeDataService.selectDatasByNodeId(nodeId);
        if (CollectionUtils.isEmpty(dataList)) {
            return MallResult.ok(new String[][]{});
        }
        String[][] datas = getCo2DataArrayByResult(dataList);
        return MallResult.ok(datas);
    }


    /*
     * 光照强度
     */

    @PostMapping(value = "/lx/interval")
    public MallResult getIntervalNodeDateLxByNodeIdtest(@RequestBody NodeIdAndDateList nodeIdAndDateList) {
        List<LzNodeData> dataList = new ArrayList<LzNodeData>();
        List<LocalDate> dateList = nodeIdAndDateListToListDate(nodeIdAndDateList);
        Iterator<LocalDate> iterator = dateList.iterator();
        LocalDate beginDate = null;
        LocalDate endDate = null;
        int lineNum = -1;
        while (iterator.hasNext()) {
            lineNum++;
            beginDate = iterator.next();
            System.out.println(beginDate);
            if (iterator.hasNext()) {
                endDate = iterator.next();
                System.out.println(endDate);
            }
            List<DBLzNodeData> list = nodeDataService.selectIntervalDatasByDateAndNodeId(beginDate, endDate,
                    nodeIdAndDateList.getNodeId());
            if (list == null || list.isEmpty()) {
                LzNodeData myNodeData = new LzNodeData(null, null, lineNum);
                dataList.add(myNodeData);
                continue;
            }
            for (int index = 0; index < list.size(); index++) {
                DBLzNodeData data = list.get(index);
                LzNodeData myNodeData = new LzNodeData(data.getTime(), data.getLx(), lineNum);
                dataList.add(myNodeData);
            }
        }
        return MallResult.ok(dataList);
    }

    @GetMapping(value = "/lx/threeday")
    public MallResult getLastThreeDayNodeDataLxByNodeId(Long nodeId) {
        List<DBLzNodeData> dataList = nodeDataService.selectLastestNDayDatasByNodeId(nodeId, -3);
        if (CollectionUtils.isEmpty(dataList)) {
            return MallResult.ok(new String[][]{});
        }
        String[][] datas = getLxDataArrayByResult(dataList);
        return MallResult.ok(datas);
    }

    @GetMapping(value = "/lx/aweek")
    public MallResult getLastWeekNodeDataLxByNodeId(Long nodeId) {
        List<DBLzNodeData> dataList = nodeDataService.selectLastestNDayDatasByNodeId(nodeId, -7);
        if (CollectionUtils.isEmpty(dataList)) {
            return MallResult.ok(new String[][]{});
        }
        String[][] datas = getLxDataArrayByResult(dataList);
        return MallResult.ok(datas);
    }

    @GetMapping(value = "/lx/amouth")
    public MallResult getLastMouthNodeDataLxByNodeId(Long nodeId) {
        List<DBLzNodeData> dataList = nodeDataService.selectLastestNDayDatasByNodeId(nodeId, -30);
        if (CollectionUtils.isEmpty(dataList)) {
            return MallResult.ok(new String[][]{});
        }
        String[][] datas = getLxDataArrayByResult(dataList);
        return MallResult.ok(datas);
    }

    @GetMapping(value = "/lx/all")
    public MallResult getNodeDataLxByNodeId(Long nodeId) {
        List<DBLzNodeData> dataList = nodeDataService.selectDatasByNodeId(nodeId);
        if (CollectionUtils.isEmpty(dataList)) {
            return MallResult.ok(new String[][]{});
        }
        String[][] datas = getLxDataArrayByResult(dataList);
        return MallResult.ok(datas);
    }

    /*
     * 水分
     */

    @PostMapping(value = "/water/interval")
    public MallResult getIntervalNodeDateWaterByNodeIdtest(@RequestBody NodeIdAndDateList nodeIdAndDateList) {
        List<LzNodeData> dataList = new ArrayList<LzNodeData>();
        List<LocalDate> dateList = nodeIdAndDateListToListDate(nodeIdAndDateList);
        Iterator<LocalDate> iterator = dateList.iterator();
        LocalDate beginDate = null;
        LocalDate endDate = null;
        int lineNum = -1;
        while (iterator.hasNext()) {
            lineNum++;
            beginDate = iterator.next();
            System.out.println(beginDate);
            if (iterator.hasNext()) {
                endDate = iterator.next();
                System.out.println(endDate);
            }
            List<DBLzNodeData> list = nodeDataService.selectIntervalDatasByDateAndNodeId(beginDate, endDate,
                    nodeIdAndDateList.getNodeId());
            if (CollectionUtils.isEmpty(list)) {
                LzNodeData myNodeData = new LzNodeData(null, null, lineNum);
                dataList.add(myNodeData);
                continue;
            }
            for (int index = 0; index < list.size(); index++) {
                DBLzNodeData data = list.get(index);
                LzNodeData myNodeData = new LzNodeData(data.getTime(), data.getWater(), lineNum);
                dataList.add(myNodeData);
            }
        }
        return MallResult.ok(dataList);
    }

    @GetMapping(value = "/water/threeday")
    public MallResult getLastThreeDayNodeDataWaterByNodeId(Long nodeId) {
        List<DBLzNodeData> dataList = nodeDataService.selectLastestNDayDatasByNodeId(nodeId, -3);
        if (CollectionUtils.isEmpty(dataList)) {
            return MallResult.ok(new String[][]{});
        }
        String[][] datas = getWaterDataArrayByResult(dataList);
        return MallResult.ok(datas);
    }

    @GetMapping(value = "/water/aweek")
    public MallResult getLastWeekNodeDataWaterByNodeId(Long nodeId) {
        List<DBLzNodeData> dataList = nodeDataService.selectLastestNDayDatasByNodeId(nodeId, -7);
        if (CollectionUtils.isEmpty(dataList)) {
            return MallResult.ok(new String[][]{});
        }
        String[][] datas = getWaterDataArrayByResult(dataList);
        return MallResult.ok(datas);
    }

    @GetMapping(value = "/water/amouth")
    public MallResult getLastMouthNodeDataWaterByNodeId(Long nodeId) {
        List<DBLzNodeData> dataList = nodeDataService.selectLastestNDayDatasByNodeId(nodeId, -30);
        if (CollectionUtils.isEmpty(dataList)) {
            return MallResult.ok(new String[][]{});
        }
        String[][] datas = getWaterDataArrayByResult(dataList);
        return MallResult.ok(datas);
    }

    @GetMapping(value = "/water/all")
    public MallResult getNodeDataWaterByNodeId(Long nodeId) {
        List<DBLzNodeData> dataList = nodeDataService.selectDatasByNodeId(nodeId);
        if (CollectionUtils.isEmpty(dataList)) {
            return MallResult.ok(new String[][]{});
        }
        String[][] datas = getWaterDataArrayByResult(dataList);
        return MallResult.ok(datas);
    }


    private String[][] getTempDataArrayByResult(List<DBLzNodeData> listDatas) {
        int dataSize = listDatas.size();
        String[][] datas = new String[dataSize][2];
        Iterator<DBLzNodeData> iterator = listDatas.iterator();
        int index = 0;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        while (iterator.hasNext()) {
            DBLzNodeData data = iterator.next();
            datas[index][0] = dateFormat.format(data.getTime());
            datas[index][1] = String.valueOf(data.getTemp());
            index++;
        }
        return datas;
    }

    private String[][] getHumiDataArrayByResult(List<DBLzNodeData> listDatas) {
        int dataSize = listDatas.size();
        String[][] datas = new String[dataSize][2];
        Iterator<DBLzNodeData> iterator = listDatas.iterator();
        int index = 0;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        while (iterator.hasNext()) {
            DBLzNodeData data = iterator.next();
            datas[index][0] = dateFormat.format(data.getTime());
            datas[index][1] = String.valueOf(data.getHumi());
            index++;
        }
        return datas;
    }

    private String[][] getCo2DataArrayByResult(List<DBLzNodeData> listDatas) {
        int dataSize = listDatas.size();
        String[][] datas = new String[dataSize][2];
        Iterator<DBLzNodeData> iterator = listDatas.iterator();
        int index = 0;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        while (iterator.hasNext()) {
            DBLzNodeData data = iterator.next();
            datas[index][0] = dateFormat.format(data.getTime());
            datas[index][1] = String.valueOf(data.getCo2());
            index++;
        }
        return datas;
    }

    private String[][] getLxDataArrayByResult(List<DBLzNodeData> listDatas) {
        int dataSize = listDatas.size();
        String[][] datas = new String[dataSize][2];
        Iterator<DBLzNodeData> iterator = listDatas.iterator();
        int index = 0;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        while (iterator.hasNext()) {
            DBLzNodeData data = iterator.next();
            datas[index][0] = dateFormat.format(data.getTime());
            datas[index][1] = String.valueOf(data.getLx());
            index++;
        }
        return datas;
    }

    private String[][] getWaterDataArrayByResult(List<DBLzNodeData> listDatas) {
        int dataSize = listDatas.size();
        String[][] datas = new String[dataSize][2];
        Iterator<DBLzNodeData> iterator = listDatas.iterator();
        int index = 0;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        while (iterator.hasNext()) {
            DBLzNodeData data = (DBLzNodeData) iterator.next();
            datas[index][0] = dateFormat.format(data.getTime());
            datas[index][1] = String.valueOf(data.getWater());
            index++;
        }
        return datas;
    }

}
