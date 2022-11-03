import * as React from 'react';
import { Button, ButtonGroup } from "@mui/material"
import axios from "axios";
import { useLocation, useNavigate } from "react-router-dom";
import config from "./config";

export default function Conclusion(){
    const location = useLocation();
    const conclusion = location.state.conclusion;
    const how = location.state.how;
    const navigate = useNavigate();

    return<div>
      <h1>{conclusion}</h1>
      <h2>{how}</h2>
      <Button key="home" onClick={() => navigate('/')}>Go Home</Button>
    </div>
}
