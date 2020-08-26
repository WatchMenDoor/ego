package com.bjsxt.front.portal.controller;

import com.bjsxt.front.portal.service.ItemService;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: liuxw
 * @Date: 2020-05-05
 * @Description: com.bjsxt.front.portal.controller
 * @version: 1.0
 */
@RestController
@RequestMapping("/frontend/item")
public class ItemController {


    @Autowired
    private ItemService itemService;

    //查询商品信息
    @RequestMapping("/selectItemInfo")
    public Result selectItemInfo(Long itemId){
        try{
           return this.itemService.selectItemInfo(itemId);
        }catch (Exception e){
            e.printStackTrace();
        }
       return Result.build(500,"error" );
    }

    //查询商品描述
    @RequestMapping("/selectItemDescByItemId")
    public Result  selectItemDescByItemId(Long itemId){
        try{
            return this.itemService.selectItemDescByItemId(itemId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error" );
    }

    //查询商品规格参数
    @RequestMapping("/selectTbItemParamItemByItemId")
    public Result  selectTbItemParamItemByItemId(Long itemId){
        try{
            return this.itemService.selectTbItemParamItemByItemId(itemId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Result.build(500,"error" );
    }
}
