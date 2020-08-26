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
@TableName(value = "tb_area")
public class DBTbArea implements Serializable{
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private Long parentId;

    private String area;

    private Instant createTime;

    private Instant updateTime;
}