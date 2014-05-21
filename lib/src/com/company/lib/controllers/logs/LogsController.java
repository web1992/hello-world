package com.company.lib.controllers.logs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.company.lib.controllers.LoginRequired;
import com.company.lib.dao.OperLogDAO;
import com.company.lib.entity.OperLog;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

@LoginRequired
@Path("")
public class LogsController {
                 //Controller
	@Autowired
	private OperLogDAO operLogDAO;
	
    // 16)
    @Get("logsList")
    public String list(@Param("offset") int offset,Invocation inv) {
    	List<OperLog> operLogs = operLogDAO.find();
    	inv.addModel("operLogs", operLogs);
        return "logs-list";
    }
}