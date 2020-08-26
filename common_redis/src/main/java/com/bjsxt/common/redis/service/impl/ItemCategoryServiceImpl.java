package com.bjsxt.common.redis.service.impl;

import com.bjsxt.common.redis.service.ItemCategoryService;
import com.bjsxt.utils.CatResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Auther: liuxw
 * @Date: 2020-05-04
 * @Description: com.bjsxt.common.redis.service.impl
 * @version: 1.0
 */
/*
* 缓存商品首页
* */
@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;



    @Value("${frontend_catresult_redis_key}")
    private String frontend_catresult_redis_key;

    /*添加商品分类*/
    @Override
    public void insertItemCategory(CatResult catResult) {
          this.redisTemplate.opsForValue().set(this.frontend_catresult_redis_key, catResult);
    }


    /*查询分类*/
    @Override
    public CatResult selectItemCategory() {
        return (CatResult)this.redisTemplate.opsForValue().get(this.frontend_catresult_redis_key);
    }
}
