/*信箱消息表格*/
layui.use('table', function(){
    var table = layui.table;

    var infotable=table.render({
        elem: '#test3'
        ,url:'/teacher/checkhomeworkwork'
        ,toolbar: '#toolbarDemo3'
        ,limit:10
        ,limits:['10','20','30']
        ,height:500
        ,title: '用户数据表'
        ,cols: [[
            {type: 'checkbox', fixed: 'left'}
            ,{field:'id', title:'ID', width:80}
            ,{field:'homeworkId', title:'作业', width:80,}
            ,{field:'studentId', title:'学生', width:80}
            ,{field:'type', title:'类型', width:80}
            ,{field:'filename', title:'文件名', width:100}
            ,{field:'text', title:'文本答案',width:80}
            ,{field:'info', title:'评价',width:80}
            ,{field:'piyu', title:'批语',edit:'text'}
            ,{field:'filescore', title:'文件分',width:80,edit:'text'}
            ,{field:'textscore', title:'文本分',width:80,edit:'text'}
            ,{field:'score', title:'总分',width:80,edit:'text'}
            ,{fixed: 'tex', title:'操作', toolbar: '#barDemo3', width:200,fixed: 'right'}
        ]]
        ,page: true
    });
    //头工具栏事件
    table.on('toolbar(test3)', function(obj){
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
    table.on('tool(test3)', function(obj){
        var data = obj.data;
        var info=data.text;
        var id=data.id;
        var piyu=data.piyu;
        var filescore=data.filescore;
        var textscore=data.textscore;
        var filename=data.filename;
        var studentid=data.studentId;
        if(obj.event === 'see'){
            // 打回
            // 删掉提交信息 教师未读信息减一 消息队列消息剔除
            trunbackHomework(id,studentid);
            // layer.msg(info);
            // 最后重新渲染数据表格
            // readinfo(id);// 读取这个消息  用户未读消息要减少
            infotable.reload();
        }
        else if(obj.event==='edit')
        {
            uploadhomeworkscore(id,piyu,filescore,textscore);
            infotable.reload();
        }
        else if (obj.event=='down')
        {
            $.ajax({
                method:"post",
                url:"/teacher/downloadHomeworkInfo",
                data:{'filename':filename},
                dataType:"json",
                // success:function(data) {
                //     alert(data.msg)
                // },
                // error:function () {
                //     alert("下载失败")
                // }
            });
        }
    });
});
function trunbackHomework(id,studentid) {
    $.ajax({
        method:"post",
        url:"/teacher/trunbackHomework",
        data:{'id':id,'studentid':studentid},
        dataType:"json",
        success:function(data) {
            alert(data.msg)
        },
        error:function () {
            alert("打回失败")
        }
    });
}

function uploadhomeworkscore(id,piyu,filescore,textscore) {
    /*
    *
    * 把这条消息标志位已读
    * 同时用户未读消息减一
    *
    * */
    // 打分
    //  alert(info)
    $.ajax({
        method:"post",
        url:"/teacher/uploadHomeworkScore",
        data:{'id':id,'piyu':piyu,'filescore':filescore,'textscore':textscore},
        dataType:"json",
        success:function(data) {
            alert(data.msg)
        },
        error:function () {
            alert("打分失败")
        }
    });
}