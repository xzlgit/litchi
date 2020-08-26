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
@TableName(value = "tb_content")
public class DBTbContent {
    public static final String ID_FIELD = "id";
    public static final String CATEGORY_ID_FIELD = "category_id";
    public static final String TITLE_FIELD = "title";
    public static final String SUB_TITLE_FIELD = "sub_title";
    public static final String URL_FIELD = "url";
    public static final String PICTURE_FIELD = "picture";
    public static final String PICTURE_2_FIELD = "picture2";
    public static final String CREATE_TIME_FIELD = "create_time";
    public static final String UPDATE_TIME_FIELD = "update_time";
    public static final String CONTENT_FIELD = "content";

    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private Long categoryId;

    private String title;

    private String subTitle;

    private String titleDesc;

    private String url;

    private String picture;

    private String picture2;

    private Instant createTime;

    private Instant updateTime;

    private String content;
}