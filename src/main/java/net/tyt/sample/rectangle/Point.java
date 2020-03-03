package net.tyt.sample.rectangle;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 *
 * @author 69TytarIA
 */
@Builder
@Getter
@EqualsAndHashCode
public class Point {
    private final int x;
    private final int y;
    
    public String toString() {
        return "("+x+","+y+")";
    }
}
