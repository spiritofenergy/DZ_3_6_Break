

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("1. Пользователь вводит 10 слов в массив. Найдите первое слово, в котором есть две\n" +
                "гласные буквы подряд");
        Scanner sc = new Scanner(System.in);

        System.out.println("Введите 10 слов:");
        String[] arr = new String[10];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.next();
        }
        String twoVowels = null;
        out: for (var word : arr) {
            var vowelCount = 0;
            for (var c : word.toCharArray()) {
                switch(Character.toLowerCase(c)) {
                    case 'а','е','ё','и','о','у','ы','э','ю','я' -> {
                        if (++vowelCount == 2) {
                            twoVowels = word;
                            break out;
                        }
                    }
                    default -> vowelCount = 0;
                };
            }
        }
        if (null == twoVowels) {
            System.out.println("Слово НЕ найдено");
        } else {
            System.out.println("Слово найдено: " + twoVowels);
        }

        System.out.println("2. Пользователь вводит массив чисел. Найдите первое четное число");
        int number [] = new int[]{3, 4, 7, 45, 67, 34, 90, 43, 46};
        for (int i = 0; i < number.length; i++){
            if (number[i] % 2 == 0){
                System.out.println(number[i]);
                break;
            }
        }

        System.out.println("3. Найдите первое чётное число в массиве, которое больше 100");
        int moreHundred[] = new int[]{34, 56, 234, 786, 23, 4, 768, 45, 245, 566, 3};
        for (int i = 0; i < moreHundred.length; i++){
            if(moreHundred[i] > 100 && moreHundred[i] % 2 == 0){
                System.out.println(moreHundred[i]);
                break;
            }
        }

        System.out.println("4. Найдите последнее слово в массиве, которое написано ЗаБоРчИкОм (что б\n" +
                "узнать, заглавная ли буква, используйте Character.isUpperCase(letter) )\n");
        String strArray[] = new String[]{"Символ", "имеет", "имеет", "ЗнАчЕнИе"};
        String strUno  = strArray[strArray.length - 1];
        char [] letters = strUno.toCharArray();
        boolean isUpper = Character.isUpperCase(letters[0]);
        System.out.println(isUpper);

        System.out.println("5. Выводите числа от 1 до того момента, как сумма всех цифр в числе не будет\n" +
                "равна 20 (пример такого числа - 875)\n");

        int numberUno [] = new int[]{2, 6, 4, 8, 3, 0, 6, 3, 6, 9, 4, 5, 2, 1, 6, 7, 6, 4, 8, 3, 0, 6, 3, 6, 9, 4, 5, 2, 1, 6, 7};
        int numberDos [] = new int [numberUno.length];
        int sum = 0;
        for (int i = 0; i < numberDos.length; i++){
            numberDos[i] = numberUno[i];
            sum = sum + numberUno[i];
            if (20 <= sum) {
                break; }
        }System.out.println("Сумма чисел равна: " + sum);
        for (int y = 0; y < numberDos.length; y++){
            if(numberDos[y] != 0 ){
                System.out.print("[" +numberDos[y]+"], ");
                break;
            }
        }

        System.out.println("6. Выведите все даты невисокосного года. В январе 31 день, феврале - 28, далее\n" +
                "чередуется - в марте 31, в апреле 30… ");

        System.out.println("7. Сохраняйте снимки NASA с 1 января того момента, пока в поле Explanation нет\n" +
                "слова “Earth”.\n");

        String key = "WgH82FSmI6M04geem07EWObXt2MbUuZGM2dmfYTg";
        for (int day = 1; day < 30; day++) {

            String page = downloadWebPage("https://api.nasa.gov/planetary/apod?api_key=" + key + "&date=2022-01-" + day + "");
            int urlBegin = page.lastIndexOf("url");
            int urlEnd = page.lastIndexOf("}");
            String url = page.substring(urlBegin + 6, urlEnd - 1);

            int beginExplanation = page.lastIndexOf("explanation");
            int endExplanation = page.lastIndexOf("hdurl");
            String urlExplanation = page.substring(beginExplanation + 13, endExplanation -2);


            InputStream in = new URL(url).openStream();
            Files.copy(in, Paths.get(day + "январь_2022.jpg"));
            if(urlExplanation.contains("Earth")){
                System.out.println("Мы нашли её Earth");
                System.out.println("Explanation: " + urlExplanation);
                break;
            }
        }
        System.out.println("Готово");
    }

    private static String downloadWebPage(String url) throws IOException {
        StringBuilder result = new StringBuilder();
        String line;
        URLConnection urlConnection = new URL(url).openConnection();
        try (
                InputStream is = urlConnection.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is))){
            while ((line = br.readLine()) != null){
                result.append(line);
            }
        }
        return result.toString();

    }
}