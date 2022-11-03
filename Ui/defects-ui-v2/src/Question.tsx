import * as React from 'react';
import { Button, Card, CardActions, CardContent, Typography } from "@mui/material"
import axios from "axios";
import { useLocation, useNavigate } from "react-router-dom";
import config from "./config";

export default function Question(){
    const location = useLocation();
    const [question, setQuestion] = React.useState(location.state.question);

    const navigate = useNavigate();
    const buttons = [
        <Button sx={{ color: '#5b446a', borderColor: '#5b446a'}} key="yes" variant="outlined" color="secondary" onClick={() => onButtonClicked(true)}>Yes</Button>,
        <Button sx={{ color: '#5b446a', borderColor: '#5b446a'}}  key="no" variant="outlined" color="secondary" onClick={() => onButtonClicked(false)}>No</Button>
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
    return(

<Card variant="outlined" sx={{ width: '40%', margin: 'auto auto', marginTop: '20px', maxWidth: '500px', textAlign: 'center', color: '#5b446a', backgroundColor: 'rgb(237, 247, 237)' }}>
<CardContent>
  <Typography gutterBottom variant="h5" component="div">
    {question}
  </Typography>
</CardContent>
<CardActions sx={{ justifyContent: "space-between", marginInline: '20%' }}>
  {buttons}
</CardActions>
</Card>
    )
}
