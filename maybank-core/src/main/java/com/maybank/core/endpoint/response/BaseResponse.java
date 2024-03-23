package com.maybank.core.endpoint.response;

import com.maybank.core.util.ObjectUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * @author Tewaraj
 */
public abstract class BaseResponse implements Serializable {

    protected static final long serialVersionUID = 1L;

    protected static final Logger LOGGER = LoggerFactory.getLogger(BaseResponse.class);

    @Override
    public String toString() {
        return this.toJson();
    }

    public String toJson() {
        return ObjectUtil.toJson(this);
    }

}
