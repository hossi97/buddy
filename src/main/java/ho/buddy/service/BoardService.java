package ho.buddy.service;

import ho.buddy.domain.Board;
import ho.buddy.domain.Member;
import ho.buddy.repository.BoardRepository;
import ho.buddy.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public void write(Board board, Member member) {
        Member findMember = userRepository.findById(member.getId()).get();
        board.setMemberAndBoard(findMember);
        boardRepository.save(board);
    }

    public Page<Board> getBoards(Pageable page) {
        return boardRepository.findAll(page);
    }

    public Board getBoard(Long id) {
        return boardRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("해당 글에 대한 정보를 찾을 수 없습니다.");
        });
    }

    @Transactional
    public void delete(Long id) {
        boardRepository.deleteById(id);
    }

    @Transactional
    public void update(Long id, Board board) {
        Board findBoard = boardRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("해당 글에 대한 정보를 찾을 수 없습니다.");
        });
        findBoard.setTitle(board.getTitle());
        findBoard.setContent(board.getContent());
        // boardRepository.save(findBoard); // 기존 글수정
    }
}
