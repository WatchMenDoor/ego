package com.bjsxt.backend.item.service;

import com.bjsxt.pojo.TbItemParam;
import com.bjsxt.utils.Result;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Auther: liuxw
 * @Date: 2020-04-20
 * @Description: com.bjsxt.backend.item.service
 * @version: 1.0
 */
public interface ItemParamService {

    Result selectItemParamByItemCatId(Long itemCatId);

    Result selectItemParamAll(Integer page,Integer rows);

    Result insertItemParam(Long itemCatId,String paramData);

    Result deleteItemParamById(Long id);
}
