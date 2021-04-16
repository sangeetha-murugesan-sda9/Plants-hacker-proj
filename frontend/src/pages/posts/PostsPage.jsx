// NPM Packages
import React, { useEffect, useState } from "react";

// Project files
import PostsApi from "../../api/PostsApi";
import Form from "./Form";
import Card from "./Card";
import CommentCard from "../Comments/CommentCard";
import CommentForm from "../Comments/CommentForm";
import CommentApi from "../../api/CommentApi";
import AuthApi from "../../api/AuthApi";



export default function PostsPage() {
  // Local state
  const [posts, setPosts] = useState([]);
  const [comment, setComments] = useState([]);
  // const [email, setEmail] = useState([]);

  // Methods
  async function createPost(postData) {
    try {
      const response = await PostsApi.createPost(postData);
      const post = response.data;
      const newPosts = posts.concat(post) ;
      setPosts(newPosts);
    } catch (e) {
      console.error(e);
    }
  }

 /* async function addEmail(email){
   try{
     const responses = await PostsApi.addEmail(email);
     const email = responses.data;
     const newPosts = posts.concat(email) ;
     setPosts(newPosts);
   }
   catch (e) {
     console.error(e);
   }
  }*/

  async function deletePost(post) {
    try {
      await PostsApi.deletePost(post.id);
      const newPosts = posts.filter((p) => p.id !== post.id);

      setPosts(newPosts);
    } catch (e) {
      console.error(e);
    }
  }

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

  useEffect(() => {
    PostsApi.getAllPosts()
      .then(({ data }) => setPosts(data))
      .catch((err) => console.error(err));
  }, [setPosts]);


  // Components
  const CardsArray = posts.map((post) => (
    <Card key={post.id} post={post} onDeleteClick={() => deletePost(post)} />
  ));

  return (
    <div>
       <Form onSubmit={(postData) => createPost(postData)} />
      <CommentForm onSubmit={(CommentData) => createComment(CommentData)} />
      {CardsArray}
    </div>
  );
}
