package com.bjsxt.backend.item.controller;

import com.bjsxt.backend.item.service.ItemCategoryService;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: liuxw
 * @Date: 2020-04-19
 * @Description: com.bjsxt.backend.item.controller
 * @version: 1.0
 */
//商品类目controller
@RequestMapping("/backend/itemCategory")
@RestController
public class ItemCategoryController {

    @Autowired
    private ItemCategoryService itemCategoryService;

    //查询商品类目的子节点
    @RequestMapping("/selectItemCategoryByParentId")
    public Result selectItemCategoryByParentId(@RequestParam(value = "id",defaultValue = "0") Long id){
        try {

            return this.itemCategoryService.selectItemCategoryByParentId(id);
        }catch (Exception e){

        }
        return Result.build(500, "error");
    }
}
