package com.bjsxt.backend.item.controller;

import com.bjsxt.backend.item.service.ItemService;
import com.bjsxt.pojo.TbItem;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Auther: liuxw
 * @Date: 2020-04-19
 * @Description: com.bjsxt.backend.item.controller
 * @version: 1.0
 */
//商品
@RestController
@RequestMapping("/backend/item")
public class ItemController {


    @Autowired
    private ItemService itemService;


    //查询商品数据
    @RequestMapping("/selectTbItemAllByPage")
    public Result selectTbItemAllByPage(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "2") Integer rows){
        try {

            return this.itemService.selectTbItemAllByPage(page, rows);
        }catch (Exception e){

        }
        return Result.build(500, "error");
    }


    //添加商品
    @RequestMapping("/insertTbItem")
    public Result insertTbItem(TbItem tbItem,String desc,String itemParams){
        try {

            return this.itemService.insertTbItem(tbItem, desc, itemParams);
        }catch (Exception e){

        }
        return Result.build(500, "error");
    }
    //删除商品
    @RequestMapping("/deleteItemById")
    public Result deleteItemById(Long tbItemid){
        try {

            return this.itemService.updateItemById(tbItemid);
        }catch (Exception e){

        }
        return Result.build(500, "error");
    }

    //预查询商品
    @PostMapping("/preUpdateItem")
    public Result preUpdateItem(Long tbItemid){
        try {

            return this.itemService.preUpdateItem(tbItemid);
        }catch (Exception e){

        }
        return Result.build(500, "error");
    }
    //更新商品
    @RequestMapping("/updateTbItem")
    public Result updateTbItem(TbItem tbItem,String desc,String itemParams){
        try {

            return this.itemService.updateItem(tbItem, desc, itemParams);
        }catch (Exception e){

        }
        return Result.build(500, "error");
    }
}
