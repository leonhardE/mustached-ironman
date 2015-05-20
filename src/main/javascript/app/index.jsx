var React = require('react');
var App = require('./components/react-calendar.jsx')();

var dateCollection = new App.DateCollection();
dateCollection.url = '/webapi/data/jsons';

dateCollection.fetch({
	success: function(coll) {

		React.render(
			<div>
		      <button onClick={function() {
		          dateCollection
		            .findWhere({summary: 'today'})
		            .set({summary: 'do something'})
		        }}>change something
		      </button>
		      <button onClick={function() {
		          dateCollection
		            .add(new App.Entry({
		                date: new Date(),
		                summary: 'hello'
		              }))
		        }}>add something
		      </button>
		      <App.CalendarMonth
		        year={new Date().getFullYear()}
		        month={new Date().getMonth()}
		        onClick={console.log.bind(console)}
		        weekDays={App.weekDaysStrings}
		        data={dateCollection} />
		   </div>,
			document.getElementById('calendar')
		);

	}
});

// TEST FUNCTIONS
$('#read-btn').on('click',
	function() {
		var dates = new App.DateCollection();
		dates.url = '/webapi/data/jsons';
		dates.fetch({
			success: function(dates, res, opt) {
				dates.each(function(date) {
					console.log(date.get('date') + "= " + date.get('summary'));
				});
			}
		});
		console.log(dates);
	}
);

$('#save-btn').click(function() {
		dateCollection.create({
			success: function(dates, res, opt) {
				console.log("success: " + res);
			},
			error: function(dates, res, opt) {
				console.log("error: " + res);
			}
		});
});
