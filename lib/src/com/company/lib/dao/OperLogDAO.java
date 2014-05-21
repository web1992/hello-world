package com.company.lib.dao;

import java.util.List;

import com.company.lib.entity.OperLog;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;

@DAO
public interface OperLogDAO {

    @SQL("select id, user_name, resource_pattern, resource_id, success, remarks, create_time from oper_log")
    public List<OperLog> find();

    @SQL("insert into oper_log (user_name, resource_pattern, resource_id, success, remarks) values (:1.userName, :1.resourcePattern, :1.resourceId, :1.success, :1.remarks)")
    public void save(OperLog operLog);

}

