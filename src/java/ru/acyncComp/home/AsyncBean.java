package ru.acyncComp.home;

import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;


@Stateless
public class AsyncBean implements AsyncBeanLocal {

    @Asynchronous
    @Override
    public void slowMethod() {
  // начало выполнения метода
        long startTime = System.currentTimeMillis();
        System.out.println("******** slowMethod() started !!!");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(AsyncBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("******** slowMethod() is finished after"
                + (endTime - startTime) + " milliseconds");
    }    

    @Asynchronous
    @Override
    public Future<String> returnMethod() {
        try {
            Thread.sleep(15000);
        } catch (InterruptedException ex) {
            Logger.getLogger(AsyncBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        Future<String> fs = new AsyncResult("Result from returnMethod() !!!");
    return fs;
    }   
}
