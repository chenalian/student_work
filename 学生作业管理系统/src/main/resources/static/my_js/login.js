/*登录处理*/
layui.use('form', function(){
    var form = layui.form;
    var $=layui.$;
    // 点击图片更换验证码
    $("#suiji").click(
        function () {
            this.src="/login/indentycode?t="+new Date().getTime();
        }
    );
    //监听提交
    form.on('submit(formDemo)', function(data){
        // layer.msg(JSON.stringify(data.field));
        var data=$("#form").serialize();
        // alert(data);
        $.ajax({
            method:"post",
            url:"/login/form",
            data:data,
            dataType:"json",
            success:function(data) {
                var user=data.msg;
                if (user == 'student')
                {
                    window.location.href="/student";

                }
                else if (user == 'teacher')
                {
                    window.location.href="/teacher";
                }
                else{
                    alert(user);
                }
            },
            error:function () {
                alert(data.msg)
            }
        });
        return false;
    });
});

