import React, {useState} from "react";

import {Button , Grid, TextField,Paper } from "@mui/material";
import {call} from "../service/ApiService";
/*
class AddLecture extends React.Component {
    constructor(props) {
        super(props);
        this.state = { item: { title: "" } };
        this.add = props.add;
    }
    onInputChange = (e) => {
        const thisItem = this.state.item;
        thisItem.title = e.target.value;
        this.setState({ item: thisItem });
        console.log(thisItem);
    };
    onButtonClick = () => {
        this.add(this.state.item);
        this.setState({ item: { title: "" } });
    };
    enterKeyEventHandler = (e) => {
        if (e.key === "Enter") {
            this.onButtonClick();
        }
    };

    render() {
        return (
            <Paper style={{ margin: 16, padding: 16 }}>
                <Grid container>
                    <Grid xs={11} md={11} item style={{ paddingRight: 16 }}>
                        <TextField
                            placeholder="Add Lecture here"
                            fullWidth
                            onChange={this.onInputChange}
                            value={this.state.item.title}
                            onKeyPress={this.enterKeyEventHandler}
                        />
                    </Grid>
                    <Grid xs={1} md={1} item>
                        <Button
                            fullWidth
                            color="secondary"
                            variant="outlined"
                            onClick={this.onButtonClick}
                        >
                            +
                        </Button>
                    </Grid>
                </Grid>
            </Paper>
        );
    }
}







*/


const AddSchedule = (props) =>{
//사용자의 입력을 저장할 오브젝트

    const [state, setState] = useState({schedule: {
            lectureCode : " ",
            date : " ",
            startTime : " ",
            endTime : " "
    }});
    const addSchedule = props.addSchedule;



    //onInputChange 함수
    const onInputChange = (e) =>{
        const thisSchedule = state.schedule;
        thisSchedule.lectureTitle=e.target.value;
        setState({schedule:thisSchedule});
        console.log(thisSchedule);
    };

    //onButtonClick 함수
    const onButtonClick =()=> {
        addSchedule(state.schedule);
        setState({schedule:{lectureTitle: ""}});//addItem 함수 사용
        //setItem({lectureTitle:""});
    }

    //enterKeyEvent 함수
    const enterKeyEventHandler =(e) =>{
        if(e.key === "Enter"){
            onButtonClick();
        }
    };

    //onInputChange 함수 ~ TextField 연결
    return(
        <Paper style = {{ margin: 16, padding: 16 }}>
            <Grid container>
                <Grid xs ={11} md={11} item style={{paddingRight:16}}>
                    <TextField placeholder="Add Lecture here"
                               fullWidth
                               onChange={onInputChange}

                               value={state.item.lectureTitle}
                               onKeyPress={enterKeyEventHandler}
                    />
                </Grid>
                <Grid xs ={1} md={1} item>
                    <Button fullWidth style={{height:'100%'}} color ="secondary" variant="outlined"
                            onClick={onButtonClick}>
                        +
                    </Button>
                </Grid>
            </Grid>
        </Paper>
    );
}









export default AddSchedule;

