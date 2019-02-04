package org.activiti;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

import java.util.Map;

public class MyDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution execution) throws Exception {
        execution.setVariable("approbation", verificationPedagogique(execution));
    }

    private Boolean verificationPedagogique(DelegateExecution execution){
        Map<String,Object> mapVar = execution.getVariables();
        for(String key : mapVar.keySet()){
            Object val = mapVar.get(key);
            if(val instanceof String){
                String sVal = (String) val;
                if(sVal.equals("")){
                    return false;                }
            }
        }
        String post = (String) execution.getVariable("poste");

        return post.length() >= 5;
    }

}
