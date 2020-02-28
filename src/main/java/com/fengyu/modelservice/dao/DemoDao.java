package com.fengyu.modelservice.dao;

import com.fengyu.modelservice.vo.UserVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 *
 */
@Mapper
public interface DemoDao {
    /**
     *
     * @return
     */
    List<UserVO> getAll();
}
