import React from "react";
import "./app.css";
import Login from "./Login/Login";
import { Routes, Route } from "react-router-dom";
import HomePage from "./HomePage";

const express = require('express');
const app = express();
app.get('/', (req, res) => {
    res.send('Welcome to CORS server 😁')
})
app.get('/cors', (req, res) => {
    res.send('This has CORS enabled 🎈')
})
app.listen(8080, () => {
    console.log('listening on port 8080')
})

function App() {
  return (
    <div className="app">
      <div className="section">
          <Routes>
            <Route path="/" element={<HomePage/>}/>
            <Route path="/login" element={ <Login/>} />
         </Routes>

       
      </div>
    </div>
  );
}

export default App;


