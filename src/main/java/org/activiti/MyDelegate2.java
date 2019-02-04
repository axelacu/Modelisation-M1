package org.activiti;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class MyDelegate2 implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        delegateExecution.setVariable("approbation",verificationAdministratif(delegateExecution));
    }

    private Boolean verificationAdministratif(DelegateExecution execution ){
        String email  =(String)execution.getVariable("mailTutor");

        if(email.contains("@")) {
            return true;
        }
        return  false;
    }
}
