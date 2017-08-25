/*
 * 项目名：beifeng-web
 * 文件名：DemoController.java
 * 版权：Copyright (c) 2014-2015 Beijing BiYouWoShi Tech. Co. Ltd. All Rights Reserved.
 * 描述：DemoAction
 * 修改人：yanglin
 * 修改时间：2017年8月24日 上午11:37:32
 * 修改内容：
 * 版本编号：1.0
 */
package com.beifeng.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.beifeng.web.conmon.persistence.support.PageInfo;
import com.beifeng.web.conmon.persistence.support.QueryCondition;
import com.beifeng.web.model.sys.log.OpLog;
import com.beifeng.web.service.IDemoService;
import com.beifeng.web.conmon.log.MethodLog;

/**
 * DemoController
 *	
 * @Description DemoAction 
 * @author yanglin
 * @version 1.0,2017年8月24日
 * @see
 * @since
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    IDemoService demoService;
    
    @MethodLog(remark = "到demo数据列表页")
    @RequestMapping("/list")
    public String selectAllUser(HttpServletRequest request,Model model,OpLog opLog) {
        return "demo/demo_list";
    }
    
    @MethodLog(remark = "获取demo列表数据")
    @ResponseBody
    @RequestMapping("/datajson")
    public Map<String, Object> loadDataJson(HttpServletRequest request,OpLog opLog) {
        Map<String, Object> result=new HashMap<String, Object>();
        QueryCondition condition=new QueryCondition();
        String currentPage=request.getParameter("page");
        String pageSize=request.getParameter("rows"); 
        Map<String, Object> params=new HashMap<String, Object>();
        Integer total= demoService.getDemoCount(condition);
        result.put("total", total);
        //添加分页
        PageInfo pageInfo = new PageInfo(total, Integer.parseInt(pageSize));
        pageInfo.setPage(Integer.parseInt(currentPage));
        condition.setPageInfo(pageInfo);;
        List<Map<String, Object>> users=demoService.queryDemos(condition);
        result.put("rows", users);
        return result;
    }
}
