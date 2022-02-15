import java.util.StringTokenizer;
import java.util.*;
public class StrClass {
    private static String maxNum = null;

    public static String[] divide (String str, String delim) {
        String[] res;
        if (delim.length() > 1) {
            StringTokenizer st = new StringTokenizer(str, delim);
            int n = st.countTokens();
            res = new String[n];
            for (int i = 0; st.hasMoreTokens(); i++) {
                res[i] = st.nextToken();
            }
        }
        else {
            res = str.split(delim);
        }
        return res;
    }
    public static void output (String[] a) {
        for (String item : a) {
            if (item.length() != 0) System.out.println(item);
        }
    }
    public static String[] getNumbers (String[] a) {
        String[] res = new String[a.length];
        boolean isNumber = true;
        int k = 0;
        for (String item : a) {
            try {
                Integer.parseInt(item, 2);
            } catch (Exception e) {
                isNumber = false;
            }
            if (isNumber) {
                if (maxNum == null) {
                    maxNum = item;
                } else {
                    if (Integer.parseInt(item, 2) > Integer.parseInt(maxNum, 2)) {
                        maxNum = item;
                    }
                }
                res[k] = item;
                k++;
            }
            isNumber = true;
        }
        return Arrays.copyOf(res, k);
    }
    public static void palindromes (String[] a) {
        StringBuffer sb;
        boolean hasPalindrome = false;
        for (String item : a) {
            sb = new StringBuffer(item);
            if (item.compareTo((sb.reverse()).toString()) != 0) {
                System.out.println(item);
                hasPalindrome = true;
            }
        }
        if (!hasPalindrome) {
            System.out.println("Не удалось найти такие лексемы.");
        }
    }
    public static void searchP (String str, String delim, int p) {
        String num = Integer.toBinaryString(p);
        int pos = StrClass.findLeksPos(str, delim, num);
        if (pos < 0) {
            System.out.println("Данное число не удалось найти.");
        } else {
            String fmt = String.format("Позиция числа = %d.", pos + 1);
            System.out.println(fmt);
        }
    }
    public static String changeStr (String str, String delim, String[] leks) {
        int start = -1;
        int end = -1;
        char[] ch;
        boolean hasLetter;
        boolean hasDigit;
        int i;
        for (i = 0; i < leks.length; i++) {
            hasLetter = false;
            hasDigit = false;
            ch = new char[leks[i].length()];
            leks[i].getChars(0, leks[i].length(), ch, 0);
            for (char item : ch) {
                if (Character.isLetter(item)) {
                    hasLetter = true;
                }
                if (Character.isDigit(item)) {
                    hasDigit = true;
                }
            }
            if (hasLetter && hasDigit) {
                break;
            }
        }
        if (i != leks.length) {
            start = findLeksPos(str, delim, leks[i]);
            end = start + leks[i].length();
        }
        StringBuffer sb = new StringBuffer(str);
        if (start != -1) {
            sb.delete(start, end);
        }
        sb.insert(0, maxNum);
        return sb.toString();
    }
    private static int findLeksPos (String str, String delim, String leks) {
        int pos = 0;
        int k = 0;
        boolean hasDelimBefore;
        boolean hasDelimAfter;
        while (k <= str.length()) {
            pos = str.indexOf(leks, k);
            if (pos == -1) {
                break;
            }
            if (pos != 0) {
                hasDelimBefore = delim.indexOf(str.charAt(pos - 1)) != -1;
            }
            else {
                hasDelimBefore = true;
            }
            if (pos + leks.length() != str.length()) {
                hasDelimAfter = delim.indexOf(str.charAt(pos + leks.length())) != -1;
            }
            else {
                hasDelimAfter = true;
            }
            if (hasDelimBefore && hasDelimAfter) {
                break;
            }
            k = pos + 1;
        }
        return pos;
    }
}
