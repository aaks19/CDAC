import { Navigate, Route, Routes } from "react-router-dom";
import "./App.css";
import StudentTable from "./pages/StudentTable";
import StudentForm from "./pages/StudentForm";
import StudentEdit from "./pages/StudentEdit";

function App() {
  return (
    <>
      <div>
        <Routes>
          <Route path="/" element={<Navigate replace to={"/table"}></Navigate>}></Route>

          <Route path="/table" element={<StudentTable/>}></Route>

          <Route path="/form" element={<StudentForm/>}></Route>

          <Route path="/edit/:id" element={<StudentEdit/>}></Route>
        </Routes>
      </div>
    </>
  );
}

export default App;
