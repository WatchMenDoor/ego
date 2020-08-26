package com.bjsxt.backend.item.service.impl;

import com.bjsxt.backend.item.feign.CommonItemFeignClient;
import com.bjsxt.backend.item.service.ItemService;
import com.bjsxt.pojo.TbItem;
import com.bjsxt.pojo.TbItemDesc;
import com.bjsxt.pojo.TbItemParamItem;
import com.bjsxt.utils.IDUtils;
import com.bjsxt.utils.PageResult;
import com.bjsxt.utils.Result;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * @Auther: liuxw
 * @Date: 2020-04-19
 * @Description: com.bjsxt.backend.item.service.impl
 * @version: 1.0
 */
//商品管理
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private CommonItemFeignClient commonItemFeignClient;

    //商品查询
    @Override
    public Result selectTbItemAllByPage(Integer page, Integer rows) {
        PageResult pageResult = this.commonItemFeignClient.selectTbItemAllByPage(page, rows);
        if (pageResult!=null&&pageResult.getResult()!=null&&pageResult.getResult().size()>0){
            return Result.ok(pageResult);
        }
        return Result.error("查无结果");
    }

    //商品的添加
    @Override
    @LcnTransaction
    public Result insertTbItem(TbItem tbItem, String desc, String itemParams) {
        //补齐Tbitem的数据
        long itemId = IDUtils.genItemId();
        Date date = new Date();
        tbItem.setId(itemId);
        tbItem.setStatus((byte)1);
        tbItem.setCreated(date);
        tbItem.setUpdated(date);
        Integer tbItem1 = this.commonItemFeignClient.insertTbItem(tbItem);

        //补充商品描述
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setCreated(date);
        tbItemDesc.setItemId(itemId);
        tbItemDesc.setUpdated(date);
        Integer itemDesc = this.commonItemFeignClient.insertItemDesc(tbItemDesc);

        //补充商品规格参数模板
        TbItemParamItem tbItemParamItem = new TbItemParamItem();
        tbItemParamItem.setParamData(itemParams);
        tbItemParamItem.setItemId(itemId);
        tbItemParamItem.setCreated(date);
        tbItemParamItem.setUpdated(date);
        Integer itemParamItem = this.commonItemFeignClient.insertItemParamItem(tbItemParamItem);
        if (tbItem1==1&&itemDesc==1&&itemParamItem==1){
            return Result.ok();
        }

        return Result.error("商品添加失败");
    }


    //删除商品
    @Override
    @LcnTransaction
    public Result updateItemById(Long tbItemid) {
        TbItem tbItem = new TbItem();
        tbItem.setId(tbItemid);

        Integer integer = this.commonItemFeignClient.deleteItemById(tbItem);
        if (integer!=null){
            return Result.ok();
        }
        return  Result.error("删除失败");

    }

    //预查询商品
    @Override
    public Result preUpdateItem(Long tbitemid) {
        Map<String, Object> map = this.commonItemFeignClient.preUpdateItem(tbitemid);
        if (map!=null){
            return Result.ok(map);
        }

        return  Result.error("预查询失败");
    }


    //更新商品
    @Override
    @LcnTransaction
    public Result updateItem(TbItem tbItem, String desc, String itemParams) {
        //更新商品

        Date date = new Date();
        tbItem.setUpdated(date);
        Integer integer = this.commonItemFeignClient.updateTbItem(tbItem);
        //更新商品描述
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setUpdated(date);
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setItemId(tbItem.getId());
        Integer integer1 = this.commonItemFeignClient.updateItemDesc(tbItemDesc);
        //更新商品参数
        TbItemParamItem tbItemParamItem = new TbItemParamItem();
        tbItemParamItem.setUpdated(date);
        tbItemParamItem.setItemId(tbItem.getId());
        tbItemParamItem.setParamData(itemParams);
        Integer integer2 = this.commonItemFeignClient.updateItemParamItem(tbItemParamItem);

        return Result.ok();
    }


}
