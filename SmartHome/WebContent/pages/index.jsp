<%@include file="elements/header.jsp" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>   
<%@page import="java.util.Date,java.text.Format,java.text.SimpleDateFormat" %>

<link class="include" rel="stylesheet" type="text/css" href="css/jquery.jqplot.min.css" />
<link class="include" rel="stylesheet" type="text/css" href="css/style.css" />
<script language="JavaScript" type="text/JavaScript" src="js/jquery.min.js"></script>
<script class="include" type="text/javascript" src="js/jquery.jqplot.min.js"></script>
    
<!-- End Don't touch this! -->

<!-- Additional plugins go here -->>
  <script class="include" type="text/javascript" src="js/plugins/jqplot.canvasTextRenderer.min.js"></script>
  <script class="include" type="text/javascript" src="js/plugins/jqplot.canvasAxisLabelRenderer.min.js"></script>
  <script class="include" type="text/javascript" src="js/plugins/jqplot.enhancedLegendRenderer.min.js"></script>
  <script class="include" type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.0/jquery-ui.min.js"></script>
 
 <style type="text/css">

   

  </style>
 
    <div class="chart-container">    
        <div id="chart1"></div>
        <div class="jqplot-datestamp"></div>
    </div>

    <pre class="code brush:js"></pre>
  
  <script class="code" type="text/javascript">
  var inside=new Array();  
  var outside=new Array();
	
	<c:forEach items="${temps}" var="tem" varStatus="tempStatus"> 
		<c:if test="${tem.sensor.id == 1}"> 
			inside[${tempStatus.index}] = ${tem.temp};
		</c:if>
		<c:if test="${tem.sensor.id == 2}"> 
			outside[${tempStatus.index}] = ${tem.temp};
		</c:if>  
	</c:forEach>  

  
$(document).ready(function(){

    var data = [inside,outside];
    var labels = ["Inside", "Outside"];
    var ticks = [[1, "v01"], [2, "v02"]];

    // make the plot
    plot1 = $.jqplot("chart1", data, {
        title: "Temperatures",
        animate: true,
        axesDefaults: {
            labelRenderer: $.jqplot.CanvasAxisLabelRenderer
        },
        seriesDefaults: {
            showMarker: false
        },
        legend: {
            show: true,
            renderer: $.jqplot.EnhancedLegendRenderer,
            placement: "outsideGrid",
            labels: labels,
            location: "ne",
            rowSpacing: "0px",
            rendererOptions: {
                // set to true to replot when toggling series on/off
                // set to an options object to pass in replot options.
                seriesToggle: 'normal',
                seriesToggleReplot: {resetAxes: true}
            }
        },
        axes: {
            xaxis: {
                label: 'Timp',
                pad: 1.01,
                tickOptions: {
                    showGridline: true
                }
            },
            yaxis: {
                label: 'Temperatura ',
                tickOptions: {
                    suffix: '�C'
                },
                padMin: 0,
                padMax: 1.05
            }
        },
        grid: {
            drawBorder: false,
            shadow: false,
            // background: 'rgba(0,0,0,0)'  works to make transparent.
            background: "white"
        }
    });

    // add a date at the bottom.

   // var d = new $.jsDate();
    //$("div.jqplot-datestamp").html("Generated on "+d.strftime("%v"));

    // make it resizable.
    
    $("div.chart-container").resizable({delay:20});

    $("div.chart-container").bind("resize", function(event, ui) {
        plot1.replot();
    });
});

</script>

<a href="/SmartHome/gpio.html?state=on">ON</a> | <a href="/SmartHome/gpio.html?state=off">OFF</a>

<table border="2px">
	<tr>
		<td>Sensor</td>
		<td>Time</td>
		<td>Temperature</td>
	</tr>
    <c:forEach items="${temps}" var="temp">
        <tr>
            <td>${temp.sensor.name} </td>            
            <td>
	            <jsp:useBean id="dateValue" class="java.util.Date" />
				<jsp:setProperty name="dateValue" property="time" value="${temp.timestamp}" />            
				<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${dateValue}"/> 
             </td>
            <td>${temp.temp}</td>
        </tr>
    </c:forEach>
</table>

<%@include file="elements/footer.jsp" %>