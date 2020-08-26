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
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "tb_order")
public class DBTbOrder implements Serializable {
    @TableId(value = "order_id", type = IdType.AUTO)
    private String orderId;

    private String payment;

    private Integer paymentType;

    private String postFee;

    private Integer status;

    private Instant createTime;

    private Instant updateTime;

    private Long paymentTime;

    private Long consignTime;

    private Long endTime;

    private Long closeTime;

    private String shippingName;

    private String shippingCode;

    private Long userId;

    private String buyerMessage;

    private String buyerNick;

    private Integer buyerRate;

    private Long vendorId;

    @TableField(exist = false)
    private List<DBTbOrderItem> items;

    @TableField(exist = false)
    private DBTbOrderShipping shipping;
}