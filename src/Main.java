import java.io.*;

public class Main {
    static int[] sdvig;
    static String key;
    static String deistvie;
    static String file1;
    static String file2;
    static final String CODE_DEYSTVIE = "code";
    static final String DECODE_DEYSTVIE = "decode";

    public static void main(String[] args) throws IOException {

        if (!commandLine(args)) {
            return;
        }

        key = (args[3]);
        sdvig = new int[key.length()];
        for (int i = 0; i < sdvig.length; i++) {
            sdvig[i] = key.charAt(i);
        }

        if (deistvie.equalsIgnoreCase(CODE_DEYSTVIE)) {
            try (FileReader fileReader = new FileReader(file1); FileWriter fileWriter = new FileWriter(file2)) {
                int i = 0;
                while (fileReader.ready()) {
                    i++;
                    i = i > sdvig.length - 1 ? 0 : i;
                    char c = (char) fileReader.read();
                    c += sdvig[i];
                    fileWriter.write(c);
                }
            }
        }

        if (deistvie.equalsIgnoreCase(DECODE_DEYSTVIE)) {
            try (FileReader fileReader = new FileReader(file1); FileWriter fileWriter2 = new FileWriter(file2)) {
                int i = 0;
                while (fileReader.ready()) {
                    i++;
                    i = i > sdvig.length - 1 ? 0 : i;
                    char c = (char) fileReader.read();
                    c -= sdvig[i];
                    fileWriter2.write(c);
                }
            }
        }
    }

    public static boolean commandLine(String[] args) {
        //может быть либо 1 парметр (Хелп или ?) либо 3 (действие, файл1, файл2, key)
        if (args.length != 1 && args.length != 4) {
            return false;
        }

        if (!args[0].equalsIgnoreCase("code") && !args[0].equalsIgnoreCase("decode")) {
            System.out.println("Возможные параметры code или decode");
            return false;
        }

        if ((args.length == 1 && args[0].equals("help")) || args.length == 1 && args[0].equals("?")) {
            System.out.println("Welcome to help!");
            System.out.println("Возможные параметры code или decode");
        }

        if (args[1].equalsIgnoreCase(args[2])) {
            System.out.println("file1 не может совпадать с file2!!!");
            return false;
        }
        deistvie = args[0];
        file1 = args[1];
        file2 = args[2];
        System.out.printf("Deistvie = %s\nFile1 = %s\nFile2 = %s", deistvie, file1, file2);
        return true;
    }

}