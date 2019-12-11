package com.tuniu.ord.core.mail.template.item;

import com.tuniu.ord.core.mail.template.handler.IPlacehoderHandler;

public class PlacehoderItem implements IPlacehoderItem {

    private String key;

    private String name;

    private IPlacehoderHandler handler;

    public PlacehoderItem(String key, String name, IPlacehoderHandler placehoderHandler) {
        this.key = key;
        this.name = name;
        this.handler = placehoderHandler;
    }

    /**
     * @return the key
     */
    @Override
    public String getKey() {
        return key;
    }

    /**
     * @param key
     *            the key to set
     */
    public void setKey(String key) {
        this.key = key;
    }

    /**
     * @return the name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the handler
     */
    @Override
    public IPlacehoderHandler getHandler() {
        return handler;
    }

    /**
     * @param handler
     *            the handler to set
     */
    public void setHandler(IPlacehoderHandler handler) {
        this.handler = handler;
    }

}
