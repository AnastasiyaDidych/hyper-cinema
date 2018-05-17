package com.softserve.edu.hypercinema.util;

import com.softserve.edu.hypercinema.entity.TicketEntity;
import org.apache.commons.lang3.StringUtils;

public class BarcodeGenerator {

    public static String generateStringBarcode(TicketEntity ticket) {
         /*-
         22 symbols:

         18 T100 3G05 9890 1806 2000
         A   B    C    D    E    F

         A: 2 symbols - year

         B: 1st symbol - first letter of Movie name
            2-4 symbols = length of movie name

         C : 1st symbol - first letter of hall Technology (usually it will be number, like 2D or 3D)
             2nd symbol - first letter of hall name
             3-4 symbols = length of hall name

         D: 1-2 symbols = row
            3-4 symbols = number

         E: 1-2 symbols = day
            3-4 symbols = month

         F: 1-4 symbols = time (20:00 -> 2000)
         */

        return new StringBuilder()
                .append(buildA(ticket).toString())
                .append(buildB(ticket).toString())
                .append(buildC(ticket).toString())
                .append(buildD(ticket).toString())
                .append(buildE(ticket).toString())
                .append(buildF(ticket).toString())
                .toString();
    }

    private static StringBuilder buildA(TicketEntity ticket) {
        /*-
            A: 2 symbols - year
        */

        int year = ticket.getSession().getDate().getYear();

        return new StringBuilder()
                .append(cutNumbersFromEnd(year, 2))
                .append("-");
    }

    private static StringBuilder buildB(TicketEntity ticket) {
        /*-
            B: 1st symbol - first letter of Movie name
               2-4 symbols = length of movie name
        */
        String movieName = ticket.getSession().getMovie().getTitle();

        int length = movieName.length();

        return new StringBuilder()
                .append(StringUtils.substring(movieName, 0, 1).toUpperCase())
                .append(cutNumbersFromEnd(length, 3))
                .append("-");
    }

    private static StringBuilder buildC(TicketEntity ticket) {
        /*-
            C : 1st symbol - first letter of hall Technology (usually it will be number, like 2D or 3D)
                2nd symbol - first letter of hall name
                3-4 symbols = length of hall name
        */

        String technology = ticket.getSession().getHall().getTech();
        String hallName = ticket.getSession().getHall().getName();
        int length = hallName.length();

        return new StringBuilder()
                .append(StringUtils.substring(technology, 0, 1))
                .append(StringUtils.substring(hallName, 0, 1).toUpperCase())
                .append(cutNumbersFromEnd(length, 2))
                .append("-");
    }

    private static StringBuilder buildD(TicketEntity ticket) {
        /*-
             D: 1-2 symbols = row
                3-4 symbols = number
        */

        int row = ticket.getSeat().getRow();
        int number = ticket.getSeat().getNumber();

        return new StringBuilder()
                .append(cutNumbersFromEnd(row, 2))
                .append(cutNumbersFromEnd(number, 2))
                .append("-");
    }

    private static StringBuilder buildE(TicketEntity ticket) {
        /*-
           E: 1-2 symbols = day
              3-4 symbols = month
        */

        int day = ticket.getSession().getDate().getDayOfMonth();
        int month = ticket.getSession().getDate().getMonthValue();

        return new StringBuilder()
                .append(cutNumbersFromEnd(day, 2))
                .append(cutNumbersFromEnd(month, 2))
                .append("-");
    }

    private static StringBuilder buildF(TicketEntity ticket) {
        /*-
            F: 1-4 symbols = time (20:00 -> 2000)
        */

        int hour = ticket.getSession().getStartTime().getHour();
        int minutes = ticket.getSession().getStartTime().getMinute();

        return new StringBuilder()
                .append(cutNumbersFromEnd(hour, 2))
                .append(cutNumbersFromEnd(minutes, 2));
    }

    private static String cutNumbersFromEnd(int text, int lastNumbers) {
        text = (int) Math.pow(10, lastNumbers) + text;
        String str = text + "";
        if (str.length() < lastNumbers) {
            lastNumbers = str.length();
        }
        return StringUtils.substring(str, str.length() - lastNumbers, str.length());
    }

}
