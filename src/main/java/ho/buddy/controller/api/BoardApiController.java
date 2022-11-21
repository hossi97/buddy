package ho.buddy.controller.api;

import ho.buddy.config.auth.CustomUserContext;
import ho.buddy.domain.Board;
import ho.buddy.domain.Member;
import ho.buddy.dto.ResponseDto;
import ho.buddy.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal
    CustomUserContext principal) {

        Member member = principal.getMember();

        boardService.write(board, member);
        return new ResponseDto<Integer>(HttpStatus.OK, HttpStatus.OK.value(), 1);
    }

    @PutMapping("/api/board/{id}/edit")
    public ResponseDto<Integer> update(@PathVariable Long id, @RequestBody Board board) {
        boardService.update(id, board);
        return new ResponseDto<Integer>(HttpStatus.OK, HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/api/board/{id}")
    public ResponseDto<Integer> delete(@PathVariable Long id) {
        boardService.delete(id);
        return new ResponseDto<Integer>(HttpStatus.OK, HttpStatus.OK.value(), 1);
    }
}
