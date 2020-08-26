package com.bjsxt.front.portal.service.impl;

import com.bjsxt.front.portal.feign.CommonItemFeignClient;
import com.bjsxt.front.portal.feign.CommonRedisFeginClient;
import com.bjsxt.front.portal.service.ItemService;
import com.bjsxt.pojo.TbItem;
import com.bjsxt.pojo.TbItemDesc;
import com.bjsxt.pojo.TbItemParamItem;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: liuxw
 * @Date: 2020-05-05
 * @Description: com.bjsxt.front.portal.service.impl
 * @version: 1.0
 */
@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private CommonItemFeignClient commonItemFeignClient;

    @Autowired
    private CommonRedisFeginClient commonRedisFeginClient;
    @Override
    public Result selectItemInfo(Long itemId) {
        //从缓存中查找
        try{
            TbItem tbItem = this.commonRedisFeginClient.selectItemBasicInfo(itemId);
            if (tbItem!=null){
                return Result.ok(tbItem);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        TbItem tbItem = this.commonItemFeignClient.selectItemInfo(itemId);
        //插入缓存
        try{

            if (tbItem!=null){
                this.commonRedisFeginClient.insertItemBasicInfo(tbItem);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if (tbItem!=null){
            return Result.ok(tbItem);
        }
        return Result.error("商品基本信息查询失败");
    }


    //查询商品介绍
    @Override
    public Result selectItemDescByItemId(Long itemId) {

        //从缓存中查找
        try{
            TbItemDesc tbItemDesc = this.commonRedisFeginClient.selectItemDescInfo(itemId);
            if (tbItemDesc!=null){
                return Result.ok(tbItemDesc);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        TbItemDesc tbItemDesc = this.commonItemFeignClient.selectItemDescByItemId(itemId);
        //放入缓存
        try{

            if (tbItemDesc!=null){
                this.commonRedisFeginClient.insertItemDescInfo(tbItemDesc);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if (tbItemDesc!=null){
            return Result.ok(tbItemDesc);
        }
        return Result.error("商品描述信息查询失败");
    }


    //查询商品规格参数
    @Override
    public Result selectTbItemParamItemByItemId(Long itemId) {
        //从缓存中查找
        try{
            TbItemParamItem tbItemParamItem = this.commonRedisFeginClient.selectItemParamItemInfo(itemId);
            if (tbItemParamItem!=null){
                return Result.ok(tbItemParamItem);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        TbItemParamItem tbItemParamItem = this.commonItemFeignClient.selectTbItemParamItemByItemId(itemId);
        //放入缓存
        try{

            if (tbItemParamItem!=null){
                this.commonRedisFeginClient.insertItemParamItemInfo(tbItemParamItem);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        if (tbItemParamItem!=null){
            return Result.ok(tbItemParamItem);
        }
        return Result.error("商品规格参数信息查询失败");
    }
}
