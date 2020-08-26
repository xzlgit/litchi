package cn.litchi.model.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName(value = "lz_monitor_regulation_group", resultMap = "groupResultMap")
public class DBLzMonitorRegulationGroup implements Serializable, Comparable<DBLzMonitorRegulationGroup> {
    public static final String ID_FIELD = "id";

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long orchardId;
    private String name;
    private Boolean enable;
    private String message;
    private Integer beginDay;
    private Integer endDay;

    private Instant beginDate;

    private Instant endDate;

    private Instant createTime;

    private Instant updateTime;
    @TableField(exist = false)
    private List<DBLzMonitorRegulationItem> items;
    private List<Long> nodeList;

    @Override
    public int compareTo(DBLzMonitorRegulationGroup o) {
        return (int) (Collections.max(this.items).getKeepMinutes() - Collections.max(o.items).getKeepMinutes());
    }
}
