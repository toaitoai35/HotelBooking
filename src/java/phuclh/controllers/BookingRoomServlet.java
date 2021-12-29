/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuclh.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import phuclh.daos.HotelDAO;
import phuclh.dtos.CartDTO;
import phuclh.dtos.RoomCartDTO;
import phuclh.utils.HelpUtls;

/**
 *
 * @author Acer
 */
public class BookingRoomServlet extends HttpServlet {

    private static final String SUCCESS = "SelectRoomServlet";
    private static final String ERROR = "index.html";
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
            String roomID = request.getParameter("txtRoomID");
            String roomDescription = request.getParameter("txtRoomDescription");
            String roomImg = request.getParameter("txtRoomImg");
            String roomPrice = request.getParameter("txtRoomPrice");
            String checkInDate = request.getParameter("txtCheckInDate");
            String checkOutDate = request.getParameter("txtCheckOutDate");
            float total = 0;
            
            if (HelpUtls.getRangeOfTwoDate(checkInDate, checkOutDate) == 0) {
                total = 1 * Float.parseFloat(roomPrice);
            } else {
                total = HelpUtls.getRangeOfTwoDate(checkInDate, checkOutDate) * Float.parseFloat(roomPrice);
            }
            HotelDAO hotelDAO = new HotelDAO();
            String hotelName = hotelDAO.getHotelNameByRoomID(roomID);
            
            RoomCartDTO roomCartDTO = new RoomCartDTO(roomID, roomImg, roomDescription, hotelName, Date.valueOf(checkInDate), Date.valueOf(checkOutDate), Float.parseFloat(roomPrice), 1, total);
            HttpSession session = request.getSession();
            CartDTO cart = (CartDTO) session.getAttribute("CART");
            
            if (cart == null) {
                cart = new CartDTO();
            }
            cart.add(roomCartDTO);
            session.setAttribute("CART", cart);
            url = SUCCESS;
        } catch (Exception e) {
            System.out.println("Error at BookingRoomServlet:" + e.toString());
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
