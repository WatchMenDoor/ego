package com.bjsxt.common.redis.service;

import com.bjsxt.pojo.TbItem;
import com.bjsxt.pojo.TbItemDesc;
import com.bjsxt.pojo.TbItemParamItem;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Auther: liuxw
 * @Date: 2020-05-06
 * @Description: com.bjsxt.common.redis.service
 * @version: 1.0
 */
public interface ItemService {

    void insertItemBasicInfo(TbItem tbItem);
    TbItem selectItemBasicInfo( Long tbItemId);


    void insertItemDescInfo( TbItemDesc tbItemDesc);
    TbItemDesc selectItemDescInfo(Long tbItemId);

    void insertItemParamItemInfo( TbItemParamItem tbItemParamItem);
    TbItemParamItem selectItemParamItemInfo( Long tbItemId);
}
