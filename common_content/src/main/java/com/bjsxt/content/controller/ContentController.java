package com.bjsxt.content.controller;

import com.bjsxt.content.service.ContentService;
import com.bjsxt.pojo.TbContent;
import com.bjsxt.utils.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Auther: liuxw
 * @Date: 2020-05-03
 * @Description: com.bjsxt.content.controller
 * @version: 1.0
 */
@RestController
@RequestMapping("/service/content")
public class ContentController {

    @Autowired
    private ContentService contentService;


    @RequestMapping("/selectTbContentAllByCategoryId")
    public PageResult selectTbContentAllByCategoryId(@RequestParam Integer page,@RequestParam Integer rows,@RequestParam Long categoryId){
        return this.contentService.selectTbContentAllByCategoryId(page, rows, categoryId);
    }

    @RequestMapping("/insertTbContent")
    public Integer insertTbContent(@RequestBody TbContent tbContent){
        return this.contentService.insertTbContent(tbContent);
    }

    @RequestMapping("/deleteContentByIds")
    public Integer deleteContentByIds(@RequestParam Long id){
        return this.contentService.deleteContentByIds(id);
    }


    //查询首页大广告位
    @RequestMapping("/selectFrontendContentByAD")
    public List<Map> selectFrontendContentByAD(){
        return this.contentService.selectFrontendContentByAD();
    }

}
