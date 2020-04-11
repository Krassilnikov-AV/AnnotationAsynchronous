package ru.acyncComp.home;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "Caller", urlPatterns = {"/Caller"})
public class Caller extends HttpServlet {

    @EJB
    private AsyncBeanLocal asyncBean;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, InterruptedException, ExecutionException {

        response.setContentType("text/html;charset=UTF-8");

        System.out.println("$$$$$$ The servlet is calling slowMethod()");
        asyncBean.slowMethod();
        System.out.println("$$$$$$ The servlet is doint something else....");
        System.out.println("$$$$$$ and something else....");

        System.out.println("$$$$$$ The servlet is calling returnMethod()");
        Future<String> fs = asyncBean.returnMethod();
        System.out.println("$$$$$$ Now we are checking if returnMethod() is finished every 2 sec");
        while (!fs.isDone()) {
            System.out.println("$$$$$$  returnMethod() is not finished yet :(");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Caller.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("$$$$$$ returnMethod() is finished !!! ");
      
        System.out.println("$$$$$$ the returned values is : " + fs.get());
        
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
        try {
            processRequest(request, response);
        } catch (InterruptedException ex) {
            Logger.getLogger(Caller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(Caller.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);
        } catch (InterruptedException ex) {
            Logger.getLogger(Caller.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExecutionException ex) {
            Logger.getLogger(Caller.class.getName()).log(Level.SEVERE, null, ex);
        }
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
