<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page isELIgnored="false"%>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
String webRoot = request.getContextPath()+"/";
String webRootPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+webRoot;
String webRootImgPath = request.getScheme()+"://"+request.getServerName()+webRoot;
pageContext.setAttribute("webRoot",webRootPath);
pageContext.setAttribute("webRootImg",webRootImgPath);
%>
