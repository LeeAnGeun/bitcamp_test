package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import db.DBClose;
import db.DBConnection;
import dto.EmployeeDto;

public class EmployeeDao {
	
	private static EmployeeDao dao = null;
	
	private EmployeeDao() {
		DBConnection.initConnection();
	}
	
	public static EmployeeDao getInstance() {
		if(dao == null) {
			dao = new EmployeeDao();
		}
		return dao;
	}
	
	public List<EmployeeDto> getEmployeeList() {
		
		String sql = " SELECT SEQ, NAME, PHONE, EMAIL, HIREDATE "
				+ " FROM EMPLOYEE "
				+ " ORDER BY SEQ ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		
		List<EmployeeDto> list = new ArrayList<EmployeeDto>();
		
		try {
			
			conn = DBConnection.getConnection();
			System.out.println("1/4 getEmployeeList");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/4 getEmployeeList");
			
			rs = psmt.executeQuery();
			System.out.println("3/4 getEmployeeList");
			
			while(rs.next()) {
				EmployeeDto dto = new EmployeeDto(rs.getInt(1), 
												rs.getString(2), 
												rs.getString(3), 
												rs.getString(4), 
												rs.getString(5));
										
				list.add(dto);
				
			}
			System.out.println("4/4 getEmployeeList");
		} catch (SQLException e) {
			System.out.println("getEmployeeList fail");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, rs);
		}
		
		return list;
	}
	
	public boolean addEmployee(EmployeeDto dto) {
		String sql = " INSERT INTO EMPLOYEE(SEQ, NAME, PHONE, EMAIL, HIREDATE) "
				+ " VALUES(SEQ_EMPLOYEE.NEXTVAL, ?, ?, ?, ?) ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 addEmployee success");
			
			psmt = conn.prepareStatement(sql);
			System.out.println("2/3 addEmployee success");
			
			// ? 값을 지정해준다.
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getPhone());
			psmt.setString(3, dto.getEmail());
			psmt.setString(4, dto.getHiredate());
			
			count = psmt.executeUpdate();

			System.out.println("3/3 addEmployee success");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, null);
		}
		
		return count>0?true:false;
	}
	
	public boolean deleteEmployee(int seq) {
		
		 String sql = " DELETE FROM EMPLOYEE "
                 + " WHERE SEQ = ? ";
		
		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 S deleteEmployee");
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, seq);
			System.out.println("2/3 S deleteEmployee");
			
			count = psmt.executeUpdate();
			System.out.println("3/3 S deleteEmployee");
			
		} catch (Exception e) {		
			System.out.println("fail deleteEmployee");
			e.printStackTrace();
		} finally {
			DBClose.close(conn, psmt, null);			
		}
		
		return count>0?true:false;
	}
	
	public boolean updateEmployee(EmployeeDto dto) {
		String sql = " UPDATE EMPLOYEE SET "
				+ " PHONE=?, HIREDATE=? "
				+ " WHERE SEQ=? ";

		Connection conn = null;
		PreparedStatement psmt = null;
		int count = 0;
		
		try {
			conn = DBConnection.getConnection();
			System.out.println("1/3 S updateEmployee");
			
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getPhone());
			psmt.setString(2, dto.getHiredate());
			psmt.setInt(3, dto.getSeq());
			
			System.out.println("2/3 S updateEmployee");
			
			count = psmt.executeUpdate();
			System.out.println("3/3 S updateEmployee");
			
		} catch (Exception e) {			
			e.printStackTrace();
		} finally{
			DBClose.close(conn, psmt, null);			
		}		
		
		return count>0?true:false;
	}
}
