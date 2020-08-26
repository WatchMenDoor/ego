package com.bjsxt.content.service.impl;

import com.bjsxt.content.service.ContentCategoryService;
import com.bjsxt.mapper.TbContentCategoryMapper;
import com.bjsxt.pojo.TbContentCategory;
import com.bjsxt.pojo.TbContentCategoryExample;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Auther: liuxw
 * @Date: 2020-05-03
 * @Description: com.bjsxt.content.service.impl
 * @version: 1.0
 */
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;


    @Override
    public List<TbContentCategory> selectContentCategoryByParentId(Long parentId) {
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbContentCategory> tbContentCategories = this.tbContentCategoryMapper.selectByExample(example);
        return tbContentCategories;
    }


    //添加内容分类
    @Override
    @LcnTransaction
    public Integer insertContentCategory(TbContentCategory tbContentCategory) {

        //将节点对象插入
        Date date = new Date();
        tbContentCategory.setIsParent(false);
        tbContentCategory.setCreated(date);
        tbContentCategory.setUpdated(date);
        tbContentCategory.setStatus(1);
        tbContentCategory.setSortOrder(1);
        int insert = this.tbContentCategoryMapper.insert(tbContentCategory);
        //查询父节点是否是叶子节点
        TbContentCategory tbContentCategory1 = this.tbContentCategoryMapper.selectByPrimaryKey(tbContentCategory.getParentId());
        if (!tbContentCategory1.getIsParent()){
            tbContentCategory1.setUpdated(date);
            tbContentCategory1.setIsParent(true);
            this.tbContentCategoryMapper.updateByPrimaryKeySelective(tbContentCategory1);
        }
        return insert;
    }


    //删除商品分类
    @Override
    @LcnTransaction
    public Integer deleteContentCategoryById(Long categoryId) {
        //查询当前节点
        TbContentCategory tbContentCategory = this.tbContentCategoryMapper.selectByPrimaryKey(categoryId);

        //删除当前节点的子节点
        Integer status = this.deleteNode(tbContentCategory);

        //查询当前节点的父节点
        TbContentCategory tbContentCategory1 = this.tbContentCategoryMapper.selectByPrimaryKey(tbContentCategory.getParentId());
        //查看当前节点是否有兄弟节点

            TbContentCategoryExample example = new TbContentCategoryExample();
            TbContentCategoryExample.Criteria criteria = example.createCriteria();
            criteria.andParentIdEqualTo(tbContentCategory.getParentId());
            List<TbContentCategory> list = this.tbContentCategoryMapper.selectByExample(example);
            if (list.size()==0){
                tbContentCategory1.setUpdated(new Date());
                tbContentCategory1.setIsParent(false);
                this.tbContentCategoryMapper.updateByPrimaryKeySelective(tbContentCategory1);

            }
            return 200;
    }

    private Integer deleteNode(TbContentCategory tbContentCategory) {
               if (tbContentCategory.getIsParent()){
                    //是父节点
                   //查询当前节点的子节点
                   TbContentCategoryExample example = new TbContentCategoryExample();
                   TbContentCategoryExample.Criteria criteria = example.createCriteria();
                   criteria.andParentIdEqualTo(tbContentCategory.getParentId());
                   List<TbContentCategory> list = this.tbContentCategoryMapper.selectByExample(example);
                   for (TbContentCategory child:list) {
                       this.deleteNode(child);
                       this.tbContentCategoryMapper.deleteByPrimaryKey(child.getId());//没有也可以
                   }
               }else{
                   //不是父节点
                   this.tbContentCategoryMapper.deleteByPrimaryKey(tbContentCategory.getId());
               }

               return 200;
    }


    //修改商品分类
    @Override
    public Integer updateContentCategory(TbContentCategory tbContentCategory) {
        Date date = new Date();
        tbContentCategory.setUpdated(date);
        return this.tbContentCategoryMapper.updateByPrimaryKeySelective(tbContentCategory);
    }


}
