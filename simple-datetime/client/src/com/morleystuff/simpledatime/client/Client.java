package com.morleystuff.simpledatime.client;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {

        if (args.length != 1) {
            System.out.println("Pass the server ip as the only commandline argument");
            return;
        }
        Socket socket = new Socket(args[0], 59090);
        Scanner in = new Scanner(socket.getInputStream());
        System.out.println("Server Response: " + in.nextLine());
    }

}
