package com.bjsxt.backend.item.service;

import com.bjsxt.pojo.TbItem;
import com.bjsxt.utils.Result;

import java.util.Map;

/**
 * @Auther: liuxw
 * @Date: 2020-04-19
 * @Description: com.bjsxt.backend.item.service
 * @version: 1.0
 */
public interface ItemService {

    Result selectTbItemAllByPage(Integer page,Integer rows);

    Result insertTbItem(TbItem tbItem,String desc,String itemParams);

    Result updateItemById(Long tbItemid);

    Result preUpdateItem(Long tbitemid);

    Result updateItem(TbItem tbItem,String desc,String itemParams);
}
