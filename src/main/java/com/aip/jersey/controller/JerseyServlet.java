package com.aip.jersey.controller;

import com.aip.jersey.dao.JerseyDAO;
import com.aip.jersey.model.Jersey;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/jerseys")
public class JerseyServlet extends HttpServlet {

    private final JerseyDAO dao = new JerseyDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "new":
                    request.getRequestDispatcher("/jerseyForm.jsp").forward(request, response);
                    break;
                case "edit":
                    int id = Integer.parseInt(request.getParameter("id"));
                    request.setAttribute("jersey", dao.getById(id));
                    request.getRequestDispatcher("/jerseyForm.jsp").forward(request, response);
                    break;
                case "delete":
                    dao.delete(Integer.parseInt(request.getParameter("id")));
                    response.sendRedirect(request.getContextPath() + "/jerseys");
                    break;
                default:
                    listJerseys(request, response);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idText = request.getParameter("id");

        Jersey j = new Jersey();
        j.setTeamName(request.getParameter("teamName"));
        j.setPlayerName(request.getParameter("playerName"));
        j.setJerseyNumber(Integer.parseInt(request.getParameter("jerseyNumber")));
        j.setSize(request.getParameter("size"));
        j.setPrice(Double.parseDouble(request.getParameter("price")));
        j.setQuantity(Integer.parseInt(request.getParameter("quantity")));

        try {
            if (idText == null || idText.isEmpty()) {
                dao.add(j);
            } else {
                j.setId(Integer.parseInt(idText));
                dao.update(j);
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
        response.sendRedirect(request.getContextPath() + "/jerseys");
    }

    private void listJerseys(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String keyword = request.getParameter("search");
        List<Jersey> list = (keyword == null || keyword.trim().isEmpty())
                ? dao.getAll()
                : dao.search(keyword.trim());
        request.setAttribute("jerseyList", list);
        request.getRequestDispatcher("/jerseys.jsp").forward(request, response);
    }
}