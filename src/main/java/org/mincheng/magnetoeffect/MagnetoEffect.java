package org.mincheng.magnetoeffect;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MagnetoEffect {

    private final List<Point> anchors = new ArrayList<>();

    public Point check(Point point) {
        Optional<Point> nearestAnchor = findNearestAnchorFor(point);
        return nearestAnchor.orElse(point);
    }

    private Optional<Point> findNearestAnchorFor(Point point) {
        return anchors.stream()
                .map(anchor -> new AbstractMap.SimpleEntry<Point, Double>(anchor, getDistance(anchor, point)))
                .filter(entry -> entry.getValue() < Math.pow(5, 2))
                .min(Map.Entry.comparingByValue())
                .map(AbstractMap.SimpleEntry::getKey);
    }

    private Double getDistance(Point anchor, Point point) {
        return Math.pow(anchor.getX() - point.getX(), 2) + Math.pow(anchor.getY() - point.getY(), 2);
    }

    public void addAnchor(Point anchor) {
        this.anchors.add(anchor);
    }
}
