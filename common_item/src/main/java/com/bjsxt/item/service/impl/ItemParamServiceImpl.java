package com.bjsxt.item.service.impl;

import com.bjsxt.item.service.ItemParamService;
import com.bjsxt.mapper.TbItemParamItemMapper;
import com.bjsxt.mapper.TbItemParamMapper;
import com.bjsxt.pojo.TbItemParam;
import com.bjsxt.pojo.TbItemParamExample;
import com.bjsxt.pojo.TbItemParamItem;
import com.bjsxt.pojo.TbItemParamItemExample;
import com.bjsxt.utils.PageResult;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: liuxw
 * @Date: 2020-04-20
 * @Description: com.bjsxt.item.service.impl
 * @version: 1.0
 */
//ItemParam服务
@Service
public class ItemParamServiceImpl implements ItemParamService {


    @Autowired
    private TbItemParamMapper tbItemParamMapper;
    @Override
    public TbItemParam selectItemParamByItemCatId(Long itemCatId) {
        TbItemParamExample example = new TbItemParamExample();
        TbItemParamExample.Criteria criteria = example.createCriteria();
        criteria.andItemCatIdEqualTo(itemCatId);
        List<TbItemParam> list = this.tbItemParamMapper.selectByExampleWithBLOBs(example);
        if (list!=null&&list.size()>0){
            return list.get(0);
        }
        return null;
    }


    //查询所有商品规格参数模板
    @Override
    public PageResult selectItemParamAll(Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        TbItemParamExample example = new TbItemParamExample();
        List<TbItemParam> list = this.tbItemParamMapper.selectByExampleWithBLOBs(example);
        PageInfo<TbItemParam> pageInfo = new PageInfo<>(list);
        PageResult result = new PageResult();
        result.setPageIndex(page);
        result.setResult(pageInfo.getList());
        result.setTotalPage(pageInfo.getTotal());
        return result;
    }


    //添加商品分类规格参数对象
    @Override
    @LcnTransaction
    public Integer insertItemParam(TbItemParam tbItemParam) {
        return this.tbItemParamMapper.insertSelective(tbItemParam);
    }

    @Override
    public Integer deleteItemParamById(Long id) {
        return this.tbItemParamMapper.deleteByPrimaryKey(id);
    }
}
