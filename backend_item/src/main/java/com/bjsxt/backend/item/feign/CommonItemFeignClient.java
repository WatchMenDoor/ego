package com.bjsxt.backend.item.feign;

import com.bjsxt.pojo.*;
import com.bjsxt.utils.PageResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @Auther: liuxw
 * @Date: 2020-04-19
 * @Description: com.bjsxt.backend.item.feign
 * @version: 1.0
 */
@FeignClient(value = "common-item")
public interface CommonItemFeignClient {

    //----------/service/item
    @RequestMapping("/service/item/selectTbItemAllByPage")
    PageResult selectTbItemAllByPage(@RequestParam("page") Integer page, @RequestParam("rows") Integer rows);

    //添加商品
    @RequestMapping("/service/item/insertTbItem")
    Integer insertTbItem(@RequestBody TbItem tbItem);
   //删除商品
   @PostMapping("/service/item/deleteItemById")
   Integer deleteItemById(@RequestBody TbItem tbItem);
   //预查询商品
    @RequestMapping("/preUpdateItem")
     Map<String,Object> preUpdateItem(@RequestParam("itemId") Long itemId);
    //更新商品
    @RequestMapping("/updateTbItem")
    public Integer updateTbItem(@RequestBody TbItem tbItem);

    //-----------/service/itemcategory
    @PostMapping("/service/itemCategory/selectItemCategoryByParentId")
    List<TbItemCat> selectItemCategoryByParentId(@RequestParam("id")Long id);

    //-----------/service/itemParam
    @PostMapping("/service/itemParam/selectItemParamByItemCatId")
    TbItemParam selectItemParamByItemCatId(@RequestParam("itemCatId") Long itemCatId);

    @RequestMapping("/selectItemParamAll")
    PageResult selectItemParamAll(@RequestParam("page") Integer page,@RequestParam("rows") Integer rows);

    @RequestMapping("/deleteItemParamById")
    Integer deleteItemParamById(@RequestParam("id") Long id);


    @RequestMapping("/insertItemParam")
    Integer insertItemParam(@RequestBody TbItemParam tbItemParam);

    //----------/service/itemtemDesc
    @RequestMapping("/service/itemDesc/insertItemDesc")
    Integer insertItemDesc(@RequestBody TbItemDesc tbItemDesc);

    @RequestMapping("/ItemDesc")
     Integer updateItemDesc(@RequestBody TbItemDesc tbItemDesc);

    //-----------/service/itemParamItem
    @RequestMapping("/service/itemParamItem/insertItemParamItem")
     Integer insertItemParamItem(@RequestBody TbItemParamItem tbItemParamItem);


    @RequestMapping("/updateItemParamItem")
     Integer updateItemParamItem(@RequestBody TbItemParamItem tbItemParamItem);

}
