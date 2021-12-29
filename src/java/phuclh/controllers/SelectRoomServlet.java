/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuclh.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import phuclh.daos.RoomDAO;
import phuclh.dtos.RoomDTO;

/**
 *
 * @author Acer
 */
public class SelectRoomServlet extends HttpServlet {

    private static final String SUCCESS = "homepage.jsp";
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
            String hotelID = request.getParameter("txtHotelID");
            String checkInDate = request.getParameter("txtCheckInDate");
            String checkOutDate = request.getParameter("txtCheckOutDate");
            String amountRoom = request.getParameter("txtAmountRoom");
            
            if (amountRoom.equals("")) {
                amountRoom = "1";
            }
            
            RoomDAO roomDAO = new RoomDAO();
            roomDAO.getListRoomByHotelIDWithDate(hotelID, checkInDate, checkOutDate, amountRoom);
            
            List<RoomDTO> roomList = roomDAO.getRoomList();
            
            if(roomList != null) {
                request.setAttribute("ROOM_LIST", roomList);
                url = SUCCESS;
            }
            
        } catch (Exception e) {
            System.out.println("Error at SelectRoomServlet:" + e.toString());
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
