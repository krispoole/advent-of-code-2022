package Day_5.main.java.com.entity;

public class Stack {
    
    private Integer stackNumber;
    private String stackString;

    public Stack(Integer stackNumber, String stackString) {
        this.stackNumber = stackNumber;
        this.stackString = stackString;
    }

    public Integer getStackNumber() {
        return stackNumber;
    }

    public void setStackNumber(Integer stackNumber) {
        this.stackNumber = stackNumber;
    }

    public String getStackString() {
        return stackString;
    }

    public void setStackString(String stackString) {
        this.stackString = stackString;
    }
    

}
