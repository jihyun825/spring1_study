package kr.or.ddit.board.web;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Controller
@RequestMapping("/board")
public class BoardRetrieveController {
	
	@Inject
	private IBoardService boardService;

	@RequestMapping(value="/list.do")
	public String boardList(@RequestParam(name="page", required = false,defaultValue = "1")int currentPage,
			@RequestParam(required = false,defaultValue = "title") String searchType,
			@RequestParam(required = false) String searchWord,
			Model model) {
		
		//일반적인 게시판 목록 조회(방법1) - 페이징이 구현되어있지않다.
		//List<BoardVO> boardlist = boardService.selectBoardList();
		//model.addAttribute("boardList",boardlist);
		
		
		// 페이징 및 검색이 적용된 목록 조회(방법2)
		PaginationInfoVO<BoardVO> pagingVO = new PaginationInfoVO<BoardVO>();
		// 브라우저에서 검색한 검색 유형, 키워드를 이용하여 검색처리
		//검색키워드가 있으면 검색을 한거고, 키워드가없으면 검색을 하지않음
		if(StringUtils.isNotBlank(searchWord)) {
			pagingVO.setSearchType(searchType);
			pagingVO.setSearchWord(searchWord);
			model.addAttribute("searchType",searchType);
			model.addAttribute("searchWord",searchWord);
			
		}
		pagingVO.setCurrentPage(currentPage);
		
		int totalRecord = boardService.selectBoardCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<BoardVO> dataList = boardService.selectBoardList2(pagingVO);
		pagingVO.setDataList(dataList);
		model.addAttribute("pagingVO", pagingVO);
		
		List<BoardVO> alltBoardList = boardService.alltBoardList();
		model.addAttribute("alltBoardList",alltBoardList);
		return "board/list";
	}
	@RequestMapping(value="/detail.do",method=RequestMethod.GET)
	public String boardDetail(int boNo,Model model) {
		String goPage = "";
		BoardVO boardVo = boardService.selectBoard(boNo);
		if(boardVo == null) {
			goPage = "redirect:/board/list.do";
		}else {
			
			model.addAttribute("board", boardVo);
			goPage = "board/view";
		}
		return goPage;
		
	}
}
