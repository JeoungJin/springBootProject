package com.kosta.myapp;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kosta.myapp.repository.MemberProfileRepository;
import com.kosta.myapp.repository.MemberRepository;
import com.kosta.myapp.vo.MemberDTO;
import com.kosta.myapp.vo.ProfileDTO;

import lombok.extern.java.Log;

@SpringBootTest
@Log
public class ProfileTest {

	@Autowired
	MemberProfileRepository profileRepo;
	
	@Autowired
	MemberRepository memberRepo;
	
	//update
	//@Test
	public void  selectByIdUpdate() {
		Long fno = 112L;
		profileRepo.findById(fno).ifPresentOrElse(profile->{
			profile.setFname("프로파일수정");
			profileRepo.save(profile);
		}, ()->{System.out.println(fno + "프로파일이 존재하지않음");});
	}
	
	//delete
	//@Test
	public void  selectByIdDelete() {
		Long fno = 120L;
		profileRepo.findById(fno).ifPresentOrElse(profile->{
			profileRepo.deleteById(fno);
		}, ()->{System.out.println(fno + "프로파일이 존재하지않음");});
	}
	//id로 조회
	//@Test
	public void  selectById() {
		Long fno = 120L;
		profileRepo.findById(fno).ifPresentOrElse(profile->{
			System.out.println(profile);
		}, ()->{System.out.println(fno + "프로파일이 존재하지않음");});
	}
	//member조회 
	@Test
	public void selectByMember() {
		MemberDTO member = memberRepo.findById("zzilre2").orElse(null);
		if(member==null) return;
		List<ProfileDTO> plist = profileRepo.findByMember(member);
		plist.forEach(profile->{
			log.info(profile.toString());
			log.info(profile.getMember().toString());
			log.info("-----------------");
		});
	}
	
	//@Test
	public void selectAll() {
		profileRepo.findAll().forEach(profile->{
			System.out.println(profile);
		});
	}
	
	//@Test
	public void insert() {
		Optional<MemberDTO> member = memberRepo.findById("zzilre2") ;
		if(member == null) {
			System.out.println("존재하지않는 멤버이다.");
			return;
		}
		MemberDTO existMember = member.get();
		IntStream.rangeClosed(1, 3).forEach(i->{
			ProfileDTO profile = ProfileDTO.builder()
					.fname("zzilre2face" + i + "jpg")
					.current_yn(i==1?true:false)
					.member(existMember)
					.build();
			profileRepo.save(profile);
		});
	}
}







