import './App.css'
import LibraryEdit from './pages/LibraryEdit'
import LibraryForm from './pages/LibraryForm'
import LibraryList from './pages/LibraryList'
import {Route, Routes} from 'react-router-dom'

function App() {

  return (
    <>
      <h1>Library Management</h1>
      <Routes>
        <Route path='/' element={<LibraryList/>}></Route>
        <Route path='/form' element={<LibraryForm/>}></Route>
        <Route path='/edit/:id' element={<LibraryEdit/>}></Route>
      </Routes>
    </>
  )
}

export default App
