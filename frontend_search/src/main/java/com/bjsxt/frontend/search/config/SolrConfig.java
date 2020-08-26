package com.bjsxt.frontend.search.config;

import org.apache.solr.client.solrj.SolrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrTemplate;

/**
 * @Auther: liuxw
 * @Date: 2020-05-04
 * @Description: com.bjsxt.frontend.search.config
 * @version: 1.0
 */
/*
* 配置solrtemplate
* */
@Configuration
public class SolrConfig {
    @Autowired
    private SolrClient solrClient;

    @Bean
    public SolrTemplate getSolrTemplate(){
        return new SolrTemplate(solrClient);
    }
}
