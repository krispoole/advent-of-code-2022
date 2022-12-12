package Day_11;

import java.util.ArrayList;

public class Monkey {

    private int name;
    private ArrayList<Long> items;
    private String[] operation;
    private Long testDenominator;
    private int ifTrue;
    private int ifFalse;
    private Long inspectionCount;

    public Monkey(int name){
        this.name = name;
    }

    
    public Long getTestDenominator() {
        return testDenominator;
    }

    public void setTestDenominator(Long testDenominator) {
        this.testDenominator = testDenominator;
    }

    public String[] getOperation() {
        return operation;
    }

    public void setOperation(String[] operation) {
        this.operation = operation;
    }
    

    public Long getInspectionCount() {
        return inspectionCount;
    }

    public void setInspectionCount(Long inspectionCount) {
        this.inspectionCount = inspectionCount;
    }

    public ArrayList<Long> getItems() {
        return items;
    }

    public void setItems(ArrayList<Long> items) {
        this.items = items;
    }


    public int getName() {
        return name;
    }


    public void setName(int name) {
        this.name = name;
    }


    public int getIfTrue() {
        return ifTrue;
    }


    public void setIfTrue(int ifTrue) {
        this.ifTrue = ifTrue;
    }


    public int getIfFalse() {
        return ifFalse;
    }


    public void setIfFalse(int ifFalse) {
        this.ifFalse = ifFalse;
    }
    

}
