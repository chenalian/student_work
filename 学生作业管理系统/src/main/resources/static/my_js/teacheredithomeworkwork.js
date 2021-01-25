/*信箱消息表格*/
layui.use(['table','jquery'], function(){
    var table = layui.table;
    var $=layui.$;
    var infotable=table.render({
        elem: '#test1'
        ,url:'/teacher/findallHomeworksbyteacherid'
        ,toolbar: '#toolbarDemo'
        ,limit:10
        ,limits:['10','20','30']
        ,height:500
        ,title: '用户数据表'
        ,cols: [[
            {type: 'checkbox', fixed: 'left'}
            ,{field:'id', title:'ID', width:80}
            ,{field:'classId', title:'班级号', width:120,}
            ,{field:'stime', title:'开始时间', width:150}
            ,{field:'etime', title:'截止时间', width:80}
            ,{field:'type', title:'种类', width:100}
            ,{field:'filename', title:'文件名',width:80}
            ,{field:'text', title:'文本'}
            ,{field:'state', title:'状态',width:80}
            ,{fixed: 'tex', title:'操作', toolbar: '#barDemo1', width:80,fixed: 'right'}
        ]]
        ,page: true
    });
    //头工具栏事件
    table.on('toolbar(test1)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case 'getCheckData':
                var data = checkStatus.data;
                console.log(data);
                console.log(data.length)
                layer.alert(JSON.stringify(data));
                $.each(data,function(i,v){
                    console.log(data[i].id+"=="+v);
                });
                break;
            // case 'getCheckLength':
            //     var data = checkStatus.data;
            //     layer.msg('选中了：'+ data.length + ' 个');
            //     break;
            // case 'isAll':
            //     layer.msg(checkStatus.isAll ? '全选': '未全选');
            //     break;
        };
    });
    //监听行工具事件
    table.on('tool(test1)', function(obj){
        var data = obj.data;
        var info=data.text;
        var id=data.id;
        if(obj.event==='edit')
        {
           // alert(id);
            xadmin.open('编辑作业','/teacher/TeacherEditHomeworkTable?id='+id,600,400);
            infotable.reload();
        }
    });
});

