var React = require('react');

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

/* VIEW */
var CalendarEntry = React.createClass({
  propTypes: {
    //data: React.PropTypes.instanceOf(Entry)
  },
  render: function() {
    return (
      <div className="entry">
          <span className="fa fa-caret-right">
            {this.props.data.get('summary')}
          </span>
        </div>
    );
  }
});

var CalendarCell = React.createClass({
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
      <td onClick={this.onClick}
            className={"calendarCell" + (this.props.inFocussedMonth?"":" shadowCell")}>
          <div className="calendarCellBox">
            <div className="date">
              {this.props.children}
            </div>
            {
              this.props.data.map(function(result, index) {
                return <CalendarEntry data={result} key={'CalendarEntry_'+index} />
              })
            }
          </div>
        </td>
    );
  }
});

var ColHead = React.createClass({
  render: function() {
    return (
      <th>
          {this.props.day}
        </th>
    );
  }
});

var CalendarMonth = React.createClass({
  propTypes: {
    year: React.PropTypes.number,
    month: React.PropTypes.number,
    onClick: React.PropTypes.func,
    weekDays: React.PropTypes.arrayOf(React.PropTypes.string),
    //data: React.PropTypes.instanceOf(DateCollection)
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
      <div>
          <table className="calendarTable">
            <thead>
              <tr>
                {
                  this.props.weekDays.map(function(result) {
                    return <ColHead day={result} key={'ColHead_'+result.toString()} />
                  })
                }
              </tr>
            </thead>
            <tbody>
              {
                this.rowIndices.map(function(result, row){
                  return (
                    <tr key={'CalendarMonth-row_'+row}>
                    {
                      this.props.weekDays.map(function(result, col){
                        var day = row * 7 + col - this.dayOffset;
                        var date = new Date(this.firstDateOfMonth.getTime());
                        date.setDate(day);
                        return <CalendarCell
                          onClick={this.props.onClick}
                          date = {date}
                          inFocussedMonth={date.getMonth()===this.props.month}
                          data={
                              this.state.data.filter(function(datum){
                                return datum.isSameDay(date);
                              })
                            }
                          key={'CalendarCell_'+date.valueOf()}>
                            {date.getDate()}
                          </CalendarCell>
                      }, this)
                    }
                    </tr>
                  )
                }, this)
              }
            </tbody>
          </table>
        </div>
    );
  }
});

module.exports.CalendarMonth = CalendarMonth;
module.exports.weekDaysStrings = weekDaysStrings;
