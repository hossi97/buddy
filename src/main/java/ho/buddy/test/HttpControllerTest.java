package ho.buddy.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpControllerTest {

    // QueryParameter + @RequestParam
    @GetMapping("/http/get/param")
    public String getTest1(@RequestParam int id, @RequestParam String username) {
        return "get 요청 : " + id + ", " + username;
    }

    // QueryParameter + Entity
    @GetMapping("/http/get/param/entity")
    public String getTest2(TestUser u) {
        return "get 요청 : " + u.getId() + ", " + u.getUsername() + ", " + u.getEmail() + ", " + u.getPassword();
    }

    // MIME: form 태그 (x-www-form-urlencoded) + Entity
    @PostMapping("/http/post/form/entity")
    public String postTest1(TestUser u) {
        return "post 요청 : " + u.getId() + ", " + u.getUsername() + ", " + u.getEmail() + ", " + 	u.getPassword();
    }

    // MIME: raw (text/plain) + @RequestBody
    @PostMapping("/http/post/raw")
    public String postTest2(@RequestBody String text) {
        return "post 요청 : " + text;
    }

    // MIME: json (json/...) + @RequestBody
    @PostMapping("/http/post/json")
    public String postTest3(@RequestBody String text) {
        return "post 요청 : " + text;
    }

    // MIME: json (json/...) + Entity
    @PostMapping("/http/post/json/entity")
    public String postTest4(@RequestBody TestUser u) {
        return "post 요청 : " + u.getId() + ", " + u.getUsername() + ", " + u.getEmail() + ", " + 	u.getPassword();
    }

}
