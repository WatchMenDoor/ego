package com.bjsxt.content.service.impl;

import com.bjsxt.content.service.ContentService;
import com.bjsxt.mapper.TbContentMapper;
import com.bjsxt.pojo.TbContent;
import com.bjsxt.pojo.TbContentExample;
import com.bjsxt.utils.PageResult;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Auther: liuxw
 * @Date: 2020-05-03
 * @Description: com.bjsxt.content.service.impl
 * @version: 1.0
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private TbContentMapper tbContentMapper;

    @Value("${frontend.AD}")
    private Long ad;

    @Override
    public PageResult selectTbContentAllByCategoryId(Integer page, Integer rows, Long categoryId) {
        PageHelper.startPage(page, rows);
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        List<TbContent> tbContents = this.tbContentMapper.selectByExample(example);
        PageInfo<TbContent> pageInfo = new PageInfo<>(tbContents);
        PageResult pageResult = new PageResult();
        pageResult.setTotalPage(pageInfo.getTotal());
        pageResult.setResult(pageInfo.getList());
        pageResult.setPageIndex(page);
        return pageResult;
    }

    @Override
    @LcnTransaction
    public Integer insertTbContent(TbContent tbContent) {
        Date date = new Date();
        tbContent.setCreated(date);
        tbContent.setUpdated(date);
        return this.tbContentMapper.insertSelective(tbContent);
    }

    @Override
    @LcnTransaction
    public Integer deleteContentByIds(Long id) {

        return this.tbContentMapper.deleteByPrimaryKey(id);
    }


    //查询首页大广告位
    @Override
    public List<Map> selectFrontendContentByAD() {
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(this.ad);
        List<TbContent> list = this.tbContentMapper.selectByExample(example);
        List<Map> result = new ArrayList<>();
        for (TbContent content: list) {
             Map map = new HashMap();
             map.put("heightB", 240);
             map.put("src", content.getPic());
             map.put("width", 670);
             map.put("alt", content.getSubTitle());
             map.put("srcB", null);
             map.put("widthB", 550);
             map.put("href",content.getUrl());
             map.put("height", 240);

             result.add(map);
        }

        return result;
    }
}
