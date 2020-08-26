package com.bjsxt.item.service;

import com.bjsxt.pojo.TbItemDesc;

/**
 * @Auther: liuxw
 * @Date: 2020-04-21
 * @Description: com.bjsxt.item.service
 * @version: 1.0
 */
public interface ItemDescService {
    Integer insertItemDesc(TbItemDesc tbItemDesc);
    Integer updateItemDesc(TbItemDesc tbItemDesc);

    TbItemDesc selectItemDescByItemId(Long itemId);
}
