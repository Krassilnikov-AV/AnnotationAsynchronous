/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.acyncComp.home;

import java.util.concurrent.Future;
import javax.ejb.Local;

/**
 *
 * @author Aleks
 */
@Local
public interface AsyncBeanLocal {

    void slowMethod();

    Future<String> returnMethod();
    
}
