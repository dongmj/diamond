<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>���������Ϣ</title>
	<script src="../../../js/prototype_for_validation.js" type="text/javascript"></script>
    <script src="../../../js/effects.js" type="text/javascript"></script>
	<script src="../../../js/validation_cn.js" type="text/javascript"></script>
	<script src="../../../js/jquery-1.4.4.js" type="text/javascript"></script>
	<style type="text/css">
		body td {
			color: #333;
			font-family: Arial, Helvetica, sans-serif;
			font-size: 10pt;
		}
		.validation-advice {
			margin: 0px 0;
			padding: 0px;
			margin-left: 10px;
			color : #FF3300;
			font-weight: bold;
			display: inline;
		}
		</style>
</head>
<body>
<#include "/common/message.ftl"/>
<center><h1><strong>����DR</strong></h1></center>
<p align='center'>
     <form action="/admin.do?method=postConfig" method="post" id="config-form">
     	<input type="hidden" name="dataId" id="dataId"/>
     	<input type="hidden" name="group" id="group"/>
        <table align='center'>
        <tr>
            <td>��ԴID:</td>
            <td> <input id="resId" type='text' width="256" class="required max-length-128"/></td>
        </tr>
        <tr>
            <td>��Դ����:</td>
            <td>
               <input id="attr" type='text' width="256" class="required max-length-128"/>
            </td>
        </tr>
        <tr>
            <td>Ӧ��:</td>
            <td>
               <input id="app" type='text' width="256" class="required max-length-128"/>
            </td>
        </tr>
         <tr>
            <td colspan="2">����ֵ:</td>
         </tr> 
         <tr>  
            <td colspan="2">
               <textarea cols="100" rows="20" name="content" class="required max-length-65535"></textarea>
            </td>
        </tr>
        <tr> 
            <td colspan="2"><input type="submit" value="�ύ"/>
        </tr>
     </form>
  </p>
  <script type="text/javascript">
    	new Validation('config-form',{immediate:true}); 
    	$(document).ready(function() {
			$("#attr").change(function() {
				updateDataId();
			});
			$("#resId").change(function() {
				updateDataId();
			});
			$("#app").change(function() {
				var group = $(this).val();
				$("#group").val(group);
				updateDataId();
			});
			function updateDataId() {
				var app = $("#app").val();
				var resId = $("#resId").val();
				var attr = $("#attr").val();
				var dataId = "DRM." + app + ":name=" + resId + "." + attr + ",version=2.0@DRM";
				$("#dataId").val(dataId);
			}
    	});
  </script>
  </body>
  </html>