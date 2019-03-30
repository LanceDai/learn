package test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Random;

/**
 * @author LanceDai
 * @date 2019/3/11 18:35
 * @description *
 */
public class EightSort {


    //插入排序
    //直接插入排序
    private static <T extends Comparable<? super T>> T[] straightInsertionSort(T[] data, int start, int end) {
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
    private static <T extends Comparable<? super T>> T[] shellSort(T[] data, int start, int end) {
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
    private static <T extends Comparable<? super T>> T[] straightSelectSort(T[] data, int start, int end) {
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

    /**
     * 堆排序
     */
    private static <T extends Comparable<? super T>> T[] heapSort(T[] data, int start, int end) {
        //建堆
        for (int i = (data.length >>> 1) - 1; i >= 0; --i) {
            shiftDown(data, data.length, i, data[i]);
        }
        //构建结果
        T temp;
        for (int i = 1; i < data.length; i++) {
            temp = data[0];
            data[0] = data[data.length - i];
            data[data.length - i] = temp;
            shiftDown(data, data.length - i, 0, data[0]);
        }
        return data;
    }

    private static <T extends Comparable<? super T>> void shiftDown(T[] data, int size, int index, T x) {
        while (index < (size >>> 1)) {
            int child = (index << 1) + 1;
            T c = data[child];
            int right = child + 1;
            if (right < size && c.compareTo(data[right]) < 0) {
                c = data[child = right];
            }
            if (x.compareTo(c) >= 0) {
                break;
            }
            data[index] = c;
            index = child;
        }
        data[index] = x;
    }

    //交换排序
    //冒泡排序
    private static <T extends Comparable<? super T>> T[] bubbleSort(T[] data, int start, int end) {
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
    private static <T extends Comparable<? super T>> T[] quickSort(T[] data, int start, int end) {
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
    private static Integer[] margeSort(Integer[] data, int start, int end) {
        if (start >= end) {
            return data;
        }
        int mid = (start + end) >> 1;
        margeSort(data, start, mid);
        margeSort(data, mid + 1, end);
        //合并
        int i = start, j = mid + 1, cursor = start;
        while (i <= mid || j <= end) {
            if (i > mid) {
                tempData[cursor] = data[j++];
            } else if (j > end) {
                tempData[cursor] = data[i++];
            } else if (data[i].compareTo(data[j]) < 0) {
                tempData[cursor] = data[i++];
            } else {
                tempData[cursor] = data[j++];
            }
            cursor++;
        }
        System.arraycopy(tempData, start, data, start, end - start + 1);
//        for (cursor = start; cursor <= end; ++cursor) {
//            data[cursor] = tempData[cursor];
//        }
        return data;
    }

    //基数排序
    private static Integer[] basicSort(Integer[] data, int start, int end) {
        int maxVal = -1;
        for (int i = 0; i < data.length; i++) {
            maxVal = Math.max(maxVal, data[i]);
        }
        int digitPosition = 1;
        while (maxVal / digitPosition > 0) {
            int[] bins = new int[data.length];
            int[] digitCount = new int[10];
            for (int i = 0; i < data.length; i++) {
                digitCount[(data[i] / digitPosition) % 10]++;
            }
            for (int i = 1; i < digitCount.length; i++) {
                digitCount[i] += digitCount[i - 1];
            }
            for (int i = data.length - 1; i >= 0; --i) {
                bins[--digitCount[(data[i] / digitPosition) % 10]] = data[i];
            }
            for (int i = 0; i < data.length; i++) {
                data[i] = bins[i];
            }
            digitPosition *= 10;
        }
        return data;
    }

    static Integer[] tempData;

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Random random = new Random();
        Integer[] integers = new Integer[(random.nextInt(10) + 1) * 10000];
        for (int i = 0; i < integers.length; i++) {
            integers[i] = random.nextInt(1000000);
            random.setSeed(random.nextLong());
        }
        tempData = new Integer[integers.length];
        System.out.println("integers = " + Arrays.toString(integers));
        System.out.println("integers length = " + integers.length);
        Integer[] rightRes = integers.clone();
        Arrays.sort(rightRes);
        Class c = EightSort.class;
        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods) {
            String methodName = method.getName();
            if (methodName.matches("\\S*Sort")) {
                Integer[] mayRightRes = computeCostTime(method, integers.clone(), 0, integers.length - 1);
                for (int i = 0; i < integers.length; i++) {
                    assert mayRightRes[i].equals(rightRes[i]);
                }
            }
        }
    }

    private static Integer[] computeCostTime(Method method, Object... args)
            throws InvocationTargetException, IllegalAccessException {
        System.out.println("method.getName() = " + method.getName());
        long start = System.currentTimeMillis();
        Integer[] res = (Integer[]) method.invoke(null, args);
        System.out.println("cost time is " + (System.currentTimeMillis() - start) + "ms");
        return res;
    }
}
