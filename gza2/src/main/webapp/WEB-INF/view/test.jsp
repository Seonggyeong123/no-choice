<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*, java.sql.*, java.io.*" %>
<%
String sql = "";
Connection con = null;
PreparedStatement pstmt = null;
ResultSet rs = null; OutputStream output = response.getOutputStream();
InputStream input = null; 
try {
    con = DriverManager.getConnection("jdbc:mysql://localhost/board","root","king3977");
    sql =" select File from image where ID = 5 ";
    pstmt = con.prepareStatement(sql);
    rs = pstmt.executeQuery();
    if (rs.next()) {
        input = rs.getBinaryStream("File");
        int byteRead;
        while((byteRead = input.read()) != -1) {
        output.write(byteRead);
        }
        input.close();
    }
} catch(Exception e) {
    out.print(e);
} finally {
    try {if (rs != null) rs.close();} catch (Exception ex) {}
    try {if (pstmt != null) pstmt.close();} catch (Exception ex) {}
    try {if (con != null) con.close();} catch (Exception ex) {}
}
input.close();
output.flush();
output.close();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>