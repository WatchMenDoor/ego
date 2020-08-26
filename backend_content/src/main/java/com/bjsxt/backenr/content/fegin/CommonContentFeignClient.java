package com.bjsxt.backenr.content.fegin;

import com.bjsxt.pojo.TbContent;
import com.bjsxt.pojo.TbContentCategory;
import com.bjsxt.utils.PageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Auther: liuxw
 * @Date: 2020-05-03
 * @Description: com.bjsxt.backenr.content.fegin
 * @version: 1.0
 */
@FeignClient(value = "common-content")
public interface CommonContentFeignClient {


    //-------------------/service/contentcategory
    @RequestMapping("/service/contentCategoryController/selectContentCategoryByParentId")
    List<TbContentCategory> selectContentCategoryByParentId(@RequestParam("parentId") Long parentId);

    @RequestMapping("/service/contentcategory/insertContentCategory")
    Integer insertContentCategory(@RequestBody TbContentCategory tbContentCategory);

    @RequestMapping("/service/contentcategory/deleteContentCategoryById")
     Integer deleteContentCategoryById(@RequestParam("categoryId") Long categoryId);

    @RequestMapping("/service/contentcategory/updateContentCategory")
    Integer updateContentCategory(@RequestBody TbContentCategory tbContentCategory);


    //---------------------------/service/content
    @RequestMapping("/service/content/selectTbContentAllByCategoryId")
     PageResult selectTbContentAllByCategoryId(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows, @RequestParam("categoryId") Long categoryId);

    @RequestMapping("/service/content/insertTbContent")
     Integer insertTbContent(@RequestBody TbContent tbContent);

    @RequestMapping("/service/content/deleteContentByIds")
    Integer deleteContentByIds(@RequestParam("id") Long id);
}
