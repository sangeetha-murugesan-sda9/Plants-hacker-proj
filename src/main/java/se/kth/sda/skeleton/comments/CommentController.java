package se.kth.sda.skeleton.comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.kth.sda.skeleton.ResourceNotFoundException;
import se.kth.sda.skeleton.posts.Post;
import se.kth.sda.skeleton.posts.PostRepository;
import se.kth.sda.skeleton.user.UserRepository;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;


@RestController
public class CommentController {

        CommentRepository commentsRepository;
        PostRepository postRepository;
        UserRepository userRepository;
        HashMap<Long, String> commentBysEmailID = new HashMap<>();

        @Autowired
        public CommentController(CommentRepository commentsRepository, PostRepository postRepository,
                                 UserRepository userRepository) {
            this.commentsRepository = commentsRepository;
            this.postRepository = postRepository;
            this.userRepository = userRepository;
        }


    @PostMapping("/posts/{postsId}/comments")
    public ResponseEntity<Comment> createComment(@PathVariable Long postsId, @RequestBody Comment comments, Principal principal){
        Post posts = postRepository.findById(postsId).orElseThrow(ResourceNotFoundException::new);
        String userName = principal.getName();

        comments.setEmail(userName);
        System.out.println("String 123:" + comments.getEmail());
        comments.setPosts(posts);

        commentsRepository.save(comments);
        return ResponseEntity.status(HttpStatus.CREATED).body(comments);
    }

        @DeleteMapping("/comments/{id}")
        public ResponseEntity<Comment> deleteComment(@PathVariable Long id,Principal principal){
            Comment comments = commentsRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

            if(comments.getEmail().equalsIgnoreCase(principal.getName())) {
                commentsRepository.delete(comments);
                return ResponseEntity.ok(comments);
            }
            else {
                throw new ResourceNotFoundException();
            }
        }


        @GetMapping("/posts/{postsId}/comments")
        public ResponseEntity<List<Comment >>  getAllCommentsToParticularArticle(@PathVariable Long postsId  )  {
            Post posts = postRepository.findById(postsId).orElseThrow(ResourceNotFoundException::new);
            return ResponseEntity.ok( posts.getComments());
        }



    }


