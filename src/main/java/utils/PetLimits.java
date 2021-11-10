package utils;

import java.util.Map;

public class PetLimits {
    public final static Map<String, Long> id = Map.of(
            "min", 1L,
            "max", 9223372036854775807L
    );

    public final static Map<String, Integer> name = Map.of(
            "min", 4,
            "max", 12
    );

    public final static String [] status = {"available", "pending", "sold"};

    public final static Map <String, Map<String, Integer>> photoUrls = Map.of(
            "urlCount", Map.of(
                    "min", 1,
                    "max",6),
            "length", Map.of(
                    "min", 5,
                    "max", 20)
    );

    public final static Map <String, Map<String, Integer>> tags = Map.of(
            "tagCount", Map.of(
                    "min", 1,
                    "max",6),
            "name", Map.of(
                    "min", 3,
                    "max", 12),
            "id", Map.of(
                    "min", 1,
                    "max", 100_000)
    );

    public final static Map <String, Map <String, Integer>> category = Map.of(
            "name", Map.of(
                    "min", 3,
                    "max", 15),
            "id", Map.of(
                    "min", 1,
                    "max", 100_000)
    );
}
