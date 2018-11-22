package br.com.cardif.etl.grafica.annotations;


import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LayoutField {
	String[] nameField();
//	String[] author() default "me";
}
