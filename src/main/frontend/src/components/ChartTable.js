import "../styles/MyClass.css";
import React, { useState, useRef } from "react";
import AddChart from "./modals/AddChart";
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';

const ChartTable = () => {
    const [info, setInfo] = useState([]);
    const lectureNum = useRef(1);
    const [modalOpen, setModalOpen ] = useState(false); 

    const handleRemove = ({id}) => {
        const newInfo = info.filter((data) => data.id !== id)
        setInfo(newInfo);
    }




    const handleSave = (data) => {
        setInfo(info => info.concat(
            {
                id: lectureNum.current,
                title: data.title,
                content: data.content,
                homework: data.homework,
                createdAt: data.createdAt,
                updatedAt: data.updatedAt,
                user_id: data.user_id
                // number: lectureNum.current,
                // date: data.date,
                // contents: data.contents,
                // homework: data.homework,
                // homeworkOX: data.homeworkOX,
                // progress: data.progress
            }
        ))
        lectureNum.current += 1;
    }
    return (
        <div className='ProgressChart'>

            <div className="PageName PageNameProgressChart">
                <p>학습 진도표</p>
            </div>
            <section className="Chart">
                <table className="ChartTable">
                    <thead>
                        <th>클래스 번호</th>
                        <th>수업 이름</th>
                        <th>학습 내용 및 진도</th>
                        <th>숙제</th>
                        <th>생성 날짜</th>
                        <th>수정 날짜</th>
                        <th>작성자</th>
                        <th>삭제</th>
                    </thead>
                    <tbody>
                        {info.map(({id, title,content, homework, createdAt, updatedAt, user_id }) => (
                            <tr key={id + title + content + homework + createdAt + updatedAt + user_id}>
                                <td>{id}</td>
                                <td>{title}</td>
                                <td>{content}</td>
                                <td>{homework}</td>
                                <td>{createdAt}</td>
                                <td>{updatedAt}</td>
                                <td>{user_id}</td>
                                <td><DeleteIcon onClick = {() =>handleRemove({id})}/></td>
                            </tr>
                        ))}
                    </tbody>
                </table>
                <button className='add'onClick={()=> {setModalOpen(true)}}>기록 추가</button>
                <AddChart isOpen = {modalOpen} onClose = {()=> setModalOpen(false)} onChartAdded={(data) => handleSave(data)}/>
            </section>
        </div>
    )
}
export default ChartTable;