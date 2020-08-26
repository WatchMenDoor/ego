package com.bjsxt.backenr.content.service.impl;

import com.bjsxt.backenr.content.fegin.CommonContentFeignClient;
import com.bjsxt.backenr.content.service.ContentService;
import com.bjsxt.pojo.TbContent;
import com.bjsxt.utils.PageResult;
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
public class ContentServiceImpl implements ContentService {
    @Autowired
    private CommonContentFeignClient commonContentFeignClient;

    @Override
    public Result selectTbContentAllByCategoryId(Integer page, Integer rows, Long categoryId) {
        PageResult result = this.commonContentFeignClient.selectTbContentAllByCategoryId(page, rows, categoryId);
        if (result!=null&&result.getResult().size()>0){
            return Result.ok(result);
        }
        return Result.error("商品内容查询失败");

    }

    @Override
    public Result insertTbContent(TbContent tbContent) {
        Integer integer = this.commonContentFeignClient.insertTbContent(tbContent);
        if (integer!=null){
            return Result.ok();
        }
        return Result.error("商品内容添加失败");
    }

    @Override
    @LcnTransaction
    public Result deleteContentByIds(Long ids) {
        Integer integer = this.commonContentFeignClient.deleteContentByIds(ids);

        if (integer!=null){
            return Result.ok();
        }
        return Result.error("商品内容删除失败");
    }
}
