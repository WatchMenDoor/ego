package com.bjsxt.front.portal.service.impl;

import com.bjsxt.front.portal.feign.CommonContentFeginClient;
import com.bjsxt.front.portal.feign.CommonItemFeignClient;
import com.bjsxt.front.portal.feign.CommonRedisFeginClient;
import com.bjsxt.front.portal.service.ContentService;
import com.bjsxt.utils.CatResult;
import com.bjsxt.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Auther: liuxw
 * @Date: 2020-05-03
 * @Description: com.bjsxt.front.portal.service.impl
 * @version: 1.0
 */
@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private CommonContentFeginClient commonContentFeginClient;

    @Autowired
    private CommonRedisFeginClient commonRedisFeginClient;
    @Override
    public Result selectFrontendContentByAD() {
        //查询缓存
        try{
            List<Map> maps = this.commonRedisFeginClient.selectContentAD();
            //判断缓存是否为空
            if (maps!=null&&maps.size()>0 ){
                return Result.ok(maps);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        //查询数据库
        List<Map> maps = this.commonContentFeginClient.selectFrontendContentByAD();
        if (maps!=null&&maps.size()>0) {
            //添加到缓存
            this.commonRedisFeginClient.insertContentAD(maps);
        }
        if (maps!=null&&maps.size()>0){
            return Result.ok(maps);
        }

        return Result.error("大广告位加载失败");
    }
}
