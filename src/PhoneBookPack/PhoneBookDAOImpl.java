package PhoneBookPack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;



public class PhoneBookDAOImpl implements PhoneBookDAO {
	
	private Connection getConnection() throws SQLException{
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(dburl,
					"c##bituser",
					"bituser");			
		} catch(ClassNotFoundException e) {
			System.err.println("드라이버 로드 실패");
		}		
		return conn;
	}

	public List<PhoneBookVO> getList() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;		
		List<PhoneBookVO> list = new ArrayList<>();		
		try {
			conn = getConnection();
			stmt = conn.createStatement();			
			String sql = "SELECT id, name, hp, tel" +
						" FROM phone_book order by id";
			rs = stmt.executeQuery(sql);			
			while(rs.next()) {
				Long id = rs.getLong(1);
				String name = rs.getString(2);
				String hp = rs.getString(3);
				String tel = rs.getString(4);				
				PhoneBookVO vo = new PhoneBookVO(id, name, hp, tel);
				list.add(vo);				
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			}catch(Exception e) {}
		}
		return list;
	}

	public boolean insert(PhoneBookVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;				
		try {
			conn = getConnection();
			String sql = "insert into phone_book values(seq_phone_book_pk.NEXTVAL, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getHp());
			pstmt.setString(3, vo.getTel());			
			pstmt.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();
			}catch(Exception e) {}
		}
		return true;
	}

	public List<PhoneBookVO> search(String keyword) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		
		List<PhoneBookVO> list = new ArrayList<>();		
		String sql = "SELECT id, name, hp, tel FROM PHONE_BOOK "
				+ "WHERE name LIKE ? order by id";
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");			
			rs = pstmt.executeQuery();			
			while(rs.next()) {
				Long id = rs.getLong("id");
				String name = rs.getString("name");
				String hp = rs.getString("hp");
				String tel = rs.getString("tel");				
				PhoneBookVO vo = new PhoneBookVO(id, name, hp, tel);
				list.add(vo);
				}
			} catch(SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					rs.close();
					pstmt.close();
					conn.close();
				}catch(Exception e) {}			
			}				
			return list;
	}
	
	public boolean delete(Long id) {
		Connection conn = null;
		PreparedStatement pstmt = null;		
		try {
			conn = getConnection();
			String sql = "delete from phone_book where id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);
			pstmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch(Exception e) {}
		}
		return true;
	}	
}
