import java.util.Comparator;
import java.awt.Point;

class PointCmp implements Comparator<Point> {
    public int compare(Point a, Point b) {
        return (a.y < b.y) ? -1 : (a.y > b.y) ? 1 : 0;
    }
}