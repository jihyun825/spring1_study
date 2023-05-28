package kr.or.ddit.controller;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
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
	
	@RequestMapping(value="/list.do", method = RequestMethod.GET)
	public String boardList(Model model) {
		List<BoardVO> boardList = bservice.boardList();
		model.addAttribute("list",boardList);
		return "/pages/ddit_list";
	}
	
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
	
	@RequestMapping(value="/insert.do")
	public String insert() {
		return "/pages/ddit_form";
	}
	
	@RequestMapping(value="/insertBoard", method=RequestMethod.POST)
	public String insertBoard(@RequestBody BoardVO board, MemberVO member) {
		String goPage = "";
		String botitle = board.getBotitle();
		String bocontent = board.getBocontent();
		String mem_id = member.getMem_id();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("botitle", botitle);
		map.put("bocontent", bocontent);
		map.put("mem_id", mem_id);
		ServiceResult result = bservice.insertBoard(map);
		
		if(result == ServiceResult.OK) {
			goPage = "redirect:/list.do";
		}else {
			goPage = "redirect:/list.do";
		}
		return goPage;
		
		
	}
}
















