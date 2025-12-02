import { Navigate, Route, Routes } from 'react-router-dom'
import './App.css'
import RestaurantTable from './pages/restauranttable'
import RestaurantForm from './pages/restaurantform'
function App() {

  return (
    <>
     <Routes>
      <Route path='/' element={<Navigate replace to={"/table"}></Navigate>}></Route>

      <Route path='/table' element={<RestaurantTable/>}></Route>

      <Route path='/form' element={<RestaurantForm/>}></Route>

      {/* <Route path='/edit/:id' element={} */}
     </Routes>
    </>
  )
}

export default App
