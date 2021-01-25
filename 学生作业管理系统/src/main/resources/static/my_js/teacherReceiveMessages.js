/*信箱消息表格*/
layui.use('table', function(){
    var table = layui.table;
    var infotable=table.render({
        elem: '#test'
        ,url:'/teacher/findallteacherMessages'
        ,toolbar: '#toolbarDemo'
        ,title: '消息数据表'
        ,cols: [[
            {type: 'checkbox', fixed: 'left'}
            ,{field:'id', title:'ID', width:80}
            ,{field:'sendid', title:'发送者', width:120,}
            ,{field:'receid', title:'接受者', width:150}
            ,{field:'sendstate', title:'发送者类型', width:80}
            ,{field:'recestate', title:'接收者类型', width:100}
            ,{field:'state', title:'消息类型',width:80}
            ,{field:'info', title:'信息'}
            ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:80}
        ]]
        ,limit:10
        ,height:500
        ,page: true
    });
    //头工具栏事件
    table.on('toolbar(test)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case 'getCheckData':
                var data = checkStatus.data;
                // console.log(data);
                // console.log(data.length)
                // layer.alert(JSON.stringify(data.info));
                var info='';
                var id=[];
                $.each(data,function(i,v){
                    var temp=i+1;
                    info = info+'第'+temp+'条:'+v.info+'<br/>';
                    id[i]=v.id;
                });
                // console.log(id);
                readmanyinfo(id);
                layer.alert(info);
                infotable.reload();
                break;
        };
    });
    //监听行工具事件
    table.on('tool(test)', function(obj){
        var data = obj.data;
        var info=data.info;
        var id=data.id;
        if(obj.event === 'see'){
            alert(info);
            // 最后重新渲染数据表格
            readinfo(id);// 读取这个消息  用户未读消息要减少
            infotable.reload();
        }
    });
});
function readmanyinfo(meeeageid) {
    /*
    *
    * 把这条消息标志位已读
    * 同时用户未读消息减一
    *
    * */
    $.ajax({
        method:"post",
        url:"/teacher/readmanyinfo",
        data:{'messageid':meeeageid},
        dataType:"json",
        success:function(data) {
            // alert(data.msg)
        },
        error:function () {
            // alert("读取失败")
        }
    });
}
/*用户读取消息 未读消息减一 消息表减一*/
function readinfo(meeeageid) {
    /*
    *
    * 把这条消息标志位已读
    * 同时用户未读消息减一
    *
    * */
    $.ajax({
        method:"post",
        url:"/teacher/readinfo",
        data:{'messageid':meeeageid},
        dataType:"json",
        success:function(data) {
            // alert(data.msg)
        },
        error:function () {
            // alert("读取失败")
        }
    });
}