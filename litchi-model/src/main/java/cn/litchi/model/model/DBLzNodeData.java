package cn.litchi.model.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.Instant;

@lombok.Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "lz_node_data")
@ToString
/**
 * 节点数据
 */
public class DBLzNodeData implements Serializable{

    public static final String ID_FIELD = "id";
    public static final String LIMIT_ID_FIELD = "limit_id";
    public static final String NODE_ID_FIELD = "node_id";
    public static final String TIME_FIELD = "time";
    public static final String TEMP_FIELD = "temp";
    public static final String HUMI_FIELD = "humi";
    public static final String LX_FIELD = "lx";
    public static final String TLX_FIELD = "tlx";
    public static final String WATER_FIELD = "water";
    public static final String CO2_FIELD = "co2";
    public static final String WIND_DIRECTION_FIELD = "wind_direction";
    public static final String WIND_STRENGTH_FIELD = "wind_Strength";
    public static final String RAIN_FALL_FIELD = "rainfall";
    public static final String VOLT_FIELD = "volt";

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private Long limitId;

    private Long nodeId;

    private Instant time;

    private Double temp;

    private Double humi;

    private Double lx;

    private Double tlx;

    private Double water;

    private Double co2;

    private Byte windDirection;

    private Double windStrength;

    private Double rainfall;

    private Double volt;
}