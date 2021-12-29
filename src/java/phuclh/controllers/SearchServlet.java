/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package phuclh.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import phuclh.daos.HotelDAO;
import phuclh.dtos.HotelDTO;

/**
 *
 * @author Acer
 */
public class SearchServlet extends HttpServlet {

    private static final String SUCCESS = "homepage.jsp";
//    private static final String ERROR = "LoadServlet";

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

//        String url = ERROR;
        String url = SUCCESS;

        try {
            String searchValue = request.getParameter("txtSearch");
            String checkInDate = request.getParameter("txtCheckInDate");
            String checkOutDate = request.getParameter("txtCheckOutDate");
            String amountRoom = request.getParameter("txtAmountRoom");
            HotelDAO hotelDAO = new HotelDAO();

//            Date d1 = Date.valueOf(checkInDate);
//            Date d2 = Date.valueOf(checkOutDate);
//            boolean check = d1.after(d2);
            if (amountRoom.equals("")) {
                amountRoom = "1";
            }

            if (searchValue.equals("")) {
                request.setAttribute("SEARCH_ERROR", "Please enter hotel name or area you want to make a reservation");
            } else {
                if (checkInDate.equals("") || checkOutDate.equals("")) {
                    request.setAttribute("SEARCH_ERROR", "Please enter the date you want to make a reservation");
                }
                if (!checkInDate.equals("") || !checkOutDate.equals("")) {
                    if (Date.valueOf(checkInDate).after(Date.valueOf(checkOutDate))) {
                        request.setAttribute("SEARCH_ERROR", "Please enter the checkout date after the checkin date");
                    } else {
                        hotelDAO.searchHotelWithDate(searchValue, checkInDate, checkOutDate, Integer.parseInt(amountRoom.trim()));

                        List<HotelDTO> hotelList = hotelDAO.getHotelList();

                        if (hotelList != null) {
                            request.setAttribute("HOTEL_LIST", hotelList);
                            url = SUCCESS;
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error at SearchServlet:" + e.toString());
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
