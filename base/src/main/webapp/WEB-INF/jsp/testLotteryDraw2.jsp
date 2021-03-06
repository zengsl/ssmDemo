<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<title>转盘</title>
<style>
    *{padding:0;margin:0}
    body{
        text-align:center
    }
    .ly-plate{
        position:relative;
        width:509px;
        height:509px;
        margin: 50px auto;
    }
    .rotate-bg{
        width:509px;
        height:509px;
        background:url(${Context_Path}/resource/images/lottery/ly-plate.png);
        position:absolute;
        top:0;
        left:0
    }
    .ly-plate div.lottery-star{
        width:214px;
        height:214px;
        position:absolute;
        top:150px;
        left:147px;
        /*text-indent:-999em;
        overflow:hidden;
        background:url(rotate-static.png);
        -webkit-transform:rotate(0deg);*/
        outline:none
    }
    .ly-plate div.lottery-star #lotteryBtn{
        cursor: pointer;
        position: absolute;
        top:0;
        left:0;
        *left:-107px
    }
</style>
</head>
<body>
    <div class="ly-plate">
        <div class="rotate-bg"></div>
        <div class="lottery-star"><img src="${Context_Path}/resource/images/lottery/rotate-static.png" id="lotteryBtn"></div>
    </div>
</body>
<script type="text/javascript" src="${Context_Path}/resource/js/jquery-1.7.2.min.js"></script>
<script src="${Context_Path}/resource/js/jQueryRotate.2.2.js"></script>

<script>
$(function(){
    var timeOut = function(){  //超时函数
        $("#lotteryBtn").rotate({
            angle:0, 
            duration: 10000, 
            animateTo: 2160,  //这里是设置请求超时后返回的角度，所以应该还是回到最原始的位置，2160是因为我要让它转6圈，就是360*6得来的
            callback:function(){
                alert('网络超时')
            }
        }); 
    }; 
    var rotateFunc = function(awards,angle,text){  //awards:奖项，angle:奖项对应的角度，text:提示文字
        $('#lotteryBtn').stopRotate();
        $("#lotteryBtn").rotate({
            angle:0, 
            duration: 5000, 
            animateTo: angle+1440, //angle是图片上各奖项对应的角度，1440是我要让指针旋转4圈。所以最后的结束的角度就是这样子^^
            callback:function(){
                alert(text)
            }
        }); 
    };
    
    $("#lotteryBtn").rotate({ 
       bind: 
         { 
            click: function(){
                var time = [0,1];
                    time = time[Math.floor(Math.random()*time.length)];
                if(time==0){
                    timeOut(); //网络超时
                }
                if(time==1){
                    var data = [1,2,3,0]; //返回的数组
                        data = data[Math.floor(Math.random()*data.length)];
                    if(data==1){
                        rotateFunc(1,157,'恭喜您抽中的一等奖')
                    }
                    if(data==2){
                        rotateFunc(2,247,'恭喜您抽中的二等奖')
                    }
                    if(data==3){
                        rotateFunc(3,22,'恭喜您抽中的三等奖')
                    }
                    if(data==0){
                        var angle = [67,112,202,292,337];
                            angle = angle[Math.floor(Math.random()*angle.length)]
                        rotateFunc(0,angle,'很遗憾，这次您未抽中奖')
                    }
                }
            }
         } 
       
    });
    
})
</script>
</html>