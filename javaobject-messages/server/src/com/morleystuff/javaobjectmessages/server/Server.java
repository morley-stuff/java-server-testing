package com.morleystuff.javaobjectmessages.server;

import com.morleystuff.javaobjectmessages.core.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        try (ServerSocket listener = new ServerSocket(59092)) {
            System.out.println("Listening for new connections...");
            while (true) {
                try (Socket socket = listener.accept()) {
                    System.out.println("Connected on: " + socket);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
                    ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                    while (true) {
                        Message receivedMessage = (Message) objectInputStream.readObject();
                        if (receivedMessage == null) {
                            break;
                        }
                        System.out.println("Received message: " + receivedMessage.getMessage());
                        Message myMessage = new Message(receivedMessage.getMessage().toUpperCase());
                        objectOutputStream.writeObject(myMessage);
                    }
                }
                System.out.println("Connection closed");
            }
        }
    }

}
