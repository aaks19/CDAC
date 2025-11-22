import React from 'react'

export default function CourseListComponent(props) {
  return (
    <>
    <ul>
        {props.arr.map((val,index)=><li key={index}>{val}</li>)}
    </ul>
    </>
  )
}
