import geometry.*;
import org.junit.*;

import static org.junit.Assert.*;

public class GeometryTest {

    @Test(timeout = 50)
    public void pointLengthTest(){
        Point p1 = new Point(3,4);
        double expectedLength = 5.0;
        double actualLength = p1.length();
        assertEquals(expectedLength, actualLength, 0.01);
    }

    @Test(timeout = 50)
    public void distanceVectorTest(){
        Point p1 = new Point(3,4);
        Point p2 = new Point(6,7);
        Point distanceVector = p1.distanceVector(p2);
        double expected_x = -3.0;
        double expected_y = -3.0;
        double actual_x = distanceVector.getX();
        double actual_y = distanceVector.getY();
        assertEquals(expected_x,actual_x, 0.01);
        assertEquals(expected_y,actual_y, 0.01);
    }

    @Test(timeout = 50)
    public void circleIntersectionTest1(){
        Circle c1 = new Circle(new Point(1,2), 2);
        Circle c2 = new Circle(new Point(1,7), 3);
        Boolean expected = true;
        Boolean actual = c1.intersects(c2);
        assertEquals(expected, actual);
    }
    @Test(timeout = 50)
    public void circleIntersectionTest2(){
        Circle c1 = new Circle(new Point(1,2), 2);
        Circle c2 = new Circle(new Point(1,10), 3);
        Boolean expected = false;
        Boolean actual = c1.intersects(c2);
        assertEquals(expected, actual);
    }
}
