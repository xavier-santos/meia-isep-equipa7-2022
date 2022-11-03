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
    <Button key="ripped" onClick={() => onButtonClicked("ksession-rules-ripped")}>Ripped</Button>,
    <Button key="bubbled" onClick={() => onButtonClicked("ksession-rules-bubbled")}>Bubbled</Button>,
    <Button key="collapsed" onClick={() => onButtonClicked("ksession-rules-collapsed")}>Collapsed</Button>,
    <Button key="lackofcomponent" onClick={() => onButtonClicked("ksession-rules-lackcomponent")}>Lack of component</Button>,
    <Button key="misplaced" onClick={() => onButtonClicked("ksession-rules-misplaced")}>Misplaced component</Button>,
    <Button key="lackoffilling" onClick={() => onButtonClicked("ksession-rules-lackfilling")}>Lack of filling</Button>,
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
                  <Typography gutterBottom variant="h2" component="div">
              Defect Detection
            </Typography>
    <Box
      sx={{
        display: 'flex',
        '& > *': {
          m: 1,
        },
      }}
    >
      <ButtonGroup
        orientation="vertical"
        aria-label="vertical contained button group"
        variant="contained"
        color="success"

      >
        {buttons}
      </ButtonGroup>
    </Box>
    </div>
  );
}

export default App;
