package com.xier.sesame.attence.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.dozer.DozerBeanMapper;

public class DozerMapperUtil {
	
	private static DozerBeanMapper mapp = null;       
    
    public static DozerBeanMapper getMapper(){   
        if(mapp==null){   
            mapp = new DozerBeanMapper();   
        }   
        return mapp;   
    }    
    
    public static <R, T> List<T> mapList(Collection<R> sourceList, Class<T> destinationClass) {
        List<T> destinationList = new ArrayList<T>();
        for (R sourceObject : sourceList) {
            T destinationObject = getMapper().map(sourceObject, destinationClass);
            destinationList.add(destinationObject);
        }
        return destinationList;
    }

}
