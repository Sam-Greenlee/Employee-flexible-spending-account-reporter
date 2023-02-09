//Programmer: Samuel Greenlee
//Program: Java03 Program Assignment
//Description: should receive information
//	about an employee’s request for funds from
//	their flexible spending accounts, and write
//	that information to a text file
//Date Created On: 3/31/2020

package Presentation;

public class StringUtils {

    public static String padWithSpaces(String s, int length) {
        if (s.length() < length) {
            StringBuilder sb = new StringBuilder(s);
            while (sb.length() < length) {
                sb.append(" ");
            }
            return sb.toString();
        } else {
            return s.substring(0, length);
        }
    }
}
