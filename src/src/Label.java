package src;
public class Label {
    private final String labelTag;
    private final int line;
    public Label(String labelTag,int line)
    {
        this.labelTag=labelTag;
        this.line=line;
    }

    public String getLabelTag() {
        return labelTag;
    }

    public int getLine() {
        return line;
    }
    
}
