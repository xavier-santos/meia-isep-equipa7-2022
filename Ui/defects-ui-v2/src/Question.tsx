import * as React from 'react';
import { Button, ButtonGroup } from "@mui/material"
import axios from "axios";
import { useLocation, useNavigate } from "react-router-dom";
import config from "./config";

export default function Question(){
    const location = useLocation();
    const [question, setQuestion] = React.useState(location.state.question);

    const navigate = useNavigate();
    const buttons = [
        <Button key="yes" onClick={() => onButtonClicked(true)}>Yes</Button>,
        <Button key="no" onClick={() => onButtonClicked(false)}>No</Button>
      ];
      const onButtonClicked = (answer: boolean) =>{
        axios.post(config.API_URL+'nextStep',null, {
          params: {response: answer}
        })
        .then(function (response) {
            console.log(response);
          if(response.data.isConclusion){
            navigate('/conclusion', {
                state: {
                  conclusion: response.data.Conclusion,
                  how: response.data.How,
                }
              });
            return;
          }
          setQuestion(response.data.Question);
        })
        .catch(function (error) {
          console.log(error);
        });
      }
    return<div>
        <h1>{question}</h1>
        <ButtonGroup variant="text" aria-label="text button group">
        {buttons}
        </ButtonGroup>
    </div>
}
