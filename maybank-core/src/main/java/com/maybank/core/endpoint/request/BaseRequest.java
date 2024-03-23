package com.maybank.core.endpoint.request;

import com.maybank.core.util.ObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * @author Tewaraj
 */
public abstract class BaseRequest implements Serializable {

    protected static final long serialVersionUID = 1L;

    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseRequest.class);

    public String toJsonString() {
        return ObjectUtil.toJson(this);
    }

}
