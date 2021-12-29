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
import phuclh.daos.UserDAO;
import phuclh.dtos.UserDTO;
import phuclh.dtos.UserErrorDTO;
import phuclh.utils.HelpUtls;

/**
 *
 * @author Acer
 */
public class CreateUserServlet extends HttpServlet {

    private static final String SUCCESS = "createNewAccount.jsp";
//    private final static String ERROR = "index.html";
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
        
        String url = SUCCESS;
                
        try {
            /* TODO output your page here. You may use following sample code. */
            String email = request.getParameter("txtEmail");
            String password = request.getParameter("txtPassword");
            String rePassword = request.getParameter("txtRePassword");
            String username = request.getParameter("txtUsername");
            String address = request.getParameter("txtAddress");
            String phone = request.getParameter("txtPhone");
            boolean check = false;
            boolean checkEmail = false;
            UserErrorDTO userError = new UserErrorDTO();
            UserDAO userDAO = new UserDAO();
            
            Date currentDate = new Date(System.currentTimeMillis());
            
            if (email.equals("")) {
                check = true;
                userError.setEmailError("This field cant be blank!");
            }
            if (password.equals("")) {
                check = true;
                userError.setPassword("This field cant be blank!");
            }
            if (rePassword.equals("")) {
                check = true;
                userError.setRePassword("This field cant be blank!");
            }
            if (username.equals("")) {
                check = true;
                userError.setUsername("This field cant be blank!");
            }
            if (address.equals("")) {
                check = true;
                userError.setAddress("This field cant be blank!");
            }
            if (phone.equals("")) {
                check = true;
                userError.setPhone("This field cant be blank!");
            }
               if (!phone.matches("[0-9]{10}")){
                check= false;
                userError.setPhone("phone numbers having 10 digits.");
            }
            if (check) {
                request.setAttribute("CREATE_ERROR", userError);
            } else {
                if (!rePassword.equals(password)) {
                    check = true;
                    userError.setRePassword("Password is not macth");
                }
                
                checkEmail = HelpUtls.checkFormat(email, "^[a-z][a-z0-9_\\.]{5,32}@[a-z0-9]{2,}(\\.[a-z0-9]{2,4}){1,2}$");
                if (checkEmail) {
                    check = true;
                    userError.setEmailError("Email is not valid format");
                }
                
                checkEmail = userDAO.checkUserExist(email);
                if (checkEmail) {
                    check = true;
                    userError.setEmailError("Email is exist");
                }
                if (check) {
                    request.setAttribute("CREATE_ERROR", userError);
                } else {
                    String encodePassword = HelpUtls.encodePassword(password);
                    UserDTO userDTO = new UserDTO(email, encodePassword, username, phone, address, "User", true, currentDate);
                    
                    boolean checkInsert = userDAO.createUser(userDTO);
                    
                    if (checkInsert) {
                        url = SUCCESS;
                        request.setAttribute("CREATE_USER", "Create successful");
                    }
                }
            }
        } catch (Exception e) {
//            System.out.println("Error at CreateUserServlet:" + e.toString());
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
