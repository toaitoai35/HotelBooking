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
import phuclh.daos.PromotionDAO;
import phuclh.dtos.UserDTO;

/**
 *
 * @author Acer
 */
public class UsePromotionServlet extends HttpServlet {


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
        try {
            /* TODO output your page here. You may use following sample code. */
            String code = request.getParameter("txtPromotion");
            System.out.println("-----"+code);
            Date currentDate = new Date(System.currentTimeMillis());
            System.out.println(currentDate);

            HttpSession session = request.getSession();
            UserDTO userDTO = (UserDTO) session.getAttribute("LOGIN_INFO");

            PromotionDAO proDAO = new PromotionDAO();
            String checkCodeUsed = proDAO.isCodeUsed(userDTO.getEmail(), code);
            System.out.println("-----"+checkCodeUsed + "-------");
            if (checkCodeUsed.equals("")) {
                float proValue = proDAO.isCodeExpired(code, currentDate);
                System.out.println(proValue);
                if (proValue > 0) {
                    boolean insertPromotion = proDAO.insertPromotionDetail(userDTO.getEmail(), code);
                    System.out.println(insertPromotion);
                     if (insertPromotion) {
                        session.setAttribute("DISCOUNT", proValue);
                    }
                } else {
                    request.setAttribute("MESSAGE", "This code has been expired");
                }
            } else {
                request.setAttribute("MESSAGE", "Your account has been used this code");
            }
        } catch (Exception e) {
//            System.out.println("Error at UsePromotionServlet:" + e.toString());
    e.printStackTrace();
        } finally {
            request.getRequestDispatcher("view.jsp").forward(request, response);
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
