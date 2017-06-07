<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   

<div class="pager"> 
					<div class="k1"></div>
					<div class="k2"> 
                <a class = "frist" href="#">首页</a>  
                <a class = "last" href="#"> ◄上一页</a>   
                <a class = "next" href="#"> 下一页► </a>  
                <a class = "end" href="#"> 末页</a>  
            总${pageEntity.totalsCount}条，第${pageEntity.currentPage}/${pageEntity.totalsPage}页，到第<input size=2 id="goInput" value=''/>页,  
                    <input type="button" value="提交" onclick="gotoPageByInput();"/>  
                    </div>
</div>

