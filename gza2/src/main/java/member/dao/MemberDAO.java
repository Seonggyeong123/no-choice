package member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import member.model.Member;

public class MemberDAO {

	public Member selectById(Connection conn, String id) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(
					"select * from member where id = ?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			Member member = null;
			if (rs.next()) {
				member = new Member(
						rs.getString("id"),
						rs.getString("member_name"), 
						rs.getString("password"),
						rs.getString("phone_num"),
						rs.getString("birthday"));
			}
			return member;
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	public void insert(Connection conn, Member mem) throws SQLException {
		try (PreparedStatement pstmt = 
				conn.prepareStatement("insert into member values(?,?,?,?,?)")) {
			pstmt.setString(1, mem.getId());
			pstmt.setString(2, mem.getMemberName());
			pstmt.setString(3, mem.getPassword());
			pstmt.setString(4, mem.getPhoneNum());
			pstmt.setString(5, mem.getBirthday());
			pstmt.executeUpdate();
		}
	}

	public void update(Connection conn, Member member) throws SQLException {
		try (PreparedStatement pstmt = conn.prepareStatement(
				"update member set member_name = ?, password = ?, phone_num = ?, birthday = ? where id = ?")) {
			pstmt.setString(1, member.getMemberName());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getPhoneNum());
			pstmt.setString(4, member.getBirthday());
			pstmt.setString(5, member.getId());
			pstmt.executeUpdate();
		}
	}
}