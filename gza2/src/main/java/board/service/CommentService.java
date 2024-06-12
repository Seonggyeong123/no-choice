package board.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import board.dao.CommentDao;
import board.model.Comment;
import board.model.Reply;
import jdbc.connection.ConnectionProvider;

public class CommentService {

    private CommentDao commentDao = new CommentDao();

    public List<Comment> getCommentsByArticle(int articleNo) {
        try (Connection conn = ConnectionProvider.getConnection()) {
            return commentDao.getCommentsByArticle(conn, articleNo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addComment(Comment comment) {
        try (Connection conn = ConnectionProvider.getConnection()) {
            commentDao.insertComment(conn, comment);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addReply(Reply reply) {
        try (Connection conn = ConnectionProvider.getConnection()) {
            commentDao.insertReply(conn, reply);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteComment(int commentNo) {
        try (Connection conn = ConnectionProvider.getConnection()) {
            commentDao.deleteComment(conn, commentNo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteReply(int replyNo) {
        try (Connection conn = ConnectionProvider.getConnection()) {
            commentDao.deleteReply(conn, replyNo);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateComment(int commentNo, String content) {
        try (Connection conn = ConnectionProvider.getConnection()) {
            commentDao.updateComment(conn, commentNo, content);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateReply(int replyNo, String content) {
        try (Connection conn = ConnectionProvider.getConnection()) {
            commentDao.updateReply(conn, replyNo, content);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
