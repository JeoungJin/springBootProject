package com.kosta.myapp.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kosta.myapp.repository.WebBoardRepository;
import com.kosta.myapp.repository.WebReplyRepository;
import com.kosta.myapp.vo.PageMaker;
import com.kosta.myapp.vo.PageVO;
import com.kosta.myapp.vo.relation.WebBoard;
import com.kosta.myapp.vo.relation.WebReply;
import com.querydsl.core.types.Predicate;

import lombok.extern.java.Log;

@Log
@Controller
@RequestMapping("/board/*")
public class WebBoardController {
	@Autowired
	WebBoardRepository boardRepo;
	
	@Autowired
	WebReplyRepository replyRepo;
	
	
	@RequestMapping("/replyByBoard.go")
	public String replyByBoard(Long bno,Model model) {
		List<WebReply> relist = replyRepo.getRepliesOfBoard2(bno);
		model.addAttribute("relist", relist);
		return "board/replyByBoard";
	}
	
	
	@RequestMapping("/boardlist.go")
	public String boardlist(Model model, 
			HttpSession session,
			HttpServletRequest request) {
		System.out.println(session.getServletContext().getContextPath());
		model.addAttribute("blist", boardRepo.findAll());
		return "board/boardlist";
	}
	
	@RequestMapping("/boardlist2.go")
	public String boardlist2(Model model, 
			HttpSession session,
			HttpServletRequest request, PageVO pvo) {
		Pageable page = pvo.makePaging(0, "bno");
		Predicate predicate =  
				boardRepo.makePredicate(pvo.getType(), pvo.getKeyword());
		Page<WebBoard> blist = boardRepo.findAll(predicate, page);
		log.info("page수:" + blist.getSize());
		log.info("blist:" + blist.getContent());
		PageMaker<WebBoard> pmaker = new PageMaker<>(blist);
		model.addAttribute("blist", pmaker);
		return "board/boardlist";
	}
	
	@GetMapping("/register")
	public void register( ) {
		log.info("get등록");
		 
	}
	@PostMapping("/register")
	public void registerPost(@ModelAttribute("vo") WebBoard board) {
		log.info("post등록");
		log.info(board.toString());
		boardRepo.save(board);
	}
	@GetMapping("/view")
	public void view(Long bno, @ModelAttribute("pageVO") PageVO vo, Model model) {
		boardRepo.findById(bno).ifPresent(board->{
			model.addAttribute("vo", board);
		});
	}
	
	@GetMapping("/modify")
	public void modify(Long bno, @ModelAttribute("pageVO") PageVO vo,
			 Model model) {
		boardRepo.findById(bno).ifPresent(board->{
			model.addAttribute("vo", board);
		});
		log.info("get modify");
		
	}
	@PostMapping("/modify")
	public String modify(WebBoard board ) {
		System.out.println(board);
		log.info("post modify");
		boardRepo.save(board);
		return "redirect:/board/boardlist2.go";
		
	}
}

