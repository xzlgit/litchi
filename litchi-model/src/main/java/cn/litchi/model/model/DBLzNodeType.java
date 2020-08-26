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

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "lz_node_type")
public class DBLzNodeType implements Serializable{
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private String type;

    private Instant createTime;

    private Instant updateTime;
}