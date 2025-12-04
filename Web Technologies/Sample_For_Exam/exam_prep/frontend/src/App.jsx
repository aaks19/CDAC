// import { useState } from 'react'
// import reactLogo from './assets/react.svg'
// import viteLogo from '/vite.svg'
import './App.css'
import AddEmployee from './pages/AddEmployee'
import ListEmployee from './pages/ListEmployee'

function App() {
  // const [count, setCount] = useState(0)

  return (
    <div>
      <h1>Add Employee</h1>
      <AddEmployee/>
      <h1>Employee List</h1>
      <ListEmployee/>
    </div>
  )
}

export default App
