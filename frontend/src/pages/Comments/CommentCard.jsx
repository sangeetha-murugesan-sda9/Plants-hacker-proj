import React from "react";

export default function CommentCard({ comment, onDeleteClick }) {
    return (
        <div className="card mt-3">
            <div className="p-3 mb-2 bg-info text-white">
            <div className="card-body">
                <p>{comment.body}</p>

                <button className="btn btn-danger" onClick={onDeleteClick}>
                    Delete
                </button>
            </div>
        </div>
        </div>
    );
}