package kr.or.ddit.free.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.free.service.IFreeService;
import kr.or.ddit.vo.FreeVO;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Controller
@RequestMapping("/free")
public class FreeController {
	
	@Inject
	IFreeService freeService;
	
	@RequestMapping(value="/form.do",method = RequestMethod.GET)
	public String formFree() {
		return "free/form";
		
	}
	@RequestMapping(value ="/insert.do", method=RequestMethod.POST)
	public String insertFree(FreeVO vo, Model model) {
		String goPage = "";
		
		Map<String,String> errors = new HashMap<String, String>();
		
		if(StringUtils.isBlank(vo.getBoTitle())) {
			errors.put("boTitle","제목을 입력하세요");
		}
		if(StringUtils.isBlank(vo.getBoContent())) {
			errors.put("boContent", "내용을 입력하세요!");
		}
		if(errors.size() > 0) {
			model.addAttribute("errors",errors);
			model.addAttribute("notice",vo);
			goPage = "free/form";
		}else {
			vo.setBoWriter("a001");
			ServiceResult result = freeService.insertFree(vo);
			if(result.equals(ServiceResult.OK)) {
				goPage = "redirect:/free/detail.do?boNo="+vo.getBoNo();
			}else {
				errors.put("msg", "서버에러! 다시시도해주세요!");
				model.addAttribute("errors",errors);
				goPage = "free/form";
			}
			
		}
		
		return goPage;
	}
	
	@RequestMapping(value="/detail.do")
	public String detailFree(int boNo, Model model) {
		FreeVO vo = freeService.selectFree(boNo);
		String goPage = "";
		if(vo == null) {
			goPage = "redirect:/free/list.do";
		}else {
			model.addAttribute("free",vo);
			goPage = "free/view";
		}
		return goPage;
	}
	
	@RequestMapping(value="/update.do", method = RequestMethod.GET)
	public String updateFreeForm(int boNo,Model model) {
		FreeVO vo = freeService.selectFree(boNo);
		String goPage = "";
		if(vo == null) {
			goPage = "redirect:/free/list.do";
		}else {
			model.addAttribute("free",vo);
			model.addAttribute("status","u");
			goPage = "free/form";
		}
		return goPage;
		
		
	}
	
	@RequestMapping(value="/update.do",method=RequestMethod.POST)
	public String updateFree(FreeVO vo, Model model) {
		String goPage = "";
		ServiceResult result = freeService.updateFree(vo);
		
		if(result.equals(ServiceResult.OK)) {
			goPage = "redirect:/free/detail.do?boNo="+vo.getBoNo();
		}else {
			model.addAttribute("notice",vo);
			model.addAttribute("status","u");
			goPage="free/form";
		}
		return goPage;
	}
	
	@RequestMapping(value="/delete.do")
	public String deleteFree(int boNo, Model model) {
		String goPage = "";
		ServiceResult result = freeService.deleteFree(boNo);
		
		if(result.equals(ServiceResult.OK)) {
			goPage = "redirect:/free/list.do";
		}else {
			goPage = "redirect:/free/detail.do?boNo="+boNo;
		}
		
		return goPage;
	}
	
	
	@RequestMapping(value="/list.do")
	public String freeList(@RequestParam(name="page", required = false, defaultValue = "1")int currentPage,
			@RequestParam(required = false,defaultValue = "title") String searchType,
			@RequestParam(required = false) String searchWord,
			Model model) {
		
			PaginationInfoVO<FreeVO> pagingVo = new PaginationInfoVO<FreeVO>();
		
		if(StringUtils.isNotBlank(searchWord)) {
			pagingVo.setSearchType(searchType);
			pagingVo.setSearchWord(searchWord);
			model.addAttribute("searchType",searchType);
			model.addAttribute("searchWord",searchWord);
		}
		
		pagingVo.setCurrentPage(currentPage);
		
		int totalRecord = freeService.selectFreeCount(pagingVo);
		pagingVo.setTotalRecord(totalRecord);
		
		List<FreeVO> dataList = freeService.selectFree2(pagingVo);
		pagingVo.setDataList(dataList);
		model.addAttribute("pagingVo",pagingVo);
		List<FreeVO> alltFreeList = freeService.alltFreeList();
		model.addAttribute("alltFreeList",alltFreeList);
		
		
		return "free/list";
		
	}
	
	

}
