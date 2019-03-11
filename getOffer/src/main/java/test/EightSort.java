package test;

import com.sun.org.apache.bcel.internal.generic.Select;

import java.util.Arrays;

/**
 * @author LanceDai
 * @date 2019/3/11 18:35
 * @description *
 */
public class EightSort {


    //插入排序
    //直接插入排序
    private static <T extends Comparable> T[] straightInsertionSort(T[] data) {
        for (int i = 1; i < data.length; ++i) {
            T tmp = data[i];
            int j;
            for (j = i - 1; j >= 0 && tmp.compareTo(data[j]) < 0; --j) {
                data[j + 1] = data[j];
            }
            data[j + 1] = tmp;
        }
        return data;
    }

    //希尔排序
    private static <T extends Comparable> T[] shellSort(T[] data) {
        for (int step = data.length >> 1; step > 0; step >>= 1) {
            for (int i = step; i < data.length; ++i) {
                T temp = data[i];
                int j;
                for (j = i - step; j >= 0 && temp.compareTo(data[j]) < 0; j -= step) {
                    data[j + step] = data[j];
                }
                data[j + step] = temp;
            }
        }
        return data;
    }

    //选择排序
    //直接选择
    private static <T extends Comparable> T[] straightSelectSort(T[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            for (int j = i + 1; j < data.length; j++) {
                if (data[i].compareTo(data[j]) > 0) {
                    T temp;
                    temp = data[i];
                    data[i] = data[j];
                    data[j] = temp;
                }
            }
        }
        return data;
    }

    //堆排序
    private static <T extends Comparable> T[] heapSort(T[] data) {
        //建堆
        heapify(data, data.length);
        //构建结果
        T temp;
        for (int i = 1; i < data.length; i++) {
            temp = data[0];
            data[0] = data[data.length - i];
            data[data.length - i] = temp;
            heapify(data, data.length - i);
        }
        return data;
    }

    private static <T extends Comparable> void heapify(T[] data, int size) {
        for (int i = (size >>> 1) - 1; i >= 0; --i) {
            int k = i;
            T temp = data[i];
            while (k < (size >>> 1)) {
                int child = (k << 1) + 1;
                T c = data[child];
                int right = child + 1;
                if (right < size && c.compareTo(data[right]) < 0) {
                    c = data[child = right];
                }
                if (data[i].compareTo(c) >= 0) {
                    break;
                }
                data[k] = c;
                k = child;
            }
            data[k] = temp;
        }
    }

    //交换排序
    //冒泡排序
    private static <T extends Comparable> T[] bubbleSort(T[] data) {
        for (int i = 1; i < data.length; i++) {
            for (int j = 0; j < data.length - i; j++) {
                if (data[j].compareTo(data[j + 1]) > 0) {
                    T temp;
                    temp = data[j + 1];
                    data[j + 1] = data[j];
                    data[j] = temp;
                }
            }
        }
        return data;
    }

    //快速排序
    private static <T extends Comparable> T[] quickSort(T[] data, int start, int end) {
        if (start >= end) {
            return data;
        }
        T key = data[start];
        int newStart = start, newEnd = end, flag = 1;
        while (newStart < newEnd) {
            if (flag == 1) {
                if (key.compareTo(data[newEnd]) > 0) {
                    data[newStart++] = data[newEnd];
                    flag ^= 1;
                } else {
                    newEnd--;
                }
            } else {
                if (key.compareTo(data[newStart]) < 0) {
                    data[newEnd--] = data[newStart];
                    flag ^= 1;
                } else {
                    newStart++;
                }
            }
        }
        assert newStart == newEnd;
        data[newStart] = key;
        quickSort(data, start, newStart - 1);
        quickSort(data, newEnd + 1, end);
        return data;
    }

    //归并排序
    private static <T extends Comparable> T[] margeSort(T[] data, int start, int end) {
        if (start >= end) {
            return data;
        }
        int mid = (start + end) >> 1;
        T[] left = margeSort(data, start, mid);
        T[] right = margeSort(data, start, mid);
        //合并
        int newStart = start, newEnd = end;
        T[] newData = data.clone();
        while (newStart < newEnd) {
            data[] =
        }
    }

    //基数排序
    public static void main(String[] args) {
        Integer[] integers = new Integer[]{1, -1, 3, 0};
        System.out.println(Arrays.toString(straightInsertionSort(integers.clone())));
        System.out.println(Arrays.toString(shellSort(integers.clone())));

        System.out.println(Arrays.toString(straightSelectSort(integers.clone())));
        System.out.println(Arrays.toString(heapSort(integers.clone())));

        System.out.println(Arrays.toString(bubbleSort(integers.clone())));
        System.out.println(Arrays.toString(quickSort(integers.clone(), 0, integers.length - 1)));


        System.out.println(Arrays.toString(integers));
    }
}
