import java.util.*;

class Shape {
    int getArea() {
        return 0;
    }
}

class Rectangle extends Shape {
    int length, breadth;

    Rectangle(int l, int b) {
        length = l;
        breadth = b;
    }

    @Override
    int getArea() {
        int area = length * breadth;
        System.out.println("Area of the rectangle is: " + area + " sq. units.");
        return area;
    }
}

public class Area {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the dimensions of the rectangle (length and breadth): ");
        int l = s.nextInt();
        int b = s.nextInt();

        Rectangle rect = new Rectangle(l, b);
        rect.getArea();

        s.close();
    }
}
