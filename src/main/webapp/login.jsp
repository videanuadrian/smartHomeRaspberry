<%@ taglib prefix="j" uri="/jodd" %>
<%@ taglib prefix="joy" uri="/jodd-joy" %>
<%@ page import="jodd.servlet.ServletUtil" %>
<% ServletUtil.storeContextPath(pageContext, "CTX");%>
<html>
<head>
	<title>smartHome | Sign In</title>
</head>
<body>
  <joy:auth auth="false">
  
    <form  action="index.html" id="login" method="post" autocomplete="off">
        <input type="hidden" name="path">
        User: <input type="text" name="j_username"/>
        Password: <input type="text" name="j_password"/>
        <input type="submit"/>
    </form>
  
   </joy:auth>
</body>
</html>