import React, { useState, useRef } from "react";
import Modal from 'react-modal';
import DatePicker from "react-datepicker";
import "react-datepicker/dist/react-datepicker.css";



export default function AddChart({ isOpen, onClose, onChartAdded }) {
    const [id, setId] = useState("");
    const [title, setTitle] = useState(" ");
    const [content, setContent] = useState(" ");
    const [homework, setHomework] = useState(" ");
    const [createdAt, setCreatedAt] = useState(new Date());
    const [updatedAt, setUpdatedAt] = useState(new Date());
    const [user_id, setUser_id] = useState(" ");


    const onSubmit = (data) => {
        data.preventDefault();
        onChartAdded({
            id,
            title,
            content,
            homework,
            // createdAt,
            // updatedAt,
            user_id
        })
        onClose();
    }
    //if문으로 일정 과외 나누기

    return (

        <div className="addChart">
            <Modal isOpen={isOpen} onRequestClose={onClose} className="addChartModal">
                <form onSubmit={onSubmit} className="addChartForm">

                    <div className="addChartFormInsider">
                         <div>
                            <label className="text charNum">수업 회차</label>
                            <input className="UserInput lectureId" value={id} onChange={(e) => setId(e.target.value)} />

                        </div>
                        <div>
                            <label className="text title">수업 이름</label>
                            <input className="UserInput lectureDate" value={title} onChange={(e) => setTitle(e.target.value)} />

                        </div>

                        <div>
                            <label className="text lectureContent">학습 내용 및 진도</label>
                            <textarea className="UserInput lectureContent" value={content} onChange={(e) => setContent(e.target.value)} />
                        </div>

                        <div>
                            <label className="text homework">숙제</label>
                            <textarea className="UserInput homework" value={homework} onChange={(e) => setHomework(e.target.value)} />
                        </div>
                        <div>
                            <label className="text user_id">작성자</label>
                            <textarea className="UserInput user_id" value={user_id} onChange={(e) => setUser_id(e.target.value)}/>
                        </div>

                        <div>
                            <button type="submit" className="closeBtn" /*  onClick={() => closeModal(false)} */>저장하기</button>
                        </div>
                        {/* <div>
                        <input className="UserInput inputNumber" value={number} onChange={(e) => setNumber(e.target.value)} />
                            <label className="numberOfLecture">회차</label><br></br>
                            
                        </div> */}

                        {/* <div>
                            <label className="text chartDate">수업 일시</label>
                            <input type = 'date' className="UserInput lectureDate" value={date} onChange={(e) => setDate(e.target.value)} />

                        </div>

                        <div>
                            <label className="lectureContents">학습 내용 및 진도</label>
                            <textarea className="lectureContents" value={contents} onChange={(e) => setContents(e.target.value)} />
                        </div>

                        <div>
                            <label className="homework">숙제</label>
                            <input className="homework" value={homework} onChange={(e) => setHomework(e.target.value)} />
                        </div>
                        <div>
                            <label className="homeworkOX">O, X</label>
                            <select className="homeworkOX" value={homeworkOX} onChange={(e) => setHomeworkOX(e.target.value)}>
                                <option>○</option>
                                <option>X</option>
                                <option>△</option>
                            </select>
                        </div>
                        <div>
                            <label className="progress">수행도</label>
                            <textarea className="progress" value={progress} onChange={(e) => setProgress(e.target.value)}/>
                        </div>

                        <div>
                            <button type="submit" className="closeBtn" onClick={() => closeModal(false)}>저장하기</button>
                        </div> */}
                    </div>


                </form>
            </Modal>
        </div>

    )

}