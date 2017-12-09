<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Relatório Solar</title>
<!--Load the AJAX API-->
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
var properties = '${date}';
	google.charts.load('current', {
		'packages' : [ 'bar' ]
	});
	google.charts.setOnLoadCallback(drawChart);

	function drawChart() {
		var data = google.visualization.arrayToDataTable([
				[ 'Year', 'Usina 1', 'Usina 2', 'Usina 3' ],
				[ '2014', 1000, 400, 200 ], [ '2015', 1170, 460, 250 ],
				[ '2016', 660, 1120, 300 ], [ '2017', 1030, 540, 350 ] ]);

		var options = {
			chart : {
				title : 'GERAÇÃO DIÁRIA - KWH',
				subtitle : properties,
			}
		};

		var chart = new google.charts.Bar(document
				.getElementById('columnchart_material'));

		chart.draw(data, google.charts.Bar.convertOptions(options));
	}
</script>
</head>



<body>
	<h1 align="center">Relatório Solar: ${date}</h1>
	<div id="columnchart_material" style="width: 400; height: 600"></div>
</body>
</html>