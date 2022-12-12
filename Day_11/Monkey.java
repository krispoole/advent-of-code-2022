package Day_11;

import java.math.BigInteger;
import java.util.ArrayList;

public class Monkey {

    private int name;
    private ArrayList<BigInteger> items;
    private String[] operation;
    private int testDenominator;
    private int ifTrue;
    private int ifFalse;
    private BigInteger inspectionCount;

    public Monkey(int name){
        this.name = name;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }
    
    public int getTestDenominator() {
        return testDenominator;
    }

    public void setTestDenominator(int testDenominator) {
        this.testDenominator = testDenominator;
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

    public String[] getOperation() {
        return operation;
    }

    public void setOperation(String[] operation) {
        this.operation = operation;
    }

    public ArrayList<BigInteger> getItems() {
        return items;
    }

    public void setItems(ArrayList<BigInteger> items) {
        this.items = items;
    }

    public BigInteger getInspectionCount() {
        return inspectionCount;
    }

    public void setInspectionCount(BigInteger inspectionCount) {
        this.inspectionCount = inspectionCount;
    }
    

}
