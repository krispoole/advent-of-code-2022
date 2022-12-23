package Day_17;

import java.util.ArrayList;
import java.util.List;

public class Shapes {

    private String[][] shape1;
    private String[][] shape2;
    private String[][] shape3;
    private String[][] shape4;
    private String[][] shape5;

    ArrayList<String[][]> shapes = new ArrayList<>();

    public Shapes() {

        shape1 = new String[][] { { "#", "#", "#", "#" } };
        shape2 = new String[][] { {".", "#", "."},{ "#","#","#" }, { ".", "#", "." } };
        shape3 = new String[][] { {".", ".", "#"},{ ".", ".", "#" }, { "#", "#", "#" } };
        shape4 = new String[][] { {"#"}, {"#"}, {"#"}, {"#"} };
        shape5 = new String[][] { { "#", "#" }, { "#", "#" } };

        shapes.add(shape1);
        shapes.add(shape2);
        shapes.add(shape3);
        shapes.add(shape4);
        shapes.add(shape5);

    }

    public String[][] getShape1() {
        return shape1;
    }

    public void setShape1(String[][] shape1) {
        this.shape1 = shape1;
    }

    public String[][] getShape2() {
        return shape2;
    }

    public void setShape2(String[][] shape2) {
        this.shape2 = shape2;
    }

    public String[][] getShape3() {
        return shape3;
    }

    public void setShape3(String[][] shape3) {
        this.shape3 = shape3;
    }

    public String[][] getShape4() {
        return shape4;
    }

    public void setShape4(String[][] shape4) {
        this.shape4 = shape4;
    }

    public String[][] getShape5() {
        return shape5;
    }

    public void setShape5(String[][] shape5) {
        this.shape5 = shape5;
    }

    public ArrayList<String[][]> getShapes() {
        return shapes;
    }

    public void setShapes(ArrayList<String[][]> shapes) {
        this.shapes = shapes;
    }

    public String[][] getShape(int shapeCounter) {
        if (shapeCounter == 0) {
            return shape1;
        } else if (shapeCounter == 1) {
            return shape2;
        } else if (shapeCounter == 2) {
            return shape3;
        } else if (shapeCounter == 3) {
            return shape4;
        } else if (shapeCounter == 4) {
            return shape5;
        } else {
            return null;
        }

    }

    
    
    
}
