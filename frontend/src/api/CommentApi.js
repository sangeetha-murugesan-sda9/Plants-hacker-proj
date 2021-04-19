import Api from "./Api";

class CommentApi {


    getCommentsById(comments,id) {
        return Api.get('/posts/'+id+'/comments',comments);
    }

    createComment(comments,id) {
        return Api.post('/posts/'+id+'/comments',comments);
    }

    deleteComment(id) {
        return Api.delete('/comments/'+id);
    }
}

export default new CommentApi();