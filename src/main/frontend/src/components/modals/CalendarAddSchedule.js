import React, { useState, useRef } from "react";
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";
import '../../styles/CalendarAddSchedule.css'
import Modal from 'react-modal';

export default function CalendarAddSchedule({ isOpen, onClose, onEventAdded }/* { isOpen, onClose, onEventAdded } */) {
    const [lectureCode, setLectureCode] = useState(null);
    const [date, setDate] = useState(new Date());
    const [startTime, setStartTime] = useState(new Date());
    const [endTime, setEndTime] = useState(new Date());

    // const [added, setAdded] = useState({
    //     title: "",
    //     start: "",
    //     end: ""
    // })

    const onSubmit = (event) => {
        event.preventDefault();
        onEventAdded({
            lectureCode,
            date,
            startTime,
            endTime
        })
        onClose();
    }
    //if문으로 일정 과외 나누기

    return (

        <div className="addEvntBase">


            <Modal isOpen={isOpen} onRequestClose={onClose} className="addEvntModal">
                <button type="button"> 일정 </button>
                <button type="button"> 수업 </button>
                <form onSubmit={onSubmit} className="addEvntForm">

                    <div className="addEvntFormInsider">
                        <div>
                            <label className="text eventTitle">과외 선택</label><br></br>
                            <input className="UserInput inputTitle" placeholder="과외 선택" value={lectureCode} onChange={(e) => setLectureCode(e.target.value)} />
                        </div>

                        <div>
                            <label className="text eventDate">과외 날짜</label>
                            <DatePicker className="UserInput inputEnd startDate" selected={date} onChange={(date) => setDate(date)} />

                        </div>

                        <div>
                            <label className="text eventStartTime">과외 시작 시간</label>
                            <DatePicker className="UserInput inputStart startTime" showTimeSelect showTimeSelectOnly timeIntervals={15} timeCaption="Time" selected={startTime} dateFormat="h:mm aa" onChange={(time) => { setStartTime(time)}} />
                        </div>

                        <div>
                            <label className="text eventEndTime">과외 종료 시간</label>
                            <DatePicker className="UserInput inputEnd endTime" showTimeSelect showTimeSelectOnly timeIntervals={15} timeCaption="Time" selected={endTime} dateFormat="h:mm aa" onChange={(endTime) => setEndTime(endTime)} />
                        </div>

                        <div>
                            <button type="submit" className="closeBtn" /*  onClick={() => closeModal(false)} */>저장하기</button>
                        </div>
                    </div>


                </form>
            </Modal>
        </div>

    )

}
