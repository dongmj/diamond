<%
Object o = request.getAttribute("message");
if(o==null||o.equals("")){
	out.println("success");
}else{
	out.println(o);
}
%>