package cn.litchi.model.model;

import cn.litchi.model.respone.model.LzHarm;
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
@TableName(value = "lz_harm")
public class DBLzHarm implements Serializable {

    public static final String ID_FIELD = "id";
    public static final String TYPE_FIELD = "type";
    public static final String NAME_FIELD = "name";
    public static final String PICTURE_FIELD = "picture";
    public static final String DETAIL_FIELD = "detail";
    public static final String FEATURE_FIELD = "feature";
    public static final String RULE_FIELD = "rule";
    public static final String MESSAGE_FIELD = "message";
    public static final String CREATE_TIME_FIELD = "create_time";
    public static final String UPDATE_TIME_FIELD = "update_time";

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private String type;

    private String name;

    private String picture;

    private String detail;

    private String feature;

    private String rule;

    private String measure;

    private Instant createTime;

    private Instant updateTime;

    public LzHarm toResponeModel() {
        return LzHarm.builder()
                .id(this.id)
                .name(this.name)
                .pic(this.picture)
                .build();
    }
}