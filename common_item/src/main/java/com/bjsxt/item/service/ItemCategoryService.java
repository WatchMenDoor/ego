package com.bjsxt.item.service;

import com.bjsxt.pojo.TbItemCat;
import com.bjsxt.utils.CatResult;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Auther: liuxw
 * @Date: 2020-04-19
 * @Description: com.bjsxt.item.service
 * @version: 1.0
 */
public interface ItemCategoryService {
    List<TbItemCat> selectItemCategoryByParentId(@RequestParam Long id);

    CatResult selectItemCategoryAll();
}
