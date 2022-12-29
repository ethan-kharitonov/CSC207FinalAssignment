package geometry;

/**
 * Represents a circle
 * @author Ethan
 */
public class Circle {
    private Point center;
    private float radius;

    /**
     * Create a circle
     * @param center the center point
     * @param radius the radius
     */
    public Circle(Point center, float radius){
        this.center = center;
        this.radius = radius;
    }

    /**
     * Determine whether two circles are intersected
     * @param other the other circle
     * @return whether two circles are intersected
     */
    public boolean intersects(Circle other){
        Point dist = center.distanceVector(other.center);
        return dist.length() <= radius + other.radius;
    }
    public Point getCenter(){
        return center;
    }
    public float getRadius(){
        return radius;
    }
}
