package com.homestream.HomeStream.main.assets;

public class Assets
{
    public boolean arrayContainsValue(Object[] array, Object value)
    {
        for(Object item : array) if(item.equals(value)) return true;
        return false;
    }
}
