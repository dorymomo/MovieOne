package com.koreait.dao;

import java.util.Scanner;

import com.koreait.dto.MemberDTO;

	public class MemberDAO {
		//김성민 회원 가입, 로그인
		
		private List<MemberDTO> memberList = new ArrayList<>();
		private Scanner sc = new Scanner(System.in);
		
		//회원가입 메소드
		
		public List<MemberDTO> join(MemberDTO memberDTO){
			System.out.println("아이디 입력 : ");
			memberDTO.setId(sc.nextLine());
			for(MemberDTO m : memberList) {
				if(m.getId().equals(memberDTO.getId())) {
					return memberList;
				}
			}
			
		System.out.println("비밀번호 입력 : ");
		memberDTO.setMemPw(sc.nextLine());
		System.out.println("이름 입력 : ");
		memberDTO.setMemName(sc.nextLine());
		System.out.println("전화번호 입력 : ");
		memberDTO.setMemPhoneNo(sc.nextLine());
		System.out.println("이메일 입력 : ");
		memberDTO.setMemEmail(sc.nextLine());
		sc.nextLine();
	    memberList.add(memberDTO);
	    return memberList;
		}
		
		//로그인 메소드
		
		public boolean login(String id, String pw) {
			for(MemberDTO m : memberList) {
				if(m.getId().equals(id) && m.getPw().equals(pw)) {
					return true;
				}
			}
			return false;
		}
		
		public boolean loginStream(String id, String pw) {
		      return memberList.stream().anyMatch(u -> u.getId().equals(id) && u.getPw().equals(pw));
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}
