import * as React from 'react';
import { Alert, AlertTitle, Button } from "@mui/material"
import { useLocation, useNavigate } from "react-router-dom";

export default function Conclusion(){
    const location = useLocation();
    const conclusion = location.state.conclusion;
    const how = location.state.how;
    const navigate = useNavigate();

    return<div>
      <Alert severity="success"  sx={{ width: '80%', margin: 'auto auto', marginTop: '20px', maxWidth: '500px' }}>
        <AlertTitle>{conclusion}</AlertTitle>
        <div>{how}</div>
        <Button key="home" variant="outlined" color="success"   sx={{ marginTop: "20px"}} onClick={() => navigate('/')}>Go Home</Button>
      </Alert>
    </div>
}
