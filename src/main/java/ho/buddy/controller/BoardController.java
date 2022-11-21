package ho.buddy.controller;

import ho.buddy.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardSerivice;

    @GetMapping("/")
    public String index(Model model, @PageableDefault(size=2, sort="id", direction = Direction.DESC) Pageable page) {
        model.addAttribute("boards", boardSerivice.getBoards(page));

        return "index";
    }

    @GetMapping("/board/{id}")
    public String board(Model model, @PathVariable Long id){
        model.addAttribute("board", boardSerivice.getBoard(id));

        return "board/detail";
    }

    @GetMapping("/board/write")
    public String write() {
        return "board/write";
    }

    @GetMapping("/board/{id}/edit")
    public String edit(@PathVariable Long id, Model model){
        model.addAttribute("board", boardSerivice.getBoard(id));

        return "board/edit";
    }

}
