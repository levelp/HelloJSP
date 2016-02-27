package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Сервлет для сортировки массива
 */
public class SortArray extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Параметр
        String parameter = request.getParameter("parameter");

        // Старт HTTP сессии
        HttpSession session = request.getSession(true);
        session.setAttribute("parameter", parameter);

        // Открываем поток, в который будем печатать текст для браузера
        PrintStream out = new PrintStream(response.getOutputStream());

        // Получаем параметр a - массив для сортировки
        String aStr = request.getParameter("a");
        // Печатаем его для отладки
        out.println("sort: a = \"" + aStr + "\"");

        // Разбираем и обрабатываем JSON
        try {
            ArrayList<Integer> list = parseJSON(aStr);
            Collections.sort(list);
            printJsonArray(out, list);
        } catch (Exception e) {
            out.print(e.toString());
        }
    }

    /**
     * Разбираем массив целых чисел в формате JSON
     *
     * @param json Строка в формате JSON
     * @return Список чисел для сортировки
     */
    ArrayList<Integer> parseJSON(String json) {
        // Убираем разделители (пробелы и др.) в начале и конце строки
        json = json.trim();
        // Удаляем начальную и конечную скобки
        if (json.startsWith("["))
            json = json.substring(1);
        if (json.endsWith("]"))
            json = json.substring(0, json.length() - 1);
        // Опять убираем разделитеи в начале и конце строки
        json = json.trim();

        // Делим строчку по запятым
        String[] elements = json.split(",");

        // Каждую строчку пытаемся преобразовать в целое число
        ArrayList<Integer> list = new ArrayList<>();
        for (String s : elements) {
            list.add(Integer.parseInt(s.trim()));
        }
        // Возвращаем готовый список
        return list;
    }

    /**
     * Печать массива в формате JSON
     *
     * @param out  Поток для печати
     * @param list Список
     */
    void printJsonArray(PrintStream out, List<Integer> list) {
        out.print("[");
        for (int i = 0; i < list.size(); i++) {
            if (i != 0)
                out.print(", ");
            out.print(list.get(i));
        }
        out.println("]");
    }
}

