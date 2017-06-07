<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>   
<html >   
<head>   
    <meta charset="utf-8">   
    <title>Login</title>   
     <link rel="stylesheet" type="text/css" href="../css/Login.css" />  
</head>   
<body>   
    <div id="login">   
        <h1>药品管理系统</h1>   
        <h2>V1.0</h2>
        <form action="/HIS/user/choose" method="post">   
            <input class="a" type="text" required="required" placeholder="用户名" name="uname"></input>   
            <input class="a" type="password" required="required" placeholder="密码" name="pwd"></input> 
     		<input type="radio" name="C" value="系统管理员">系统管理员
      		<input type="radio" name="C" value="药品管理员">药品管理员   		
            <button class="but" type="submit">登陆</button>
        </form>   
    </div>
    <div class = "kefu">客服电话：166166788</div>   
</body>   
</html>  