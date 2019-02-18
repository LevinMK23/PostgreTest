public class Test {

    public static void main(String[] args) {
        int [] a = {1, 2, 3, 4, 5};
        show(a);
        update(a, 2, 1);
        System.out.println();
        show(a);
    }

    static void update(int [] a, int p, int v){
        a[p] = v;
    }

    static void show(int [] a){
        for (int i : a){
            System.out.print(i + " ");
        }
    }
}
