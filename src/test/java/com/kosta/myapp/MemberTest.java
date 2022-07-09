package com.kosta.myapp;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.myapp.repository.MemberRepository;
import com.kosta.myapp.vo.MemberDTO;
import com.kosta.myapp.vo.MemberRoleEnum;

import lombok.extern.java.Log;

@SpringBootTest
@Log
public class MemberTest {
	@Autowired
	MemberRepository mRepo;	
	
	@Test
	public void jpqlTest1() {
		//List<Object[]> plist = mRepo.getMemberWithProfileCount1("zz");
		List<Object[]> plist = mRepo.getMemberWithProfileCount2("zz");
		plist.forEach(objArray->{
			//System.out.println(Arrays.toString(objArray));
			log.info(objArray[0] + "멤버의 ===> 프로파일건수는 " + objArray[1]);
		});
		
	}
	
	
	
	
	//@Test
	public void delete() {
		mRepo.deleteById("zzilre5");
	}
	//@Test
	public void selectById() {
		mRepo.findById("zzilre1").ifPresentOrElse(originMember->{
			System.out.println(originMember); 
		}, ()->{System.out.println("존재하지 않는 멤버입니다.");});
		
	}
	//@Test
	public void update() {
		mRepo.findById("zzilre1").ifPresentOrElse(originMember->{
			originMember.setMname("홍길동");
			originMember.setMpassword("9999");
			originMember.setMrole(MemberRoleEnum.MANAGER);
			mRepo.save(originMember);
		}, ()->{System.out.println("존재하지 않는 멤버입니다.");});
	}
	//@Test
	public void selectAll() {
		mRepo.findAll().forEach(m->{
			System.out.println(m);
		});
	}	
	//@Test
	public void insert() {
		IntStream.rangeClosed(1, 5).forEach(i->{
			MemberDTO member = MemberDTO.builder()
					.mid("zzilre"+i)
					.mname("멤버" + i)
					.mpassword("1234")
					.mrole(i%2==0?MemberRoleEnum.ADMIN:MemberRoleEnum.USER)
					.build();
			mRepo.save(member);
		});
	}
	
}





