package com.bjsxt.backend.item.service.impl;

import com.bjsxt.backend.item.feign.CommonItemFeignClient;
import com.bjsxt.backend.item.service.ItemParamService;
import com.bjsxt.pojo.TbItemParam;
import com.bjsxt.utils.PageResult;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Auther: liuxw
 * @Date: 2020-04-20
 * @Description: com.bjsxt.backend.item.service.impl
 * @version: 1.0
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {


    @Autowired
    private CommonItemFeignClient commonItemFeignClient;
    @Override
    public Result selectItemParamByItemCatId(Long itemCatId) {
        TbItemParam tbItemParam = this.commonItemFeignClient.selectItemParamByItemCatId(itemCatId);
        if (tbItemParam!=null){
            return Result.ok(tbItemParam);
        }
        return Result.error("查无数据");
    }


    //查询所有规格参数模板
    @Override
    public Result selectItemParamAll(Integer page, Integer rows) {

        PageResult result = this.commonItemFeignClient.selectItemParamAll(page, rows);
        if (result!=null&&result.getResult().size()>0){
            return Result.ok(result);
        }
        return Result.error("商品规格参数加载失败");
    }

    //添加商品规格参数
    @Override
    public Result insertItemParam(Long itemCatId,String paramData) {
        TbItemParam tbItemParam = new TbItemParam();
        Date  date = new Date();
        tbItemParam.setItemCatId(itemCatId);
        tbItemParam.setParamData(paramData);
        tbItemParam.setCreated(date);
        tbItemParam.setUpdated(date);
        Integer integer = this.commonItemFeignClient.insertItemParam(tbItemParam);
        if (integer!=null){
            return Result.ok();
        }
        return Result.error("商品规格参数添加失败");
    }
    //删除商品规格参数
    @Override
    public Result deleteItemParamById(Long id) {
        Integer integer = this.commonItemFeignClient.deleteItemParamById(id);
        if (integer!=null&&integer>0){
            return Result.ok();
        }
        return Result.error("商品规格参数删除失败");
    }


}
