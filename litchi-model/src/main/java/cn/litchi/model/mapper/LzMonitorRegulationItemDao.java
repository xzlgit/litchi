package cn.litchi.model.mapper;

import cn.litchi.model.model.DBLzMonitorRegulationItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LzMonitorRegulationItemDao extends BaseMapper<DBLzMonitorRegulationItem> {

     List<DBLzMonitorRegulationItem> getItemsByGroupId(@Param("groupId") Long id);
}
