package com.qixinmini.common.security.annotation;

import com.qixinmini.common.security.configure.MicroserviceResourceServerConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(MicroserviceResourceServerConfigure.class)
public @interface EnableMicroserviceResourceServer {
}
