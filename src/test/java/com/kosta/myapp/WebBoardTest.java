package com.kosta.myapp;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.myapp.repository.WebBoardRepository;
import com.kosta.myapp.repository.WebReplyRepository;
import com.kosta.myapp.vo.relation.WebBoard;
import com.kosta.myapp.vo.relation.WebReply;

@SpringBootTest
public class WebBoardTest {

	@Autowired
	WebBoardRepository boardRepo;
	@Autowired
	WebReplyRepository replyRepo;
	
	
	@Test
	public void insert2() {
		IntStream.rangeClosed(1, 10).forEach(i->{
			WebBoard board = WebBoard.builder()
					.title("화요일"+i)
					.content("MSA특강")
					.writer("Hong")   
					.build();
			boardRepo.save(board);
		});
	}
	
	
	//@Test
	public void insert() {
		IntStream.rangeClosed(1, 50).forEach(i->{
			WebBoard board = WebBoard.builder()
					.title("월요일"+i)
					.content("비가 안온다")
					.writer("작성자"+ i%5)   //0~4
					.build();
			List<WebReply> replyList = new ArrayList<>();
			IntStream.rangeClosed(1,10).forEach(j->{
				WebReply reply = WebReply.builder()
						.replyer("댓글작성자" + j%5)
						.replyText("비오는거 싫음.....")
						.board(board)
						.build();
				replyList.add(reply);
			});
			board.setReplies(replyList);
			boardRepo.save(board);
		});
	}
}








