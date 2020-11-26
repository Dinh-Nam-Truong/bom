package uet.oop.bomberman;

import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controllers {

    public static int level;
    public static int row;
    public static int column;

    public static List<Entity> stillObjects = new ArrayList<>();

    public String readLevel(File f) {
        String a = "";
        try {
            Scanner sc = new Scanner(f);
            int i = -1;
            while (sc.hasNext()) {
                if (i == -1) {
                    level = sc.nextInt();
                    row = sc.nextInt();
                    column = sc.nextInt();
                    i++;
                } else {
                    a += sc.nextLine();
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return a;
    }

    public void creatMap(String level) {
        char[] a = level.toCharArray();
        for(int i = 0; i<row; i++) {
            for(int j = 0; j<column; j++) {
                if (a[column*i + j] == '#') {
                    Entity o = new Wall(j, i, Sprite.wall.getFxImage());
                    stillObjects.add(o);
                } else {
                    if (a[column*i + j] == '*') {
                        Entity o = new Brick(j, i, Sprite.brick.getFxImage());
                        stillObjects.add(o);
                    } else {
                        if (a[column*i + j] == 'x') {
                            Entity o1 = new Grass(j, i, Sprite.grass.getFxImage());
                            Entity o2 = new Portal(j, i, Sprite.portal.getFxImage());
                            stillObjects.add(o1);
                            stillObjects.add(o2);
                        } else {
                            Entity o = new Grass(j, i, Sprite.grass.getFxImage());
                            stillObjects.add(o);
                        }
                    }
                }
            }
        }
    }
}
