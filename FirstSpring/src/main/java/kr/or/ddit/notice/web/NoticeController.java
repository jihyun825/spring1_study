package kr.or.ddit.notice.web;

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
import kr.or.ddit.notice.service.INoticeService;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	@Inject
	private INoticeService noticeService;
	
	@RequestMapping(value="/form.do", method=RequestMethod.GET)
	public String noticeForm() {
		return "notice/form";
	
	}
	
	@RequestMapping(value="/insert.do",method=RequestMethod.POST)
	public String noticeInsert(NoticeVO vo,Model model) {
		String goPage = "";
		Map<String, String> errors = new HashMap<String,String>();
		
		if(StringUtils.isBlank(vo.getBoTitle())) {
			errors.put("boTitle","제목을 입력하세요");
		}
		
		if(StringUtils.isBlank(vo.getBoContent())) {
			errors.put("boContent", "내용을 입력하세요");
		}
		
		if(errors.size()>0) {
			model.addAttribute("errors",errors);
			model.addAttribute("notice",vo);
			goPage = "notice/form";
		}else {
			vo.setBoWriter("a001");
			ServiceResult result = noticeService.insertNotice(vo);
			System.out.println(result);
			if((result.equals(ServiceResult.OK))) {
				goPage="redirect:/notice/detail.do?boNo="+vo.getBoNo();
				System.out.println(vo.getBoNo());
			}else {
				errors.put("msg","서버에러! 다시 시도해주세요!");
				model.addAttribute("errors",errors);
				goPage = "notice/form";
			}
		}
		
		return goPage;
	}
	
	@RequestMapping(value="/detail.do")
	public String noticeDetail(int boNo, Model model) {
		String goPage = "";
		NoticeVO vo = noticeService.selectNotice(boNo);
		
		if(vo == null) {
			goPage = "redirect:/notice/list.do";
		}else {
		model.addAttribute("notice",vo);
		 goPage = "notice/view";
		}
		
		return goPage;
	}
	@RequestMapping(value="/update.do",method=RequestMethod.GET)
	public String noticeUpdateForm(int boNo,Model model) {
		String goPage = "";
		NoticeVO vo = noticeService.selectNotice(boNo);
		if(vo == null) {
			goPage = "redirect:/notice/list.do";
		}else {
			model.addAttribute("notice",vo);
			model.addAttribute("status","u");
			goPage = "notice/form";
		}
		return goPage;
	}
	@RequestMapping(value="/update.do",method=RequestMethod.POST)
	public String noticeUpdate(NoticeVO vo, Model model) {
		String goPage="";
		ServiceResult result = noticeService.updateNotice(vo);
		
		if(result.equals(ServiceResult.OK)) {
			goPage = "redirect:/notice/detail.do?boNo=" + vo.getBoNo();
		}else {
			model.addAttribute("notice",vo);
			model.addAttribute("status","u");
			goPage = "notice/form";
		}
		return goPage;
	}
	
	@RequestMapping(value="/delete.do",method = RequestMethod.POST)
	public String noticeDelete(int boNo, Model model) {
		String goPage= "";
		ServiceResult result = noticeService.deleteNotice(boNo);
		
		if(result.equals(ServiceResult.OK)) {
			goPage = "redirect:/notice/list.do";
		}else {
			goPage = "redirect:/notice/detail.do?boNo="+boNo;
		}
		return goPage;
	}
	
	@RequestMapping(value="/list.do")
	public String noticeList(@RequestParam(name="page", required = false, defaultValue = "1")int currentPage,
			@RequestParam(required = false,defaultValue = "title") String searchType,
			@RequestParam(required = false) String searchWord,
			Model model) {
		
		PaginationInfoVO<NoticeVO> pagingVo = new PaginationInfoVO<NoticeVO>();
		
		if(StringUtils.isNotBlank(searchWord)) {
			pagingVo.setSearchType(searchType);
			pagingVo.setSearchWord(searchWord);
			model.addAttribute("searchType",searchType);
			model.addAttribute("searchWord",searchWord);
		}
		
		pagingVo.setCurrentPage(currentPage);
		
		int totalRecord = noticeService.selectNoticeCount(pagingVo);
		pagingVo.setTotalRecord(totalRecord);
		
		List<NoticeVO> dataList = noticeService.selectNotice2(pagingVo);
		pagingVo.setDataList(dataList);
		model.addAttribute("pagingVo",pagingVo);
		List<NoticeVO> alltNoticeList = noticeService.alltNoticeList();
		model.addAttribute("alltNoticeList",alltNoticeList);
		
		return "notice/list";
		
	}
}

