import React from 'react'
import { useEffect } from 'react'
import { useState } from 'react'
import LibraryService from '../service/LibraryService'
import { Link, Navigate } from 'react-router-dom'

export default function LibraryList() {
    const [book, setbook] = useState([])
    const [ searchtxt, setsearchtxt] = useState("")

    useEffect(()=>{
        fetchData();
    },[])

    const fetchData= async()=>{
        var result = await LibraryService.getAllBooks();
        console.log(result);
        setbook(result.data.data)
    }

    const deleteBook=(id)=>{
        LibraryService.deleteBook(id)
        .then(()=>{
            fetchData();
        })
        .catch((err)=>{
            console.log(err);
        })
    }

    const handleChange=(e)=>{
        setsearchtxt(e.target.value)
    }

  return (
    <div>

        <label htmlFor="search">Search Book : </label>
        <input type="text" name="searchtxt" id="search" value={searchtxt} onChange={handleChange} /><br />
        <br />

        <Link to="/form">
        <button>Add Books</button>
        </Link>
      {book.filter((b)=>b.bname.toLowerCase().includes(searchtxt.toLowerCase())).map(b=>(
        <div key={b.id}>
            {b.id} | {b.bname} | {b.bauthor} | {b.price} | {b.year} 

            <Link to={`/edit/${b.id}`} state={{bookdata : b}}>
            <button>Edit</button>
            </Link>

            
            <button onClick={()=>deleteBook(b.id)}>Delete</button>
            
        </div>
      ))}
    </div>
  )
}
