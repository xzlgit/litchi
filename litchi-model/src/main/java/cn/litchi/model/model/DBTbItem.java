package cn.litchi.model.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
@TableName(value = "tb_item")
public class DBTbItem implements Serializable {

    public static final byte ENABLE = 1;

    public static final byte DISABLE = 2;

    public static final byte DELETE = 3;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String title;

    private String sellPoint;

    private Long price;

    private Integer num;

    private String barcode;

    private String image;

    private Long cid;

    private Byte status;

    private Instant createTime;

    private Instant updateTime;

    private Long vendorId;

    private String area;

    private Byte promote;

    @TableField(exist = false)
    private DBTbItemDesc desc;
}