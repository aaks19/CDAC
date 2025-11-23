import React from 'react'

export default function CourseListComponent(props) {
  return (
    <div>
      <ul>
        {props.arr.map((val,index)=><li key={index}>{val}</li>)}
      </ul>
    </div>
  )
}
