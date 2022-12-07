package aam18_lab3;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Launcher {

    public static void main(String[] args) {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml"); // Загрузка файла с биновами

            WatchsDAO watchsDAO = (WatchsDAO) context.getBean("customerDAO"); // Загрузка бина доступа к таблице часов 

            watchsDAO.deleteAll(); // Удаление всех записей
            
            Watchs watchs = new Watchs("Traser", "Quartz"); // Создание нового объекта таблицы часов 
            watchsDAO.insert(watchs); // Вставить новый объект (запись) в таблицу часов

            watchsDAO.insert(new Watchs("Garmin", "Quartz")); // Вставить новый объект (запись) в таблицу часов
            watchsDAO.insert(new Watchs("Zinvo", "Mechanical1")); // Вставить новый объект (запись) в таблицу часов
            watchsDAO.insert(new Watchs("Boccia", "Mechanical2")); // Вставить новый объект (запись) в таблицу часов

            System.out.println("Начальная БД:");
            List<Watchs> list = watchsDAO.selectAll();
            for (Watchs myWatchs : list) {
                System.out.println(myWatchs.getBrand()+ " " + myWatchs.getMechanism());
            }
            System.out.println();
            
            watchsDAO.deleteByBrand("inv"); // Удаление записей по фрагменту бренда
            watchsDAO.deleteByMechanism("cal2");
            watchsDAO.delete("Garmin", "Quartz"); // Удаление записи по бренду и механизму

            
            
            System.out.println("Поиск по фрагменту бренда - aser");
            List<Watchs> watchs_list = watchsDAO.findByBrand("aser"); // Поиск записей по фрагменту бренда
            if (watchs_list != null) {
                for (Object element : watchs_list) {
                    System.out.println(element);
                }
            } else {
                System.out.println("Нет данных");
            }
            System.out.println();
            
            

            watchsDAO.append("Orient", "Mechanical3"); // Добавлние записей
            watchsDAO.append("Diesel", "Quartz");
            watchsDAO.append("Polar7", "Quartz");
            watchsDAO.append("Oris", "Mechanical");
            watchsDAO.appendOnlyBrand("Brand????");

            watchsDAO.update("Mechanical", "Mechanical3"); // Изменение записей в таблице
            watchsDAO.updateBrand("Polar", "Polar7"); // Изменение записей в таблице

            System.out.println("БД после изменений:");
            list = watchsDAO.selectAll();
            for (Watchs myWatchs : list) {
                System.out.println(myWatchs.getBrand() + " " + myWatchs.getMechanism());
            }
            System.out.println();
            
            System.out.println("Поиск по фрагменту механизма - anica");
            watchs_list = watchsDAO.findByMechanism("anica"); // Поиск записей по фрагменту механизма
            if (watchs_list != null) {
                for (Object element : watchs_list) {
                    System.out.println(element);
                }
            } else {
                System.out.println("Нет данных");
            }
            System.out.println();

            System.out.println("Вывод записей с брендом Diesel и механизмом Quartz:");

            list = watchsDAO.select("Diesel", "Quartz");
            for (Watchs myWatchs : list) {
                System.out.println(myWatchs.getBrand() + " " + myWatchs.getMechanism());
            }
            
            System.out.println("Вывод записей с брендом Oris:");

            list = watchsDAO.selectByBrand("Oris");
            for (Watchs myWatchs : list) {
                System.out.println(myWatchs.getBrand() + " " + myWatchs.getMechanism());
            }
            
            System.out.println("Вывод записей с механизмом Mechanical:");

            list = watchsDAO.selectByMechanism("Mechanical");
            for (Watchs myWatchs : list) {
                System.out.println(myWatchs.getBrand() + " " + myWatchs.getMechanism());
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error!");
        }
    }
    
}
