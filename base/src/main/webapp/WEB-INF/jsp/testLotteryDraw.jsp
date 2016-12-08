<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<script type="text/javascript">
var names = "曾声亮,曾小黑,李yan,刘康,花花,阅子,羊咩咩";  //抽奖人
var timer = null; // 定时器Id
// 开始抽奖
function start(){
	document.getElementById("person").innerHTML = "";
	var index = null; //数组下标
	var nameArr = names.split(","); //解析人名
	var info = document.getElementById("info");
	// 新建定时器
	timer = window.setInterval(function(){
		index = parseInt(7 * Math.random()); //生成0~7的随机数
		info.value = nameArr[index];
	}, 100);
}

// 结束抽奖
function stop(){
	if(timer != null){
		window.clearInterval(timer); // 清除定时器
		var info = document.getElementById("info");
		var person = document.getElementById("person");
		person.innerHTML = info.value;
	}
}

// 清除记录
function clearRecord(){
	document.getElementById("person").innerHTML = "";
	document.getElementById("info").value = "";
}
</script>
<title>抽奖系统</title>
</head>
<body>
	<p>抽奖系统</p>
		 <input type="text" value="" id="info" readonly="readonly" /> <br/><br/>
	获奖人：<span id="person" style="color:red;"></span> <br/>
	<input type="button" value="开始"  onclick="start();"/>
	<input type="button" value="停止" onclick="stop();"/>
	<input type="button" value="清除" onclick="clearRecord();"/><br/>
	Message:${message }
</body>
</html>
