<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>���������Ϣ</title>

	<script src="../../../js/prototype_for_validation.js" type="text/javascript"></script>
    <script src="../../../js/effects.js" type="text/javascript"></script>
	<script src="../../../js/validation_cn.js" type="text/javascript"></script>
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
<center><h1><strong>���þܾ��������</strong></h1></center>
<p align='center'>
     <form action="/admin.do?method=setRefuseRequestCount" method="post" id="count-form">
        <table align='center'>
        <tr>
            <td>��ǰֵ:</td>
            <td><c:out value="${count}"/></td>
        </tr>
        
        <tr>
            <td>����ֵ:</td>
            <td>
               <input type='text' name='count' width="256" class="required validate-number"/>
            </td>
        </tr>
        <tr> 
            <td colspan="2"><input type="submit" value="�ύ"/>
        </tr>
     </form>
  </p>
<script type="text/javascript">
    	new Validation('count-form',{immediate:true}); 
  </script>
  </body>
  </html>