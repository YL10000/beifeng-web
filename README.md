# beifeng-web
使用spring mvc + mybatis的web项目

>该项目使用spring注解实现了mvc模式
>后台使用mybatis操作数据库
    如果要实现数据库的增删改查的实体，需要继承BaseModel,对用的dao继承IBaseDao，会自动生成常用的增删改查方法，不需要额外书写xml
    对于垮实体的连表查询，使用com.beifeng.web.conmon.persistence.support.QueryCondition参数
    在QueryCondition中添加pageInfo信息，可以进行自动分页查询，sql语句中不需要进行分页处理
    PageInfo pageInfo = new PageInfo(total, Integer.parseInt(pageSize));
    pageInfo.setPage(Integer.parseInt(currentPage));
    condition.setPageInfo(pageInfo);
>操作日志注解配置
    在要添加日志的方法上添加@MethodLog注解
    在方法中添加OpLog opLog参数会自动注入进去
    @MethodLog(remark = "到demo数据列表页")
    @RequestMapping("/list")
    public String selectAllUser(HttpServletRequest request,Model model,OpLog opLog) {
        return "demo/demo_list";
    }
>数据库sql
>>demo表
      CREATE TABLE `demo` (
          `id` int(10) NOT NULL auto_increment,
          `name` varchar(20) collate utf8_bin default NULL,
          `age` int(10) default NULL,
          PRIMARY KEY  (`id`)
      ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin
>>操作日志表
      CREATE TABLE `sys_op_log` (
          `id` int(10) NOT NULL auto_increment,
          `remark` varchar(100) collate utf8_bin default NULL COMMENT '日志备注',
          `method` varchar(100) collate utf8_bin default NULL COMMENT '方法名',
          `result` int(2) default NULL COMMENT '执行结果：0：失败，1：成功',
          `content` varchar(2048) collate utf8_bin default NULL COMMENT '信息',
          `exception` varchar(256) collate utf8_bin default NULL,
          `create_time` datetime default NULL COMMENT '创建时间',
          PRIMARY KEY  (`id`)
      ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin

