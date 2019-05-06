package com.el.dev;

import lombok.val;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author neo.pan
 * @since 2018/04/13
 */
public class DevEdsCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        val env = context.getEnvironment();
        return env.acceptsProfiles("dev") && env.acceptsProfiles("eds");
    }

}
