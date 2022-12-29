package geometry;

/**
 * Represents a point
 * @author Ethan.
 * @author Daniel
 */
public class Point {
    private float x,y;

    /**
     * Create a point
     * @param x the x-coordinate of point
     * @param y the y-coordinate of point
     */
    public Point(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }
    public float getY(){
        return y;
    }
    public void setX(float x){
        this.x =x;
    }
    public void setY(float y){
        this.y =y;
    }

    /**
     * Translate the point horizontally/vertically
     * @param p the point being added
     */
    public void add(Point p){
        x += p.x;
        y += p.y;
    }

    /**
     * Stretch/Compress the point
     * @param scalar the factor of stretch/compression
     */
    public void multiply(float scalar){
        x *= scalar;
        y *= scalar;
    }

    /**
     * Get the square of magnitude
     * @return the square of magnitude
     */
    public float lengthSquared() {
        return x * x + y * y;
    }

    /**
     * Get the magnitude
     * @return the magnitude.
     */
    public float length(){
        return (float) Math.sqrt(lengthSquared());
    }

    /**
     * Get the difference vector.
     * @param p the other point
     * @return their difference vector
     */
    public Point distanceVector(Point p) {
        return new Point(x - p.x, y - p.y);
    }

    /**
     * Get the normalized point
     * @return the normalized point
     */
    public Point normalized(){
        float length = length();
        if(length == 0){
            throw new IllegalArgumentException("Cannot normalize the 0 vector");
        }
        return new Point(x/length, y /length);
    }

    /**
     * Determine whether the point is origin.
     * @return whether the point is origin.
     */
    public boolean isZero(){
        return x ==0 && y ==0;
    }

    public boolean equals(Point p){
        return this.x == p.x && this.y == p.y;
    }

}