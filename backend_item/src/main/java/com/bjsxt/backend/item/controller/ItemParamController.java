package com.bjsxt.backend.item.controller;

import com.bjsxt.backend.item.service.ItemParamService;
import com.bjsxt.pojo.TbItemParam;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: liuxw
 * @Date: 2020-04-20
 * @Description: com.bjsxt.backend.item.controller
 * @version: 1.0
 */
@RestController
@RequestMapping("/backend/itemParam/")
public class ItemParamController {

    @Autowired
    private ItemParamService itemParamService;

   //根据商品分类id查询分类模板
    @RequestMapping("/selectItemParamByItemCatId/{itemCatId}")
    public Result selectItemParamByItemCatId(@PathVariable Long itemCatId){

        try {

            return this.itemParamService.selectItemParamByItemCatId(itemCatId);
        }catch (Exception e){

        }
        return Result.build(500, "error");

    }

    //根据商品分类id查询所有分类模板
    @RequestMapping("/selectItemParamAll")
    public Result selectItemParamAll(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "3") Integer rows){

        try {

            return this.itemParamService.selectItemParamAll(page, rows);
        }catch (Exception e){

        }
        return Result.build(500, "error");

    }

    //根据商品分类id添加分类模板
    @RequestMapping("/insertItemParam")
    public Result insertItemParam(Long itemCatId,String paramData){

        try {

            return this.itemParamService.insertItemParam(itemCatId, paramData);
        }catch (Exception e){

        }
        return Result.build(500, "error");

    }


    //根据id删除分类模板
    @RequestMapping("/deleteItemParamById")
    public Result deleteItemParamById(Long id){

        try {

            return this.itemParamService.deleteItemParamById(id);
        }catch (Exception e){

        }
        return Result.build(500, "error");

    }
}
