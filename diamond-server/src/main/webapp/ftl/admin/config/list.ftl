<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>Diamond配置信息管理</title>
<script type="text/javascript">
   function confirmForDelete(){
       return window.confirm("你确认要删除该配置信息吗??");  
   }
   function queryConfigInfo(method){
       document.all.queryForm.method.value=method;
       document.all.queryForm.submit();       
   }
  
</script>
</head>
<#assign adminUrl="/admin.do">

<body>
<#include "/common/message.ftl"/>
<center><h1><strong>配置信息管理</strong></h1></center>
 <p align='center'>
       <form name="queryForm" action="${adminUrl}">
       <table align='center'>
           <tr>
               <td>dataId:</td>
               <td><input type="text" name="dataId"/></td>
               <td>组名:</td>
               <td><input type="text" name="group"/></td>
               <td>
                  <input type='hidden' name="pageNo" value='1'/>
                  <input type='hidden' name="method" value='${method!"listConfig"}'/>
                  <input type='hidden' name="pageSize" value='15'/>
                  <input type='button' value='查询' onclick="queryConfigInfo('listConfig');"/>
                  <input type='button' value='模糊查询' onclick="queryConfigInfo('listConfigLike');"/></td>
           </tr>
       </table>
       </form>
    </p>
   <p align='center'>
     <#if page??>
      <table border='1' width="800">
          <tr>
              <td>dataId</td>
              <td>组名</td>
              <td>操作</td>
          </tr>
          <#list page.pageItems as configInfo>
            <tr>
               <td name="tagDataID">
                  ${configInfo.dataId}
               </td>
              <td name="tagGroup">
                  ${configInfo.group}
               </td>
               <#assign getConfigInfoUrl="/admin.do?method=detailConfig&group=${configInfo.group}&dataId=${configInfo.dataId}">
               <#assign deleteConfigInfoUrl="/admin.do?method=deleteConfig&id=${configInfo.id}">
               <#assign saveToDiskUrl="/notify.do?method=notifyConfigInfo&group=${configInfo.group}&dataId=${configInfo.dataId}">
               <#assign previewUrl="/config.co?group=${configInfo.group}&dataId=${configInfo.dataId}">
              <td>
                 <a href="${getConfigInfoUrl}">编辑</a>&nbsp;&nbsp;&nbsp;
                 <a href="${deleteConfigInfoUrl}" onclick="return confirmForDelete();">删除</a>&nbsp;&nbsp;&nbsp;
                 <a href="${saveToDiskUrl}" target="_blank">保存磁盘</a>
                 <a href="${previewUrl}" target="_blank">预览</a>
              </td>
            </tr>
          </#list>
       </table>
       <p align='center'>
          总页数:${page.pagesAvailable}&nbsp;&nbsp;当前页:${page.pageNumber}
          &nbsp;&nbsp;&nbsp;&nbsp;
          <#assign nextPage="/admin.do?method=${method!'listConfig'}&group=${group!''}&dataId=${dataId!''}&pageNo=${page.pageNumber+1}&pageSize=15">
          <#assign prevPage="/admin.do?method=${method!'listConfig'}&group=${group!''}&dataId=${dataId!''}&pageNo=${page.pageNumber-1}&pageSize=15">
          <#assign firstPage="/admin.do?method=${method!'listConfig'}&group=${group!''}&dataId=${dataId!''}&pageNo=1&pageSize=15">
          <#assign lastPage="/admin.do?method=${method!'listConfig'}&group=${group!''}&dataId=${dataId!''}&pageNo=${page.pagesAvailable}&pageSize=15">
         <a href="${firstPage}">首页</a>&nbsp;&nbsp;
         <#if page.pageNumber==1 && page.pagesAvailable gt 1>
         <a href="${nextPage}">下一页</a>  &nbsp; &nbsp;
         <#elseif page.pageNumber gt 1 && page.pagesAvailable==page.pageNumber>
         <a href="${prevPage}">上一页</a>  &nbsp; &nbsp;
         <#elseif page.pageNumber==1 && page.pagesAvailable==1>
         <#else>
         <a href="${prevPage}">上一页</a>  &nbsp; &nbsp;
		 <a href="${nextPage}">下一页</a>  
         </#if> 
          <a href="${lastPage}">末页</a>&nbsp;&nbsp; 
       </p>
     </#if>
  </p>
  <p align='center'>
    <a href="/ftl/admin/config/new.ftl">添加配置信息</a>  &nbsp;&nbsp;&nbsp;&nbsp; <a href="/ftl/admin/dr/new.ftl">添加DR</a>  &nbsp;&nbsp;&nbsp;&nbsp; <a href="/ftl/admin/config/upload.ftl">上传配置信息</a>
  </p>
</body>
</html>