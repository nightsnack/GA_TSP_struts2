<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>遗传算法求解TSP</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <meta name="description" content="map created using amCharts pixel map generator" />
    <%@ include file="/common/header.jsp" %>
   <style>
#map {
  width: 100%;
  height:70%;
}										
</style>
   
   <script type="text/javascript" src="https://www.amcharts.com/lib/3/ammap.js"></script>
		<script type="text/javascript" src="https://www.amcharts.com/lib/3/maps/js/myanmarLow.js"></script>

		<!-- amCharts javascript code -->
		<script type="text/javascript">
            var targetSVG = "M9,0C4.029,0,0,4.029,0,9s4.029,9,9,9s9-4.029,9-9S13.971,0,9,0z M9,15.93 c-3.83,0-6.93-3.1-6.93-6.93S5.17,2.07,9,2.07s6.93,3.1,6.93,6.93S12.83,15.93,9,15.93 M12.5,9c0,1.933-1.567,3.5-3.5,3.5S5.5,10.933,5.5,9S7.067,5.5,9,5.5 S12.5,7.067,12.5,9z";
            var planeSVG = "M19.671,8.11l-2.777,2.777l-3.837-0.861c0.362-0.505,0.916-1.683,0.464-2.135c-0.518-0.517-1.979,0.278-2.305,0.604l-0.913,0.913L7.614,8.804l-2.021,2.021l2.232,1.061l-0.082,0.082l1.701,1.701l0.688-0.687l3.164,1.504L9.571,18.21H6.413l-1.137,1.138l3.6,0.948l1.83,1.83l0.947,3.598l1.137-1.137V21.43l3.725-3.725l1.504,3.164l-0.687,0.687l1.702,1.701l0.081-0.081l1.062,2.231l2.02-2.02l-0.604-2.689l0.912-0.912c0.326-0.326,1.121-1.789,0.604-2.306c-0.452-0.452-1.63,0.101-2.135,0.464l-0.861-3.838l2.777-2.777c0.947-0.947,3.599-4.862,2.62-5.839C24.533,4.512,20.618,7.163,19.671,8.11z";
            
			AmCharts.makeChart("map",{
					"type": "map",
					"pathToImages": "http://www.amcharts.com/lib/3/images/",
					"addClassNames": true,
					"fontSize": 15,
					"color": "#000000",
					"projection": "mercator",
					"backgroundAlpha": 1,
					"backgroundColor": "rgba(255,255,255,1)",
					"dataProvider": {
						"map": "myanmarLow",
						"getAreasFromMap": true,
                        "linkToObject": "1",
						"images": [
							
                            {
        "id": "1",
        "color": "#000000",
        "svgPath": targetSVG,
        "title": "仰光",
        "latitude": <s:property value="latitude[0]"/>,    
        "longitude": <s:property value="longitude[0]"/>,

        "lines": [ 
                  
<c:forEach begin="0" end="12" varStatus="i">
{
       "latitudes": [ ${latitude[bestTour[i.index]]},${latitude[bestTour[i.index+1]]}],
       "longitudes":[ ${longitude[bestTour[i.index]]},${longitude[bestTour[i.index+1]]}]
},
</c:forEach>  
              ]
      },
      <c:forEach begin="0" end="13" varStatus="i">
		 {
		        "svgPath": targetSVG,
		        "title": ${i.index+1},
		        "color": "#000000",
		        "latitude": ${latitude[i.index]},
		        "longitude":${longitude[i.index]}
		 },
		</c:forEach>            
						]
					},
                
					"balloon": {
						"horizontalPadding": 15,
						"borderAlpha": 0,
						"borderThickness": 1,
						"verticalPadding": 15
					},
					"areasSettings": {
						"color": "rgba(139,252,199,1)",
						"outlineColor": "rgba(255,255,255,1)",
						"rollOverOutlineColor": "rgba(255,255,255,1)",
						"rollOverBrightness": 20,
						"selectedBrightness": 20,
						"selectable": true,
						"unlistedAreasAlpha": 0,
						"unlistedAreasOutlineAlpha": 0
					},
					"imagesSettings": {
						"alpha": 1,
						"color": "rgba(139,252,199,1)",
						"outlineAlpha": 0,
						"rollOverOutlineAlpha": 0,
						"outlineColor": "rgba(255,255,255,1)",
						"rollOverBrightness": 20,
						"selectedBrightness": 20,
						"selectable": true
					},
					"linesSettings": {
						"color": "#CC0000",
						"selectable": true,
						"rollOverBrightness": 20,
						"selectedBrightness": 20
					},
					"zoomControl": {
						"zoomControlEnabled": true,
						"homeButtonEnabled": false,
						"panControlEnabled": false,
						"right": 38,
						"bottom": 30,
						"minZoomLevel": 0.25,
						"gridHeight": 100,
						"gridAlpha": 0.1,
						"gridBackgroundAlpha": 0,
						"gridColor": "#FFFFFF",
						"draggerAlpha": 1,
						"buttonCornerRadius": 2
					}
				});
		</script>
   
   
  </head>
  <!-- ADD THE CLASS layout-top-nav TO REMOVE THE SIDEBAR. -->
  <body class="hold-transition skin-yellow layout-top-nav">
    <div class="wrapper">

      <header class="main-header">
        <nav class="navbar navbar-static-top">
          <div class="container">
            <div class="navbar-header">
              <a href="#" class="navbar-brand"><b>Night</b>SNACK</a>
              <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse">
                <i class="fa fa-bars"></i>
              </button>
            </div>

            
            <!-- Navbar Right Menu -->
          
          </div><!-- /.container-fluid -->
        </nav>
      </header>
      <!-- Full Width Column -->
      <div class="content-wrapper">
        <div class="container">
          <!-- Content Header (Page header) -->
          <section class="content-header">
            <h1>
              遗传算法求解TSP问题
              <small>计算结果</small>
            </h1>
            
          </section>

          <!-- Main content -->
          <section class="content">
           <div class="row">
            <!-- left column -->
            <div class="col-md-12">
              <!-- general form elements -->
             <div class="box">
                <div class="box-header with-border">
                  <h3 class="box-title">缅甸14个省的最佳走法</h3>
                </div><!-- /.box-header -->
                <div class="box-body">
                  最佳长度出现代数：<s:property value="bestT"/><br>
					最佳长度:<s:property value="bestLength"/><br>
					最佳路径：<s:iterator value="bestTour" var="cityid">
					<s:property value="cityid"/>,
					</s:iterator><br>
					<br>
                </div><!-- /.box-body -->
               
              </div><!-- /.box -->
            </div>
           </div>
                          <div id="map"></div>
           
           

           
           
          </section><!-- /.content -->
        </div><!-- /.container -->
      </div><!-- /.content-wrapper -->
      <footer class="main-footer">
        <div class="container">
          <div class="pull-right hidden-xs">
            <b>Version</b> 0.0.1
          </div>
          <strong>Copyright &copy; 2014-2016 <a href="http://nuitsnack.lofter.com">Night·Snack</a>.</strong> All rights reserved.
        </div><!-- /.container -->
      </footer>
    </div><!-- ./wrapper -->
<%@include file="/common/footer.jsp" %>

  </body>
  
</html>