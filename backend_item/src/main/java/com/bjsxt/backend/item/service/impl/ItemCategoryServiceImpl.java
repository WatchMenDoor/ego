package com.bjsxt.backend.item.service.impl;

import com.bjsxt.backend.item.feign.CommonItemFeignClient;
import com.bjsxt.backend.item.service.ItemCategoryService;
import com.bjsxt.pojo.TbItemCat;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: liuxw
 * @Date: 2020-04-19
 * @Description: com.bjsxt.backend.item.service.impl
 * @version: 1.0
 */
@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

    @Autowired
    private CommonItemFeignClient commonItemFeignClient;

    //根据类目id查询子类目
    @Override
    public Result selectItemCategoryByParentId(Long id) {
        List<TbItemCat> list = this.commonItemFeignClient.selectItemCategoryByParentId(id);
        if (list!=null&&list.size()>0){
            return Result.ok(list);
        }
        return Result.error("查无结果");
    }
}