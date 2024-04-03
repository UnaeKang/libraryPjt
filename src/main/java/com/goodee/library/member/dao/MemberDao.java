package com.goodee.library.member.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.goodee.library.member.dto.MemberDto;

@Repository
public class MemberDao {

	private static final Logger LOGGER = LogManager.getLogger(MemberDao.class);

	@Autowired
	private SqlSession sqlSession;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	private final String namespace = "com.goodee.library.memberMapper.";
	
	public int idDoubleCheck(String m_id) {
		LOGGER.info("아이디 중복 검사");
		int result = 0; 
		try {
			result = sqlSession.selectOne(namespace+"idDoubleCheck",m_id);
		}catch(Exception e) {
			LOGGER.error("아이디 중복 검사시 에러 발생");
		}
		return result;
	}	

	public int createMember(MemberDto dto){
		LOGGER.info("회원정보 데이터베이스 추가");
		int result = 0; 
		try {
			dto.setM_pw(passwordEncoder.encode(dto.getM_pw()));
			result = sqlSession.insert(namespace+"createMember",dto);
		}catch(Exception e){
			LOGGER.error("회원정보 데이터베이스 추가시 에러 발생");
		}
		return result;
	}
	
	public MemberDto selectMemberOne(MemberDto dto) {
		LOGGER.info("아이디 기준 멤버 조회");
		MemberDto loginedDto = new MemberDto();
			try {
				loginedDto = sqlSession.selectOne(namespace+"selectMemberOne",dto.getM_id());
				if(loginedDto != null) {
					// 비밀번호 일치 여부 확인 
					if(passwordEncoder.matches(dto.getM_pw(), loginedDto.getM_pw())==false) {
						loginedDto = null;
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return loginedDto;
	}
	
	
	
	
	
}