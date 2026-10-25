<%@ page import="java.sql.*" %>
<%@ page import="org.example.util.DBConnection" %>
<html><body>

<h2>Add Product</h2>
<form action="inventory" method="post">
Code: <input name="code" required><br>
Name: <input name="name" required><br>
Quantity: <input name="qty" required><br>
Price: <input name="price" required><br>
<button>Add</button>
</form>

<h2>Products</h2>
<table border="1">
<tr><th>Code</th><th>Name</th><th>Quantity</th><th>Price</th></tr>
<%
try (Connection con = DBConnection.getConnection();
     Statement st = con.createStatement();
     ResultSet rs = st.executeQuery("SELECT * FROM inventory")) {
    while (rs.next()) {
%>
<tr>
<td><%=rs.getInt(1)%></td>
<td><%=rs.getString(2)%></td>
<td><%=rs.getInt(3)%></td>
<td><%=rs.getDouble(4)%></td>
</tr>
<%  }
} %>
</table>
</body></html>