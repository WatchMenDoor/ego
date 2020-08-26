package com.bjsxt.item.service;

import com.bjsxt.pojo.TbItemParamItem;

/**
 * @Auther: liuxw
 * @Date: 2020-04-21
 * @Description: com.bjsxt.item.service
 * @version: 1.0
 */
public interface ItemParamItemService {

    Integer insertItemParamItem(TbItemParamItem tbItemParamItem);
    Integer updateItemParamItem(TbItemParamItem tbItemParamItem);
    TbItemParamItem selectTbItemParamItemByItemId(Long itemId);
}
