package cn.litchi.rpc;

import cn.litchi.model.utils.MallResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "litchi-order-server")
@RequestMapping("/cart")
public interface CartServiceRpc {
    @PostMapping("/item")
    MallResult addCartItem(@RequestBody Long itemId, @RequestParam("userId") Long userId);

    @GetMapping("/")
    MallResult getCart(@RequestParam("userId") Long userId);

    @GetMapping("item")
    MallResult deleteCartItem(@RequestParam("itemId") Long itemId, @RequestParam("userId") Long userId);
}
