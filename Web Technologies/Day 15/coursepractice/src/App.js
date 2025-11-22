import logo from "./logo.svg";
import "./App.css";
import 'bootstrap/dist/css/bootstrap.css'
import { useEffect, useState } from "react";
import CourseListComponent from "./components/CourseListComponent";
import CourseFormComponent from "./components/CourseFormComponent";

function App() {
  const [coursearr,setcoursearr] = useState(["Java","C","Pyhton"])
  const [searcharr,setsearcharr] = useState([])
  const [searchtxt,setsearchtxt] = useState("")

  const handlechange=(ev)=>{
setsearchtxt(ev.target.value)
  }

  useEffect(()=>{
    setsearcharr([...coursearr])
  },[coursearr])

  useEffect(()=>{
    if(searchtxt===""){
      setsearcharr([...coursearr])
    }else{
      const arr = coursearr.filter(c=>c.includes(searchtxt))
      setsearcharr([...arr])
    }
  },[searchtxt])


  const addCourse=()=>{

  }

   

  return (
    <>
     
      <div className="container">
        <div className="row">
          <div className="col-sm-12 col-md-6">
             <label htmlFor="search">Search</label> :{" "}
      <input type="text" name="searchtxt" id="search" value={searchtxt} onChange={handlechange} /><br/><br/>
            <CourseListComponent arr={searcharr} />

          </div>
          <div className="col-sm-12 col-md-6">
            <CourseFormComponent/>
          </div>
        </div>
      </div>

    </>
  );
}

export default App;
