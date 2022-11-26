import React, { Component,useState, useRef } from 'react';
import FullCalendar from '@fullcalendar/react';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import interactionPlugin from "@fullcalendar/interaction"
import '../../node_modules/@fullcalendar/common/main.css';
import AddIcon from '@mui/icons-material/Add';
import listPlugin from '@fullcalendar/list';
import '../styles/Calendar.css';
import {call} from "../service/ApiService";
import CalendarAddSchedule from "../components/modals/CalendarAddSchedule";

const Calendar = (props) => {
    const calendarRef = useRef(null);
    const [modalOpen, setModalOpen ] = useState(false);
    const [eventAdded, setEventAdded] = useState({
        lectureCode : " ",
        date : " ",
        startTime : " ",
        endTime : " "
    })
    const onEventAdded = added => {
        let calendarApi = calendarRef.current.getApi();
        calendarApi.addEvent(added);
    }

    const [hoverBtn, setHover] = useState(false);

    function openMouseHover(){
        setHover(true);
    }
    function closeMouseHover(){
        setHover(false);
    }

        return (
            <div className="Calendar">
                <FullCalendar
                    ref = {calendarRef}
                    defaultView="dayGridMonth"
                    plugins={[dayGridPlugin ,timeGridPlugin, interactionPlugin]}
                    headerToolbar={{
                        left: "prev next",
                        center: "title",
                        right: "dayGridMonth timeGridWeek today"
                    }}
                    contentHeight={550}
                    selectable={true} //날짜 드래그 선택
                    editable={true} //일정 옮김
                    dayHeaderFormat={{
                        weekday: 'long'
                    }}
                    eventContent={renderEventContent}
                    event = {{
                        onEventAdded
                    }}
                />
                <div>
                    <button className="PlusBtn"  onMouseEnter={openMouseHover} >
                        <AddIcon />
                        <div className='PlusBtnWord'>
                            만들기
                        </div>
                    </button>
                </div>
                {hoverBtn && <div className='hoverButtons'>
                    <button className='schedule'onMouseEnter={openMouseHover} onMouseLeave= {closeMouseHover} onClick={()=> {setModalOpen(true)}}>일정 추가</button>
                    <button className='lecture'onMouseEnter={openMouseHover} onMouseLeave={closeMouseHover} onClick={()=> {setModalOpen(true)}}>과외 추가</button>
                </div>}
                <div>
                    <CalendarAddSchedule isOpen = {modalOpen} onClose = {()=> setModalOpen(false)} onEventAdded={(added) => onEventAdded(added)}/>
                </div>
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
