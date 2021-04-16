package se.kth.sda.skeleton.comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.kth.sda.skeleton.ResourceNotFoundException;
import se.kth.sda.skeleton.posts.Post;
import se.kth.sda.skeleton.posts.PostRepository;

import java.util.List;

@RestController
public class CommentController {

    CommentRepository commentsRepository;
        PostRepository postRepository;

        @Autowired
        public CommentController(CommentRepository commentsRepository, PostRepository postRepository) {
            this.commentsRepository = commentsRepository;
            this.postRepository = postRepository;
        }

        @PostMapping("/posts/{postsId}/comments")
        public ResponseEntity<Comment> createComment(@PathVariable Long postsId, @RequestBody Comment comments){
            Post posts = postRepository.findById(postsId).orElseThrow(ResourceNotFoundException::new);

            comments.setPosts(posts);

            commentsRepository.save(comments);
            return ResponseEntity.status(HttpStatus.CREATED).body(comments);
        }

        @DeleteMapping("/comments/{id}")
        public ResponseEntity<Comment> deleteComment(@PathVariable Long id){
            Comment comments = commentsRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

            commentsRepository.delete(comments);
            return  ResponseEntity.ok(comments);
        }
        @GetMapping("/posts/{postsId}/comments")
        public ResponseEntity<List<Comment >>  getAllCommentsToParticularArticle(@PathVariable Long postsId  )  {
            Post posts = postRepository.findById(postsId).orElseThrow(ResourceNotFoundException::new);
            return ResponseEntity.ok( posts.getComments());
        }



    }


