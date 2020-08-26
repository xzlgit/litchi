package cn.litchi.litchiproductserver.service;

import cn.litchi.model.mapper.TbItemDao;
import cn.litchi.model.mapper.TbItemDescDao;
import cn.litchi.model.model.DBTbItem;
import cn.litchi.model.model.DBTbItemDesc;
import cn.litchi.model.utils.MallResult;
import cn.litchi.rpc.ProductServiceRpc;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class productService implements ProductServiceRpc {

    @Autowired
    private TbItemDao itemDao;

    @Autowired
    private TbItemDescDao itemDescDao;

    @Override
    public List<DBTbItem> getItemList(Integer offset, Integer limit, Long vendorId) {
        return itemDao.selectPage(new Page<>(offset, limit),
                new QueryWrapper<DBTbItem>().lambda().eq(DBTbItem::getVendorId, vendorId))
                .getRecords();
    }

    @Override
    public Boolean addItem(DBTbItem item, String desc) {
        item.setCreateTime(Instant.now());
        item.setUpdateTime(Instant.now());
        itemDao.insert(item);
        DBTbItemDesc tbItemDesc = DBTbItemDesc.builder()
                .itemId(item.getId())
                .itemDesc(desc)
                .createTime(Instant.now())
                .updateTime(Instant.now())
                .build();
        itemDescDao.insert(tbItemDesc);
        return true;
    }

    @Override
    public List<Long> deleteItem(List<Long> itemId, Long vendorId) {
        LambdaQueryWrapper<DBTbItem> queryWrapper =
                new QueryWrapper<DBTbItem>().lambda()
                        .eq(DBTbItem::getVendorId, vendorId)
                        .in(DBTbItem::getId, itemId);
        List<Long> ids = itemDao.selectList(queryWrapper).stream().map(DBTbItem::getId).collect(Collectors.toList());
        itemDao.deleteBatchIds(ids);
        itemDescDao.delete(new QueryWrapper<DBTbItemDesc>().lambda().eq(DBTbItemDesc::getItemId, ids));
        itemId.removeAll(ids);
        return itemId;
    }

    @Override
    public DBTbItem getItem(Long itemId) {
        DBTbItem item = itemDao.selectById(itemId);
        item.setDesc(itemDescDao.selectById(itemId));
        return item;
    }

    @Override
    public Boolean updateItem(DBTbItem item, Long vendorId) {
        LambdaQueryWrapper<DBTbItem> wrapper =
                new QueryWrapper<DBTbItem>().lambda()
                        .eq(DBTbItem::getId, item.getId())
                        .eq(DBTbItem::getVendorId, vendorId);
        item.setUpdateTime(Instant.now());
        itemDescDao.updateById(item.getDesc());
        return itemDao.update(item, wrapper) == 1;
    }

    @Override
    public Boolean enableItem(Long id, Long vendorId) {
        DBTbItem item = DBTbItem.builder().status(DBTbItem.ENABLE).build();
        LambdaQueryWrapper<DBTbItem> wrapper = new QueryWrapper<DBTbItem>().lambda()
                .eq(DBTbItem::getId, id)
                .eq(DBTbItem::getVendorId, vendorId);
        return itemDao.update(item, wrapper) == 1;
    }

    @Override
    public Boolean disableItem(Long id, Long vendorId) {
        DBTbItem item = DBTbItem.builder().status(DBTbItem.DISABLE).build();
        LambdaQueryWrapper<DBTbItem> wrapper = new QueryWrapper<DBTbItem>().lambda()
                .eq(DBTbItem::getId, id)
                .eq(DBTbItem::getVendorId, vendorId);
        return itemDao.update(item, wrapper) == 1;
    }
}
