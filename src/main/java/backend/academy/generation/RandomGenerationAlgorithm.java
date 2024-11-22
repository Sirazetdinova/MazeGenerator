package backend.academy.generation;

import backend.academy.cell.Cell;
import backend.academy.cell.CellType;
import backend.academy.maze.Maze;
import java.util.Random;
import java.util.Stack;

public class RandomGenerationAlgorithm implements GenerationAlgorithm {
    private final Random random = new Random();

    @Override
    public Maze generate(int height, int width) {
        Cell[][] grid = new Cell[height][width];
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = new Cell(x, y, CellType.WALL);
            }
        }

        Stack<Cell> stack = new Stack<>();
        Cell start = new Cell(1, 1, CellType.EMPTY);
        grid[1][1] = start;
        stack.push(start);

        while (!stack.isEmpty()) {
            Cell current = stack.peek();
            Cell next = getRandomNeighbor(grid, current);

            if (next != null) {
                stack.push(next);
                grid[next.y()][next.x()].type(CellType.EMPTY);
                grid[(current.y() + next.y()) / 2][(current.x() + next.x()) / 2].type(CellType.EMPTY);
            } else {
                stack.pop();
            }
        }

        return new Maze(height, width, grid);
    }

    private Cell getRandomNeighbor(Cell[][] grid, Cell cell) {
        int x = cell.x();
        int y = cell.y();
        Cell[] neighbors = new Cell[4];
        int count = 0;

        if (x > 1 && grid[y][x - 2].type() == CellType.WALL) {
            neighbors[count++] = new Cell(x - 2, y, CellType.EMPTY);
        }
        if (x < grid[0].length - 2 && grid[y][x + 2].type() == CellType.WALL) {
            neighbors[count++] = new Cell(x + 2, y, CellType.EMPTY);
        }
        if (y > 1 && grid[y - 2][x].type() == CellType.WALL) {
            neighbors[count++] = new Cell(x, y - 2, CellType.EMPTY);
        }
        if (y < grid.length - 2 && grid[y + 2][x].type() == CellType.WALL) {
            neighbors[count++] = new Cell(x, y + 2, CellType.EMPTY);
        }

        if (count == 0) {
            return null;
        }
        return neighbors[random.nextInt(count)];
    }
}
