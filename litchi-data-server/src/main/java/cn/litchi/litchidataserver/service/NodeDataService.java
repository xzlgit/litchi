package cn.litchi.litchidataserver.service;

import cn.litchi.model.mapper.LzAlarmLogDao;
import cn.litchi.model.mapper.LzNodeDataDao;
import cn.litchi.model.model.DBLzAlarmLog;
import cn.litchi.model.model.DBLzMonitorRegulationGroup;
import cn.litchi.model.model.DBLzNodeData;
import cn.litchi.model.request.AlarmQueryReq;
import cn.litchi.model.request.MonitorGroupQueryReq;
import cn.litchi.model.utils.DateUtils;
import cn.litchi.model.utils.StringUtils;
import cn.litchi.rpc.NodeDataServiceRpc;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static cn.litchi.model.utils.CollectionsUtilsExtend.checkListNotNull;
import static cn.litchi.model.utils.DateUtils.checkNodeDataParamDay;
import static cn.litchi.model.utils.DateUtils.dayOfYear;

@RestController
public class NodeDataService implements NodeDataServiceRpc {

    @Autowired
    private LzNodeDataDao nodeDataDao;
    @Autowired
    private LzAlarmLogDao lzAlarmLogDao;

    @Override
    public List<DBLzNodeData> selectDatasByNodeId(@RequestParam("nodeId") Long nodeId) {
        QueryWrapper<DBLzNodeData> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(DBLzNodeData.NODE_ID_FIELD, nodeId);
        List<DBLzNodeData> datas = nodeDataDao.selectList(queryWrapper);
        return checkListNotNull(datas);
    }

    @Override
    public List<DBLzNodeData> selectLastestNDayDatasByNodeId(@RequestParam("nodeId") Long nodeId,
                                                             @RequestParam("nday") int nday) {
        long queryTime = DateUtils.getEpochMilliAtStartofDayByMinusDays(checkNodeDataParamDay(nday));
        QueryWrapper<DBLzNodeData> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge(DBLzNodeData.TIME_FIELD, queryTime).eq(DBLzNodeData.NODE_ID_FIELD, nodeId);
        List<DBLzNodeData> datas = nodeDataDao.selectList(queryWrapper);
        return checkListNotNull(datas);
    }

    @Override
    public List<DBLzNodeData> selectIntervalDatasByDateAndNodeId(@RequestParam("beginDate") LocalDate beginDate,
                                                                 @RequestParam("endDate") LocalDate endDate,
                                                                 @RequestParam("nodeId") Long nodeId) {
        long startOfDay = DateUtils.getEpochMilliAtStartofDay(beginDate);
        long endOfDay = DateUtils.getEpochMilliAtEndofDay(endDate);
        QueryWrapper<DBLzNodeData> queryWrapper = new QueryWrapper<>();
        queryWrapper.ge(DBLzNodeData.TIME_FIELD, startOfDay)
                .le(DBLzNodeData.TIME_FIELD, endOfDay)
                .eq(DBLzNodeData.NODE_ID_FIELD, nodeId);
        List<DBLzNodeData> datas = nodeDataDao.selectList(queryWrapper);
        return checkListNotNull(datas);
    }

    @Override
    public List<DBLzNodeData> selectIntervalDatasByDate(Instant beginDate, Instant endDate) {
        QueryWrapper<DBLzNodeData> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().ge(DBLzNodeData::getTime, beginDate)
                .le(DBLzNodeData::getTime, endDate);
        List<DBLzNodeData> list = nodeDataDao.selectList(queryWrapper);
        return checkListNotNull(list);
    }

    @Override
    public List<DBLzAlarmLog> getAlarmLog() {
        return lzAlarmLogDao.selectList(null);
    }

    @Override
    public List<DBLzAlarmLog> queryAlarmLog(@RequestBody AlarmQueryReq req) {
        int type = req.getQueryType();
        String queryKey = req.getQueryKey();
        QueryWrapper<DBLzAlarmLog> queryWrapper = new QueryWrapper<>();
        if (type == AlarmQueryReq.NODE_ID) {
            queryWrapper.lambda().eq(DBLzAlarmLog::getNodeId, Long.valueOf(queryKey));
        } else if (type == AlarmQueryReq.GROUP_ID) {
            queryWrapper.lambda().eq(DBLzAlarmLog::getGroupId, Long.valueOf(queryKey));
        }
        if (req.getBeginDate() != null) {
            queryWrapper.lambda().ge(DBLzAlarmLog::getCreateTime, req.getBeginDate());
        }
        if (req.getEndDate() != null) {
            queryWrapper.lambda().le(DBLzAlarmLog::getCreateTime, req.getEndDate());
        }
        List<DBLzAlarmLog> logs = lzAlarmLogDao.selectList(queryWrapper);
        return logs;
    }
}
