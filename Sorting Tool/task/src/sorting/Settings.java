package sorting;

public class Settings {
    private SortingType sortingType;
    private DataType dataType;
    private String inputFileName;
    private String outputFileName;
    private boolean argsCorrect = true;

    public Settings(String[] args) {
        // defaults
        sortingType = SortingType.NATURAL;
        dataType = DataType.WORD;

        // if the -sortingType argument is provided but the type is not, print a message No sorting type defined!
        // if the -dataType argument is provided but the type is not, print No data type defined!
        // if unknown command-line arguments are provided, print "-arg" is not a valid parameter. It will be skipped. for each unknown argument -arg;
        // if there are strings in the input, but the data type is defined as long, print "abc" is not a long. It will be skipped. for each string abc from the input.
        // If -inputFile is provided followed by the file name, read the input data from the file.
        // If -outputFile is provided followed by the file name, output only the error messages to the console and print the results to the file.

        // reading args
        for (int ii = 0; ii < args.length; ii++) {
            switch (args[ii]) {
                case "-sortingType":
                    if (ii + 1 > args.length - 1) {
                        argsCorrect = false;
                    } else {
                        ii++;
                        switch (args[ii]) {
                            case "natural":
                                sortingType = SortingType.NATURAL;
                                break;
                            case "byCount":
                                sortingType = SortingType.BY_COUNT;
                                break;
                            default:
                                argsCorrect = false;
                                break;
                        }
                    }
                    if (!argsCorrect) {
                        System.out.println("No sorting type defined!");
                    }
                    break;
                case "-dataType":
                    if (ii + 1 > args.length - 1) {
                        argsCorrect = false;
                    } else {
                        ii++;
                        switch (args[ii]) {
                            case "long":
                                dataType = DataType.LONG;
                                break;
                            case "line":
                                dataType = DataType.LINE;
                                break;
                            case "word":
                                dataType = DataType.WORD;
                                break;
                            default:
                                argsCorrect = false;
                                break;
                        }
                    }
                    if (!argsCorrect) {
                        System.out.println("No data type defined!");
                    }
                    break;
                case "-inputFile":
                    if (ii + 1 > args.length - 1) {
                        argsCorrect = false;
                        System.out.println("No input file defined!");
                    } else {
                        ii++;
                        inputFileName = args[ii];
                    }
                    break;
                case "-outputFile":
                    if (ii + 1 > args.length - 1) {
                        argsCorrect = false;
                        System.out.println("No output file defined!");
                    } else {
                        ii++;
                        outputFileName = args[ii];
                    }
                    break;
                default:
                    System.out.printf("\"%s\" is not a valid parameter. It will be skipped.\n", args[ii]);
                    argsCorrect = false;
                    ii++;
                    break;
            }
        }
    }

    public boolean isArgsCorrect() {
        return argsCorrect;
    }

    public SortingType getSortingType() {
        return sortingType;
    }

    public DataType getDataType() {
        return dataType;
    }

    public String getInputFileName() {
        return inputFileName;
    }

    public String getOutputFileName() {
        return outputFileName;
    }
}

enum DataType {
    LONG, WORD, LINE
}

enum SortingType {
    NATURAL, BY_COUNT
}