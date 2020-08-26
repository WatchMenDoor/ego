package com.bjsxt.common.redis.controller;

import com.bjsxt.common.redis.service.ItemCategoryService;
import com.bjsxt.utils.CatResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: liuxw
 * @Date: 2020-05-04
 * @Description: com.bjsxt.common.redis.controller
 * @version: 1.0
 */
/*
* 缓存商品首页分类
* */
@RestController
@RequestMapping("/redis/itemCategory")
public class ItemCategoryController {

    @Autowired
    private ItemCategoryService itemCategoryService;

    //向redis中添加首页商品分类
    @RequestMapping("/insertItemCategory")
    public void insertItemCategory(@RequestBody CatResult catResult){
         this.itemCategoryService.insertItemCategory(catResult);

    }

    //查询Redis中的缓存的首页商品分类
    @RequestMapping("/selectItemCategory")
    public CatResult selectItemCategory(){
        return this.itemCategoryService.selectItemCategory();
    }
}
