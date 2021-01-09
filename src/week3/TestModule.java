package week3;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;

public class TestModule {

    private final int[][] randomData;
    private final int[][] reverseData;

    private final long[][] randomDataResult;
    private final long[][] reverseDataResult;

    public TestModule() {
        randomData = new int[3][];
        reverseData = new int[3][];

        randomDataResult = new long[3][9];
        reverseDataResult = new long[3][9];
    }

    public void initAllData() {
        initArray(0, 1000);
        initArray(1, 10000);
        initArray(2, 100000);
    }

    public void initArray(int index, int size) {
        Random random = new Random();

        randomData[index] = new int[size];
        reverseData[index] = new int[size];

        for (int i = 0; i < size; i++) {
            randomData[index][i] = random.nextInt(size) + 1;
            reverseData[index][i] = size - i;
        }
    }

    public void test(SortModule sortModule) {
        long start = System.currentTimeMillis();
        sortModule.bubbleSort(Arrays.copyOf(randomData[0], randomData[0].length));
        randomDataResult[0][0] = System.currentTimeMillis() - start;

        start = System.currentTimeMillis();
        sortModule.selectionSort(Arrays.copyOf(randomData[0], randomData[0].length));
        randomDataResult[0][1] = System.currentTimeMillis() - start;

        start = System.currentTimeMillis();
        sortModule.insertionSort(Arrays.copyOf(randomData[0], randomData[0].length));
        randomDataResult[0][2] = System.currentTimeMillis() - start;

        start = System.currentTimeMillis();
        sortModule.mergeSort(Arrays.copyOf(randomData[0], randomData[0].length));
        randomDataResult[0][3] = System.currentTimeMillis() - start;

        start = System.currentTimeMillis();
        sortModule.quickSort(Arrays.copyOf(randomData[0], randomData[0].length), 0);
        randomDataResult[0][4] = System.currentTimeMillis() - start;

        start = System.currentTimeMillis();
        sortModule.quickSort(Arrays.copyOf(randomData[0], randomData[0].length), 1);
        randomDataResult[0][5] = System.currentTimeMillis() - start;

        start = System.currentTimeMillis();
        sortModule.quickSort(Arrays.copyOf(randomData[0], randomData[0].length), 2);
        randomDataResult[0][6] = System.currentTimeMillis() - start;
    }

    public void showResult() {

    }
}
