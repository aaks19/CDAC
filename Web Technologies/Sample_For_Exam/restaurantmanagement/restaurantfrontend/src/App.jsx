import {Navigate, Route, Routes} from 'react-router-dom'

import './App.css'

function App() {

  return (
    <div>
      <Routes>
          <Route path='/' element={<Navigate replace to='/table'></Navigate>}></Route>
          <Route path='/table' element={<RestaurantTable/>}></Route>
      </Routes>
    </div>
  )
}

export default App
