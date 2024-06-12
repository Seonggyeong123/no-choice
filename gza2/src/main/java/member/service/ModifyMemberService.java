package member.service;

import java.sql.Connection;
import java.sql.SQLException;

import jdbc.JdbcUtil;
import jdbc.connection.ConnectionProvider;
import member.dao.MemberDAO;
import member.model.Member;

public class ModifyMemberService {
    private MemberDAO memberDAO = new MemberDAO();

    // Modify the member information
    public void modifyMember(String id, String newMemberName, String curPwd, String newPwd, String newPhoneNum, String newBirthday) {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            conn.setAutoCommit(false);

            Member member = memberDAO.selectById(conn, id);
            if (member == null) {
                throw new MemberNotFoundException(); // Handle member not found
            }
            if (!member.matchPassword(curPwd)) {
                throw new InvalidPasswordException(); // Handle invalid password
            }

            member.modifyMember(newMemberName, newPwd, newPhoneNum, newBirthday);
            memberDAO.update(conn, member); // Update member in the database
            conn.commit();

        } catch (SQLException e) {
            JdbcUtil.rollback(conn); // Rollback in case of an error
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn); // Ensure connection is closed
        }
    }

    // Fetch member by ID
    public Member getMemberById(String id) {
        Connection conn = null;
        try {
            conn = ConnectionProvider.getConnection();
            return memberDAO.selectById(conn, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JdbcUtil.close(conn); // Ensure connection is closed
        }
    }
}