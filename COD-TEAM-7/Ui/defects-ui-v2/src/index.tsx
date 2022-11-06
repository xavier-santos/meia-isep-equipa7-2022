import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import './App.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Question from './Question';
import Conclusion from './Conclusion';

const root = ReactDOM.createRoot(
  document.getElementById('root') as HTMLElement
);
root.render(
  <React.StrictMode>
    <BrowserRouter>
      <Routes>
        <Route path='/' element={<App/>}/>
        <Route path='/question' element={<Question/>}/>
        <Route path='/conclusion' element={<Conclusion/>}/>
      </Routes>
    </BrowserRouter>
  </React.StrictMode>
);