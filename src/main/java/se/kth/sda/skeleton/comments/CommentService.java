package se.kth.sda.skeleton.comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.kth.sda.skeleton.ResourceNotFoundException;
import se.kth.sda.skeleton.posts.Post;
import se.kth.sda.skeleton.posts.PostRepository;


@Service
public class CommentService {

    CommentRepository commentsRepository;
    PostRepository postRepository;

    public CommentService(CommentRepository commentsRepository, PostRepository postRepository) {
        this.commentsRepository = commentsRepository;
        this.postRepository = postRepository;
    }






}