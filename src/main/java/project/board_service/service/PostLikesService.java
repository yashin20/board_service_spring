package project.board_service.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project.board_service.entity.Member;
import project.board_service.entity.Post;
import project.board_service.entity.PostLikes;
import project.board_service.exception.DataNotFoundException;
import project.board_service.repository.MemberRepository;
import project.board_service.repository.PostLikesRepository;
import project.board_service.repository.PostRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostLikesService {

    private final PostLikesRepository postLikesRepository;
    private final MemberRepository memberRepository;
    private final PostRepository postRepository;

    /* member - post 좋아요(likes) 로직*/
    @Transactional
    public void toggleLike(Long memberId, Long postId) {
        //1. 대상 member / post
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new DataNotFoundException("Member not found"));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new DataNotFoundException("Post not found"));

        /*게시글 좋아요 처리 로직
        * 1. 이미 좋아요를 누른 경우 -> 좋아요 취소
        * 2. 좋아요를 누르지 않은 경우 -> 좋아요 누름*/
        if (postLikesRepository.existsByMemberAndPost(member, post)) {
            PostLikes postLikes = postLikesRepository.findByMemberAndPost(member, post)
                    .orElseThrow(() -> new DataNotFoundException("Post Likes not found"));
            postLikesRepository.delete(postLikes);
            post.decrementLikes();
        } else {
            PostLikes postLikes = new PostLikes();
            postLikes.setMember(member);
            postLikes.setPost(post);
            postLikesRepository.save(postLikes);
            post.incrementLikes();
        }
    }

    /*member - post 이미 좋아요를 눌렀는지 확인하는 로직*/
    public boolean isLikedByMember(Long memberId, Long postId) {
        //1. 대상 member / post
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new DataNotFoundException("Member not found"));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new DataNotFoundException("Post not found"));

        return postLikesRepository.existsByMemberAndPost(member, post);
    }
}
