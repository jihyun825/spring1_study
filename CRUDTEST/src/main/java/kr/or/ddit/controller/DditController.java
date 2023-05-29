package kr.or.ddit.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.service.IBoardService;
import kr.or.ddit.service.IMemberService;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class DditController {
	@Inject
	IMemberService service;
	
	@Inject
	IBoardService bservice;
	
	//최초 메인페이지 호출
	@RequestMapping(value="/")
	public String dditSignin() {
		return "pages/ddit_signin";
	}
	
	//로그인 프로세스
	@RequestMapping(value="/signIn.do", method=RequestMethod.POST)
	public String signIn(MemberVO member, Model model, RedirectAttributes redirect, HttpSession session) {
		String goPage ="";
		
		//누락된 내용이있으면
		if(StringUtils.isBlank(member.getMem_id()) ||StringUtils.isBlank(member.getMem_pw())) {
			goPage = "redirect:/";
			redirect.addFlashAttribute("sure","no");
			
		}else { //정상적으로 값이 들어왔으면
			MemberVO reMem = service.singIn(member);
			if(reMem == null) {
				redirect.addFlashAttribute("isOk","no");
				goPage = "redirect:/pages/ddit_signin";
			}else {
				session.setAttribute("member",reMem);
				
				goPage = "redirect:/list.do";
			}
		}
		return goPage;
	}
	
	//회원가입 페이지 호출
	@RequestMapping(value = "/signUpPage", method=RequestMethod.GET)
	public String signUpPage() {
		return  "pages/ddit_signup";
	}
	//회원가입 프로세스
	@RequestMapping(value="/signUp", method=RequestMethod.POST)
	public String signUp(MemberVO member, Model model,RedirectAttributes redirect) {
		String goPage = "";
		
		if(StringUtils.isBlank(member.getMem_id()) ||
			StringUtils.isBlank(member.getMem_pw())||
			StringUtils.isBlank(member.getMem_email())||
			StringUtils.isBlank(member.getMem_gender())||
			StringUtils.isBlank(member.getMem_agree())||
			StringUtils.isBlank(member.getMem_phone()) ||
			StringUtils.isBlank(member.getMem_name()) ) {
			goPage = "redirect:/pages/ddit_signup";
			redirect.addFlashAttribute("done","no");
		}else {
			ServiceResult result = service.signUp(member);
			if(result.equals(ServiceResult.OK)) {
				goPage = "/";
				redirect.addFlashAttribute("done","yes");
			}else {
				goPage = "redirect:/pages/ddit_signup";
				redirect.addFlashAttribute("fail","no");
			}
	}
		return goPage;
}
	//메인리스트 출력
	@RequestMapping(value="/list.do", method = RequestMethod.GET)
	public String boardList(Model model) {
		List<BoardVO> boardList = bservice.boardList();
		model.addAttribute("list",boardList);
		return "/pages/ddit_list";
	}
	//게시글 상세보기
	@RequestMapping(value="/boardDetail/{bono}")
	public String datailBoard(@PathVariable("bono")int bono,Model model,RedirectAttributes redirect) {
		String goPage ="";
		BoardVO board = bservice.detailBoard(bono);
		
		if(board != null){
			goPage = "pages/ddit_detail";
			model.addAttribute("board",board);
		}else {
			goPage = "redirect:/list.do";
			redirect.addFlashAttribute("wrong","n");
		};
		return goPage;
		
	}
	
	//게시글 삭제
	@RequestMapping(value="/boardDel/{bono}")
	public String boardDel(@PathVariable("bono")int bono) {
		String goPage ="";
		ServiceResult result = bservice.boardDel(bono);
		if(result == ServiceResult.OK) {
			goPage = "redirect:/list.do";
		}else {
			goPage = "/boardDetail/"+bono;
		}
		return goPage;
	}
	
	//게시글 등록 폼 호출
	@RequestMapping(value="/insert.do")
	public String insert() {
		return "/pages/ddit_form";
	}
	//게시글 등록 프로세스
	@RequestMapping(value="/insertBoard", method=RequestMethod.POST)
	public String insertBoard(BoardVO board, MemberVO member) {
		String goPage = "";
	    String botitle = board.getBotitle();
	    String bocontent = board.getBocontent();
	    String mem_id = member.getMem_id();
//	    log.info("botitle: " + botitle);
//	    log.info("bocontent: " + bocontent);
//	    log.info("mem_id: " + mem_id);
	    
	    HashMap<String, String> map = new HashMap<>();
	    map.put("botitle", botitle);
	    map.put("bocontent", bocontent);
	    map.put("mem_id", mem_id);
	    
	    ServiceResult result = bservice.insertBoard(map);
	    
	    if (result == ServiceResult.OK) {
	       goPage = "redirect:/list.do";
	    } else {
	    	 goPage = "redirect:/list.do";
	    }
	    
	    return goPage;
	}
	
	//게시글 수정 폼 출력
	@RequestMapping(value="/boardMod/{bono}")
	public String boardMod(Model model, @PathVariable("bono")int bono) {
		String goPage = "";
		BoardVO board = bservice.selectBoard(bono);
		
		if(board ==null) {
			goPage = "/boardDetail/"+bono;
		}else {
			model.addAttribute("board",board);
			model.addAttribute("modify","m");
			goPage ="pages/ddit_form";
			
		}
		return goPage;
			
	}
	//게시글 수정 프로세스
	@RequestMapping(value="/update.do")
	public String boardUpdate(BoardVO board, MemberVO member,RedirectAttributes redirect) {
		String goPage = "";
		HashMap<String, String> map = new HashMap<>();
		map.put("botitle",board.getBotitle());
		map.put("bocontent",board.getBocontent());
		map.put("mem_id",member.getMem_id());
		map.put("bono",board.getBono());
		log.info("botitle: " + board.getBotitle());
	    log.info("bocontent: " +board.getBocontent());
	    log.info("mem_id: " + member.getMem_id());
	    log.info("bono: " + board.getBono());
		
		
		ServiceResult result = bservice.updateBoard(map);
		
		if(result == ServiceResult.OK) {
			goPage = "redirect:/boardDetail/"+board.getBono();
		}else {
			goPage = "redirect:/boardDetail/"+board.getBono();
			redirect.addFlashAttribute("fail","f");
		}
		
		return goPage;
	}

}
















