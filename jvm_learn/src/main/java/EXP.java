public class EXP {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Student s1 = new Student("小张");
        Student s2 = new Student("小李");
        swap(s1, s2);
        System.out.println("s1:" + s1.getName());
        System.out.println("s2:" + s2.getName());
    }

    static void swap(Student x, Student y) {
//        Student temp = x;
//        x = y;
//        y = temp;
        String tmp = x.getName();
        x.name = y.name;
        y.name = tmp;
        System.out.println("x:" + x.getName());
        System.out.println("y:" + y.getName());
    }
    static class Student{
        String name;

        Student(String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }
    }
}
