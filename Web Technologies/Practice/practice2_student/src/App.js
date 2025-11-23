import logo from "./logo.svg";
import "./App.css";
import { useEffect, useState } from "react";
import StudentListComponent from "./components/StudentListComponent";
import StudentFormComponent from "./components/StudentFormComponent";

function App() {
  const [studentarr, setstudentarr] = useState(["Akshat"]);
  const [searchstudent, setsearchstudent] = useState([]);
  const [searchtxt, setsearchtxt] = useState("");

  useEffect(() => {
    setsearchstudent([...studentarr]);
  }, [studentarr]);

  useEffect(() => {
    if (searchtxt === "") {
      setsearchstudent([...studentarr]);
    } else {
      const arr = studentarr.filter((s) => s.includes(searchtxt));
      setsearchstudent([...arr]);
    }
  }, [searchtxt]);

  const insertStudent = (snm) => {
    setstudentarr([...studentarr, snm]);
  };

  const removeStudent = (snm) => {
    const arr = studentarr.filter((s) => s !== snm);
    setstudentarr([...arr]);
  };

  const updateStudent = (oldStudent, newStudent) => {
    const arr = studentarr.map((s) => (s === oldStudent ? newStudent : s));
    setstudentarr([...arr]);
  };

  const handleChange = (ev) => {
    setsearchtxt(ev.target.value);
  };
  return (
    <>
      <label htmlFor="search">Search Student:</label>
      <input
        type="text"
        name="searchtxt"
        id="search"
        value={searchtxt}
        onChange={handleChange}
      />
      <StudentListComponent arr={searchstudent} />
      <StudentFormComponent
        insertStudent={insertStudent}
        removeStudent={removeStudent}
        updateStudent={updateStudent}
      />
    </>
  );
}

export default App;
