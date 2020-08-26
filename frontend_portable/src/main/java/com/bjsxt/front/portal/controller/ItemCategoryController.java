package com.bjsxt.front.portal.controller;

import com.bjsxt.front.portal.service.ItemCategoryService;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: liuxw
 * @Date: 2020-05-03
 * @Description: com.bjsxt.front.portal
 * @version: 1.0
 */
@RestController
@RequestMapping("/frontend/itemCategory")
public class ItemCategoryController {

    @Autowired
    private ItemCategoryService itemCategoryService;


    @RequestMapping("/selectItemCategoryAll")
    public Result selectItemCategoryAll(){
        try{
            return this.itemCategoryService.selectItemCategoryAll();
        }catch (Exception e){

        }
        return Result.build(500, "error");
    }
}
