package ho.buddy.test;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testController {

    @GetMapping("/")
    public String test() {
        return "<h1>Spring Project Start!!</h1>";
    }
}
