(function() {
  "use strict";

  var weekDaysStrings = ['Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday',
    'Friday', 'Saturday'
  ];
  var weekDaysShortStrings = ['Su', 'Mo', 'Tu', 'We', 'Th', 'Fr', 'Sa'];

  /* UTIL */
  function validateObjectType(property, type) {
    if (property instanceof type) {
      return;
    }
    throw new Error('expected instance of ' + type.name + ', but type was ' +
      typeof(property) + '.');
  }

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

  /* VIEW */
  var CalendarEntry = React.createClass({displayName: "CalendarEntry",
    propTypes: {
      data: React.PropTypes.instanceOf(Entry)
    },
    render: function() {
      return (
        React.createElement("div", {className: "entry"}, 
          React.createElement("span", {className: "fa fa-caret-right"}, 
            this.props.data.get('summary')
          )
        )
      );
    }
  });

  var CalendarCell = React.createClass({displayName: "CalendarCell",
    propTypes: {
      onClick: React.PropTypes.func,
      date: React.PropTypes.instanceOf(Date),
      inFocussedMonth: React.PropTypes.bool,
      data: React.PropTypes.array // arrayOf(Entry)
    },
    onClick: function(e) {
      this.props.onClick(e.nativeEvent, this.props.date.toLocaleDateString());
    },
    render: function() {
      return (
        React.createElement("td", {onClick: this.onClick, 
            className: "calendarCell" + (this.props.inFocussedMonth?"":" shadowCell")}, 
          React.createElement("div", {className: "calendarCellBox"}, 
            React.createElement("div", {className: "date"}, 
              this.props.children
            ), 
            
              this.props.data.map(function(result, index) {
                return React.createElement(CalendarEntry, {data: result, key: 'CalendarEntry_'+index})
              })
            
          )
        )
      );
    }
  });

  var ColHead = React.createClass({displayName: "ColHead",
    render: function() {
      return (
        React.createElement("th", null, 
          this.props.day
        )
      );
    }
  });

  var CalendarMonth = React.createClass({displayName: "CalendarMonth",
    propTypes: {
      year: React.PropTypes.number,
      month: React.PropTypes.number,
      onClick: React.PropTypes.func,
      weekDays: React.PropTypes.arrayOf(React.PropTypes.string),
      data: React.PropTypes.instanceOf(DateCollection)
    },
    getInitialState: function() {
      this.firstDateOfMonth = new Date(this.props.year, this.props.month, 1);
      var firstDayOfMonth = this.firstDateOfMonth.getDay();

      this.dayOffset = (firstDayOfMonth - 1) % 7;
      this.rowIndices = [1, 2, 3, 4, 5, 6];
      return {
        data: this.props.data
      };
    },
    componentWillMount: function() {
      this.props.data.on('change add', function() {
        this.forceUpdate();
      }.bind(this));
    },
    componentWillUnmount: function() {
      this.props.data.off('change');
    },
    render: function() {
      return (
        React.createElement("div", null, 
          React.createElement("table", {className: "calendarTable"}, 
            React.createElement("thead", null, 
              React.createElement("tr", null, 
                
                  this.props.weekDays.map(function(result) {
                    return React.createElement(ColHead, {day: result, key: 'ColHead_'+result.toString()})
                  })
                
              )
            ), 
            React.createElement("tbody", null, 
              
                this.rowIndices.map(function(result, row){
                  return (
                    React.createElement("tr", {key: 'CalendarMonth-row_'+row}, 
                    
                      this.props.weekDays.map(function(result, col){
                        var day = row * 7 + col - this.dayOffset;
                        var date = new Date(this.firstDateOfMonth.getTime());
                        date.setDate(day);
                        return React.createElement(CalendarCell, {
                          onClick: this.props.onClick, 
                          date: date, 
                          inFocussedMonth: date.getMonth()===this.props.month, 
                          data: 
                              this.state.data.filter(function(datum){
                                return datum.isSameDay(date);
                              }), 
                            
                          key: 'CalendarCell_'+date.valueOf()}, 
                            date.getDate()
                          )
                      }, this)
                    
                    )
                  )
                }, this)
              
            )
          )
        )
      );
    }
  });

  React.render(
    React.createElement("div", null, 
      React.createElement("button", {onClick: function() {
          dateCollection
            .findWhere({summary: 'today'})
            .set({summary: 'do something'})
        }}, "change something"
      ), 
      React.createElement("button", {onClick: function() {
          dateCollection
            .add(new Entry({
                date: new Date(),
                summary: 'hello'
              }))
        }}, "add something"
      ), 
      React.createElement(CalendarMonth, {
        year: new Date().getFullYear(), 
        month: new Date().getMonth(), 
        onClick: console.log.bind(console), 
        weekDays: weekDaysStrings, 
        data: dateCollection})
    ),
    document.getElementById('calendar')
  );

})();
