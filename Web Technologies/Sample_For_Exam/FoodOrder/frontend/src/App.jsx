import './App.css'
import { Route, Router, Routes } from 'react-router-dom'
import FoodList from './pages/FoodList'
import FoodForm from './pages/FoodForm'
import FoodEdit from './pages/FoodEdit'

function App() {

  return (
    <>
      <Routes>
        <Route path='/' element={<FoodList/>}></Route>
        <Route path='/form' element={<FoodForm/>}></Route>
        <Route path='/edit/:id' element={<FoodEdit/>}></Route>
      </Routes>
    </>
  )
}

export default App
