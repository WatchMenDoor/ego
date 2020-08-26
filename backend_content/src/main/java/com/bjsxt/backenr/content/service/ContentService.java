package com.bjsxt.backenr.content.service;

import com.bjsxt.pojo.TbContent;
import com.bjsxt.utils.Result;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Auther: liuxw
 * @Date: 2020-05-03
 * @Description: com.bjsxt.backenr.content.service
 * @version: 1.0
 */
public interface ContentService {

    Result selectTbContentAllByCategoryId(Integer page, Integer rows, Long categoryId);

    Result insertTbContent(TbContent tbContent);

    Result deleteContentByIds(Long ids);
}
