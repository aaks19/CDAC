import React from 'react'
import { useEffect } from 'react'
import { useState } from 'react'
import LibraryService from '../service/LibraryService'
import { Link, Navigate } from 'react-router-dom'

export default function LibraryList() {
    const [book, setbook] = useState([])

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

  return (
    <div>
        <Link to="/form">
        <button>Add Books</button>
        </Link>
      {book.map(b=>(
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
