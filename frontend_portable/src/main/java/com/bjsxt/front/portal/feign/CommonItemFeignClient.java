package com.bjsxt.front.portal.feign;

import com.bjsxt.pojo.TbItem;
import com.bjsxt.pojo.TbItemDesc;
import com.bjsxt.pojo.TbItemParamItem;
import com.bjsxt.utils.CatResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @Auther: liuxw
 * @Date: 2020-05-03
 * @Description: com.bjsxt.front.portal.feign
 * @version: 1.0
 */
@FeignClient(value = "common-item")
public interface CommonItemFeignClient {

    //--------------------/service/itemCategory
    @RequestMapping("/service/itemCategory/selectItemCategoryAll")
     CatResult selectItemCategoryAll();

   //--------------------/service/item
    @RequestMapping("/service/item/selectItemInfo")
    TbItem selectItemInfo(@RequestParam("itemId") Long itemId);

   //----------------/service/itemDesc
    @RequestMapping("/service/itemDesc/selectItemDescByItemId")
    TbItemDesc selectItemDescByItemId(@RequestParam("itemId") Long itemId);

    //----------------------/service/itemParamItem
    @RequestMapping("/service/itemParamItem/selectTbItemParamItemByItemId")
    TbItemParamItem selectTbItemParamItemByItemId(@RequestParam("itemId")Long itemId);
}
