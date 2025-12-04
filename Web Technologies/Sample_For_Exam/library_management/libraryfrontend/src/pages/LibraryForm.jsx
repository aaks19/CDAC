import React from "react";
import { useState } from "react";
import LibraryService from "../service/LibraryService";
import { Navigate, useNavigate } from "react-router-dom";

export default function LibraryForm() {
  const [formdetail, setformdetail] = useState({
    id: "",
    bname: "",
    bauthor: "",
    price: "",
    year: "",
  });
  const navigate = useNavigate();
  const handleChange = (e) => {
    const { name, value } = e.target;
    setformdetail({ ...formdetail, [name]: value });
  };

  const addBook = (e) => {
    e.preventDefault();
    if (
      formdetail.id === "" ||
      formdetail.bname === "" ||
      formdetail.bauthor === "" ||
      formdetail.price === "" ||
      formdetail.year === ""
      || formdetail.price<=0
    ) {
      alert("Field cant be empty");
    } else {
      LibraryService.addBook(formdetail).then(() => {
        alert("Book added");
        navigate("/");
      });
    }
  };

  return (
    <div>
      <form onSubmit={addBook}>
        <label>Book id</label>
        <input
          type="text"
          name="id"
          id="id"
          value={formdetail.id}
          onChange={handleChange}
        />
        <br />
        <br />

        <label>Book Name</label>
        <input
          type="text"
          name="bname"
          id="bname"
          value={formdetail.bname}
          onChange={handleChange}
        />
        <br />
        <br />

        <label>Author Name</label>
        <input
          type="text"
          name="bauthor"
          id="bauthor"
          value={formdetail.bauthor}
          onChange={handleChange}
        />
        <br />
        <br />

        <label>Price</label>
        <input
          type="text"
          name="price"
          id="price"
          value={formdetail.price}
          onChange={handleChange}
        />
        <br />
        <br />

        <label>Year</label>
        <input
          type="date"
          name="year"
          id="year"
          value={formdetail.year}
          onChange={handleChange}
        />
        <br />
        <br />

        <button type="submit">Add Book</button>
      </form>
    </div>
  );
}
