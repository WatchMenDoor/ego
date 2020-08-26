package com.bjsxt.backenr.content.controller;

import com.bjsxt.backenr.content.service.ContentService;
import com.bjsxt.pojo.TbContent;
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
public class ContentController {



    @Autowired
    private ContentService contentService;

    @RequestMapping("/selectTbContentAllByCategoryId")
    public Result selectTbContentAllByCategoryId(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "30") Integer rows, Long categoryId){
        try {

            return this.contentService.selectTbContentAllByCategoryId(page, rows, categoryId);
        }catch (Exception e){

        }
        return Result.build(500, "error");
    }

    @RequestMapping("/insertTbContent")
    public Result insertTbContent(TbContent tbContent){
        try {

            return this.contentService.insertTbContent(tbContent);
        }catch (Exception e){

        }
        return Result.build(500, "error");
    }

    @RequestMapping("/deleteContentByIds")
    public Result deleteContentByIds(Long ids){
        try {

            return this.contentService.deleteContentByIds(ids);
        }catch (Exception e){

        }
        return Result.build(500, "error");
    }
}
