package backend.academy.generation;

public class GenerationAlgorithmFactory {
    public static GenerationAlgorithm getGenerator(GenerationType type) {
        return switch (type) {
            case GenerationType.KRUSKAL -> new KruskalGenerationAlgorithm();
            case GenerationType.PRIM -> new PrimGenerationAlgorithm();
            case GenerationType.GROWING_TREE -> new GrowingTreeGenerationAlgorithm();
            case GenerationType.RANDOM -> new RandomGenerationAlgorithm();
        };
    }

}
