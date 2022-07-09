package com.kosta.myapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kosta.myapp.repository.WebReplyRepository;
import com.kosta.myapp.vo.relation.WebBoard;
import com.kosta.myapp.vo.relation.WebReply;

@RestController
@RequestMapping("/replies/*")
public class WebReplyController {
	
	@Autowired
	WebReplyRepository replyRepo;
	
	@GetMapping("/{bno}")
	public List<WebReply> getReplies(@PathVariable Long bno){
		 
		return replyRepo.getRepliesOfBoard2(bno);
	}

}
