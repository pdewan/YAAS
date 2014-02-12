package yaas.visualization.code;

import util.javac.SourceMethod;

public interface MethodInvocationListener {
    public void methodPushed (SourceMethod aSourceMethod) ;
    public void methodPopped (SourceMethod aSourceMethod) ;
    
    public void newMethodPointer(int newVal) ;


}
