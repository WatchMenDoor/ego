package com.bjsxt.front.portal.feign;

import com.bjsxt.pojo.TbItem;
import com.bjsxt.pojo.TbItemDesc;
import com.bjsxt.pojo.TbItemParamItem;
import com.bjsxt.utils.CatResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @Auther: liuxw
 * @Date: 2020-05-04
 * @Description: com.bjsxt.front.portal.feign
 * @version: 1.0
 */
@FeignClient("common-redis")
public interface CommonRedisFeginClient {

    //----------------/redis/itemCategory
    @RequestMapping("/redis/itemCategory/insertItemCategory")
    public void insertItemCategory(@RequestBody CatResult catResult);

    @RequestMapping("/redis/itemCategory/selectItemCategory")
    public CatResult selectItemCategory();


    //--------------------/redis/content
    @RequestMapping("/redis/content/insertContentAD")
   void insertContentAD(@RequestBody List<Map> map);

    @RequestMapping("/redis/content/selectContentAD")
    public List<Map>  selectContentAD();


    //------------------/redis/item
    @RequestMapping("/redis/item/insertItemBasicInfo")
    void insertItemBasicInfo(@RequestBody TbItem tbItem);

    @RequestMapping("/redis/item/selectItemBasicInfo")
     TbItem selectItemBasicInfo(@RequestParam("tbItemId") Long tbItemId);

    @RequestMapping("/redis/item/insertItemDescInfo")
     void insertItemDescInfo(@RequestBody TbItemDesc tbItemDesc);

    @RequestMapping("/redis/item/selectItemDescInfo")
     TbItemDesc selectItemDescInfo(@RequestParam("tbItemId") Long tbItemId);


    @RequestMapping("/redis/item/insertItemParamItemInfo")
    void insertItemParamItemInfo(@RequestBody TbItemParamItem tbItemParamItem);
    @RequestMapping("/redis/item/selectItemParamItemInfo")
    public TbItemParamItem selectItemParamItemInfo(@RequestParam("tbItemId") Long tbItemId);

}
