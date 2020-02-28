package com.fengyu.modelservice.vo;

import lombok.Data;

@Data
public class UserVO {
   //主键ID，自增',
   private String id;
   //手机号',
   private String phone;
   //任务执行人',
   private String username;
   //man：男性；woman：女性；unknown：未知',
   private String sex;
   //账户状态：0 未开通；1：开通',
   private String accountStatus;
   //任务状态：0 无结果；1：有结果',
   private String taskStatus;
   //锁状态：0 未锁定；1：锁定 ',
   private String lockStatus;
   //备注：double 需要人工处理',
   private String remark;
}
