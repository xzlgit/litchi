package cn.litchi.rpc;

import cn.litchi.model.model.DBTbOrder;
import cn.litchi.model.utils.MallResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "litchi-order-server")
@RequestMapping("/order")
public interface OrderServiceRpc {

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    MallResult addOrder(@RequestBody DBTbOrder order);

    @GetMapping("/user/get")
    List<DBTbOrder> getOrderByUserId(@RequestParam("userId") Long userId);

    @GetMapping("vendor/get")
    List<DBTbOrder> getOrderByVendorId(@RequestParam("vendorId") Long vendorId);
}
