package com.bjsxt.item.service;

import com.bjsxt.pojo.TbItemParam;
import com.bjsxt.pojo.TbItemParamItem;
import com.bjsxt.utils.PageResult;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Auther: liuxw
 * @Date: 2020-04-20
 * @Description: com.bjsxt.item.service
 * @version: 1.0
 */
public interface ItemParamService {

    TbItemParam selectItemParamByItemCatId(Long itemCatId);


    public PageResult selectItemParamAll(Integer page, Integer rows);

    public Integer insertItemParam( TbItemParam tbItemParam);

    Integer deleteItemParamById(Long id);


}
