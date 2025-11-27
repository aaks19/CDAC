import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import StudentService from "../services/StudentService";

export default function StudentTable() {
  const [studarr, setstudarr] = useState([]);

  useEffect(() => {
    fetchData();
  }, []);

  const fetchData = async () => {
    var result = await StudentService.getAllStudent();
    console.log(result);
    setstudarr(result.data.data);
  };console.log(studarr);
  
  const deleteStudent=(id)=>{
    StudentService.deleteStudent(id)
    .then(()=>{
        fetchData();
    })
    .catch((err)=>{
        console.log(err);
        
    })
  }

  return (
    <div>
      <Link to="/form">
        <button type="button" name="add" id="add">
          Add new student
        </button>
      </Link>
      <br />
      <br />
      <table>
        <thead>
          <tr>
            <th scope="col">Student Id</th>
            <th scope="col">Name</th>
            <th scope="col">Marks</th>
            <th scope="col">Admission date</th>
            <th>action</th>
          </tr>
        </thead>
        <tbody>
          {studarr.map((stud) => (
            <tr key={stud.sid}>
              <th scope="row">{stud.sid}</th>
              <td>{stud.sname}</td>
              <td>{stud.marks}</td>
              <td>{stud.admissiondate}</td>
              <td>
                <Link to={`/edit/${stud.sid}`} state={{ studdata: stud }}>
                  <button type="button" name="edit" id="edit">
                    Edit
                  </button>
                </Link>
                <Link
                  to={`/table/details/${stud.sid}`}
                  state={{ studdate: stud }}
                >
                  <button type="button" name="view" id="view">
                    View
                  </button>
                </Link>
                <button type="button" name="delete" id="delete" onClick={()=>{deleteStudent(stud.sid)}}>
                  Delete
                </button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}
