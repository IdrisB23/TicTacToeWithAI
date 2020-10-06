package controller;

import java.util.Scanner;

public abstract class Prompter {
    private String msg;
    private Scanner scanner;
    private String input;

    public Prompter(String msg, Scanner scanner) {
        this.msg = msg;
        this.scanner = scanner;

    }

    public String getMsg() {
        return msg;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public String getInput() {
        return input;
    }

    protected void promptUser(){
        System.out.print(msg);
        input = scanner.nextLine();
    }
    protected abstract void promptUserAndParseInput();
}
