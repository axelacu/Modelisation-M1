package org.activiti;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import java.util.Map;

public class MyDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        /*
        String var = (String) execution.getVariable("input");
        var = var.toUpperCase();
        execution.setVariable("input", var);
        System.out.println("");*/
        int id = Integer.parseInt(execution.getId());
        switch (id){
            case 3:
                execution.setVariable("approbation",verificationPedagogique(execution));
                break;
        }

    }

    public boolean verificationPedagogique(DelegateExecution execution){
        Map<String,Object> mapVar = execution.getVariables();
        for(String key : mapVar.keySet()){
            Object val = mapVar.get(key);
            if(val instanceof String){
                String sVal = (String) val;
                if(sVal.equals("")){
                    return false;                }
            }
        }
        return true;
    }
    public boolean verificationAdministratif(DelegateExecution execution ){

        String email  =(String)execution.getVariable("mailTutor");

       if(email.contains("@")) {
           return true;
       }
       return  false;
    }
}
