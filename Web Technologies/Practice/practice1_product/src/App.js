import "./App.css";
import CourseFormComponent from "./components/CourseFormComponent";
import CourseListComponent from "./components/CourseListComponent";
import { useEffect, useState } from "react";

function App() {
  const [coursearr, setcoursearr] = useState(["java", "python", "C"]);
  const [searcharr, setsearcharr] = useState([]);
  const [searchtxt, setsearchtxt] = useState("");

  useEffect(() => {
    setsearcharr([...coursearr]);
  }, [coursearr]);

  useEffect(() => {
    if (searchtxt === "") {
      setsearcharr([...coursearr]);
    } else {
      const arr = coursearr.filter((c) => c.includes(searchtxt));
      setsearcharr([...arr]);
    }
  }, [searchtxt]);

  const addNewCourse = (cnm) => {
    console.log("Course added name" + cnm);
    setcoursearr([...coursearr, cnm]);
  };

  const removeCourse = (cnm) => {
    console.log("in remove course " + cnm);
    const arr = coursearr.filter((nm) => nm !== cnm);
    setcoursearr([...arr]);
  };

  const modifyCourse=(oldname, newname)=>{
    const arr = coursearr.map(c=>c===oldname?newname:c)
    setcoursearr([...arr])
  }

  const handleChange = (ev) => {
    setsearchtxt(ev.target.value);
  };

  return (
    <>
      <label htmlFor="search">Search course: </label>
      <input
        type="text"
        name="searchtxt"
        id="search"
        value={searchtxt}
        onChange={handleChange}
      ></input>
      <CourseListComponent arr={searcharr} />
      <CourseFormComponent
        insertCourse={addNewCourse}
        deleteCourse={removeCourse}
        updateCourse={modifyCourse}
      />
    </>
  );
}

export default App;
