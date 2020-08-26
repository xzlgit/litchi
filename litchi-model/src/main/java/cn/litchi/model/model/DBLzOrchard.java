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
@TableName(value = "lz_orchard")
public class DBLzOrchard implements Serializable{
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    private String name;

    private String manager;

    private String phone;

    private String address;

    private Instant createTime;

    private Instant updateTime;
}