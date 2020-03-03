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
public class RectangelFinder {

    final Map<Integer, List<Point>> pointsX = new HashMap();
    final Map<Integer, List<Point>> pointsY = new HashMap();

    public Stream<Rectangel> find(Stream<Point> points) {
        return points
                .flatMap(this::processPoint);
    }

    private Stream<Rectangel> processPoint(final Point p) {
        addPoint(p,p.getX(),pointsX);
        addPoint(p,p.getY(),pointsY);
        log.debug("pointsX = {}",pointsX);
        log.debug("pointsY = {}",pointsY);
        return rectangels(p);
    }
    
    private void addPoint(final Point p, final Integer k, final Map<Integer, List<Point>> m) {
        List<Point> l = m.get(k);
        if (l == null) {
            l = new ArrayList();
            m.put(k, l);
        }
        l.add(p);
    }

    private Stream<Rectangel> rectangels(final Point a) {
        log.debug("rectangels({})...",a);
        List<Point> lx = pointsX.getOrDefault(a.getX(), new ArrayList());
        log.debug("lx = {}",lx);
        return lx.stream()
                .filter(b -> !b.equals(a))
                .flatMap(b -> rectangels(a,b));
    }

    private Stream<Rectangel> rectangels(final Point a, final Point b) {
        log.debug("rectangels({},{})...",a,b);
        List<Point> ly = pointsY.getOrDefault(a.getY(), new ArrayList());
        log.debug("ly = {}",ly);
        return ly.stream()
                .filter(d -> !d.equals(a)).map(d -> Rectangel.builder()
                            .a(a)
                            .b(b)
                            .d(d)
                            .c(findVertexC(d.getX(),b.getY()))
                            .build())
                .filter(r -> r.getC()!= null);
    }
    
    private Point findVertexC(final int x, final int y) {
        log.debug("findVertexC({},{})...",x,y);
        List<Point> lx = pointsX.getOrDefault(x, new ArrayList());
        return lx.stream()
                .filter(p -> p.getX() == x && p.getY() == y)
                .findFirst().orElse(null);
    }
}
