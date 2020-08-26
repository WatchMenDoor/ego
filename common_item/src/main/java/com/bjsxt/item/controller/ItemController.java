package com.bjsxt.item.controller;

import com.bjsxt.item.service.ItemService;
import com.bjsxt.pojo.TbItem;
import com.bjsxt.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Auther: liuxw
 * @Date: 2020-04-19
 * @Description: com.bjsxt.item.controller
 * @version: 1.0
 */
@RestController
@RequestMapping("/service/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /*查询商品数据*/
    @RequestMapping("/selectTbItemAllByPage")
    public PageResult selectTbItemAllByPage(@RequestParam Integer page,@RequestParam Integer rows){
        return itemService.selectTbItemAllByPage(page, rows);
    }


    //商品添加
    @RequestMapping("/insertTbItem")
    public Integer insertTbItem(@RequestBody TbItem tbItem){
        return this.itemService.insertTbItem(tbItem);
    }

    //删除商品
    @RequestMapping("/deleteItemById")
    public Integer deleteItemById(@RequestBody TbItem tbItem){
        return this.itemService.updateTbItemById(tbItem);

    }

    //根据商品id查询商品分类，商品描述，商品参数
    @RequestMapping("/preUpdateItem")
    public Map<String,Object> preUpdateItem(Long itemId){
        return this.itemService.preUpdateItem(itemId);

    }

    //更新商品
    @RequestMapping("/updateTbItem")
    public Integer updateTbItem(@RequestBody TbItem tbItem){

        return this.itemService.updateTbItemById(tbItem);

    }

    //根据商品id查询商品
    @RequestMapping("/selectItemInfo")
    public TbItem selectItemInfo(@RequestParam Long itemId){
        return this.itemService.selectItemInfo(itemId);
    }
}
