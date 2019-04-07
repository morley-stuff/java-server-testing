package com.morleystuff.javaobjectmessages.client;

import com.morleystuff.javaobjectmessages.core.Message;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        if (args.length != 1) {
            System.out.println("Pass the server ip as the only commandline arg");
            return;
        }
        try (Socket socket = new Socket(args[0], 59092)) {
            System.out.println("Connected on: " + socket);
            Scanner userInput = new Scanner(System.in);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            System.out.println("Setup streams successfully");
            while (userInput.hasNextLine()) {
                Message myMessage = new Message(userInput.nextLine());
                objectOutputStream.writeObject(myMessage);
                Message receivedMessage = (Message) objectInputStream.readObject();
                System.out.println(receivedMessage.getMessage());
            }
        }
    }

}
