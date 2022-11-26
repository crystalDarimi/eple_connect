import React, { Component,useState, useRef } from 'react';
import FullCalendar from '@fullcalendar/react';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import interactionPlugin from "@fullcalendar/interaction"
import listPlugin from '@fullcalendar/list';
import '../styles/Calendar.css';
import {call} from "../service/ApiService";

const Calendar = (props) => {
    const [state, setState] = useState({ schedules: []});
    const [hoverBtn, setHover] = useState(false);
    const [event, setEvent] = useState({
        lectureCode : " ",
        date : " ",
        startTime : " ",
        endTime : " " })
    const calendarRef = useRef(null);
    const onEventAdded = added => {
        let calendarApi = calendarRef.current.getApi();
        calendarApi.addEvent(added);
    }

        return (
            <div className="Calendar">
                <FullCalendar
                    defaultView="dayGridMonth"
                    plugins={[dayGridPlugin , interactionPlugin]}
                    eventContent={renderEventContent}
                    events={[
                        {title: 'event 1', date: '2022-09-01'},
                        {title: 'event 2', date: '2022-09-02'}
                    ]}
                />
            </div>
        )





}

function renderEventContent(eventInfo){
    return (
        <>
        <b> {eventInfo.timeText}</b>
            <i>{eventInfo.event.title}</i>
        </>
    )
}
export default Calendar;
