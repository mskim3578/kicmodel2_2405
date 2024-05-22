package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.KicBoard;
import model.KicMember;

public class KicBoardDAO {
	public Connection getConnection() {
		// 1. driver
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "kic24", "1234");
			return conn;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 2 connection
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	public int insertBoard(KicBoard board) {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql = "insert into kicboard values (kicboardseq.nextval,?,?,?,?,?,sysdate,0,?)";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getName());
			pstmt.setString(2, board.getPass());
			pstmt.setString(3, board.getSubject());
			pstmt.setString(4, board.getContent());
			pstmt.setString(5, board.getFile1());
			pstmt.setString(6, "1"); // boardid

			int num = pstmt.executeUpdate();
			return num;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;

	}

	public List<KicBoard> boardList() {
		Connection conn = getConnection();
		PreparedStatement pstmt = null;
		String sql = "select * from kicboard";
		List<KicBoard> li = new ArrayList<>();
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				KicBoard m = new KicBoard();
				m.setNum(rs.getInt("num"));
				m.setPass(rs.getString("pass"));
				m.setName(rs.getString("name"));
				m.setSubject(rs.getString("subject"));
				m.setContent(rs.getString("content"));
				m.setFile1(rs.getString("file1"));
				m.setRegdate(rs.getTimestamp("regdate"));
				m.setReadcnt(rs.getInt("readcnt"));
				m.setBoardid(rs.getString("boardid"));
				li.add(m);
			}
			return li;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

} // end class
