package com.challenge.desafio;

import com.challenge.annotation.Somar;
import com.challenge.annotation.Subtrair;
import com.challenge.interfaces.Calculavel;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;


public class CalculadorDeClasses implements Calculavel {
    @Override
    public BigDecimal somar(Object o) throws InvocationTargetException, IllegalAccessException {
        BigDecimal resultado = adicao(o,Somar.class);

        return resultado;
    }

    @Override
    public BigDecimal subtrair(Object o) throws InvocationTargetException, IllegalAccessException {
       BigDecimal resultado = adicao(o,Subtrair.class);

       return resultado;
    }

    @Override
    public BigDecimal totalizar(Object o) throws InvocationTargetException, IllegalAccessException {
        BigDecimal positivo=somar(o);
        BigDecimal negativo = subtrair(o).negate();

        BigDecimal resultado = positivo.add(negativo);

        return resultado;
    }

    public BigDecimal adicao(Object o, Class annotation) throws InvocationTargetException, IllegalAccessException {
        BigDecimal resultado = BigDecimal.ZERO;
        Field[] fields = o.getClass().getDeclaredFields();
        Method[] methods = o.getClass().getMethods();

            for (Field field : fields) {
                if (field.getDeclaredAnnotation(annotation) != null && isBigDecimal(field)) {
                    for(Method method : methods){
                        if(method.getName().toUpperCase().contains("GET"+field.getName().toUpperCase())){
                            resultado= resultado.add((BigDecimal) method.invoke(o));
                        }
                }
            }
    }

        return resultado;
    }

    @Override
    public boolean isBigDecimal(Field field) {
        boolean resultado = true;

        if(field.isAnnotationPresent(Somar.class) || field.isAnnotationPresent(Subtrair.class)){
            if(!field.getType().isAssignableFrom(BigDecimal.class)){
               resultado= false;
            }
        }else{
            resultado = false;
        }
        return resultado;
    }


}
