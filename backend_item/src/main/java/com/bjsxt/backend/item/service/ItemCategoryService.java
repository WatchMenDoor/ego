package com.bjsxt.backend.item.service;

import com.bjsxt.utils.Result;

/**
 * @Auther: liuxw
 * @Date: 2020-04-19
 * @Description: com.bjsxt.backend.item.service
 * @version: 1.0
 */
public interface ItemCategoryService {
    Result selectItemCategoryByParentId(Long id);
}
