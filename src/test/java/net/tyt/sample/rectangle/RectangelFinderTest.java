package net.tyt.sample.rectangle;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 *
 * @author I.Tytar
 */
public class RectangelFinderTest {
    
    private static final Point A = Point.builder().x(1).y(1).build();
    private static final Point B = Point.builder().x(3).y(1).build();
    private static final Point C = Point.builder().x(3).y(3).build();
    private static final Point D = Point.builder().x(1).y(3).build();

    private static final Point F = Point.builder().x(0).y(0).build();
    private static final Point E = Point.builder().x(1).y(2).build();
    private static final Point H = Point.builder().x(3).y(2).build();
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of find method, of class RectangelFinder.
     */
    @Test
    public void testFind() {
        System.out.println("testFind...");
        Stream<Point> points = Stream.of(A,B,C,D,F,E);
        RectangelFinder instance = new RectangelFinder();
        Rectangel expResult = Rectangel.builder().a(A).b(B).c(C).d(D).build();
        Stream<Rectangel> result = instance.find(points);
        List<Rectangel> l = result.collect(Collectors.toList());
        System.out.println("found: "+l);
        assertEquals(1,l.size());
        assertEquals(expResult.normalize(),l.get(0).normalize());
        System.out.println("...testFind");
    }
    
}
