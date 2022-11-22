import React, { useState} from "react";
import "./App.css";
import Lecture from "./Lecture";
import {Container,List, Paper} from "@mui/material";
import AddLecture from "./AddLecture.js"
import {call} from "./service/ApiService";
import {useEffect} from "react"
import Sidebar from "./components/Sidebar";
import Header from "./components/Header";

/*
function App(){
    const [items, setItems] = useState([]);
    useEffect(() =>{
        call("/eple/v1/mystudent/lecture","GET",null)
            .then((response)=>setItems(response.data));

        const requestOptions = {
            method : "GET",
            headers:{"Content-Type":"application/json"},
        };
        fetch("http://localhost:8080/eple",requestOptions)
            .then((response)=>response.json())
            .then(
                (response)=>{
                    setItems(response.data);
                },
                (error) => {}
            );



    },[]);


    const addItem = (item) =>{
        item.id = "ID-"+item.length;
        setItems([...items,item]);
        console.log("items: ",items)
    }

    const deleteItem = (item)=>{
        //삭제할 아이템 탐색
        const newItems = items.filter(e=> e.id !== item.id)
        //식제될 아이템을 제외한 아이템을 다시 배열에 저장
        setItems([...newItems]);
    }
    const editItem=(item)=>{
        call("/eple/v1/mystudent/lecture","PUT",item)
            .then((response)=>setImmediate(response.data));
        //setItems([...items]);
    };

    let lectureItems =
        items.length > 0 && (
            <Paper style={{margin : 16}}>
                <List>
                    {items.map((item)=>(
                        <Lecture item = {item}
                                 key = {item.id}
                                 editItem = {editItem}
                                 deleteItem = {deleteItem}
                        />
                    ))}
                </List>
            </Paper>
        );



    return (<div className="App">
            <Container maxWidth="md">
                <AddLecture addItem={addItem} />
                <div className="LectureList">{lectureItems}</div>
            </Container>
        </div>);

}

export default App;

*/
/*
class App extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            items: [],
        };
    }



    componentDidMount() {
        call("/mystudent/lecture", "GET", null).then((response) =>
            this.setState({ items: response.data })
        );
    }

    add = (item) => {
        call("/mystudent/lecture", "POST", item).then((response) =>
            this.setState({ items: response.data })
        );
        const thisItems = this.state.items;
        item.id = "ID-" + thisItems.length; // key를 위한 id추가
        item.done = false; // done 초기화
        thisItems.push(item); // 배열에 아이템 추가
        this.setState({ items: thisItems }); // 업데이트는 반드시 this.setState로 해야됨.
        console.log("items : ", this.state.items);
    };

    delete = (item) => {
        call("/mystudent/lecture", "DELETE", item).then((response) =>
            this.setState({ items: response.data })
        );
        const thisItems = this.state.items;
        console.log("Before Update Items : ", this.state.items);
        const newItems = thisItems.filter((e) => e.id !== item.id); // 해당 id 걸러내기
        this.setState({ items: newItems }, () => {
            // 디버깅 콜백
            console.log("Update Items : ", this.state.items);
        });

    };

    update = (item) => {
        call("/mystudent/lecture", "PUT", item).then((response) =>
            this.setState({ items: response.data })
        );
    };

    render() {
        var lectureItems = this.state.items.length > 0 && (
            <Paper style={{ margin: 16 }}>
                <List>
                    {this.state.items.map((item, idx) => (
                        <Lecture
                            item={item}
                            key={item.id}
                            delete={this.delete}
                            update={this.update}
                        />
                    ))}
                </List>
            </Paper>
        );

        // 3. props로 넘겨주기
        return (
            <div className="App">
                <Container maxWidth="md">
                    <AddLecture addItem={this.add}/>
                    <div className="Lecture">{lectureItems}</div>
                </Container>
            </div>
        );
    }
}



*/



const App = () => {
    const [state, setState] = useState({ items: [],loading:true});
    //const [loading, setLoading] = useState(true);


    // componentDidMount 대신 userEffect 사용
    useEffect(() => {
        call("/eple/v1/mystudent/lecture", "GET", null).then((response) =>
                setState({items: response.data, loading: false})
            );
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



    let lectureItems = state.items.length > 0 && (
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
            <Header />
            <Sidebar>
                <Container maxWidth="md">
                    <AddLecture addItem={addItem} />
                    <div className="LectureList">{lectureItems}</div>
                </Container>
            </Sidebar>
        </div>
    );

    let loadingPage = <h1> 로딩중..</h1>;
    let content = loadingPage;
    if(!state.loading){
        //로딩중이 아니면 lecture page
        content = lectureListPage;
    }

    // 3. props로 넘겨주기
    return <div className="App">{lectureListPage}</div>;

};

export default App;


