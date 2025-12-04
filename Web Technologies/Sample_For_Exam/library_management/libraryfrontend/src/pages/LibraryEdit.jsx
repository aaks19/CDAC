import React from 'react'
import { useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import LibraryService from '../service/LibraryService';
import { useEffect } from 'react';

export default function LibraryEdit() {
    const [formdetail, setformdetail] = useState({id:"",bname:"",bauthor:"",price:"",year:""})
    const navigate = useNavigate();
    const location = useLocation();
    const handleChange=(e)=>{
        const {name, value} = e.target;
        setformdetail({...formdetail,[name]:value})
    }

    useEffect(()=>{
        setformdetail(location.state.bookdata)
    },[])

    const updateBook=(e)=>{
            e.preventDefault();
            if(formdetail.id==="" || formdetail.bname==="" || formdetail.bauthor==="" || formdetail.price === "" || formdetail.year===""){
                alert("Field cant be empty")
            }else{
                LibraryService.updateBook(formdetail)
                .then(()=>{
                    alert("Book added")
                    navigate("/")
                })
            }
        }
    
        

  return (
    <div>

        <form onSubmit={updateBook}>

        <label>Book id</label>
        <input type="text" name='id' id='id' value={formdetail.id} onChange={handleChange} />

        <label>Book Name</label>
        <input type="text" name='bname' id='bname' value={formdetail.bname} onChange={handleChange} />

        <label>Author Name</label>
        <input type="text" name='bauthor' id='bauthor' value={formdetail.bauthor} onChange={handleChange} />

        <label>Price</label>
        <input type="text" name='price' id='price' value={formdetail.price} onChange={handleChange} />

        <label>Year</label>
        <input type="date" name='year' id='year' value={formdetail.year} onChange={handleChange} />

        <button type='submit'>Add Book</button>
        </form>
    </div>
  )
}
