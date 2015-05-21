// Morris.js Charts sample data for SB Admin template

$(function() {
	function InformacoesClass() {
		this.carregar = function () {		
		/** carregar clientes */
		$.getJSON("./json/json-clientes.jsf", function( data ) {
			var items = [];		
			$.each( data, function(key, val) {
				   console.log(val.value);
				   console.log("---");
				   console.log(val.id); 
				    
				});
			// Area Chart
		    Morris.Area({
		        element: 'morris-area-chart',
		               data: [
		                      { y: '2009', a: 75,  b: 65 },
		                      { y: '2010', a: 50,  b: 40 },
		                      { y: '2011', a: 75,  b: 65 },
		                      { y: '2012', a: 100, b: 90 },  
		                      { y: '2013', a: 100, b: 90 },
		                      { y: '2014', a: 75,  b: 65 },
		                      { y: '2015', a: 50,  b: 40 },
		                    ],
		                    xkey: 'y',
		                    ykeys: ['a', 'b'],
		                    labels: ['Clientes Pessoa Jurídica', 'Clientes Pessoa Física'],
		                    hideHover: 'auto',
		                    resize: true
		    });


			    // Donut Chart
			    Morris.Donut({
			        element: 'morris-donut-chart',
			        data: [{
			            label: "Clientes Pessoa Jurídica",
			            value: 120
			        }, {
			            label: "Clientes Pessoa Física",
			            value: 300
			        }, {
			            label: "Vendedores",
			            value: 20
			        }],
			        resize: true
			    });

			    // Line Chart
			    Morris.Line({
			        // ID of the element in which to draw the chart.
			        element: 'morris-line-chart',
			        // Chart data records -- each entry in this array corresponds to a point on
			        // the chart.
			        data: [{
			            d: '2012-10-01',
			            visits: 802
			        }, {
			            d: '2012-10-02',
			            visits: 783
			        }, {
			            d: '2012-10-03',
			            visits: 820
			        }, {
			            d: '2012-10-04',
			            visits: 839
			        }, {
			            d: '2012-10-05',
			            visits: 792
			        }, {
			            d: '2012-10-06',
			            visits: 859
			        }, {
			            d: '2012-10-07',
			            visits: 790
			        }, {
			            d: '2012-10-08',
			            visits: 1680
			        }, {
			            d: '2012-10-09',
			            visits: 1592
			        }, {
			            d: '2012-10-10',
			            visits: 1420
			        }, {
			            d: '2012-10-11',
			            visits: 882
			        }, {
			            d: '2012-10-12',
			            visits: 889
			        }, {
			            d: '2012-10-13',
			            visits: 819
			        }, {
			            d: '2012-10-14',
			            visits: 849
			        }, {
			            d: '2012-10-15',
			            visits: 870
			        }, {
			            d: '2012-10-16',
			            visits: 1063
			        }, {
			            d: '2012-10-17',
			            visits: 1192
			        }, {
			            d: '2012-10-18',
			            visits: 1224
			        }, {
			            d: '2012-10-19',
			            visits: 1329
			        }, {
			            d: '2012-10-20',
			            visits: 1329
			        }, {
			            d: '2012-10-21',
			            visits: 1239
			        }, {
			            d: '2012-10-22',
			            visits: 1190
			        }, {
			            d: '2012-10-23',
			            visits: 1312
			        }, {
			            d: '2012-10-24',
			            visits: 1293
			        }, {
			            d: '2012-10-25',
			            visits: 1283
			        }, {
			            d: '2012-10-26',
			            visits: 1248
			        }, {
			            d: '2012-10-27',
			            visits: 1323
			        }, {
			            d: '2012-10-28',
			            visits: 1390
			        }, {
			            d: '2012-10-29',
			            visits: 1420
			        }, {
			            d: '2012-10-30',
			            visits: 1529
			        }, {
			            d: '2012-10-31',
			            visits: 1892
			        }, ],
			        // The name of the data record attribute that contains x-visitss.
			        xkey: 'd',
			        // A list of names of data record attributes that contain y-visitss.
			        ykeys: ['visits'],
			        // Labels for the ykeys -- will be displayed when you hover over the
			        // chart.
			        labels: ['Visits'],
			        // Disables line smoothing
			        smooth: false,
			        resize: true
			    });

			    // Bar Chart
			    Morris.Bar({
			        element: 'morris-bar-chart',
			        data: [{
			            device: 'iPhone',
			            geekbench: 136
			        }, {
			            device: 'iPhone 3G',
			            geekbench: 137
			        }, {
			            device: 'iPhone 3GS',
			            geekbench: 275
			        }, {
			            device: 'iPhone 4',
			            geekbench: 380
			        }, {
			            device: 'iPhone 4S',
			            geekbench: 655
			        }, {
			            device: 'iPhone 5',
			            geekbench: 1571
			        }],
			        xkey: 'device',
			        ykeys: ['geekbench'],
			        labels: ['Geekbench'],
			        barRatio: 0.4,
			        xLabelAngle: 35,
			        hideHover: 'auto',
			        resize: true
			    });

			
		});
};
	}
	var info     = new InformacoesClass();
	info.carregar();
    // Area Chart
  

});
