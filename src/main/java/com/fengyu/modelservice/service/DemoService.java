package com.fengyu.modelservice.service;

import com.fengyu.modelservice.dao.DemoDao;
import com.fengyu.modelservice.vo.ResponseResult;
import com.fengyu.modelservice.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * service层例子
 */
@Service
public class DemoService {
    /**
     * 日子记录.
     */
    public static final Logger LOG = LoggerFactory.getLogger(DemoService.class);
    @Autowired
    private DemoDao demoDao;


    public ResponseResult getPage(int page, int pageSize) {
        List<UserVO> userList = demoDao.getAll();
        return ResponseResult.success(userList);
    }

}
