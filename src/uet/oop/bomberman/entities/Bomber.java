package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import uet.oop.bomberman.graphics.Sprite;

import static uet.oop.bomberman.Controllers.stillObjects;
import static uet.oop.bomberman.graphics.Sprite.SCALED_SIZE;

public class Bomber extends Entity {

    public boolean up = false;
    public boolean down = false;
    public boolean left = false;
    public boolean right = false;

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    public Bomber (int x, int y, Image img, boolean up, boolean down, boolean left, boolean right) {
        super(x, y, img);
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
    }


    @Override
    public void update() {
        caculateMove();
        checkMove(false,false,false,false);
    }

    public void moveUp () {
        y -= Sprite.DEFAULT_SIZE;
        img = Sprite.player_up_1.getFxImage();
        y -= Sprite.DEFAULT_SIZE;
        img = Sprite.player_up_2.getFxImage();
    }

    public void moveDown () {
        y += Sprite.DEFAULT_SIZE;
        img = Sprite.player_down_1.getFxImage();
        y += Sprite.DEFAULT_SIZE;
        img = Sprite.player_down_2.getFxImage();
    }

    public void moveLeft () {
        x -= Sprite.DEFAULT_SIZE;
        img = Sprite.player_left_1.getFxImage();
        x -= Sprite.DEFAULT_SIZE;
        img = Sprite.player_left_2.getFxImage();
    }

    public void moveRight () {
        x += Sprite.DEFAULT_SIZE;
        img = Sprite.player_right_1.getFxImage();
        x += Sprite.DEFAULT_SIZE;
        img = Sprite.player_right_2.getFxImage();
    }

    public void caculateMove() {
        if(up == true) moveUp();
        if(down == true) moveDown();
        if(left == true) moveLeft();
        if(right == true) moveRight();
    }

    public void checkMove(boolean up, boolean down, boolean left, boolean right) {
        this.up = up;
        this.down = down;
        this.left = left;
        this.right = right;
    }

    public boolean checkGrass(int x, int y) {
        for(Entity n : stillObjects) {
            if(n.x == x && n.y == y) {
                if(n.getClass() == Grass.class) {
                    return true;
                }
            }
        }
        return false;
    }

    public void KeyPressed(KeyEvent e) {
        switch (e.getCode().toString()){
            case "LEFT":
                checkMove(false, false, true, false);
                left = checkGrass(x - SCALED_SIZE,y);
                break;
            case "RIGHT":
                checkMove(false, false, false, true);
                right = checkGrass(x + SCALED_SIZE, y);
                break;
            case "UP":
                checkMove(true, false, false, false);
                up = checkGrass(x, y - SCALED_SIZE);
                break;
            case "DOWN":
                checkMove(false, true, false, false);
                down = checkGrass(x, y + SCALED_SIZE);
                break;
        }
    }

    public void KeyReleased (KeyEvent e) {
        switch (e.getCode().toString()) {
            case "LEFT":
                img = Sprite.player_left.getFxImage();
                break;
            case "RIGHT":
                this.img = Sprite.player_right.getFxImage();
                break;
            case "UP":
                img = Sprite.player_up.getFxImage();
                break;
            case "DOWN":
                img = Sprite.player_down.getFxImage();
                break;
        }
    }

}
