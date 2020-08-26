package com.bjsxt.common.redis.controller;

import com.bjsxt.common.redis.service.ContentService;
import com.bjsxt.common.redis.service.ItemCategoryService;
import com.bjsxt.utils.CatResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Auther: liuxw
 * @Date: 2020-05-04
 * @Description: com.bjsxt.common.redis.controller
 * @version: 1.0
 */
@RestController
@RequestMapping("/redis/content")
public class ContentController {



    @Autowired
    private ContentService contentService;


    //向redis中添加数据
    @RequestMapping("/insertContentAD")
    public void insertContentAD(@RequestBody List<Map> map){

        this.contentService.insertContent(map);
    }

    //查询Redis中的缓存的首页商品分类
    @RequestMapping("/selectContentAD")
    public List<Map>  selectContentAD(){
       return this.contentService.selectContentAD();
    }
}
