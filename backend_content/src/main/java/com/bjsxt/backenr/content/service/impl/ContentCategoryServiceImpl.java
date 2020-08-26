package com.bjsxt.backenr.content.service.impl;

import com.bjsxt.backenr.content.fegin.CommonContentFeignClient;
import com.bjsxt.backenr.content.service.ContentCategoryService;
import com.bjsxt.pojo.TbContentCategory;
import com.bjsxt.utils.Result;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: liuxw
 * @Date: 2020-05-03
 * @Description: com.bjsxt.backenr.content.service.impl
 * @version: 1.0
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private CommonContentFeignClient commonContentFeignClient;



    @Override
    public Result selectContentCategoryByParentId(Long id) {
        List<TbContentCategory> list = this.commonContentFeignClient.selectContentCategoryByParentId(id);
        if(list!=null&&list.size()>0){
            return Result.ok(list);
        }
        return Result.error("商品分类查询失败");
    }

    @Override
    @LcnTransaction
    public Result insertContentCategory(TbContentCategory tbContentCategory) {
        Integer integer = this.commonContentFeignClient.insertContentCategory(tbContentCategory);
        if (integer!=null){
            return Result.ok();
        }
        return Result.error("商品分类添加失败");
    }


    //删除内容分类
    @Override
    public Result deleteContentCategoryById(Long categoryId) {
        Integer integer = this.commonContentFeignClient.deleteContentCategoryById(categoryId);

        if (integer==200){
            return Result.ok();
        }
        return Result.error("商品内容分类删除失败");
    }


    //修改商品分类
    @Override
    public Result updateContentCategory(TbContentCategory tbContentCategory) {
        Integer integer = this.commonContentFeignClient.updateContentCategory(tbContentCategory);
        if (integer!=null){
            return Result.ok();
        }
        return Result.error("商品分类更新失败");
    }
}
