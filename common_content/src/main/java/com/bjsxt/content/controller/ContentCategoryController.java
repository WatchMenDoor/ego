package com.bjsxt.content.controller;

import com.bjsxt.content.service.ContentCategoryService;
import com.bjsxt.pojo.TbContentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: liuxw
 * @Date: 2020-05-03
 * @Description: com.bjsxt.content.controller
 * @version: 1.0
 */
@RestController
@RequestMapping("/service/contentCategoryController")
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    //根据父节点id查找子节点
    @RequestMapping("/selectContentCategoryByParentId")
    public List<TbContentCategory> selectContentCategoryByParentId(@RequestParam Long parentId){
        return this.contentCategoryService.selectContentCategoryByParentId(parentId);
    }


    //添加内容分类
    @RequestMapping("/insertContentCategory")
    public Integer insertContentCategory(@RequestBody TbContentCategory tbContentCategory){
        return this.contentCategoryService.insertContentCategory(tbContentCategory);
    }

    //删除商品分类
    @RequestMapping("/deleteContentCategoryById")
    public Integer deleteContentCategoryById(@RequestParam Long categoryId){
        return this.contentCategoryService.deleteContentCategoryById(categoryId);
    }

    //修改商品分类
    @RequestMapping("/updateContentCategory")
    public Integer updateContentCategory(@RequestBody TbContentCategory tbContentCategory){
        return this.contentCategoryService.updateContentCategory(tbContentCategory);
    }
}
