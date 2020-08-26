package cn.litchi.model.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "lz_alarm_log")
public class DBLzAlarmLog {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private Long nodeId;
    private Long groupId;
    private String message;
    private Instant createTime;
    private Instant updateTime;
}
