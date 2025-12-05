import './App.css'
import { Route, Routes } from 'react-router-dom'
import MedicineList from './pages/MedicineList'
import MedicineForm from './pages/MedicineForm'
import MedicineEdit from './pages/MedicineEdit'

function App() {

  return (
    <>
      <Routes>
        <Route path='/' element={<MedicineList/>}></Route>
        <Route path='/form' element={<MedicineForm/>}></Route>
        <Route path='/edit/:id' element={<MedicineEdit/>}></Route>
      </Routes>
    </>
  )
}

export default App
