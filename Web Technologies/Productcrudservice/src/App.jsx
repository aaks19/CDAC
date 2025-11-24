import { Routes, Route, Navigate } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.css";
import MyHeader from "./components/MyHeader";
import MyFooter from "./components/MyFooter";
import MainNavBar from "./components/MainNavBar";
import HomeComponent from "./pages/HomeComponent";
import ProductTable from "./pages/ProductTable";
import ProductList from "./pages/ProductList";
import ProductForm from "./pages/ProductForm";
import AboutUsComponent from "./pages/AboutUsComponent";
import ProductDetails from "./pages/ProductDetails";
function App() {
  return (
    <div>
      <MyHeader />
      <MainNavBar />
      <Routes>
        <Route path="/" element={<Navigate replace to="/home" />} />

        <Route path="/home" element={<HomeComponent />} />

        <Route path="/table" element={<ProductTable />} />

        <Route path="/table/details/:id" element={<ProductDetails />} />

        <Route path="/form" element={<ProductForm />} />
        <Route path="/form/:id" element={<ProductForm />} />  

        <Route path="/list" element={<ProductList />} />

        <Route path="/aboutus" element={<AboutUsComponent />} />
      </Routes>

      <MyFooter />
    </div>
  );
}

export default App;
