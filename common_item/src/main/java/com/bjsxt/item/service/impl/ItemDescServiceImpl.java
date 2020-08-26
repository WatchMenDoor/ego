package com.bjsxt.item.service.impl;

import com.bjsxt.item.service.ItemDescService;
import com.bjsxt.mapper.TbItemDescMapper;
import com.bjsxt.pojo.TbItemDesc;
import com.bjsxt.pojo.TbItemDescExample;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Auther: liuxw
 * @Date: 2020-04-21
 * @Description: com.bjsxt.item.service.impl
 * @version: 1.0
 */
@Service
public class ItemDescServiceImpl implements ItemDescService {

    @Autowired
    private TbItemDescMapper tbItemDescMapper;
    @Override
    @LcnTransaction
    public Integer insertItemDesc(TbItemDesc tbItemDesc) {
        return this.tbItemDescMapper.insert(tbItemDesc);
    }

    @Override
    public Integer updateItemDesc(TbItemDesc tbItemDesc) {
        tbItemDesc.setUpdated(new Date());
        return this.tbItemDescMapper.updateByPrimaryKeySelective(tbItemDesc);
    }

    @Override
    public TbItemDesc selectItemDescByItemId(Long itemId) {
        TbItemDescExample example = new TbItemDescExample();
        TbItemDescExample.Criteria criteria = example.createCriteria();
        criteria.andItemIdEqualTo(itemId);
        List<TbItemDesc> tbItemDescs = this.tbItemDescMapper.selectByExampleWithBLOBs(example);

        return tbItemDescs.get(0);
    }
}
