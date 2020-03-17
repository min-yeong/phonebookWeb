package com.bit.phonebook.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.bit.phonebook.vo.PhoneBookVO;

public class PhoneBookDAOImpl implements PhoneBookDAO {
	//사용자 정보 데이터베이스
	private String dbuser = null;
	private String dbpass = null;
	
	// 생성자
	public PhoneBookDAOImpl(String dbuser, String dbpass) {
		this.dbuser = dbuser;
		this.dbpass = dbpass;
	}
	
	// connection 받아오는 메서드
	private Connection getConnection() throws SQLException{
		Connection conn = null;
		
		try {
			// 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// DB 접속 URL
			String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
			// DB Connect
			conn = DriverManager.getConnection(dburl, dbuser, dbpass);
		} catch(ClassNotFoundException e) {
			// 드라이버가 없을 경우
			e.printStackTrace();
		} 
		return conn;
	}

	@Override
	public List<PhoneBookVO> getList() {
		// Phone_book table의 데이터 값 출력
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		List<PhoneBookVO> list = new ArrayList<>();
		
		try {
			conn = getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT * FROM phone_book ORDER BY id";
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				Long PBid = rs.getLong(1);
				String PBname = rs.getString(2);
				String PBhp = rs.getString(3);
				String PBtel = rs.getString(4);
				
				PhoneBookVO vo = new PhoneBookVO(PBid, PBname, PBhp, PBtel);
				list.add(vo);
			}
		}catch(SQLException e) {
			System.err.println("SQL ERROR!");
			e.printStackTrace();
		}finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch(Exception e) {}
		}
		return list;
	}

	@Override
	public boolean insert(PhoneBookVO phonebookVO) {
		// Phone_book table의 새로운 데이터 입력
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertedCount = 0;
		
		try {
			conn = getConnection();
			String sql = "INSERT INTO phone_book VALUES(seq_phone_book.NEXTVAL, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, phonebookVO.getPBname());
			pstmt.setString(2, phonebookVO.getPBhp());
			pstmt.setString(3, phonebookVO.getPBtel());
			
			insertedCount = pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch(Exception e) {}
		}
		return 1 == insertedCount;
	}

	@Override
	public boolean delete(Long id) {
		// Phone_book table의 데이터 삭제
		Connection conn = null;
		PreparedStatement pstmt = null;
		int deletedCount = 0;
		
		try {
			conn = getConnection();
			String sql = "DELETE FROM phone_book WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			
			deletedCount = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch(Exception e) {}
		}
		return deletedCount == 1;
	}

	@Override
	public List<PhoneBookVO> get(String name) {
		// Phone_book table의 데이터 검색
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<PhoneBookVO> list = new ArrayList<>(); 
		
		try {
			conn = getConnection();
			String sql = "SELECT * FROM phone_book WHERE name Like ? ORDER BY id";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+name+"%");
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Long PBid = rs.getLong(1);
				String PBname = rs.getString(2);
				String PBhp = rs.getString(3);
				String PBtel = rs.getString(4);
				PhoneBookVO pb = new PhoneBookVO(PBid, PBname, PBhp, PBtel);
				list.add(pb);
			}
		} catch(SQLException e) {
			System.err.println("SQL ERROR!");
		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch(Exception e) {}
		}
		return list;
	}
}
