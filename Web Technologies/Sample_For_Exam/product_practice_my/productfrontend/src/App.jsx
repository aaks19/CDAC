import { Navigate, Route, Routes } from "react-router-dom";
import ProductTable from "./pages/ProductTable";
import ProductForm from "./pages/ProductForm";
import ProductList from "./pages/ProductEdit";
import ProductEdit from "./pages/ProductEdit";

function App() {
  return (
    <div>
      <Routes>
        <Route path="/" element={<Navigate replace to="/table"></Navigate>}></Route>
        <Route path="/table" element={<ProductTable/>}></Route>
        <Route path="/form" element={<ProductForm/>}></Route>
        <Route path="/edit/:id" element={<ProductEdit/>}></Route>
      </Routes>
    </div>
  );
}

export default App;
