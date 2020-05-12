package com.challenge.interfaces;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;

public interface Calculavel {
    public BigDecimal somar(Object o) throws InvocationTargetException, IllegalAccessException;
    public BigDecimal subtrair(Object o) throws InvocationTargetException, IllegalAccessException;
    public BigDecimal totalizar(Object o) throws InvocationTargetException, IllegalAccessException;
    public boolean isBigDecimal(Field field);
}
