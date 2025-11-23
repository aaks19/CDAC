import React from 'react'

export default function ProductListComponent(props) {
  return (
    <div>
      <ul>
        {props.arr.map((val,index)=><li key={index}>{val}</li>)}
      </ul>
    </div>
  )
}
