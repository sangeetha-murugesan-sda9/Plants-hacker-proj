// NPM Packages
import React, { useEffect, useState } from "react";

// Project files
import PostsApi from "../../api/CommentApi";
import Form from "./CommentForm";
import CommentCard from "./CommentCard";


export default function CommentsPage() {
    // Local state
    const [posts, setPosts] = useState([]);

    // Methods
    async function createComment(postData) {
        try {
            const response = await CommentApi.createComment(postData);
            const comment = response.data;
            const newComments = posts.concat(comment);

            setComments(newComments);
        } catch (e) {
            console.error(e);
        }
    }

    async function deleteComment(comment) {
        try {
            await CommentApi.deleteComment(post.id);
            const newComments = comment.filter((p) => p.id !== post.id);

            setComments(newComments);
        } catch (e) {
            console.error(e);
        }
    }

    useEffect(() => {
        PostsApi.getAllComments()
            .then(({ data }) => setComments(data))
            .catch((err) => console.error(err));
    }, [setComments]);


    // Components
    const CardsArray = comment.map((comment) => (
        <CommentCard key={comment.id} comment={comment} onDeleteClick={() => deleteComment(comment)} />
    ));

    return (
        <div>
            <Form onSubmit={(CommentData) => createComment(postData)} />

            {CardsArray}
        </div>
    );
}
