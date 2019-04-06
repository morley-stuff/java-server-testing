package com.morleystuff.multithreadcapitalizer.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Pass the server ip as the only commandline arg");
            return;
        }
        try (Socket socket = new Socket(args[0],59091)) {
            System.out.println("Enter lines of text the Ctrl+D or Ctrl+C to exit");
            Scanner scanner = new Scanner(System.in);
            Scanner in = new Scanner(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            while(scanner.hasNextLine()) {
                out.println(scanner.nextLine());
                System.out.println(in.nextLine());
            }
        }
    }
}
