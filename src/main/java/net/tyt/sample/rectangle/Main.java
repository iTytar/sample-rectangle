package net.tyt.sample.rectangle;

import static java.lang.System.*;
import java.util.Iterator;
import java.util.Random;
import java.util.stream.Stream;

/**
 *
 * @author I.Tytar
 */
public class Main {

    public static void main(String[] args) throws Exception {
        out.println("Start...");
        final int maxx = 10;
        final int maxy = 10;
        final int n = 100;
        RectangelFinder finder = new RectangelFinder();
        finder.find(pointsStream(maxx,maxy,n))
                .forEach(out::println);
        out.println("...Done");
    }
    
/**
 * Generate random points stream.
 * @param maxx X dimension
 * @param maxy Y dimension
 * @param n number of points
 * @return points stream
 */    
    private static Stream<Point> pointsStream(final int maxx, final int maxy,final int n) {
        final Stream<Integer> sx = Stream.generate(() -> new Random().nextInt(maxx))
                .limit(n);
        final Stream<Integer> sy = Stream.generate(() -> new Random().nextInt(maxy))
                .limit(n);
        final Iterator<Integer> i = sy.iterator();
        return sx.map(x -> Point.builder()
                .x(x)
                .y(i.next())
                .build());
    }
    
}
