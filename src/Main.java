import java.io.*;

public class Main {
    static int[] sdvig;
    static String key;
    static String mode;
    static String fileIn;
    static String fileOut;
    static final String CODE_MODE = "code";
    static final String DECODE_MODE = "decode";

    public static void main(String[] args) {

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
            } catch (Exception e) {
                System.out.println(e.getMessage());
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
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

    public static boolean commandLine(String[] args) {
        //может быть либо 1 парметр (Хелп или ?) либо 4 (действие, файл1, файл2, key)
        if (args.length != 1 && args.length != 4) {
            return false;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("help") || (args.length == 1 && args[0].equals("?"))) {
            System.out.println("Welcome to help!");
            System.out.println("Возможные параметры - code или decode");
            System.out.println("Программа принимает с коммандной строки 4 параметра:");
            System.out.println("1 - тип - кодировать или дектодировать (code decode)");
            System.out.println("2 - имя исходного файла (тхт)");
            System.out.println("3 - имя финального (закодированого) файла");
            System.out.println("4 - ключевая фраза 1......int длины");
            System.out.println("пример: code d:\\in.txt d:\\out.txt Ключевая_фраза_любой_длины_из_любых-символов....НО!!!!Без+пробелов,слешей_и_кавычек..");
            return false;
        }

        if (args.length==4 && !args[0].equalsIgnoreCase("code") && !args[0].equalsIgnoreCase("decode")) {
            System.out.println("Возможные параметры - code или decode");
            return false;
        }


        if (args.length == 4 && args[1].equalsIgnoreCase(args[2])) {
            System.out.println("Исходный файл не может совпадать с финальным файлом!");
            return false;
        }

        if(args.length==4){
            mode = args[0];
            fileIn = args[1];
            fileOut = args[2];
            key = args[3];
            return true;
        }

        return false;
    }

}
