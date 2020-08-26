package com.bjsxt.common.redis.service;

import com.bjsxt.utils.CatResult;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @Auther: liuxw
 * @Date: 2020-05-04
 * @Description: com.bjsxt.common.redis.service
 * @version: 1.0
 */
public interface ContentService {


    public void insertContent(List<Map> map);


    public List<Map>  selectContentAD();
}
