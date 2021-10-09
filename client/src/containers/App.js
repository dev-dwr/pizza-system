import './App.css';
import React, {useEffect, useState} from "react";
import axios from "axios";

const testFetch = () => {
    axios.get("http://localhost:8080/api/v1").then(res =>{
      console.log(res);
    })
}
function App() {
  useEffect(() => {
    testFetch();
  }, [])
  return (
    <div className="App">

    </div>
  );
}

export default App;
