import { useState } from 'react'
import {Routes,Route,Navigate} from 'react-router-dom'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import "bootstrap/dist/css/bootstrap.css"
import MyHeader from './components/MyHeader'
import MyFooter from './components/MyFooter'
import MainNavBar from './components/MainNavBar'
import HomeComponent from './pages/HomeComponent'
import ProductTable from './pages/ProductTable'
import ProductList from './pages/ProductList'
import ProductForm from './pages/ProductForm'
import AboutUsComponent from './pages/AboutUsComponent'
import ProductDetails from './pages/ProductDetails'
import ProductEdit from './pages/ProductEdit'
function App() {
  return (
    <div>
      <MyHeader/>
      <MainNavBar/>
      <Routes>
        <Route path="/" element={<Navigate replace to="/home"></Navigate>}></Route>

        <Route path="/home" element={<HomeComponent/>}>
        </Route>

        <Route path="/table" element={<ProductTable/>}>
        <Route path="details/:id" element={<ProductDetails/>}></Route>
        </Route>

    
         <Route path="/edit/:id" element={<ProductEdit/>}>
        </Route>
        <Route path="/list" element={<ProductList/>}>
        </Route>

        <Route path="/form" element={<ProductForm/>}>
        </Route>

        <Route path="/aboutus" element={<AboutUsComponent/>}>
        </Route>


      </Routes>
      <MyFooter/>
    </div>
  )
}

export default App
