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
import javax.servlet.http.HttpSession;
import phuclh.daos.RoomDAO;
import phuclh.dtos.CartDTO;
import phuclh.dtos.RoomCartDTO;
import phuclh.dtos.RoomDTO;

/**
 *
 * @author Acer
 */
public class UpdateCartServlet extends HttpServlet {

    private static final String SUCCESS = "view.jsp";
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
            String quantity = request.getParameter("txtQuantity");
            RoomCartDTO roomCartDTO = null;
            HttpSession session = request.getSession();
            CartDTO cart = (CartDTO) session.getAttribute("CART");
            RoomDAO roomDAO = new RoomDAO();
            RoomDTO checkQuantity = roomDAO.getRoomByID(roomID);

            for (RoomCartDTO dto : cart.getCart().values()) {
                if (dto.getRoomID().equals(roomID)) {
                    float total = (dto.getTotal()/dto.getQuantity()) * Integer.parseInt(quantity);
                    roomCartDTO = new RoomCartDTO(roomID, dto.getRoomImg(), dto.getRoomDescripton(), dto.getHotelName(), dto.getCheckInDate(), dto.getCheckOutDate(), dto.getRoomPrice(), Integer.parseInt(quantity), total);
                }
            }
            if (checkQuantity.getRoomQuantity() >= roomCartDTO.getQuantity()) {
                cart.update(roomID, roomCartDTO);
                session.setAttribute("CART", cart);
                url = SUCCESS;
            } else {
                request.setAttribute("MESSAGE", "Quantity of the room you just update is too much, please choose quantity base on our's product quantity: " + checkQuantity.getRoomQuantity());
                url = SUCCESS;
            }

        } catch (Exception e) {
//            System.out.println("Error at UpdateCartServlet:" + e.toString());
e.printStackTrace();
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
