package project.board_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.board_service.dto.CommentDto;
import project.board_service.dto.PostDto;
import project.board_service.entity.Comment;
import project.board_service.entity.Post;
import project.board_service.exception.DataAlreadyExistsException;
import project.board_service.exception.PasswordCheckFailedException;
import project.board_service.service.CommentService;
import project.board_service.service.MemberService;
import project.board_service.service.PostService;

import java.util.List;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final MemberService memberService;
    private final CommentService commentService;

    /** 게시글 생성 (create post) - "/posts/new" */
    @GetMapping("/new")
    public String createPostForm(Model model) {
        model.addAttribute("createPostForm", new PostDto.Request());

        return "posts/create";
    }
    @PostMapping("/new")
    public String createPost(@ModelAttribute("createPostForm") @Validated(PostDto.Request.Create.class) PostDto.Request dto,
                             BindingResult bindingResult, Model model) {
        /*'유효성 검사' 에러처리*/
        if (bindingResult.hasErrors()) {
            FieldError titleError = bindingResult.getFieldError("title");
            if (titleError != null) {
                model.addAttribute("titleError", titleError.getDefaultMessage());
            }

            FieldError contentError = bindingResult.getFieldError("content");
            if (contentError != null) {
                model.addAttribute("contentError", contentError.getDefaultMessage());
            }

            return "posts/create";
        }

        try {
            //0. 현재 로그인된 회원을 글쓴이로 등록
            dto.setMember(memberService.getCurrentMember());
            //1. 게시글 등록
            postService.createPost(dto);
        } catch (DataAlreadyExistsException | PasswordCheckFailedException ex) {
            bindingResult.reject("errorMessage", ex.getMessage());
            model.addAttribute("errorMessage", ex.getMessage());

            return "posts/create";
        }

        return "redirect:/";
    }

    /** 게시글 상세 정보 (post information) - "/posts/{postId}" */
    @GetMapping("/{postId}")
    public String postInformation(@PathVariable Long postId, Model model) {
        //1. 게시글 정보 (post information)
        Post post = postService.findPostById(postId);
        model.addAttribute("post", new PostDto.Response(post));

        //2. 댓글 리스트 - 게시글 소속 (post's comment list)
        List<Comment> comments = commentService.findCommentsByPost(post);
        List<CommentDto.Response> list = comments.stream().map(CommentDto.Response::new).toList();
        model.addAttribute("comments", list); //comment dto list

        //3. 댓글 작성 폼
        CommentDto.Request commentRequestDto = new CommentDto.Request();
        commentRequestDto.setPostId(postId);
        model.addAttribute("commentRequestDto", commentRequestDto);

        return "posts/information";
    }

    /** 게시글 수정 (update post) - "/posts/update/{postId}" */
    @GetMapping("/update/{postId}")
    public String updateForm(@PathVariable Long postId, Model model) {
        //1. 수정 대상 게시글
        Post post = postService.findPostById(postId);

        //2. 게시글 수정 요청 DTO
        PostDto.Request request = new PostDto.Request();
        request.setId(post.getId());
        request.setTitle(post.getTitle());
        request.setContent(post.getContent());
        request.setMember(post.getMember());
        model.addAttribute("updatePostForm", request);

        return "posts/update";
    }
    @PostMapping("/update/{postId}")
    public String update(@PathVariable Long postId,
                         @ModelAttribute("updatePostForm") @Validated(PostDto.Request.Update.class) PostDto.Request dto,
                         BindingResult bindingResult, Model model) {
        /*'유효성 검사' 에러처리*/
        if (bindingResult.hasErrors()) {
            FieldError titleError = bindingResult.getFieldError("title");
            if (titleError != null) {
                model.addAttribute("titleError", titleError.getDefaultMessage());
            }

            FieldError contentError = bindingResult.getFieldError("content");
            if (contentError != null) {
                model.addAttribute("contentError", contentError.getDefaultMessage());
            }

            return "posts/update";
        }

        try {
            postService.updatePost(dto);
        } catch (DataAlreadyExistsException | PasswordCheckFailedException ex) {
            bindingResult.reject("errorMessage", ex.getMessage());
            model.addAttribute("errorMessage", ex.getMessage());

            return "posts/update";
        }

        return "redirect:/posts/" + postId;
    }

    /** 게시글 삭제 (delete post) - "/posts/delete/{postId}" */
    @PostMapping("/delete/{postId}")
    public String delete(@PathVariable Long postId) {
        postService.deletePost(postId);
        return "redirect:/";
    }
}
