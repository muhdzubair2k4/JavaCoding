package JavaCoding.DAA.Assignment2;

public class PatternSearchAlgorithm {

    private static final int ASCII_SIZE = 256;

    static void buildBadCharacterTable(String pattern, int[] table) {

        for (int i = 0; i < ASCII_SIZE; i++) {
            table[i] = -1;
        }

        for (int i = 0; i < pattern.length(); i++) {
            table[pattern.charAt(i)] = i;
        }
    }

    static void findPattern(String text, String pattern) {

        int textLength = text.length();
        int patternLength = pattern.length();

        int[] badCharTable = new int[ASCII_SIZE];
        buildBadCharacterTable(pattern, badCharTable);

        int shift = 0;

        while (shift <= textLength - patternLength) {

            int index = patternLength - 1;

            while (index >= 0 &&
                    pattern.charAt(index) == text.charAt(shift + index)) {
                index--;
            }

            if (index < 0) {

                System.out.println("Pattern found at index: " + shift);

                if (shift + patternLength < textLength) {
                    shift += patternLength - badCharTable[text.charAt(shift + patternLength)];
                } else {
                    shift++;
                }

            } else {

                shift += Math.max(
                        1,
                        index - badCharTable[text.charAt(shift + index)]
                );
            }
        }
    }

    public static void main(String[] args) {

        String sentence = "Insertion sort typically has a smaller constant factor than merge sort";
        String key = "sort";

        findPattern(sentence, key);
    }
}