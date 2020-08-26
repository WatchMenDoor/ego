package com.bjsxt.backenr.content.service;

import com.bjsxt.pojo.TbContentCategory;
import com.bjsxt.utils.Result;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Auther: liuxw
 * @Date: 2020-05-03
 * @Description: com.bjsxt.backenr.content.service
 * @version: 1.0
 */
public interface ContentCategoryService {

    Result selectContentCategoryByParentId(Long id);

    Result insertContentCategory( TbContentCategory tbContentCategory);

    Result deleteContentCategoryById(Long categoryId);

    Result updateContentCategory( TbContentCategory tbContentCategory);
}
