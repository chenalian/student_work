layui.use(['form', 'layer', 'upload','jquery'], function () {
    var layer = layui.layer;
    var upload = layui.upload;
    var $ = layui.jquery;
    upload.render({
        elem: '#upload',
        url: '/teacher/changePublishHomework',
        auto: false,//选择文件后不自动上传
        bindAction: '#commit1',
        accept:'file'
        ,
        //上传前的回调
        before: function () {
            /*文件上传前，将表单其他信息读取出来*/
            this.data = {
                id:$("#homeworkid").val() ,
                time: $('input[name="time"]').val(),
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
            alert('上传失败！' + index);
        }
    });
})
/**
 *
 * 时间日期选择器
 *
 */
layui.use('laydate', function(){
    var laydate = layui.laydate;

    //日期时间范围
    laydate.render({
        elem: '#test1'
    });
    //开启公历节日
    laydate.render({
        elem: '#test17'
        ,calendar: true
    });

    //自定义重要日
    laydate.render({
        elem: '#test18'
        ,mark: {
            '0-10-14': '生日'
            ,'0-12-31': '跨年' //每年的日期
            ,'0-0-10': '工资' //每月某天
            ,'0-0-15': '月中'
            ,'2017-8-15': '' //如果为空字符，则默认显示数字+徽章
            ,'2099-10-14': '呵呵'
        }
        ,done: function(value, date){
            if(date.year === 2017 && date.month === 8 && date.date === 15){ //点击2017年8月15日，弹出提示语
                layer.msg('这一天是：中国人民抗日战争胜利72周年');
            }
        }
    });

    //限定可选日期
    var ins22 = laydate.render({
        elem: '#test-limit1'
        ,min: '2016-10-14'
        ,max: '2080-10-14'
        ,ready: function(){
            ins22.hint('日期可选值设定在 <br> 2016-10-14 到 2080-10-14');
        }
    });

    //前后若干天可选，这里以7天为例
    laydate.render({
        elem: '#test-limit2'
        ,min: -7
        ,max: 7
    });

    //限定可选时间
    laydate.render({
        elem: '#test-limit3'
        ,type: 'time'
        ,min: '09:30:00'
        ,max: '17:30:00'
        ,btns: ['clear', 'confirm']
    });

    //同时绑定多个
    lay('.test-item').each(function(){
        laydate.render({
            elem: this
            ,trigger: 'click'
        });
    });

    //初始赋值
    laydate.render({
        elem: '#test19'
        ,value: '1989-10-14'
        ,isInitValue: true
    });

    //选中后的回调
    laydate.render({
        elem: '#test20'
        ,done: function(value, date){
            layer.alert('你选择的日期是：' + value + '<br>获得的对象是' + JSON.stringify(date));
        }
    });

    //日期切换的回调
    laydate.render({
        elem: '#test21'
        ,change: function(value, date){
            layer.msg('你选择的日期是：' + value + '<br><br>获得的对象是' + JSON.stringify(date));
        }
    });
    //不出现底部栏
    laydate.render({
        elem: '#test22'
        ,showBottom: false
    });

    //只出现确定按钮
    laydate.render({
        elem: '#test23'
        ,btns: ['confirm']
    });

    //自定义事件
    laydate.render({
        elem: '#test24'
        ,trigger: 'mousedown'
    });

    //点我触发
    laydate.render({
        elem: '#test25'
        ,eventElem: '#test25-1'
        ,trigger: 'click'
    });

    //双击我触发
    lay('#test26-1').on('dblclick', function(){
        laydate.render({
            elem: '#test26'
            ,show: true
            ,closeStop: '#test26-1'
        });
    });

    //日期只读
    laydate.render({
        elem: '#test27'
        ,trigger: 'click'
    });

    //非input元素
    laydate.render({
        elem: '#test28'
    });

    //墨绿主题
    laydate.render({
        elem: '#test29'
        ,theme: 'molv'
    });

    //自定义颜色
    laydate.render({
        elem: '#test30'
        ,theme: '#393D49'
    });

    //格子主题
    laydate.render({
        elem: '#test31'
        ,theme: 'grid'
    });


    //直接嵌套显示
    laydate.render({
        elem: '#test-n1'
        ,position: 'static'
    });
    laydate.render({
        elem: '#test-n2'
        ,position: 'static'
        ,lang: 'en'
    });
    laydate.render({
        elem: '#test-n3'
        ,type: 'month'
        ,position: 'static'
    });
    laydate.render({
        elem: '#test-n4'
        ,type: 'time'
        ,position: 'static'
    });
});