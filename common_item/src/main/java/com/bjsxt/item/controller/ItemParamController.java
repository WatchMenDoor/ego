package com.bjsxt.item.controller;

import com.bjsxt.item.service.ItemParamService;
import com.bjsxt.pojo.TbItemParam;
import com.bjsxt.pojo.TbItemParamItem;
import com.bjsxt.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: liuxw
 * @Date: 2020-04-20
 * @Description: com.bjsxt.item.controller
 * @version: 1.0
 */
//商品分类模板
    @RestController
    @RequestMapping("/service/itemParam")
public class ItemParamController {

        @Autowired
        private ItemParamService itemParamService;

        //根据商品id查询商品规格参数
    @RequestMapping("/selectItemParamByItemCatId")
    public TbItemParam selectItemParamByItemCatId(@RequestParam Long itemCatId){

        return itemParamService.selectItemParamByItemCatId(itemCatId);
    }


    //根据商品id查询商品规格参数
    @RequestMapping("/selectItemParamAll")
    public PageResult selectItemParamAll(@RequestParam Integer page,@RequestParam Integer rows){

        return this.itemParamService.selectItemParamAll(page, rows);
    }

    //根据商品分类添加商品规格参数
    @RequestMapping("/insertItemParam")
    public Integer insertItemParam(@RequestBody TbItemParam tbItemParam){

        return this.itemParamService.insertItemParam(tbItemParam);
    }

    //删除商品规格参数模板
    @RequestMapping("/deleteItemParamById")
    public Integer deleteItemParamById(@RequestParam Long id){

        return this.itemParamService.deleteItemParamById(id);
    }
}
