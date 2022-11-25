import React, {useState} from "react";
import {ListItem , ListItemText, InputBase,ListItemSecondaryAction,IconButton} from "@mui/material";
import DeleteOutlined from "@mui/icons-material/DeleteOutlined"




const Lecture = (props) => {
    const [state, setState] = useState({ item: props.item, readOnly: true });
    const deleteItem = props.deleteItem;
    const updateItem = props.updateItem;

    const deleteEventHandler = () => {
        deleteItem(state.item);
    };

    const offReadOnlyMode = () => {
        console.log("Event!", state.readOnly);
        setState({ readOnly: false }, () => {
            console.log("ReadOnly? ", state.readOnly);
        });
    };

    const enterKeyEventHandler = (e) => {
        if (e.key === "Enter") {
            setState({ readOnly: true });
            updateItem(state.item);
        }
    };

    const editEventHandler = (e) => {
        const thisItem = state.item;
        thisItem.title = e.target.value;
        setState({ item: thisItem });
    };

    const item = state.item;
    return (
        <ListItem>
            <ListItemText>
                <InputBase
                    inputProps={{
                        "aria-label": "naked",
                        readOnly: state.readOnly,
                    }}
                    type="text"
                    id={item.id}
                    name={item.id}
                    value={item.lectureTitle}
                    fullWidth={true}
                    onClick={offReadOnlyMode}
                    onChange={editEventHandler}
                    onKeyPress={enterKeyEventHandler}
                />
            </ListItemText>

            <ListItemSecondaryAction>
                <IconButton aria-label="Delete Lecture" onClick={deleteEventHandler}>
                    <DeleteOutlined />
                </IconButton>
            </ListItemSecondaryAction>
        </ListItem>
    );
};

export default Lecture;





 /*

const Lecture = (props) => {
    const [item, setItem] = useState(props.item);
    const [readOnly, setReadOnly] = useState(true);

    //turnOffReadOnly 함수 작성
    const turnOffReadOnly = () =>{
        setReadOnly(false);
    }

    //turnOnReadOnly  함수 작성
    const turnOnReadOnly = (e) =>{
        if(e.key ==="Enter"&& readOnly ===false){
            setReadOnly(true);
            editItem(item);
        }
    };
    const deleteItem = props.deleteItem;
    const deleteEventHandler = () =>{
        deleteItem(item);
    }
    const editItem = props.editItem;

    const editEventHandler = (e) =>{
        setItem({...item,title:e.target.value});
    };



return (
    <ListItem>
        <ListItemText>
            <InputBase
                inputProps = {{"aria-label":"naked",
                readOnly:readOnly}}
                onClick={turnOffReadOnly}
                onKeyDown={turnOnReadOnly}
                onChange = {editEventHandler}
                type = "text"
                id={item.id}
                name={item.id}
                value={item.lectureTitle}
                multiline={true}
                fullWidth={true}
            />

        </ListItemText>
        <ListItemSecondaryAction>
            <IconButton aria-label = "Delete Lecture"
            onClick={deleteEventHandler}>
                <DeleteOutlined/>
            </IconButton>
        </ListItemSecondaryAction>
    </ListItem>
    );
};


export default Lecture;






/*
class Lecture extends React.Component {
    constructor(props) {
        super(props);
        this.state = { item: props.item, readOnly: true };
        this.delete = props.delete;
        this.update = props.update;
    }

    deleteEventHandler = () => {
        this.delete(this.state.item);
    };

    offReadOnlyMode = () => {
        console.log("Event!", this.state.readOnly);
        this.setState({ readOnly: false }, () => {
            console.log("ReadOnly? ", this.state.readOnly);
        });
    };

    enterKeyEventHandler = (e) => {
        if (e.key === "Enter") {
            this.setState({ readOnly: true });
            this.update(this.state.item);
        }
    };

    editEventHandler = (e) => {
        const thisItem = this.state.item;
        thisItem.title = e.target.value;
        this.setState({ item: thisItem });
    };



    render() {
        const item = this.state.item;
        return (
            <ListItem>
                <ListItemText>
                    <InputBase
                        inputProps={{
                            "aria-label": "naked",
                            readOnly: this.state.readOnly,
                        }}
                        type="text"
                        id={item.id}
                        name={item.id}
                        value={item.title}
                        fullWidth={true}
                        onClick={this.offReadOnlyMode}
                        onChange={this.editEventHandler}
                        onKeyPress={this.enterKeyEventHandler}
                    />
                </ListItemText>

                <ListItemSecondaryAction>
                    <IconButton
                        aria-label="Delete Lecture"
                        onClick={this.deleteEventHandler}
                    >
                        <DeleteOutlined />
                    </IconButton>
                </ListItemSecondaryAction>
            </ListItem>
        );
    }
}




    const Lecture = (props)=>{
    const [item,setItem] = useState(props.item);
    const [readOnly, setReadOnly] = useState(true);

    //turnOffReadOnly 함수
    const turnOffReadOnly = () => {
        setReadOnly(false);}


    //turnOnReadOnly
    const turnOnReadOnly  = (e) =>{
        if(e.key === "Enter"&& readOnly===false){
            setReadOnly(true);
            editItem(item);
        }
    }
    const deleteItem = props.deleteItem; //deleteItem 함수 연결
        const deleteEventHandler = () => {
            deleteItem(item);
        }

    const editItem = props.editItem;
    const editEventHandler = (e) => {
        setItem({...item,title:e.target.value});
    };



    return(
        <ListItem>
            <ListItemText>
                <InputBase
                    inputProps={{"arial-label":"naked",readOnly: readOnly}}
                    onClick = {turnOffReadOnly}
                    onKeyDown = {turnOnReadOnly}
                    onChange={editEventHandler}
                    type="text"
                    id={item.id} name={item.id}
                    value={item.title}
                    multiline={true}
                    fullWidth={true}

                />

            </ListItemText>
            <ListItemSecondaryAction>
                <IconButton aria-label="Delete Lecture"
                onClick={deleteEventHandler}>
                    <DeleteOutlined />
                </IconButton>
            </ListItemSecondaryAction>

        </ListItem>


    );
};


 */

