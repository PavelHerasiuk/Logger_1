package by.com.logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Это приложение с суффиксами - небольшое Java-приложение, которое обращается к файлу конфигурации,
 * переименовывает набор файлов и переименовывает их, добавляя суффикс, указанный в той же конфигурации.
 * Подробности:
 * Приложение должно читать файл конфигурации при запуске
 * Затем он должен убедиться, что все файлы из конфигурации существуют.
 * Затем он должен переименовать каждый файл, добавив суффикс из конфигурации к его имени
 * Спецификация ведения журнала
 * Приложение должно регистрировать информацию о запуске.
 * Приложение должно регистрировать информацию о прочитанной конфигурации.
 * Приложение должно регистрировать информацию о процессе переименования.
 * Приложение должно регистрировать сводную информацию.
 * Приложение должно регистрировать информацию о завершении работы.
 * Приложение должно обрабатывать и регистрировать возможные ошибки.
 * Используйте другой уровень ведения журнала. Все записи журнала также должны содержать информацию о дате и времени.
 */
public class Main {
    public static void main(String[] args) {
        Run run = new Run();
        Properties properties = run.asProperties("configuration.properties");
        String path = properties.getProperty("path");
        String suffix = properties.getProperty("suffix");
        String[] strings = properties.getProperty("fileNames").split(",");

        List<String> extended = new ArrayList<>();
        List<String> namesWithoutExtend = new ArrayList<>();
        List<File> renamedFiles = new ArrayList<>();

        for (String s : strings) {
            namesWithoutExtend.add(s.split("\\.")[0]);
            extended.add("." + s.split("\\.")[1]);
        }
        try {
            File file;
            int count = 0;
            for (String nameFile : strings) {
                file = new File(path + nameFile);
                if (file.isFile()) {
                    File newFile = new File(path + suffix + "_" + namesWithoutExtend.get(count) + extended.get(count));
                    if (file.renameTo(newFile)) {
                        renamedFiles.add(newFile);
                        count++;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(renamedFiles);
    }
}