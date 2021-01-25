var filename=null;
var id=null;
var type=null;
/*
*
*
* 作业表格
*
*
* */
layui.use('table', function(){
    var table = layui.table;
    table.render({
        elem: '#test1'
        ,url:'/student/findallStudentHomeworks'
        ,toolbar: '#toolbarDemo1'
        ,title: '作业表'
        ,cols: [[
            {type: 'checkbox', fixed: 'left'}
            ,{field:'id', title:'ID', width:80, fixed: 'left', unresize: true, sort: true}
            ,{field:'classId', title:'班级号', width:120}
            ,{field:'stime', title:'开始时间', width:150}
            ,{field:'etime', title:'结束时间', width:150}
            ,{field:'type', title:'作业类型', width:100}
            ,{field:'filename', title:'作业名',width:100}
            ,{field:'state', title:'状态',width:80}
            ,{field:'text', title:'文本详情'}
            ,{fixed: 'right', title:'操作', toolbar: '#barDemo1', width:220}
        ]]
        ,width:1200
        ,height:500
        ,page: true
    });
    //头工具栏事件
    table.on('toolbar(test1)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        switch(obj.event){
            case 'getCheckData':
                var data = checkStatus.data;
                layer.alert(JSON.stringify(data));
                break;
            case 'getCheckLength':
                var data = checkStatus.data;
                layer.msg('选中了：'+ data.length + ' 个');
                break;
            case 'isAll':
                layer.msg(checkStatus.isAll ? '全选': '未全选');
                break;
        };
    });
    //监听行工具事件
    table.on('tool(test1)', function(obj){
        var data = obj.data;
        var text=data.text;
        id=data.id;
        type=data.type;
        var filenametemp=data.filename;
        // var layer=layui.layer;
        //console.log(obj)
        if(obj.event === 'see'){
            alert(text)
            /*
            *
            * 将未读消息减一
            *
            * */
            readinfo(id);
        } else if(obj.event === 'do'){
            filename = filenametemp;
            // textHomework
            $("#textHomework").val(text);
            layer.open({
                type: 1,
                title: "查看作业",
                // closeBtn: true,
                shift: 2,
                area: ['1000px', '750px'],
                shadeClose: true,
                btn: ['确定'],
                yes: function (index, layero) {
                    // var teacher_id = sessionStorage.getItem("teacher_id");
                    // var name = sessionStorage.getItem("name");
                    // layer.close(index);
                    // var trindex = $("tr").index(obj.tr);
                    // alert(trindex);
                    // $("#publishWork").hidden();
                },
                content:$("#submitHomework"),
                anim: 6,
                success: function (layero, index) {
                    // $("#publishWork").hidden();
                    // $("#publishWork").hidden();
                },
            });
        }
        else if(obj.event === 'down') {
            downHomework(filenametemp);
        }
    });
});
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
        url:"/student/readinfo",
        data:{'messageid':meeeageid},
        dataType:"json",
        success:function(data) {
            alert(data.msg)
        },
        error:function () {
            alert("读取失败")
        }
    });

}
/*
*
* 下载文件作业
*
* */
function downHomework() {
    layui.use(['jquery'],function () {
        var $=layui.$;
        $.ajax({
            method:"post",
            url:"/student/downHomework",
            data:{'filename':filename},
            dataType:"json"
            // success:function(data) {
            //     alert("下载成功")
            // },
            // error:function () {
            //     alert("下载失败")
            // }
        });
        filename = null;
    })
}

/*
*
* 提交作业
*
*
* */
layui.use(['form', 'layer', 'upload'], function () {
    var layer = layui.layer;
    var upload = layui.upload;
    var $ = layui.jquery;
    upload.render({
        elem: '#upload',
        url: '/student/uploadHomework',
        auto: false,//选择文件后不自动上传
        bindAction: '#commit',
        accept:'file'
        ,
        //上传前的回调
        before: function () {
            /*文件上传前，将表单其他信息读取出来*/
            this.data = {
                id:id,
                type:type,
                reinfo:$('input[name="info"]').val(),
                text: $('textarea[name="text"]').val()
            }
        },
        //选择文件后的回调
        choose: function (obj) {
            alert("文件加载成功");
            // obj.preview(function (index, file, result) {
            //     $('#preview').attr('src', result);
            // })
        },
        //操作成功的回调
        done: function (res, index, upload) {
            // var code = res.code === 0 ? 1 : 2;
            // layer.alert(res.msg, {icon: code}, function () {
            //     parent.window.location.reload();
            // })
            alert(res.msg);
        },
        //上传错误回调
        error: function (index, upload) {
            alert('上传失败！');
        }
    });
})