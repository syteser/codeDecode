import java.io.*;

public class Main {
    static int[] sdvig;
    static String key;
    static String mode;
    static String fileIn;
    static String fileOut;
    static final String CODE_MODE = "code";
    static final String DECODE_MODE = "decode";
    static final String FILE_ERROR = "Файл не найден или заблокирован";

    public static void main(String[] args) throws IOException {

        if (!commandLine(args)) {
            return;
        }

        sdvig = new int[key.length()];
        for (int i = 0; i < sdvig.length; i++) {
            sdvig[i] = key.charAt(i);
        }

        if (mode.equalsIgnoreCase(CODE_MODE)) {
            try (FileReader fileReader = new FileReader(fileIn); FileWriter fileWriter = new FileWriter(fileOut)) {
                int i = -1;
                while (fileReader.ready()) {
                    if (++i == sdvig.length) i = 0;
                    char c = (char) fileReader.read();
                    c += sdvig[i];
                    fileWriter.write(c);
                }
            }
            catch (Exception e){
                System.out.println(FILE_ERROR);
            }
        }

        if (mode.equalsIgnoreCase(DECODE_MODE)) {
            try (FileReader fileReader = new FileReader(fileIn); FileWriter fileWriter = new FileWriter(fileOut)) {
                int i = -1;
                while (fileReader.ready()) {
                    if (++i == sdvig.length) i = 0;
                    char c = (char) fileReader.read();
                    c -= sdvig[i];
                    fileWriter.write(c);
                }
            }
            catch (Exception e){
                System.out.println(FILE_ERROR);
            }
        }

    }

    public static boolean commandLine(String[] args) {
        //может быть либо 1 парметр (Хелп или ?) либо 4 (действие, файл1, файл2, key)
        if (args.length != 1 && args.length != 4) {
            return false;
        }

        if (!args[0].equalsIgnoreCase("code") && !args[0].equalsIgnoreCase("decode")) {
            System.out.println("Возможные параметры code или decode");
            return false;
        }

        if ((args.length == 1 && args[0].equalsIgnoreCase("help")) || (args.length == 1 && args[0].equals("?"))) {
            System.out.println("Welcome to help!");
            System.out.println("Возможные параметры code или decode");
        }

        if (args[1].equalsIgnoreCase(args[2])) {
            System.out.println("Исходный файл не может совпадать с финальным файлом!");
            return false;
        }

        mode = args[0];
        fileIn = args[1];
        fileOut = args[2];
        key = args[3];

//        System.out.printf("Deistvie = %s\nFile1 = %s\nFile2 = %s\nKey = %s", deystvie, fileIn, fileOut, key);

        return true;
    }

}
