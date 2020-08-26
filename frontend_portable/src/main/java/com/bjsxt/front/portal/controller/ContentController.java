package com.bjsxt.front.portal.controller;

import com.bjsxt.front.portal.service.ContentService;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: liuxw
 * @Date: 2020-05-03
 * @Description: com.bjsxt.front.portal.controller
 * @version: 1.0
 */
@RestController
@RequestMapping("/frontend/content")
public class ContentController {


    @Autowired
    private ContentService contentService;

    @RequestMapping("/selectFrontendContentByAD")
    public Result selectFrontendContentByAD(){
        try{
            return this.contentService.selectFrontendContentByAD();
        }catch (Exception e){

        }
        return Result.build(500, "error");
    }
}
