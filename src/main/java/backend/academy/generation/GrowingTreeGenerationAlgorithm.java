package backend.academy.generation;

import backend.academy.cell.Cell;
import backend.academy.cell.CellType;
import backend.academy.maze.Maze;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GrowingTreeGenerationAlgorithm implements GenerationAlgorithm {
    private final Random random = new Random();

    @Override
    public Maze generate(int height, int width) {
        Cell[][] grid = new Cell[height][width];

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = new Cell(x, y, CellType.WALL);
            }
        }

        List<Cell> cells = new ArrayList<>();
        Cell start = new Cell(1, 1, CellType.EMPTY);
        grid[1][1] = start;
        cells.add(start);

        while (!cells.isEmpty()) {
            Cell current;
            if (random.nextBoolean()) {
                current = cells.get(cells.size() - 1);
            } else {
                current = cells.get(random.nextInt(cells.size()));
            }

            Cell next = getRandomNeighbor(grid, current);

            if (next != null) {
                grid[next.y()][next.x()].type(CellType.EMPTY);
                grid[(current.y() + next.y()) / 2][(current.x() + next.x()) / 2].type(CellType.EMPTY);
                cells.add(next);
            } else {
                cells.remove(current);
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
