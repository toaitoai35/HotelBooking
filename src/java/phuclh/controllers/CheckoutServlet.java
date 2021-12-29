/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuclh.controllers;

import java.io.IOException;
import java.sql.Date;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import phuclh.daos.EmailDAO;
import phuclh.daos.OrderDAO;
import phuclh.daos.RoomDAO;
import phuclh.dtos.CartDTO;
import phuclh.dtos.OrderDTO;
import phuclh.dtos.RoomCartDTO;
import phuclh.dtos.RoomDTO;
import phuclh.dtos.UserDTO;
import phuclh.utils.HelpUtls;

/**
 *
 * @author Acer
 */
public class CheckoutServlet extends HttpServlet {

    private static final String SUCCESS = "bill.jsp";
    private static final String ERROR = "view.jsp";

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
            HttpSession session = request.getSession();
            String total = request.getParameter("txtTotal");
            String code = request.getParameter("txtCodeConfirm");
            String checkCode = (String) session.getAttribute("CODE_CONFIRM");
            String orderID = HelpUtls.randomAlphaNumeric(5);
            Date orderCreateDate = new Date(System.currentTimeMillis());
            if (code.equals(checkCode)) {
                UserDTO userDTO = (UserDTO) session.getAttribute("LOGIN_INFO");
                CartDTO cartDTO = (CartDTO) session.getAttribute("CART");
                RoomDAO roomDAO = new RoomDAO();

                OrderDTO orderDTO = new OrderDTO(orderID, userDTO.getEmail(), Float.parseFloat(total), true, orderCreateDate);
                Map<String, RoomCartDTO> items = cartDTO.getCart();

                OrderDAO orderDAO = new OrderDAO();
                boolean checkInsert = orderDAO.insertOrder(orderDTO);

                if (checkInsert) {
                    boolean checkOrder = orderDAO.insertOrderDetails(orderID, items);
                    if (checkOrder) {
                        for (RoomCartDTO dto : items.values()) {
                            RoomDTO roomDTO = roomDAO.getRoomByID(dto.getRoomID());
                            boolean checkUpdate = roomDAO.updateQuantity(roomDTO.getRoomQuantity() - dto.getQuantity(), dto.getRoomID());
                            if (checkUpdate) {
                                url = SUCCESS;
                                session.removeAttribute("CART");
                                session.removeAttribute("DISCOUNT");
                                session.removeAttribute("CODE_CONFIRM");
                            }
                        }
                    }
                }
            } else {
                request.setAttribute("MESSAGE", "Confirm code is wrong! Please check code again at email or take a new code");
            }
        } catch (Exception e) {
//            System.out.println("Error at CheckoutServlet:" + e.toString());
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
