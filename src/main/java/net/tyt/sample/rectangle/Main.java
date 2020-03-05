package net.tyt.sample.rectangle;

import static java.lang.System.*;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 *
 * @author I.Tytar
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        out.println("Start...");
//        Stream<Point> ps = pointsStream(10,10,100);
        Stream<Point> ps = Stream.generate(Main::getPoint);
        RectangleFinder finder = new RectangleFinder();
        finder.find(ps)
                .forEach(out::println);
        out.println("...Done");
    }

    /**
     * Generate random points stream.
     *
     * @param maxx X dimension
     * @param maxy Y dimension
     * @param n number of points
     * @return points stream
     */
    private static Stream<Point> pointsStream(final int maxx, final int maxy, final int n) {
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

    /**
     * Get point from console
     *
     * @return Point
     */
    private static Point getPoint() {
        out.print("Enter point [x,y]>");
        String[] sa = scanner.nextLine().split(",");
        return Point.builder()
                .x(Integer.parseInt(sa[0]))
                .y(Integer.parseInt(sa[1]))
                .build();

    }

}
