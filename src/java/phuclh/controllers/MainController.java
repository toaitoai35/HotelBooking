/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuclh.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Acer
 */
public class MainController extends HttpServlet {

    private final static String ERROR = "index.html";
    private final static String LOGIN = "LoginServlet";
    private final static String LOGOUT = "LogoutServlet";
    private final static String SEARCH = "SearchServlet";
    private final static String CREATE_USER = "CreateUserServlet";
    private final static String SELECT_ROOM_SERVLET = "SelectRoomServlet";
    private final static String BOOKING_ROOM_SERVLET = "BookingRoomServlet";
    private final static String VIEW_CART = "view.jsp";
    private final static String UPDATE_CART_SERVLET = "UpdateCartServlet";
    private final static String DELETE_CART_SERVLET = "DeleteCartServlet";
    private final static String CHECKOUT_SERVLET = "CheckoutServlet";
    private final static String PROMOTION_SERVLET = "UsePromotionServlet";
    private final static String HISTORY_SERVLET = "HistoryServlet";
    private final static String REMOVE_HISTORY_SERVLET = "RemoveHistoryServlet";
    private final static String GET_CODE_SERVLET = "GetCodeByEmailServlet";
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            /* TODO output your page here. You may use following sample code. */
            String btn = request.getParameter("btnAction");

            if (btn.equals("Login")) {
                url = LOGIN;
            }
            if (btn.equals("Create")) {
                url = CREATE_USER;
            }
            if (btn.equals("Logout")) {
                url = LOGOUT;
            }
            if (btn.equals("Search")) {
                url = SEARCH;
            }
            if (btn.equals("Select room")) {
                url = SELECT_ROOM_SERVLET;
            }
            if (btn.equals("Add To Cart")) {
                url = BOOKING_ROOM_SERVLET;
            }
            if (btn.equals("View")) {
                url = VIEW_CART;
            }
            if (btn.equals("Update")) {
                url = UPDATE_CART_SERVLET;
            }
            if (btn.equals("Delete")) {
                url = DELETE_CART_SERVLET;
            }
            if (btn.equals("Checkout")) {
                url = CHECKOUT_SERVLET;
            }
            if (btn.equals("Use code")) {
                url = PROMOTION_SERVLET;
            }
            if (btn.equals("SearchOrder")) {
                url = HISTORY_SERVLET;
            }
            if (btn.equals("Remove")) {
                url = REMOVE_HISTORY_SERVLET;
            }
            if (btn.equals("Get code")) {
                url = GET_CODE_SERVLET;
            }
        } catch (Exception e) {
            System.out.println("Error at MainController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
