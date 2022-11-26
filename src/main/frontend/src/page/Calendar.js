import React, { Component } from 'react';
import FullCalendar from '@fullcalendar/react';
import dayGridPlugin from '@fullcalendar/daygrid';

import '../styles/Calendar.css';

class Calendar extends Component {
    render() {
        return (
            <div className="Calendar">
                <FullCalendar
                    defaultView="dayGridMonth"
                    plugins={[ dayGridPlugin ]}
                    events={[
                        { title: 'event 1', date: '2022-09-01' },
                        { title: 'event 2', date: '2022-09-02' }
                    ]}
                />
            </div>
        );
    }
}
export default Calendar;
