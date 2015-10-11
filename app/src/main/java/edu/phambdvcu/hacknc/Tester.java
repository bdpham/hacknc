package edu.phambdvcu.hacknc;

/**
 * Created by willem on 10/11/15.
 */
public class Tester implements Responder {

    public String respond(Object o){
        System.out.print(o.toString());
        return o.toString();
    }
}
