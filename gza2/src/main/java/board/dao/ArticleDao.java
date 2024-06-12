package board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import board.model.Article;
import board.model.Writer;
import jdbc.JdbcUtil;

public class ArticleDao {

    public Article insert(Connection conn, Article article) throws SQLException {
        PreparedStatement pstmt = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement(
                    "insert into comunity " + "(id, writer_name, title, regdate, moddate, read_cnt, category) "
                            + "values (?,?,?,?,?,0,?)");
            pstmt.setString(1, article.getWriter().getId());
            pstmt.setString(2, article.getWriter().getName());
            pstmt.setString(3, article.getTitle());
            pstmt.setTimestamp(4, toTimestamp(article.getRegDate()));
            pstmt.setTimestamp(5, toTimestamp(article.getModifiedDate()));
            pstmt.setString(6, article.getCategory());
            int insertedCount = pstmt.executeUpdate();

            if (insertedCount > 0) {
                stmt = conn.createStatement();
                rs = stmt.executeQuery("select last_insert_id() from comunity");
                if (rs.next()) {
                    Integer newNo = rs.getInt(1);
                    return new Article(newNo, article.getWriter(), article.getTitle(), article.getRegDate(),
                            article.getModifiedDate(), 0, article.getCategory());
                }
            }
            return null;
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(stmt);
            JdbcUtil.close(pstmt);
        }
    }

    private Timestamp toTimestamp(Date date) {
        return new Timestamp(date.getTime());
    }

    public int selectCount(Connection conn) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select count(*) from comunity");
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(stmt);
        }
    }

    public List<Article> select(Connection conn, int startRow, int size) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement("select * from comunity " + "order by board_num desc limit ?, ?");
            pstmt.setInt(1, startRow);
            pstmt.setInt(2, size);
            rs = pstmt.executeQuery();
            List<Article> result = new ArrayList<>();
            while (rs.next()) {
                result.add(convertArticle(rs));
            }
            return result;
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(pstmt);
        }
    }

    private Article convertArticle(ResultSet rs) throws SQLException {
        return new Article(
            rs.getInt("board_num"),
            new Writer(rs.getString("id"), rs.getString("writer_name")),
            rs.getString("title"),
            rs.getTimestamp("regdate"),
            rs.getTimestamp("moddate"),
            rs.getInt("read_cnt"),
            rs.getString("category")
        );
    }

    private Date toDate(Timestamp timestamp) {
        return new Date(timestamp.getTime());
    }

    public Article selectById(Connection conn, int articleNo) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement("SELECT * FROM comunity WHERE board_num = ?");
            pstmt.setInt(1, articleNo);
            rs = pstmt.executeQuery();
            Article article = null;
            if (rs.next()) {
                article = convertArticle(rs);
            }
            return article;
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(pstmt);
        }
    }


    public void increaseReadCount(Connection conn, int no) throws SQLException {
        try (PreparedStatement pstmt = conn
                .prepareStatement("update comunity set read_cnt = read_cnt + 1 where board_num = ?")) {
            pstmt.setInt(1, no);
            pstmt.executeUpdate();
        }
    }

    public int update(Connection conn, int no, String title) throws SQLException {
        try (PreparedStatement pstmt = conn
                .prepareStatement("update comunity set title = ?, moddate = now() " + "where board_num = ?")) {
            pstmt.setString(1, title);
            pstmt.setInt(2, no);

            return pstmt.executeUpdate();
        }
    }

    public void delete(Connection conn, int articleNumber) throws SQLException {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement("DELETE FROM comunity WHERE board_num = ?");
            pstmt.setInt(1, articleNumber);
            pstmt.executeUpdate();
        } finally {
            JdbcUtil.close(pstmt);
        }
    }

    public int selectCountByCategory(Connection conn, String category) throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn.prepareStatement("SELECT COUNT(*) FROM comunity WHERE category = ?");
            pstmt.setString(1, category);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
            return 0;
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(pstmt);
        }
    }

    public List<Article> selectByCategory(Connection conn, String category, int startRow, int size)
            throws SQLException {
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            pstmt = conn
                    .prepareStatement("SELECT * FROM comunity WHERE category = ? ORDER BY board_num DESC LIMIT ?, ?");
            pstmt.setString(1, category);
            pstmt.setInt(2, startRow);
            pstmt.setInt(3, size);
            rs = pstmt.executeQuery();
            List<Article> result = new ArrayList<>();
            while (rs.next()) {
                result.add(convertArticle(rs));
            }
            return result;
        } finally {
            JdbcUtil.close(rs);
            JdbcUtil.close(pstmt);
        }
    }

    public static String formatDate(Date date) {
        SimpleDateFormat sdfDate = new SimpleDateFormat("MM-dd");
        SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
        SimpleDateFormat sdfFull = new SimpleDateFormat("yyyyMMdd");
        
        Date currentDate = new Date();
        
        if (sdfFull.format(currentDate).equals(sdfFull.format(date))) {
            return sdfTime.format(date);
        } else {
            return sdfDate.format(date);
        }
    }
    
}
