<%
     ServletContext sc = request.getServletContext();
     Object engineId = sc.getAttribute(com.exadel.amc.mc.war.EngineLauncher.MCENGINEID);

%>
<h1>Metrics Collector Engine (<%=engineId%>)</h1>


