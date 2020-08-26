package com.bjsxt.front.portal.service.impl;

import com.bjsxt.front.portal.feign.CommonItemFeignClient;
import com.bjsxt.front.portal.feign.CommonRedisFeginClient;
import com.bjsxt.front.portal.service.ItemCategoryService;
import com.bjsxt.utils.CatResult;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: liuxw
 * @Date: 2020-05-03
 * @Description: com.bjsxt.front.portal.service.impl
 * @version: 1.0
 */
@Service
public class ItemCategoryServiceImpl implements ItemCategoryService {

   @Autowired
   private CommonItemFeignClient commonItemFeignClient;

   @Autowired
   private CommonRedisFeginClient commonRedisFeginClient;
    @Override
    public Result selectItemCategoryAll() {
        //查询缓存
        try{
            CatResult catResult = this.commonRedisFeginClient.selectItemCategory();
            //判断缓存是否为空
            if (catResult!=null&&catResult.getData()!=null&&catResult.getData().size()>0    ){
                   return Result.ok(catResult);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //查询数据库
        CatResult catResult = this.commonItemFeignClient.selectItemCategoryAll();

        //添加到缓存
        try{
            if (catResult!=null&&catResult.getData()!=null&&catResult.getData().size()>0){
                this.commonRedisFeginClient.insertItemCategory(catResult);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        if (catResult!=null&&catResult.getData()!=null&&catResult.getData().size()>0){
            return Result.ok(catResult);
        }

        return Result.error("商品分类加载失败");
    }
}
