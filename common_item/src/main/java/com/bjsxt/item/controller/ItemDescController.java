package com.bjsxt.item.controller;

import com.bjsxt.item.service.ItemDescService;
import com.bjsxt.pojo.TbItemDesc;
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
//商品描述
    @RestController
    @RequestMapping("/service/itemDesc")
public class ItemDescController {



        @Autowired
        private ItemDescService itemDescService;
        //添加商品描述
        @RequestMapping("/insertItemDesc")
    public Integer insertItemDesc(@RequestBody TbItemDesc tbItemDesc){
            return this.itemDescService.insertItemDesc(tbItemDesc);

        }

        //更新商品描述
        @RequestMapping("/ItemDesc")
        public Integer updateItemDesc(@RequestBody TbItemDesc tbItemDesc){
            return this.itemDescService.updateItemDesc(tbItemDesc);

        }

        //根据商品id查询商品介绍
        @RequestMapping("/selectItemDescByItemId")
    public TbItemDesc selectItemDescByItemId(@RequestParam Long itemId){
        return this.itemDescService.selectItemDescByItemId(itemId);
        }
}
