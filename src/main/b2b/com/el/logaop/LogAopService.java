/*
package com.el.logaop;

import com.el.b2b.domain.So;
import com.el.b2b.domain.SoD;
import com.el.b2b.domain.SoObject;
import com.el.b2b.mapper.SoDMapper;
import com.el.b2b.service.SoService;
import com.el.edp.util.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

*/
/**
 * @Auther: roman.zhang
 * @Date: 2019/3/5 9:11
 * @Version:V1.0
 * @Description:LogAopService
 *//*

@Aspect
@Component
public class LogAopService {

    @Autowired
    private SoService soService;

    @Autowired
    private SoDMapper soDMapper;

    @Pointcut("execution(* com.el.b2b.service.SoServiceImpl.saveForm(..))")
    private void method(){
    }

    @Before("method()")
    private void beforeMethod(JoinPoint joinPoint){
        Object[] args = joinPoint.getArgs();
        SoObject soObject = (SoObject) args[0];
        So so = soObject.getSo();

        if(!StringUtils.isEmpty(so.getDocNo())){
            List<SoD> longList = soDMapper.findBySoId(so.getId());
            Long[] longs =new Long[longList.size()];
            int i=0;
            for(SoD sod:longList){
                longs[i]=sod.getId();
                i++;
            }
            soService.sync();
        }
    }

}
*/
