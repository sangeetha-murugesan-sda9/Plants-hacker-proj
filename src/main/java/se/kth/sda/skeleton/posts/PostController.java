package se.kth.sda.skeleton.posts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.kth.sda.skeleton.ResourceNotFoundException;
import se.kth.sda.skeleton.auth.AuthService;
import se.kth.sda.skeleton.user.UserRepository;
import java.security.Principal;
import java.util.List;

 @RestController
public class PostController {
    PostRepository postRepository;
    UserRepository userRepository ;

    @Autowired
    private AuthService authService;
@Autowired
    public PostController(PostRepository postRepository,UserRepository userRepository) {
        this.postRepository = postRepository;
    this.userRepository= userRepository;
    }


    @GetMapping("/posts")
    public ResponseEntity<List<Post>> listAllPosts() {
        List<Post> posts = postRepository.findAll();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Long id) {
        Post posts = postRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        System.out.println(posts);
        return ResponseEntity.ok(posts);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post updatedPost) {
        postRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        updatedPost.setId(id);
        Post posts = postRepository.save(updatedPost);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable Long id) {
        Post posts = postRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        postRepository.delete(posts);
        return new ResponseEntity<Post>(HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity<Post> createPost(@RequestBody Post post , Principal principal) {
    String userName = principal.getName();
    post.setEmail(userName);
    postRepository.save(post);
    return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

}



