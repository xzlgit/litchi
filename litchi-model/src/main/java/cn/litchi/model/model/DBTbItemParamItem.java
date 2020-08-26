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
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_item_param_item")
public class DBTbItemParamItem implements Serializable{
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private Long itemId;

    private Instant createTime;

    private Instant updateTime;

    private String paramData;
}