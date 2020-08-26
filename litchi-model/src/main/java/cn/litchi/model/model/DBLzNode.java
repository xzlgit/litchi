package cn.litchi.model.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.time.Instant;

@Data
@Builder
@ToString
@TableName(value = "lz_node")
@AllArgsConstructor
@NoArgsConstructor
/**
 * 节点
 */
public class DBLzNode implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String name;

    private Integer nodeTypeId;

    private Long orchardId;

    private String token;

    private Boolean enable;

    //    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Instant createTime;

    //    @JSONField(format="yyyy-MM-dd HH:mm:ss")
    private Instant updateTime;

    public static int getNodeTypeIdByName(String name) {
        if ("数据型".equals(name)) {
            return 1;
        } else if ("图片型".equals(name)) {
            return 2;
        } else {
            return -1;
        }
    }
}