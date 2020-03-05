package net.tyt.sample.rectangle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import lombok.extern.slf4j.Slf4j;

/**
 *
 * @author I.Tytar
 */
@Slf4j
public class RectangleFinder {

    final Map<Integer, List<Point>> pointsX = new HashMap();
    final Map<Integer, List<Point>> pointsY = new HashMap();

    /**
     * Transform stream of points to stream of rectangle
     * @param points stream of points
     * @return stream of rectangle
     */
    public Stream<Rectangle> find(Stream<Point> points) {
        return points
                .flatMap(this::rectangles);
    }

    private Stream<Rectangle> rectangles(final Point a) {
        log.debug("rectangles({})...",a);
        addPoint(a,a.getX(),pointsX);
        addPoint(a,a.getY(),pointsY);
        List<Point> lx = pointsX.getOrDefault(a.getX(), new ArrayList());
        log.debug("points list with X={}: {}",a.getX(), lx);
        return lx.stream()
                .filter(b -> !b.equals(a))
                .flatMap(b -> rectangles(a,b));
    }
    
    private void addPoint(final Point p, final Integer k, final Map<Integer, List<Point>> m) {
        List<Point> l = m.get(k);
        if (l == null) {
            l = new ArrayList();
            m.put(k, l);
        }
        l.add(p);
    }

    private Stream<Rectangle> rectangles(final Point a, final Point b) {
        log.debug("rectangels({},{})...",a,b);
        List<Point> ly = pointsY.getOrDefault(a.getY(), new ArrayList());
        log.debug("points list with Y={}: {}",a.getY(), ly);
        return ly.stream()
                .filter(d -> !d.equals(a))
                .map(d -> Rectangle.builder()
                            .a(a)
                            .b(b)
                            .d(d)
                            .c(findVertexC(d.getX(),b.getY()))
                            .build()).filter(Rectangle::isComplete);
    }
    
    private Point findVertexC(final int x, final int y) {
        log.debug("findVertexC({},{})...",x,y);
        List<Point> lx = pointsX.getOrDefault(x, new ArrayList());
        return lx.stream()
                .filter(p -> p.getX() == x && p.getY() == y)
                .findFirst().orElse(null);
    }
}
