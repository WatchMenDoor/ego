package com.bjsxt.item.service.impl;

import com.bjsxt.item.service.ItemService;
import com.bjsxt.mapper.TbItemCatMapper;
import com.bjsxt.mapper.TbItemDescMapper;
import com.bjsxt.mapper.TbItemMapper;
import com.bjsxt.mapper.TbItemParamItemMapper;
import com.bjsxt.pojo.*;
import com.bjsxt.utils.PageResult;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: liuxw
 * @Date: 2020-04-19
 * @Description: com.bjsxt.item.service.impl
 * @version: 1.0
 */
//商品查询
@Service
public class ItemServiceImpl implements ItemService {


    @Autowired
    private TbItemMapper tbItemMapper;

    @Autowired
    private TbItemParamItemMapper tbItemParamItemMapper;

    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Autowired
    private TbItemCatMapper tbItemCatMapper;


//商品查询
    @Override
    public PageResult selectTbItemAllByPage(Integer page, Integer rows) {
        PageHelper.startPage(page,rows);
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andStatusEqualTo((byte)1);
        List<TbItem> list = this.tbItemMapper.selectByExample(example);
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        PageResult result = new PageResult();
        result.setPageIndex(page);
        result.setResult(list);
        result.setTotalPage(pageInfo.getTotal());
        return result;
    }
//商品的添加
    @Override
    @LcnTransaction
    public Integer insertTbItem(TbItem tbItem) {
        return this.tbItemMapper.insert(tbItem);
    }

    //商品的删除与更新
    @Override
    @LcnTransaction
    public Integer updateTbItemById(TbItem tbItem) {

        Date date = new Date();
        tbItem.setUpdated(date);
        tbItem.setStatus((byte)3);
        return  this.tbItemMapper.updateByPrimaryKeySelective(tbItem);

    }

    //根据商品id查询所有信息
    @Override
    public Map<String, Object> preUpdateItem(Long itemId) {
        Map<String,Object> map = new HashMap<>();
        //查询商品信息
        TbItem tbItem = this.tbItemMapper.selectByPrimaryKey(itemId);
        map.put("item", tbItem);
        //查询商品描述信息
        TbItemDesc tbItemDesc = this.tbItemDescMapper.selectByPrimaryKey(itemId);
        map.put("itemDesc", tbItemDesc.getItemDesc());
        //查询商品分类信息
        TbItemCat tbItemCat = this.tbItemCatMapper.selectByPrimaryKey(tbItem.getCid());
        map.put("itemCat",tbItemCat.getName());
        //查询商品参数信息
        TbItemParamItemExample example = new TbItemParamItemExample();
        TbItemParamItemExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemParamItem> list = this.tbItemParamItemMapper.selectByExampleWithBLOBs(example);
        if (list!=null&&list.size()>0){
            map.put("itemParamItem", list.get(0));
        }

        return map;
    }

    @Override
    public TbItem selectItemInfo(Long itemId) {
        return this.tbItemMapper.selectByPrimaryKey(itemId);
    }
}
