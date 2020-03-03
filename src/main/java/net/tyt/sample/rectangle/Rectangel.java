package net.tyt.sample.rectangle;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 *
 * @author I.Tytar
 */
@Builder
@Getter
@EqualsAndHashCode
public class Rectangel {

    private final Point a;
    private final Point b;
    private final Point c;
    private final Point d;

    public boolean isComplete() {
        return a != null && b != null && c != null && d != null;
    }
    
    public Rectangel normalize() {
        if (!isComplete()) {
            throw new IllegalStateException("rectangel in not complete");
        }
        Integer[] xa = new Integer[] {a.getX(),b.getX(),c.getX(),d.getX()};
        Integer[] ya = new Integer[] {a.getY(),b.getY(),c.getY(),d.getY()};
        List<Integer> xl = Arrays.asList(xa);
        List<Integer> yl = Arrays.asList(xa);
        int xmin = Collections.min(xl);
        int xmax = Collections.max(xl);
        int ymin = Collections.min(yl);
        int ymax = Collections.max(yl);
        return Rectangel.builder()
                .a(Point.builder().x(xmin).y(ymin).build())
                .b(Point.builder().x(xmin).y(ymax).build())
                .c(Point.builder().x(xmax).y(ymax).build())
                .d(Point.builder().x(xmax).y(ymin).build())
                .build();
    }
    
    public String toString() {
        return "{"+a+","+b+","+c+","+d+"}";
    }

}
