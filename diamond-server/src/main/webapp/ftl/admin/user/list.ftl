<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>Diamond������Ϣ����</title>
<script type="text/javascript">
   function confirmForDelete(){
       return window.confirm("��ȷ��Ҫɾ�����û���??");  
   }
   
   function changePassword(user,link){
       var newPass=window.prompt("�����������룺");
       if(newPass==null||newPass.length==0)
         return false;
       link.href=link.href+"&password="+newPass;
       return window.confirm("��ȷ��Ҫ��"+user+"�������޸�Ϊ"+newPass+"��??");  
   }
  
</script>
</head>
<body>
<#include "/common/message.ftl"/>
<center><h1><strong>�û�����</strong></h1></center>
   <p align='center'>
     <#if userMap??>
      <table border='1' width="800">
          <tr>
              <td>�û���</td>
              <td>����</td>
              <td>����</td>
          </tr>
          <#list userMap?keys as itemKey>
            <tr>
              <td>${itemKey}</td>
              <td>${userMap[itemKey]}</td>
              <#assign changePasswordUrl="/admin.do?method=changePassword&userName=${itemKey}">
				<#assign deleteUserUrl="/admin.do?method=deleteUser&userName=${itemKey}&password=${userMap[itemKey]}">
              <td>
                 <a href="${changePasswordUrl}" onclick="return changePassword('${itemKey}',this);">�޸�����</a>&nbsp;&nbsp;&nbsp;
                 <a href="${deleteUserUrl}" onclick="return confirmForDelete();">ɾ��</a>&nbsp;&nbsp;&nbsp;
              </td>
            </tr>
          </#list>
       </table>
    </#if>
  </p>
  <p align='center'>
    <a href="/ftl/admin/user/new.ftl">����û�</a> &nbsp;&nbsp;&nbsp;&nbsp;<a href="/admin.do?method=reloadUser">���¼����û���Ϣ</a>
  </p>
</body>
</html>