package sorting;

public class Main {

    public static void main(final String[] args) {
        Settings settings = new Settings(args);
        if (!settings.isArgsCorrect()) {
            return;
        }
        Sorter sorter = new Sorter(settings);
        sorter.sort();
    }
}
