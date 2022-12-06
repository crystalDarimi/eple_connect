import React, { useState, useRef } from "react";
import DatePicker from "react-datepicker";
/* import Datetime from 'react-datetime'; */
import { setExtendedProp } from '@fullcalendar/react'
import "react-datepicker/dist/react-datepicker.css";
import '../../styles/CalendarAddSchedule.css'
import Modal from 'react-modal';
import Dropdown from 'react-dropdown';
import 'react-dropdown/style.css';
import { TextField } from "@mui/material";


export default function CalendarAddEvent({ isLecture, isOpen, onClose, onEventAdded, changeName }/* { isOpen, onClose, onEventAdded } */) {
    const [title, setTitle] = useState("");
    const [start, setStart] = useState(new Date());
    const [end, setEnd] = useState(new Date());
    const [lectureEntity, setLectureEntity] = useState(null)
    //lectureEntity... 값을 select 하기.
    const options = [
        'one', 'two', 'three'
    ]
    const defaultOption = options[0];


    const onSubmit = (event) => {
        event.preventDefault();
        onEventAdded({
            title,
            start,
            end,
            lectureEntity
        })
        onClose();
        // changeName({
        //     lectureTitle: {title},
        //     start: {start.toIS}

        // })
    }
    //if문으로 일정 과외 나누기

    return (

        <div className="addEvntBase">

            <Modal appElement={document.getElementById('app')} ariaHideApp={false} isOpen={isOpen} onRequestClose={onClose} className="addEvntModal">
                {/* {!isLecture && <form onSubmit={onSubmit} className="addEvntForm">

                    <div className="addEvntFormInsider">
                        <div>
                            <label className="text eventTitle">일정 제목</label><br></br>
                            <input className="UserInput inputTitle" placeholder="일정 제목" value={title} name = "LecturTitle" onChange={(e) => setTitle(e.target.value)} />
                        </div>

                        <div>
                            <label className="text eventStart">일정 시작</label>
                            <DatePicker className="UserInput inputStart start" showTimeSelect timeIntervals={15} timeCaption="Time" selected={start} name = "start" dateFormat="MM/dd/yyyy h:mm aa" onChange={(start) => setStart(start)} />

                        </div>

                        <div>
                            <label className="text eventEnd">일정 종료</label>
                            <DatePicker className="UserInput inputEnd end" showTimeSelect timeIntervals={15} timeCaption="Time" name="end" selected={end} dateFormat="MM/dd/yyyy h:mm aa" onChange={(end) => setEnd(end)} />
                        </div>

                        <div>
                            <button type="submit" className="closeBtn" >저장하기</button>
                        </div>
                    </div>


                </form>} */}
                {isLecture && <form onSubmit={onSubmit} className="addEvntForm">
                    <div className="addEvntFormInsider">
                        <div>
                            <label className="text dropdown">과외 선택</label>
                            <Dropdown className = "selectLecture" options={options} onChange={(lecture) => setLectureEntity(lecture)} value={defaultOption} placeholder="Select an option"  />
                        </div>
                        <div>
                            <label className="text eventTitle">과외 제목</label><br></br>
                            <input className="UserInput inputTitle" placeholder="일정 제목" value={title} name="LectureTitle" onChange={(e) => setTitle(e.target.value)} />
                        </div>

                        <div>
                            <label className="text eventStart">과외 시작</label>
                            <DatePicker className="UserInput inputStart start" showTimeSelect timeIntervals={15} timeCaption="Time" name="start" selected={start} dateFormat="MM/dd/yyyy h:mm aa" onChange={(start) => { setStart(start); setEnd(null) }} />
                        </div>

                        <div>
                            <label className="text eventEnd">과외 종료</label>
                            <DatePicker className="UserInput inputEnd end" showTimeSelect timeIntervals={15} timeCaption="Time" name="end" selected={end} dateFormat="MM/dd/yyyy h:mm aa" onChange={(end) => setEnd(end)} />
                        </div>

                        <div>
                            <button type="submit" className="closeBtn" >저장하기</button>
                        </div>
                    </div>
                </form>}

            </Modal>
        </div>

    )

}