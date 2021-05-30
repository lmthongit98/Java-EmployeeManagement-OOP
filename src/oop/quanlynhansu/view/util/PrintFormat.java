package oop.quanlynhansu.view.util;

public class PrintFormat {
    public static final String HEADER_FORNAT = "| %-5d | %-14s | %-13s | %-11.1f | %-13f | %-14s |%n";
    public static final String BODY_FORMAT = "";
    public static final String BOTTOM_FORMAT = "";

    public static void drawLine(int n){
        for (int i = 0; i < n; i++) {
            System.out.print("-");
        }
    }
}
