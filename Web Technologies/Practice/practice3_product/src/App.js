import logo from "./logo.svg";
import "./App.css";
import { useEffect, useState } from "react";
import ProductListComponent from "./components/ProductListComponent";
import ProductFormComponent from "./components/ProductFormComponent";

function App() {
  const [productarr, setproductarr] = useState(["laptop"]);
  const [searcharr, setsearcharr] = useState([]);
  const [searchtxt, setsearchtxt] = useState("");

  useEffect(() => {
    setsearcharr([...productarr]);
  }, [productarr]);

  useEffect(() => {
    if (searchtxt === "") {
      setsearcharr([...productarr]);
    } else {
      const arr = productarr.filter((p) => p.includes(searchtxt));
      setsearcharr([...arr]);
    }
  }, [searchtxt]);

  const addProduct = (pnm) => {
    setproductarr([...productarr, pnm]);
  };

  const removeProduct = (pnm) => {
    const arr = productarr.filter((p) => p !== pnm);
    setproductarr([...arr]);
  };

  const updateProduct = (oldname, newname) => {
    const arr = productarr.map((p) => (p === oldname ? newname : p));
    setproductarr([...arr]);
  };

  const handleChange = (ev) => {
    setsearchtxt(ev.target.value);
  };

  return (
    <>
      <label htmlFor="search">Search Product : </label>
      <input
        type="text"
        name="searchtxt"
        id="search"
        value={searchtxt}
        onChange={handleChange}
      />

      <ProductListComponent arr={searcharr} />

      <ProductFormComponent
        addProduct={addProduct}
        removeProduct={removeProduct}
        updateProduct={updateProduct}
      />
    </>
  );
}

export default App;
