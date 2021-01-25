var stompClient = null;// 创建全局变量
var receviemessages=null;
var isMessageing=false;
function receiveMessage() {
    if (isMessageing)
    {
        return receviemessages;
    }
}
function connect(distinctPath,sendPath) {// 连接方法（连接成功后会自动接收服务器传来的消息）
    var socket = new SockJS(distinctPath);
    stompClient = Stomp.over(socket);
    console.log("连接服务器成功");
    stompClient.connect({}, function(frame) {
        // setConnected(true);
        stompClient.subscribe(sendPath, function(greeting) {
            // isMessageing=true;
            // receviemessages=JSON.parse(greeting.body);
            // console.log("接收服务器传来的信息");
            // // console.log(JSON.parse(greeting.body));
            // console.log(greeting)
            // console.log(greeting.body)
            var info=greeting.body;
                layui.use(['layer','jquery'],function () {
                    var $=layui.$;
                    /*
                    *
                    * 将消息插入固定下拉列表位置
                    *
                    * */
                    var node = $('<dd><a href="javascript:void(0);" onclick="alert(\'' + info + '\')">' + info + '</a></dd>');
                    $('#messageBox').append(node);

                // <a href="javascript:void(0);" onclick="alert('1:1班:王渊老师:发布:60作业')">1:1班:王渊老师:发布:60作业</a>
                });
        });
    });
}
// 断开连接方法
function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
        console.log("关闭服务器连接成功");
    }
}
// 发送消息 meaaages智能为json数据
function sendMessage(receviePath,messages) {
    stompClient.send(receviePath, {}, messages
    );
}
function teacherconnect(id)
{
    connect("/chat","/topic/teacherinfo/"+id+"/");
}
function studentconnect(id)
{
    connect("/chat","/topic/studentinfo/"+id+"/");
}

