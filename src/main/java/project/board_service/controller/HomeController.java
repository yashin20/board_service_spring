package project.board_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.board_service.dto.PostDto;
import project.board_service.entity.Post;
import project.board_service.service.PostService;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final PostService postService;

    /** Home Page - ("/") **/
    @GetMapping("/")
    public String home(@PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 20) Pageable pageable,
                       @RequestParam(value = "keyword", required = false) String keyword,
                       @RequestParam(value = "sort", required = false) String sort,
                       Model model) {

        pageable = createPageRequest(pageable, sort);

        Page<Post> posts = (keyword != null && !keyword.isEmpty())
                ? postService.searchPosts(keyword, pageable)
                : postService.findAllPosts(pageable);
        Page<PostDto.Response> list = posts.map(PostDto.Response::new);

        model.addAttribute("posts", list); //post dto list
        model.addAttribute("keyword", keyword); //검색 키워드
        model.addAttribute("sort", sort); //정렬 조건
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber()); //이전 페이지 정보
        model.addAttribute("next", pageable.next().getPageNumber()); //다음 페이지 정보
        model.addAttribute("hasPrevious", list.hasPrevious()); //이전 페이지 존재 여부
        model.addAttribute("hasNext", list.hasNext()); //다음 페이지 존재 여부

        /**페이지 번호**/
        int currentPage = pageable.getPageNumber() + 1;
        model.addAttribute("currentPage", currentPage);

        int blockSize = 5;
        int startPage = ((currentPage - 1) / blockSize) * blockSize + 1;
        int endPage = Math.min(startPage + blockSize - 1, list.getTotalPages());
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "index";
    }

    private Pageable createPageRequest(Pageable pageable, String sort) {
        if ("createdAt".equals(sort)) {
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                    Sort.by(Sort.Direction.DESC, "createdAt"));
        } else if ("likes".equals(sort)) {
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                    Sort.by(Sort.Direction.DESC, "likes"));
        } else if ("views".equals(sort)) {
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                    Sort.by(Sort.Direction.DESC, "views"));
        }
        return pageable;
    }
}
