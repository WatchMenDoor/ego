package com.bjsxt.common.redis.service.impl;

import com.bjsxt.common.redis.service.ItemService;
import com.bjsxt.pojo.TbItem;
import com.bjsxt.pojo.TbItemDesc;
import com.bjsxt.pojo.TbItemParamItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @Auther: liuxw
 * @Date: 2020-05-06
 * @Description: com.bjsxt.common.redis.service.impl
 * @version: 1.0
 */
/*
* 缓存商品业务层*/
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Value("${frontend_item_basic_info_key}")
    private String frontend_item_basic_info_key;

    @Value("${frontend_item_basic_info_key}")
    private String frontend_item_desc_info_key;

    @Value("${frontend_item_basic_info_key}")
    private String frontend_item_param_item_info_key;

    //缓存商品基本信息
    @Override
    public void insertItemBasicInfo(TbItem tbItem) {
        this.redisTemplate.opsForValue().set(this.frontend_item_basic_info_key+":"+tbItem.getId(), tbItem);

    }


    //查询缓存中的商品基本信息
    @Override
    public TbItem selectItemBasicInfo(Long tbItemId) {
        return (TbItem) this.redisTemplate.opsForValue().get(this.frontend_item_basic_info_key+":"+tbItemId);
    }
    //缓存商品描述信息
    @Override
    public void insertItemDescInfo(TbItemDesc tbItemDesc) {
       this.redisTemplate.opsForValue().set(this.frontend_item_desc_info_key+":"+tbItemDesc.getItemId(), tbItemDesc);
    }

    //查询缓存中的商品描述信息
    @Override
    public TbItemDesc selectItemDescInfo(Long tbItemId) {
        return (TbItemDesc) this.redisTemplate.opsForValue().get(this.frontend_item_desc_info_key+":"+tbItemId);
    }

    //缓存商品规格参数信息
    @Override
    public void insertItemParamItemInfo(TbItemParamItem tbItemParamItem) {
       this.redisTemplate.opsForValue().set(this.frontend_item_param_item_info_key+":"+tbItemParamItem.getItemId(), tbItemParamItem);
    }
    //查询缓存中的商品规格参数信息
    @Override
    public TbItemParamItem selectItemParamItemInfo(Long tbItemId) {
        return (TbItemParamItem) this.redisTemplate.opsForValue().get(this.frontend_item_param_item_info_key+":"+tbItemId);
    }
}
