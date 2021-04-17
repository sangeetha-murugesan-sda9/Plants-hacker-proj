package se.kth.sda.skeleton.comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.kth.sda.skeleton.ResourceNotFoundException;
import se.kth.sda.skeleton.posts.Post;
import se.kth.sda.skeleton.posts.PostRepository;
import se.kth.sda.skeleton.user.User;
import se.kth.sda.skeleton.user.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

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

        @PostMapping(value = "/posts/{postsId}/comments", consumes = "application/json")
        public ResponseEntity<Comment> createComment(@PathVariable Long postsId, @RequestBody Comment comments){
            Post posts = postRepository.findById(postsId).orElseThrow(ResourceNotFoundException::new);
            User user = userRepository.findById(postsId).orElseThrow(ResourceNotFoundException::new);
            String emailID = (String)((Map)comments).get("email");

            Logger logger = Logger.getLogger(CommentController.class.getName());

            logger.info("DEDEDED" + emailID);

            comments.setEmail(emailID);
            comments.setPosts(posts);

            user.setEmail(emailID);

            commentsRepository.save(comments);

            userRepository.save(user);

            //commentBysEmailID.put(postsId, comments.getEmail());
            return ResponseEntity.status(HttpStatus.CREATED).body(comments);
        }

        @DeleteMapping("/comments/{id}")
        public ResponseEntity<Comment> deleteComment(@PathVariable Long id){
            Comment comments = commentsRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
            User user = userRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

            //for(Map.Entry<Long, String> entry : commentBysEmailID.entrySet())
            {
                //if(entry.getKey() == id)
                {
                    if(user.getEmail() == comments.getEmail())
                        commentsRepository.delete(comments);
                }
            }

           // commentsRepository.delete(comments);
            return  ResponseEntity.ok(comments);
        }


        @GetMapping("/posts/{postsId}/comments")
        public ResponseEntity<List<Comment >>  getAllCommentsToParticularPost(@PathVariable Long postsId  )  {
            Post posts = postRepository.findById(postsId).orElseThrow(ResourceNotFoundException::new);
            return ResponseEntity.ok( posts.getComments());
        }

    }


