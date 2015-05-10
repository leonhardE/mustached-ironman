var React = require('react');
var App = require('./components/react-calendar.jsx');

/* MODEL */
var Entry = Backbone.Model.extend({
  defaults: {
    date: new Date(),
    summary: 'date'
  },
  isSameDay: function(other) {
    var date = this.get('date');
    return date.getYear() === other.getYear() && date.getMonth() === other.getMonth() &&
      date.getDate() === other.getDate();
  }
});

var DateCollection = Backbone.Collection.extend({
  model: Entry
});

var dateCollection = (function() {
  var date1 = new Entry({
    date: new Date(2015, 3, 1),
    summary: 'first of April'
  });
  var date2 = new Entry({
    date: new Date(),
    summary: 'today'
  });
  return new DateCollection([date1, date2]);
})();


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
            .add(new Entry({
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
