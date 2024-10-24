package project.board_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.board_service.dto.PostDto;
import project.board_service.entity.Member;
import project.board_service.entity.Post;
import project.board_service.exception.DataNotFoundException;
import project.board_service.exception.UnauthorizedAccessException;
import project.board_service.repository.MemberRepository;
import project.board_service.repository.PostRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    /** Create Post **/
    @Transactional
    public Post createPost(PostDto.Request dto) {
        //1. request dto -> entity
        Post entity = dto.toEntity();

        //2. save entity
        return postRepository.save(entity);
    }

    /** Read Post **/
    /*id 기반 단건 조회*/
    public Post findPostById(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("PostService.findPostById - 존재하지 않는 게시글 입니다."));
    }
    /**게시글 정보 조회만을 위한 메서드 (조회수 증가)**/
    @Transactional
    public Post findPostOnlyView(Long id) {
        Post post = findPostById(id);
        post.incrementView();
        return post;
    }

    /** Paging Post List **/
    /*1. 게시글 전체 조회*/
    public Page<Post> findAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }
    /*2. 특정 회원 작성 게시글 조회*/
    public Page<Post> findAllPostsByMember(Member member, Pageable pageable) {
        return postRepository.findByMember(member, pageable);
    }
    /*3. Title - keyword 검색 게시글 조회 */
    public Page<Post> searchPosts(String keyword, Pageable pageable) {
        return postRepository.findByTitleContaining(keyword, pageable);
    }

    /** Update Post **/
    @Transactional
    public Post updatePost(PostDto.Request dto) {
        //1. 수정대상 게시글
        Post post = findPostById(dto.getId());

        //2. '작성자 == 로그인 회원' 확인
        if (!isAuthorValidation(post)) {
            throw new UnauthorizedAccessException("PostService.updatePost - 올바르지 않은 접근");
        }

        //3. post 수정
        post.update(dto);

        return post;
    }
    //'작성자' == '로그인 회원' 검증
    private Boolean isAuthorValidation(Post post) {
        //현재 로그인 회원
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new DataNotFoundException("PostService.isAuthorValidation - 존재하지 않는 회원"));

        return post.getMember().getId().equals(member.getId());
    }

    /** Delete Post **/
    @Transactional
    public Long deletePost(Long id) {
        Post deletePost = findPostById(id);

        //2. '작성자 == 로그인 회원' 확인
        if (!isAuthorValidation(deletePost)) {
            throw new UnauthorizedAccessException("PostService.updatePost - 올바르지 않은 접근");
        }

        Long deletedPostId = deletePost.getId();
        postRepository.delete(deletePost);
        return deletedPostId;
    }
}
