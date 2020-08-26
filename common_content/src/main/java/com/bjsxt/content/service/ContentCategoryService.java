package com.bjsxt.content.service;

import com.bjsxt.pojo.TbContentCategory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Auther: liuxw
 * @Date: 2020-05-03
 * @Description: com.bjsxt.content.service
 * @version: 1.0
 */
public interface ContentCategoryService {

    List<TbContentCategory> selectContentCategoryByParentId( Long parentId);


    Integer insertContentCategory( TbContentCategory tbContentCategory);

    Integer deleteContentCategoryById(Long categoryId);

    Integer updateContentCategory(TbContentCategory tbContentCategory);
}
