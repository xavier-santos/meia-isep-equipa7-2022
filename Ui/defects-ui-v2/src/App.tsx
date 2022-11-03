import * as React from 'react';
import Button from '@mui/material/Button';
import ButtonGroup from '@mui/material/ButtonGroup';
import Box from '@mui/material/Box';
import Typography from '@mui/material/Typography';

const buttons = [
  <Button key="ripped" onClick={() => onRippedClicked}>Ripped</Button>,
  <Button key="bubbled" onClick={() => onBubbledClicked}>Bubbled</Button>,
  <Button key="collapsed" onClick={() => onCollapsedClicked}>Collapsed</Button>,
  <Button key="lackofcomponent" onClick={() => onLackOfComponentClicked}>Lack of component</Button>,
  <Button key="misplaced" onClick={() => onMisplacedClicked}>Misplaced component</Button>,
  <Button key="lackoffilling" onClick={() => onLackOfFillingClicked}>Lack of filling</Button>,
];

function onBubbledClicked() {

}

function onCollapsedClicked() {

}

function onLackOfComponentClicked() {

}

function onMisplacedClicked() {

}

function onLackOfFillingClicked() {

}

function onRippedClicked() {

}


function App() {
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
