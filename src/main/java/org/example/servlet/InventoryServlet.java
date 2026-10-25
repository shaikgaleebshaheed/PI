package org.example.servlet;

import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import org.example.util.DBConnection;

@WebServlet("/inventory")
public class InventoryServlet extends HttpServlet {

    public void doGet(HttpServletRequest req,
                      HttpServletResponse res)
            throws IOException, ServletException {

        try (Connection con = DBConnection.getConnection()) {
            req.setAttribute("con", con);
            req.getRequestDispatcher("/inventory.jsp")
                    .forward(req, res);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    public void doPost(HttpServletRequest req,
                       HttpServletResponse res)
            throws IOException, ServletException {

        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO inventory VALUES(?,?,?,?)");

            ps.setInt(1, Integer.parseInt(req.getParameter("code")));
            ps.setString(2, req.getParameter("name"));
            ps.setInt(3, Integer.parseInt(req.getParameter("qty")));
            ps.setDouble(4, Double.parseDouble(req.getParameter("price")));
            ps.executeUpdate();

            res.sendRedirect(req.getContextPath() + "/inventory");

        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}