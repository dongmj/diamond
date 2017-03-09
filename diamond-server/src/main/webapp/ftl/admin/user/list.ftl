<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>Diamond配置信息管理</title>
<script type="text/javascript">
   function confirmForDelete(){
       return window.confirm("你确认要删除该用户吗??");  
   }
   
   function changePassword(user,link){
       var newPass=window.prompt("请输入新密码：");
       if(newPass==null||newPass.length==0)
         return false;
       link.href=link.href+"&password="+newPass;
       return window.confirm("你确认要将"+user+"的密码修改为"+newPass+"吗??");  
   }
  
</script>
</head>
<body>
<#include "/common/message.ftl"/>
<center><h1><strong>用户管理</strong></h1></center>
   <p align='center'>
     <#if userMap??>
      <table border='1' width="800">
          <tr>
              <td>用户名</td>
              <td>密码</td>
              <td>操作</td>
          </tr>
          <#list userMap?keys as itemKey>
            <tr>
              <td>${itemKey}</td>
              <td>${userMap[itemKey]}</td>
              <#assign changePasswordUrl="/admin.do?method=changePassword&userName=${itemKey}">
				<#assign deleteUserUrl="/admin.do?method=deleteUser&userName=${itemKey}&password=${userMap[itemKey]}">
              <td>
                 <a href="${changePasswordUrl}" onclick="return changePassword('${itemKey}',this);">修改密码</a>&nbsp;&nbsp;&nbsp;
                 <a href="${deleteUserUrl}" onclick="return confirmForDelete();">删除</a>&nbsp;&nbsp;&nbsp;
              </td>
            </tr>
          </#list>
       </table>
    </#if>
  </p>
  <p align='center'>
    <a href="/ftl/admin/user/new.ftl">添加用户</a> &nbsp;&nbsp;&nbsp;&nbsp;<a href="/admin.do?method=reloadUser">重新加载用户信息</a>
  </p>
</body>
</html>