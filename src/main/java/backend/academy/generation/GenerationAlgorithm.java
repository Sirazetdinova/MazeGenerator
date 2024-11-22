package backend.academy.generation;

import backend.academy.maze.Maze;

public interface GenerationAlgorithm {
    Maze generate(int height, int width);
}
