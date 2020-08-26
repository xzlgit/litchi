package cn.litchi.model.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "lz_node_data_picture")
/**
 * 节点数据-图片类型
 */
public class DBLzNodeDataPicture {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private Long nodeId;

    private Long messageId;

    private Long harmId;

    private Long limitId;

    private Long time;

    private String volt;

    private String picture;

    private Integer num;

    private Integer youngNum;

    private Integer immatureNum;

    private Integer matureNum;

    private Instant createTime;

    private Instant updateTime;
}