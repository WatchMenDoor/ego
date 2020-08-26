package com.bjsxt.content.service;

import com.bjsxt.pojo.TbContent;
import com.bjsxt.utils.PageResult;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @Auther: liuxw
 * @Date: 2020-05-03
 * @Description: com.bjsxt.content.service
 * @version: 1.0
 */
public interface ContentService {
    PageResult selectTbContentAllByCategoryId(Integer page, Integer rows, Long categoryId);

    Integer insertTbContent(TbContent tbContent);

    Integer deleteContentByIds( Long id );

    List<Map> selectFrontendContentByAD();
}
