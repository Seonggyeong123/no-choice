package board.service;

import java.sql.Connection;
import java.sql.SQLException;

import board.dao.ArticleContentDao;
import board.dao.ArticleDao;
import board.model.Article;
import jdbc.connection.ConnectionProvider;

public class DeleteArticleService {
	private ArticleDao articleDao = new ArticleDao();
	private ArticleContentDao contentDao = new ArticleContentDao();

	public void delete(int articleNumber) throws ArticleNotFoundException {
		Connection conn = null;
		try {
			conn = ConnectionProvider.getConnection();
			conn.setAutoCommit(false); // 트랜잭션 시작

			// 게시글 번호를 이용하여 해당 게시글을 데이터베이스에서 가져옵니다.
			Article article = articleDao.selectById(conn, articleNumber);

			// 만약 가져온 게시글이 null이면 존재하지 않는 게시글이므로 예외를 던집니다.
			if (article == null) {
				throw new ArticleNotFoundException();
			}

			// 게시글을 삭제합니다.
			articleDao.delete(conn, articleNumber);
			contentDao.delete(conn, articleNumber);

			conn.commit(); // 트랜잭션 커밋
		} catch (SQLException e) {
			if (conn != null) {
				try {
					conn.rollback(); // 트랜잭션 롤백
				} catch (SQLException rollbackEx) {
					rollbackEx.printStackTrace();
				}
			}
			e.printStackTrace(); // 실제 예외 처리 방법은 상황에 따라 다를 수 있습니다.
		} finally {
			if (conn != null) {
				try {
					conn.setAutoCommit(true); // 자동 커밋 모드 재설정
					conn.close();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
		}
	}
}