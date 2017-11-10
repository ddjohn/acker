import java.util.Iterator;
import java.util.Vector;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ProgressMonitor{ 
    int total, current=-1; 
    boolean indeterminate; 
    int milliSecondsToWait = 500; // half second 
    String status; 
 
    public ProgressMonitor(int total, boolean indeterminate, int milliSecondsToWait){ 
        this.total = total; 
        this.indeterminate = indeterminate; 
        this.milliSecondsToWait = milliSecondsToWait; 
    } 
 
    public ProgressMonitor(int total, boolean indeterminate){ 
        this.total = total; 
        this.indeterminate = indeterminate; 
    } 
 
    public int getTotal(){ 
        return total; 
    } 
 
    public void start(String status){ 
        if(current!=-1) 
            throw new IllegalStateException("not started yet"); 
        this.status = status; 
        current = 0; 
        fireChangeEvent(); 
    } 
 
    public int getMilliSecondsToWait(){ 
        return milliSecondsToWait; 
    } 
 
    public int getCurrent(){ 
        return current; 
    } 
 
    public String getStatus(){ 
        return status; 
    } 
 
    public boolean isIndeterminate(){ 
        return indeterminate; 
    } 
 
    public void setCurrent(String status, int current){ 
        if(current==-1) 
            throw new IllegalStateException("not started yet"); 
        this.current = current; 
        if(status!=null) 
            this.status = status; 
        fireChangeEvent(); 
    } 
 
    /*--------------------------------[ ListenerSupport ]--------------------------------*/ 
 
    private Vector<ChangeListener> listeners = new Vector<ChangeListener>(); 
    private ChangeEvent ce = new ChangeEvent(this); 
 
    public void addChangeListener(ChangeListener listener){ 
        listeners.add(listener); 
    } 
 
    public void removeChangeListener(ChangeListener listener){ 
        listeners.remove(listener); 
    } 
 
    private void fireChangeEvent(){ 
        Iterator<ChangeListener> iter = listeners.iterator(); 
        while(iter.hasNext()){ 
            ((ChangeListener)iter.next()).stateChanged(ce); 
        } 
    } 
}