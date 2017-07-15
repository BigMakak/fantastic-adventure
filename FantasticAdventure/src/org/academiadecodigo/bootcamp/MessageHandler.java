package org.academiadecodigo.bootcamp;

/**
 * Created by codecadet on 13/07/2017.
 */
public interface MessageHandler {

    void fromServer(String fromClient);

    String toServer();//Argument no server para mandar strings;
}
