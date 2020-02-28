package com.fengyu.modelservice.controller;

import com.fengyu.modelservice.service.DemoService;
import com.fengyu.modelservice.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * contrller例子
 */
@RestController
@RequestMapping({"/modelser/v1/demo"})
public class DemoController {

    @Autowired
    private DemoService demoService;

    /**
     * 示例接口.  // localhost:8180/modelser/v1/demo/demo
     * @param page 页数
     * @param pageSize 每页大小
     * @return
     */
    @RequestMapping(value = "/demo", method = RequestMethod.GET)
    public ResponseResult demo(@RequestParam(value = "page", defaultValue = "1", required = false) int page,
                               @RequestParam(value = "pageSize", defaultValue = "10", required = false)int pageSize){
        return demoService.getPage(page, pageSize);
    }
}
