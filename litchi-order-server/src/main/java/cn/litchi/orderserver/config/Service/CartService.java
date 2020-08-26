package cn.litchi.orderserver.config.Service;

import cn.litchi.model.mapper.TbItemDao;
import cn.litchi.model.model.DBTbCart;
import cn.litchi.model.model.DBTbItem;
import cn.litchi.model.utils.MallResult;
import cn.litchi.model.utils.RedisUtils;
import cn.litchi.rpc.CartServiceRpc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService implements CartServiceRpc {

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private TbItemDao itemDao;

    @Value("${cartKey}")
    public String CART_KEY;

    @Override
    public MallResult addCartItem(Long itemId, Long userId) {
        String userKey = CART_KEY + ":" + userId;
        DBTbCart cart = (DBTbCart) redisUtils.getData(userKey);
        if (cart == null) {
            cart = new DBTbCart();
            cart.setUserId(userId);
        }
        cart.getCartItemIds().add(itemId);
        redisUtils.saveData(userKey, cart);
        return MallResult.ok();
    }

    @Override
    public MallResult getCart(Long userId) {
        String userKey = CART_KEY + ":" + userId;
        DBTbCart cart = (DBTbCart) redisUtils.getData(userKey);
        if (cart == null) {
            cart = new DBTbCart();
            cart.setUserId(userId);
        }
        List<DBTbItem> items = itemDao.selectBatchIds(cart.getCartItemIds());
        return MallResult.ok(items);
    }

    @Override
    public MallResult deleteCartItem(Long itemId, Long userId) {
        String userKey = CART_KEY + ":" + userId;
        DBTbCart cart = (DBTbCart) redisUtils.getData(userKey);
        if (cart == null) {
            cart = new DBTbCart();
            cart.setUserId(userId);
        }
        cart.getCartItemIds().remove(itemId);
        redisUtils.saveData(userKey, cart);
        return MallResult.ok();
    }
}
