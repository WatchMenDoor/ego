package com.bjsxt.front.portal.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @Auther: liuxw
 * @Date: 2020-05-03
 * @Description: com.bjsxt.front.portal.feign
 * @version: 1.0
 */
@FeignClient(value = "common-content")
public interface CommonContentFeginClient {

    //-----------------------/service/content
    @RequestMapping("/service/content/selectFrontendContentByAD")
    List<Map> selectFrontendContentByAD();
}
