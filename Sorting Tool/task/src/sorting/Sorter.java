package sorting;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Sorter {
    Settings settings;
    private InputStream inputStream = System.in;
    private PrintWriter printWriter = null;

    public Sorter(Settings settings) {
        this.settings = settings;
    }

    public void sort() {
        openInputOutputStreams();
        switch (settings.getDataType()) {
            case LONG:
                sortLongs(settings.getSortingType());
                break;
            case WORD:
                sortWords(settings.getSortingType());
                break;
            case LINE:
                sortLines(settings.getSortingType());
                break;
        }
        closeInputOutputStreams();
    }

    private void openInputOutputStreams() {
        if (settings.getInputFileName() != null) {
            try {
                inputStream = new FileInputStream(settings.getInputFileName());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if (settings.getOutputFileName() != null) {
            try {
                printWriter = new PrintWriter(settings.getOutputFileName());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void closeInputOutputStreams() {
        if (!inputStream.equals(System.in)) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (printWriter != null) {
            printWriter.close();
        }
    }

    private void write(String str) {
        if (printWriter != null) {
            printWriter.print(str);
        } else {
            System.out.print(str);
        }
    }

    private void sortLongs(SortingType sortingType) {
        Scanner scanner = new Scanner(this.inputStream);
        List<Long> numberList = new ArrayList<>();

        while (scanner.hasNext()) {
            String str = scanner.next();
            try {
                numberList.add(Long.parseLong(str));
            } catch (NumberFormatException e) {
                write(String.format("\"%s\" is not a long. It will be skipped.\n", str));
            }
        }

        write(String.format("Total numbers: %d.\n", numberList.size()));
        switch (sortingType) {
            case NATURAL:
                numberList.sort(null);
                for (long item : numberList) {
                    write(item + " ");
                }
                break;
            case BY_COUNT:
                List<Map.Entry<Long, Integer>> list = SortUtil.sortByFrequency(numberList);
                printWithFrequency(list);
                break;
        }
    }


    private void sortWords(SortingType sortingType) {
        Scanner scanner = new Scanner(this.inputStream);
        List<String> stringList = new ArrayList<>();

        while (scanner.hasNext()) {
            String str = scanner.next();
            stringList.add(str);
        }

        write(String.format("Total words: %d.\n", stringList.size()));
        switch (sortingType) {
            case NATURAL:
                stringList.sort(null);
                for (String item : stringList) {
                    write(item + " ");
                }
                break;
            case BY_COUNT:
                List<Map.Entry<String, Integer>> list = SortUtil.sortByFrequency(stringList);
                printWithFrequency(list);
                break;
        }
    }

    private void sortLines(SortingType sortingType) {
        Scanner scanner = new Scanner(this.inputStream);
        List<String> stringList = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            stringList.add(str);
        }

        write(String.format("Total lines: %d.\n", stringList.size()));
        switch (sortingType) {
            case NATURAL:
                stringList.sort(null);
                write("Sorted data:");
                for (String item : stringList) {
                    write(item);
                }
                break;
            case BY_COUNT:
                List<Map.Entry<String, Integer>> list = SortUtil.sortByFrequency(stringList);
                printWithFrequency(list);
                break;
        }
    }

    private <K> void printWithFrequency(List<Map.Entry<K, Integer>> list) {
        for (Map.Entry<K, Integer> entry : list) {
            write(entry.getKey() + ": " +
                    entry.getValue() + " time(s), " +
                    Math.round(entry.getValue() * 100.0 / list.size()) + "%\n");
        }
    }
}
