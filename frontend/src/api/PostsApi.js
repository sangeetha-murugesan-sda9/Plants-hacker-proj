import Api from "./Api";

class PostsApi {
    getAllPosts() {
        return Api.get('/posts');
    }

    getPostById(id) {
        return Api.get('/posts/'+id);
    }

    createPost(post) {
        return Api.post('/post', post);
    }

    updatePost(post) {
        return Api.put('/posts', post);
    }

    deletePost(id) {
        return Api.delete('/posts/'+id);
    }

    /* addEmail(id) {
        return Api.post('/posts/'+ id);
    }

    getEmailByPostId(id){
        return Api.get('/posts/email/' + id )
    }*/

}

export default new PostsApi();