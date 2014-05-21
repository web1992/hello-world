package com.company.lib.controllers;



import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.company.lib.dao.OperLogDAO;
import com.company.lib.entity.OperLog;
import com.company.lib.entity.User;

import net.paoding.rose.web.ControllerInterceptorAdapter;
import net.paoding.rose.web.Invocation;
//通过扩展 ControllerInterceptorAdapter 实现一个拦截器类；
//拦截器放在controllers包下，称为局部拦截器，局部拦截器只作用于所在目录以及子目录的控制器
public class AutoLogInterceptor extends ControllerInterceptorAdapter {

    /*
     *  这里的executorService，在applicationContext-executor.xml中并没有id="xxx",
     *   但这没有问题，Spring会给该Bean一个默认的id（类全名）
     */
    
    @Autowired
    private ThreadPoolTaskExecutor tx;
    
    @Autowired
    private OperLogDAO operLogDAO;
    public void afterCompletion(Invocation inv, Throwable ex) throws Exception  {
        final OperLog operLog = new OperLog();
        operLog.setResourcePattern(inv.getResourceId());
        operLog.setResourceId(inv.getRequestPath().getUri());
        operLog.setSuccess(ex == null);
        operLog.setRemarks(ex == null ? "" : ex.getMessage()); 
        
        //User loginUser = (User) inv.getRequest().getAttribute("loginUser");
        HttpSession session = inv.getRequest().getSession(true);
        User loginUser=(User)session.getAttribute("userInfo");
        if(loginUser==null) return;
        if (loginUser != null) {
            operLog.setUserName(loginUser.getLoginName());
        }
        // 封装为一个任务
        Runnable task = new Runnable() {
            public void run() {
                 operLogDAO.save(operLog);
            }
        };
       // task.run();
        // 将插入到数据库的操作提交executorService做异步更新
        // 在实际场景中，这种方式要注意webapp shutdown的时候，还未执行的Task的处理问题
       // Thread t=new Thread(task);
      //  t.start();
        tx.execute(task);
      
      
    }
}