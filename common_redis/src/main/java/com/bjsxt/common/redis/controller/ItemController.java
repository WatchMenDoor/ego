package com.bjsxt.common.redis.controller;

import com.bjsxt.common.redis.service.ItemService;
import com.bjsxt.pojo.TbItem;
import com.bjsxt.pojo.TbItemDesc;
import com.bjsxt.pojo.TbItemParamItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: liuxw
 * @Date: 2020-05-06
 * @Description: com.bjsxt.common.redis.controller
 * @version: 1.0
 */
@RestController
@RequestMapping("/redis/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /*将商品基本信息加入缓存*/
    @RequestMapping("/insertItemBasicInfo")
    public void insertItemBasicInfo(@RequestBody TbItem tbItem){
        this.itemService.insertItemBasicInfo(tbItem);
    }

    //查询缓存中的商品基本信息
    @RequestMapping("/selectItemBasicInfo")
    public TbItem selectItemBasicInfo(@RequestParam Long tbItemId){

        return this.itemService.selectItemBasicInfo(tbItemId);
    }

    /*将商品描述信息加入缓存*/
    @RequestMapping("/insertItemDescInfo")
    public void insertItemDescInfo(@RequestBody TbItemDesc tbItemDesc){
        this.itemService.insertItemDescInfo(tbItemDesc);
    }

    //查询缓存中的商品描述信息
    @RequestMapping("/selectItemDescInfo")
    public TbItemDesc selectItemDescInfo(@RequestParam Long tbItemId){

        return this.itemService.selectItemDescInfo(tbItemId);
    }

    /*将商品规格参数信息加入缓存*/
    @RequestMapping("/insertItemParamItemInfo")
    public void insertItemParamItemInfo(@RequestBody TbItemParamItem tbItemParamItem){
        this.itemService.insertItemParamItemInfo(tbItemParamItem);
    }

    //查询缓存中的商品规格参数信息
    @RequestMapping("/selectItemParamItemInfo")
    public TbItemParamItem selectItemParamItemInfo(@RequestParam Long tbItemId){

        return this.itemService.selectItemParamItemInfo(tbItemId);
    }
}
