package test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ISubjectImpl implements ISubject {
    @Override
    public void execute(){
        log.info(this.getClass().getCanonicalName());
    }
}
