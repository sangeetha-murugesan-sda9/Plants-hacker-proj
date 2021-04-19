import React from "react";
import  "../css/style.css";


export default function PostCard({ post, onDeleteClick,email }) {
  return (

    <div className="card mt-3">
        <div className="p-3 mb-2 bg-dark text-white">
      <div className="card-body" >
        <p>{post.body}</p>

        <button className="button" onClick={onDeleteClick}>
          Delete
        </button>
      </div>
    </div>
    </div>

  );
}
