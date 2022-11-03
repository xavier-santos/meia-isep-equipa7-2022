import * as React from 'react';
import Button from '@mui/material/Button';
import ButtonGroup from '@mui/material/ButtonGroup';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';
import axios from 'axios';
import config from './config';
import { useNavigate } from "react-router-dom";

function App() {
  const navigate = useNavigate();
  const buttons = [
    <Button key="ripped" sx={{ backgroundColor: '#4c9173'}} onClick={() => onButtonClicked("ksession-rules-ripped")}>Ripped</Button>,
    <Button key="bubbled" sx={{ backgroundColor: '#4c9173'}} onClick={() => onButtonClicked("ksession-rules-bubbled")}>Bubbled</Button>,
    <Button key="collapsed" sx={{ backgroundColor: '#4c9173'}} onClick={() => onButtonClicked("ksession-rules-collapsed")}>Collapsed</Button>,
    <Button key="lackofcomponent" sx={{ backgroundColor: '#4c9173'}} onClick={() => onButtonClicked("ksession-rules-lackcomponent")}>Lack of component</Button>,
    <Button key="misplaced"  sx={{ backgroundColor: '#4c9173'}} onClick={() => onButtonClicked("ksession-rules-misplaced")}>Misplaced component</Button>,
    <Button key="lackoffilling"  sx={{ backgroundColor: '#4c9173'}} onClick={() => onButtonClicked("ksession-rules-lackfilling")}>Lack of filling</Button>,
  ];
  
  const onButtonClicked = (ruleName: string) =>{
    axios.post(config.API_URL+'loadEngine',null, {
      params: {kieSession: ruleName}
    })
    .then(function (response) {
      navigate('/question', {
        state: {
          question: response.data.Question,
        }
      });
      console.log(response);
    })
    .catch(function (error) {
      console.log(error);
    });
  }
  return (
    <div className="App">

      <Typography gutterBottom variant="h5" component="div" className="App-title">
        MEIA 2022 - GROUP 7            
      </Typography>
      <div className="App-header">
        <Typography gutterBottom variant="h2" component="div">
          Defect Diagnostics
        </Typography>
        <Typography gutterBottom component="div" >
          for car seats foam parts
        </Typography>
      </div>
      <Typography gutterBottom variant="h6" className="App-select" component="div" >
        Please select the Defect type:
      </Typography>

      <Box>
        <ButtonGroup
          orientation="vertical"
          aria-label="vertical contained button group"
          variant="contained"
          color="success"
          size="large"
        >
          {buttons}
        </ButtonGroup>
      </Box>
    </div>
  );
}

export default App;
