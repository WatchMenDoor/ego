package com.bjsxt.item.controller;

import com.bjsxt.item.service.ItemParamItemService;
import com.bjsxt.item.service.ItemParamService;
import com.bjsxt.pojo.TbItemParamItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: liuxw
 * @Date: 2020-04-21
 * @Description: com.bjsxt.item.controller
 * @version: 1.0
 */
@RestController
@RequestMapping("/service/itemParamItem")
public class ItemParamItemController {

    @Autowired
    private ItemParamItemService itemParamItemService;

    //添加商品规格参数
    @RequestMapping("/insertItemParamItem")
    public Integer insertItemParamItem(@RequestBody TbItemParamItem tbItemParamItem) {
        return this.itemParamItemService.insertItemParamItem(tbItemParamItem);
    }

    //更新商品规格参数
    @RequestMapping("/updateItemParamItem")
    public Integer updateItemParamItem(@RequestBody TbItemParamItem tbItemParamItem) {
        return this.itemParamItemService.updateItemParamItem(tbItemParamItem);
    }

    //根据商品id查询商品规格参数
    @RequestMapping("/selectTbItemParamItemByItemId")
    public TbItemParamItem selectTbItemParamItemByItemId(@RequestParam Long itemId){
         return this.itemParamItemService.selectTbItemParamItemByItemId(itemId);
    }
}
