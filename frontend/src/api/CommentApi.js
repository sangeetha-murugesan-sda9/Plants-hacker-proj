import Api from "./Api";

class CommentApi {

    getAllComments() {
        return Api.get('/comments');
    }



    getCommentsById(comments,id) {
        return Api.get('/posts/'+id+'/comments',comments);
    }

    createComment(comments,id) {
        return Api.post('/posts/'+id+'/comments',comments);
    }

    deleteComment(id) {
        return Api.delete('/comments/'+id);
    }


   /* addEmail(email) {
        return Api.delete('/comments/'+ email);
    }*/

}

export default new CommentApi();