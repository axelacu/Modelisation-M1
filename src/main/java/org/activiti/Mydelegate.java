package org.activiti;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class Mydelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String var = (String) execution.getVariable("input");
        var = var.toUpperCase();
        execution.setVariable("input", var);
        System.out.println("");
    }
}
