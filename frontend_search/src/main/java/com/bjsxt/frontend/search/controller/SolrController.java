package com.bjsxt.frontend.search.controller;

import com.bjsxt.frontend.search.service.SolrService;
import com.bjsxt.utils.Result;
import com.bjsxt.utils.SolrDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: liuxw
 * @Date: 2020-05-05
 * @Description: com.bjsxt.frontend.search.controller
 * @version: 1.0
 */
@RestController
@RequestMapping("/search")
public class SolrController {

    @Autowired
    private SolrService solrService;

    //向solr索引库中导入数据
    @RequestMapping("/importAll")
    public Result importAll(){
        return this.solrService.importAll();
    }

    //从solr中查询商品数据
    @RequestMapping("/list")
    public List<SolrDocument> selectByq(String q, @RequestParam(defaultValue = "1") Long page,@RequestParam(defaultValue = "50") Integer pagesize){
        try{
            return this.solrService.selectByq(q, page, pagesize);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
