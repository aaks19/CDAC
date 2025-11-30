import './App.css'
import {Navigate, Route, Routes} from 'react-router-dom';

import VehicleTable from './pages/VehicleTable';
import VehicleForm from './pages/VehicleForm';
import VehicleEdit from './pages/VehicleEdit';

function App() {

  return (
   <div>
    <Routes>
      <Route path='/' element={<Navigate replace to={"/table"}></Navigate>}></Route>
      <Route path='/table' element={<VehicleTable/>}></Route>
      <Route path='/form' element={<VehicleForm/>}></Route>
      <Route path='/edit/:id' element={<VehicleEdit/>}></Route>
    </Routes>
   </div>
  )
}

export default App
