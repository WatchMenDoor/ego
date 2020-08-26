package com.bjsxt.front.portal.service;

import com.bjsxt.utils.Result;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Auther: liuxw
 * @Date: 2020-05-05
 * @Description: com.bjsxt.front.portal.service
 * @version: 1.0
 */
public interface ItemService {

    Result selectItemInfo( Long itemId);
    Result  selectItemDescByItemId(Long itemId);
    Result  selectTbItemParamItemByItemId(Long itemId);
}
