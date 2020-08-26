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
@TableName(value = "lz_orchard_picture")
public class DBLzOrchardPicture implements Serializable {
    public static final String ID_FIELD = "id";
    public static final String ORCHARD_ID_FIELD = "orchard_id";
    public static final String PICTURE_FIELD = "picture";
    public static final String CREATE_TIME_FIELD = "create_time";
    public static final String UPDATE_TIME_FIELD = "update_time";

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private Long orchardId;

    private String picture;

    private Instant createTime;

    private Instant updateTime;
}