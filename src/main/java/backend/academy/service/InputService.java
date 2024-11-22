package backend.academy.service;

import backend.academy.generation.GenerationType;
import backend.academy.solution.SolutionType;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.Scanner;

public class InputService {
    Scanner sc;
    Random random;

    public GenerationType inputGenerationType() {
        String input = sc.nextLine();
        if ("".equals(input)) {
            return GenerationType.values()[random.nextInt(GenerationType.values().length)];
        }
        try {
            int number = Integer.parseInt(input);
            if (number >= 0 && number < GenerationType.values().length) {
                return GenerationType.values()[number];
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public InputService() {
        sc = new Scanner(System.in, StandardCharsets.UTF_8);
        random = new Random();
    }

    public SolutionType inputSolutionType() {
        String input = sc.nextLine();
        if ("".equals(input)) {
            return SolutionType.values()[random.nextInt(SolutionType.values().length)];
        }
        try {
            int number = Integer.parseInt(input);
            if (number >= 0 && number < SolutionType.values().length) {
                return SolutionType.values()[number];
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    public int[] inputMazeSize() {
        String[] cords = sc.nextLine().split(" ");
        int[] ans = new int[2];
        if (cords.length != 2) {
            return null;
        }
        try {
            ans[0] = Integer.parseInt(cords[0]);
            ans[1] = Integer.parseInt(cords[1]);
            if (ans[0] % 2 == 0 || ans[1] % 2 == 0) {
                return null;
            }
            return ans;
        } catch (Exception e) {
            return null;
        }
    }

    public int[] inputCell() {
        String[] cords = sc.nextLine().split(" ");
        int[] ans = new int[2];
        if (cords.length != 2) {
            return null;
        }
        try {
            ans[0] = Integer.parseInt(cords[0]);
            ans[1] = Integer.parseInt(cords[1]);
            return ans;
        } catch (Exception e) {
            return null;
        }
    }
}
