import React, { useState, useEffect } from "react";
import "../App.css";
import Lecture from "../components/Lecture";
import {Container, List, Paper} from "@mui/material";
import AddLecture from "../components/AddLecture.js"
import {call,signout} from "../service/ApiService";



const MyStudent = () => {
    const [state, setState] = useState({ items: []});
    const [loading, setLoading] = useState(true);


    // componentDidMount 대신 userEffect 사용
    useEffect(() => {
        call("/eple/v1/mystudent/lecture", "GET", null).then((response) =>{
            setState({items: response.data});

            setLoading(false);
        });
    }, []);

    const addItem = (item) => {

        const thisItems = state.items;
        item.id = "ID-" + thisItems.length; // key를 위한 id추가
        thisItems.push(item); // 배열에 아이템 추가
        setState({ items: thisItems }); // 업데이트는 반드시 this.setState로 해야됨.
        console.log("items : ", state.items);




        call("/eple/v1/mystudent/lecture", "POST", item).then((response) =>
            setState({ items: response.data })
        );
    };

    const deleteItem = (item) => {
        call("/eple/v1/mystudent/lecture", "DELETE", item).then((response) =>
            setState({ items: response.data })
        );
    };

    const updateItem = (item) => {
        call("/eple/v1/mystudent/lecture", "PUT", item).then((response) =>
            setState({ items: response.data })
        );
    };



    var lectureItems = state.items.length > 0 && (
        <Paper style={{ margin: 16 }}>
            <List>
                {state.items.map((item, idx) => (
                    <Lecture
                        item={item}
                        key={item.id}
                        deleteItem={deleteItem}
                        updateItem={updateItem}
                    />
                ))}
            </List>
        </Paper>
    );

    //로딩중이 아닐 때 렌더링할 부분
    let lectureListPage = (
        <div>
            <Container maxWidth="md">
                <AddLecture addItem={addItem} />
                <div className="LectureList">{lectureItems}</div>
            </Container>
        </div>
    );

    let loadingPage = <h1> 로딩중..</h1>;
    let content = loadingPage;
    if(!loading){
        //로딩중이 아니면 lecture page
        content = lectureListPage;
    }

    // 3. props로 넘겨주기
    return <div className="MyStudent"> {content}
    </div>

};

export default MyStudent;

