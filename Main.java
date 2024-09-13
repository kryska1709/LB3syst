//В телевизионном танцевальном марафоне с определением победителя с помощью телезрителей
//после каждого тура объявляется sms-голосование, в котором зрители указывают наиболее
//понравившуюся им пару из максимум 16 пар, которые участвуют в проекте.
//Вам предлагается написать программу, которая будет обрабатывать результаты
//sms-голосования по данному вопросу. Результаты голосования получены в виде номеров пар.
//На вход программе в первой строке подаётся количество пришедших sms-сообщений N.
//В каждой из последующих N строк записан номер пары от 1 до 16.
//Пример входных данных: 421632 Программа должна вывести список призеров,
//порядке убывания количества голосов, отданных за ту или иную пару,
//с указанием количества отданных за неё голосов.
//При этом каждая пара должна быть выведена ровно один раз вне зависимости от того,
//сколько раз она встречается в списке. Пример выходных данных для приведённого
//выше примера входных данных:2 23 116 1
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner ok = new Scanner(System.in);
        System.out.println("введите количество голосов");
        int N = ok.nextInt(); // количество голосов

        if (N > 100) {
            System.out.println("голосование окончено");
            return;
        }

        int[] pairs = new int[N]; // массив состоящий из голосов за пары
        for (int i = 0; i < N; i++) {
            pairs[i] = (int) (Math.random() * 16) + 1; // заполнение массива случайными числами от 1 до 16
        }

        Map<Integer, Integer> pairspodch = new HashMap<>(); // использование хэш таблицы
        for (int arry : pairs) {
            if (pairspodch.containsKey(arry)) {
                pairspodch.put(arry, pairspodch.get(arry) + 1); // проход по массиву и проверка заполненности
            } else {
                pairspodch.put(arry, 1);
            }
        }

        for (int i = 1; i <= 16; i++) {
            if (!pairspodch.containsKey(i)) pairspodch.put(i, 0); // если таблица не содержит ключ то добавить его
        }

        System.out.println("наши призёры: ");
        pairspodch.entrySet().stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .limit(3)
                .forEach(it -> System.out.println(it.getKey() + " " + it.getValue())); // сортировка в порядке убывания первых трех пар и их вывод

        System.out.println("результаты участников: ");
        pairspodch.entrySet().stream()
                .sorted(Map.Entry.<Integer, Integer>comparingByValue().reversed())
                .forEach(it -> System.out.println(it.getKey() + " " + it.getValue()));
    }
}
