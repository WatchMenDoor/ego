package com.bjsxt.item.service;

import com.bjsxt.pojo.TbItem;
import com.bjsxt.utils.PageResult;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @Auther: liuxw
 * @Date: 2020-04-19
 * @Description: com.bjsxt.item.service
 * @version: 1.0
 */
public interface ItemService {
    PageResult selectTbItemAllByPage(Integer page,Integer rows);
    Integer insertTbItem(TbItem tbItem);
    Integer updateTbItemById(TbItem tbItem);

    Map<String,Object> preUpdateItem(Long itemId);

    public TbItem selectItemInfo(Long itemId);
}
