package cn.litchi.model.model;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "lz_monitor_regulation_item")
public class DBLzMonitorRegulationItem implements Serializable, Comparable<DBLzMonitorRegulationItem> {
    public static final String GROUP_ID_FIELD = "group_id";

    public static final int THRESHOLD_TYPE_AVERAGE_VALUE = 1;
    public static final int THRESHOLD_TYPE_MIN_VALUE = 2;
    public static final int THRESHOLD_TYPE_MAX_VALUE = 3;

    public static final String DB_TEMPERATURE_STRING = "温度";
    public static final String DB_HUMIDITY_STRING = "湿度";
    public static final String DB_LIGHT_INTENSITY_STRING = "光照强度";
    public static final String DB_LIGHT_LENGTH_STRING = "光照时长";
    public static final String DB_SOIL_WATER_STRING = "土壤水分";
    public static final String DB_WIND_STRENGTH_STRING = "风力强度";
    public static final String DB_RAINFALL_STRING = "降水量";
    public static final String DB_VOLT_STRING = "电池电压";

    public static final String OPERATOR_GREATER = ">";
    public static final String OPERATOR_LESS = "<";
    public static final String OPERATOR_EQUALS = "=";


    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long groupId;
    private String name;
    private Integer indexNum;
    private String dataType;
    private Integer thresholdType;
    private Double threshold;
    private String operator;
    private Long keepMinutes;
    private Boolean enable;
    private Instant createTime;
    private Instant updateTime;


    @Override
    public int compareTo(DBLzMonitorRegulationItem o) {
        return (int) (this.keepMinutes - o.keepMinutes);
    }

    public List<Double> matchMonitorData(List<DBLzNodeData> data) {
        if (DB_TEMPERATURE_STRING.equals(dataType)) {
            return data.stream().filter(it -> timeFilter(it)).map(DBLzNodeData::getTemp).collect(Collectors.toList());
        } else if (DB_HUMIDITY_STRING.equals(dataType)) {
            return data.stream().filter(it -> timeFilter(it)).map(DBLzNodeData::getHumi).collect(Collectors.toList());
        } else if (DB_LIGHT_INTENSITY_STRING.equals(dataType)) {
            return data.stream().filter(it -> timeFilter(it)).map(DBLzNodeData::getLx).collect(Collectors.toList());
        } else if (DB_LIGHT_LENGTH_STRING.equals(dataType)) {
            return data.stream().filter(it -> timeFilter(it)).map(DBLzNodeData::getTlx).collect(Collectors.toList());
        } else if (DB_SOIL_WATER_STRING.equals(dataType)) {
            return data.stream().filter(it -> timeFilter(it)).map(DBLzNodeData::getWater).collect(Collectors.toList());
        } else if (DB_WIND_STRENGTH_STRING.equals(dataType)) {
            return data.stream().filter(it -> timeFilter(it)).map(DBLzNodeData::getWindStrength).collect(Collectors.toList());
        } else if (DB_RAINFALL_STRING.equals(dataType)) {
            return data.stream().filter(it -> timeFilter(it)).map(DBLzNodeData::getRainfall).collect(Collectors.toList());
        } else if (DB_VOLT_STRING.equals(dataType)) {
            return data.stream().filter(it -> timeFilter(it)).map(DBLzNodeData::getVolt).collect(Collectors.toList());
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    public boolean shouldAlarm(double alarm, double value) {
        if (OPERATOR_GREATER.equals(getOperator())) {
            return value > alarm;
        } else if (OPERATOR_EQUALS.equals(getOperator())) {
            return Math.abs(value - alarm) < 0.01;
        } else if (OPERATOR_LESS.equals(getOperator())) {
            return value < alarm;
        } else {
            return false;
        }
    }

    private boolean timeFilter(DBLzNodeData data) {
        Instant now = Instant.now().minusSeconds(keepMinutes);
        return now.isBefore(data.getTime());
    }

    public static int getThresholdTypeByName(String name) {
        if (name.contains("平均")) {
            return THRESHOLD_TYPE_AVERAGE_VALUE;
        } else if (name.contains("最小")) {
            return THRESHOLD_TYPE_MIN_VALUE;
        } else if (name.contains("最大")) {
            return THRESHOLD_TYPE_MAX_VALUE;
        } else {
            return 0;
        }
    }
}
