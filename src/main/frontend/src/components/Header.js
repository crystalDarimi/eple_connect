import React, { Children } from 'react'
import "../styles/Header.css"
import AccountBoxIcon from '@mui/icons-material/AccountBox';
import NotificationsIcon from '@mui/icons-material/Notifications';
import Sidebar, { menuItem } from './Sidebar'
import {Button,Toolbar,AppBar, Typography} from "@mui/material";
import {signout} from "../service/ApiService";

const Header = () =>{
    return (
        <header >
                    <nav>
                        <ul>

                            {/* <li className='Notification'><NotificationsIcon fontSize='large' /></li> */}
                             {/* <li className='Profile'><AccountBoxIcon fontSize='large' /></li>icon 빼고 프로필 설정 작업 필요 */}
                            <li className='signout'> <Button color="inherit" raised onClick={ signout }>
                                로그아웃
                            </Button></li>
                        </ul>
                     </nav>

        </header>
    )
}
export default Header;

