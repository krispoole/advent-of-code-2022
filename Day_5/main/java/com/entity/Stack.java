package Day_5.main.java.com.entity;

public class Stack {
    
    private Long stackNumber;
    private String stackString;

    public Stack(Long stackNumber, String stackString) {
        this.stackNumber = stackNumber;
        this.stackString = stackString;
    }

    public Long getStackNumber() {
        return stackNumber;
    }

    public void setStackNumber(Long stackNumber) {
        this.stackNumber = stackNumber;
    }

    public String getStackString() {
        return stackString;
    }

    public void setStackString(String stackString) {
        this.stackString = stackString;
    }
    

}
