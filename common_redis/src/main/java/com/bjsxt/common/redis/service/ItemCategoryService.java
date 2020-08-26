package com.bjsxt.common.redis.service;

import com.bjsxt.utils.CatResult;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Auther: liuxw
 * @Date: 2020-05-04
 * @Description: com.bjsxt.common.redis.service
 * @version: 1.0
 */
public interface ItemCategoryService {
    void insertItemCategory(CatResult catResult);

    CatResult selectItemCategory();
}
