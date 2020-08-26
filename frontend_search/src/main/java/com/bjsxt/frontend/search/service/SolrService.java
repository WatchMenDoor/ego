package com.bjsxt.frontend.search.service;

import com.bjsxt.utils.Result;
import com.bjsxt.utils.SolrDocument;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @Auther: liuxw
 * @Date: 2020-05-05
 * @Description: com.bjsxt.frontend.search.service
 * @version: 1.0
 */
public interface SolrService {
    Result importAll();

    List<SolrDocument> selectByq(String q,Long page,Integer pagesize);
}
