package Day_8;

public class Directory {

    private String directoryNameString;
    private String parentDirectoryString;
    private Integer directorySize;

    public Directory(String directoryNameString){
        this.directoryNameString = directoryNameString;
        this.parentDirectoryString = "";
        this.directorySize = 0;
    }

    public String getDirectoryNameString() {
        return directoryNameString;
    }

    public void setDirectoryNameString(String directoryNameString) {
        this.directoryNameString = directoryNameString;
    }

    public String getParentDirectoryString() {
        return parentDirectoryString;
    }

    public void setParentDirectoryString(String parentDirectoryString) {
        this.parentDirectoryString = parentDirectoryString;
    }

    public Integer getDirectorySize() {
        return directorySize;
    }

    public void setDirectorySize(Integer directorySize) {
        this.directorySize = directorySize;
    }
    
}
