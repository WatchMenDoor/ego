package com.bjsxt.frontend.search.service.impl;

import com.bjsxt.frontend.search.service.SolrService;
import com.bjsxt.mapper.SolrItemMapper;
import com.bjsxt.pojo.SolrItem;
import com.bjsxt.utils.Result;
import com.bjsxt.utils.SolrDocument;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.HighlightOptions;
import org.springframework.data.solr.core.query.HighlightQuery;
import org.springframework.data.solr.core.query.SimpleHighlightQuery;
import org.springframework.data.solr.core.query.result.HighlightEntry;
import org.springframework.data.solr.core.query.result.HighlightPage;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: liuxw
 * @Date: 2020-05-05
 * @Description: com.bjsxt.frontend.search.service.impl
 * @version: 1.0
 */
@Service
public class SolrServiceImpl implements SolrService {

    @Autowired
    private SolrItemMapper solrItemMapper;

    @Autowired
    private SolrTemplate solrTemplate;

    @Value("${spring.data.solr.core}")
    private String collection;


    @Override
    public Result importAll() {

        List<SolrItem> itemList = this.solrItemMapper.getItemList();
        try{
            //将数据添加到索引库
            for (SolrItem item: itemList) {
                //创建solrinputdocument对象
                SolrInputDocument document = new SolrInputDocument();
                document.setField("id", item.getId());
                document.setField("item_title", item.getTitle());
                document.setField("item_sell_point",item.getSell_point());
                document.setField("item_price",item.getPrice());
                document.setField("item_image",item.getImage());
                document.setField("item_category_name",item.getName());
                document.setField("item_desc",item.getItem_desc());

                //写入索引库
                this.solrTemplate.saveDocument(this.collection,document);
            }
            this.solrTemplate.commit(this.collection);
            return Result.ok();
        }catch (Exception e){

        }

        return Result.error("导入失败");
    }


    //搜索索引库
    @Override
    public List<SolrDocument> selectByq(String q, Long page, Integer pagesize) {
        //设置查询条件
        HighlightQuery query = new SimpleHighlightQuery();
        Criteria criteria = new Criteria("item_keywords");
        criteria.is(q);
        query.addCriteria(criteria);

        //设置高亮属性
        HighlightOptions highlightOptions = new HighlightOptions();
        highlightOptions.addField("item_title");//设置高亮显示的域
        highlightOptions.setSimplePrefix("<em style='color:red'");
        highlightOptions.setSimplePostfix("</em>");
        query.setHighlightOptions(highlightOptions);

        //分页
        query.setOffset((page-1)*pagesize);
        query.setRows(pagesize);


        HighlightPage<SolrDocument> highlightPage =  this.solrTemplate.queryForHighlightPage(this.collection, query, SolrDocument.class);
        List<HighlightEntry<SolrDocument>> highlighted = highlightPage.getHighlighted();
        for (HighlightEntry<SolrDocument> highlightEntry:highlighted) {
            SolrDocument entity = highlightEntry.getEntity();//实体对象，原始的实体对象
            List<HighlightEntry.Highlight> highlights = highlightEntry.getHighlights();
            //如果有高亮就取高亮
            if (highlights!=null&&highlights.size()>0&&highlights.get(0).getSnipplets().size()>0){
                entity.setItem_title(highlights.get(0).getSnipplets().get(0));
            }
        }


        List<SolrDocument> list =highlightPage.getContent();
        return list;
    }
}
