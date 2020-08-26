package com.bjsxt.backenr.content.controller;

import com.bjsxt.backenr.content.service.ContentCategoryService;
import com.bjsxt.pojo.TbContentCategory;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: liuxw
 * @Date: 2020-05-03
 * @Description: com.bjsxt.backenr.content.controller
 * @version: 1.0
 */
@RestController
@RequestMapping("/content")
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;



    //根据当前id查询子id
    @RequestMapping("/selectContentCategoryByParentId")
    public Result selectContentCategoryByParentId(@RequestParam(defaultValue = "0") Long id){
        try {

            return this.contentCategoryService.selectContentCategoryByParentId(id);
        }catch (Exception e){

        }
        return Result.build(500, "error");
    }



    //添加内容分类
    @RequestMapping("/insertContentCategory")
    public Result insertContentCategory( TbContentCategory tbContentCategory){
        try {

            return this.contentCategoryService.insertContentCategory(tbContentCategory);
        }catch (Exception e){

        }
        return Result.build(500, "error");
    }

    //删除内容分类
    @RequestMapping("/deleteContentCategoryById")
    public Result deleteContentCategoryById(@RequestParam Long categoryId){
        try {

            return this.contentCategoryService.deleteContentCategoryById(categoryId);
        }catch (Exception e){

        }
        return Result.build(500, "error");
    }

    //修改内容分类
    @RequestMapping("/updateContentCategory")
    public Result updateContentCategory( TbContentCategory tbContentCategory){
        try {

            return this.contentCategoryService.updateContentCategory(tbContentCategory);
        }catch (Exception e){

        }
        return Result.build(500, "error");
    }
}
